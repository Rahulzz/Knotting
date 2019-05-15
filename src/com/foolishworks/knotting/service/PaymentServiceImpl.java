package com.foolishworks.knotting.service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.foolishworks.knotting.constants.KnottingConstants;
import com.foolishworks.knotting.entity.Coupon;
import com.foolishworks.knotting.entity.Transaction;
import com.foolishworks.knotting.intf.KnottingServicesService;
import com.foolishworks.knotting.intf.PaymentDao;
import com.foolishworks.knotting.intf.PaymentService;

@Service
@Transactional
@PropertySource("classpath:config.properties")
public class PaymentServiceImpl extends KnottingConstants implements PaymentService {

	private final Logger slf4jLogger = LoggerFactory.getLogger(PaymentServiceImpl.class);

	@Autowired
	private Environment environment;

	@Autowired
	private PaymentDao paymentDao;
	
	@Autowired
	private KnottingServicesService knottingServicesService;

	private String hashCalculator(String type,String content){

		slf4jLogger.debug("Entering function {}", "hashCalculator");

		byte[] hashseq=content.getBytes();
		StringBuffer hexString = new StringBuffer();

		try{
			MessageDigest algorithm = MessageDigest.getInstance(type);
			algorithm.reset();
			algorithm.update(hashseq);
			byte messageDigest[] = algorithm.digest();

			for (int i=0;i<messageDigest.length;i++) {
				String hex=Integer.toHexString(0xFF & messageDigest[i]);
				if(hex.length()==1) hexString.append("0");
				hexString.append(hex);
			}

		}
		catch(NoSuchAlgorithmException nsae){

		}

		slf4jLogger.debug("Exiting function {}", "hashCalculator");

		return hexString.toString();

	}

	public String generateTransactionId(Long memberId, Long memberEntryId, Float amount, Float total, Float discount, Coupon coupon){

		slf4jLogger.debug("Entering function {}", "generateTransactionId");

		Transaction transaction = new Transaction();

		Float floatAmount = Float.parseFloat(amount.toString());
		Integer integerAmount = floatAmount.intValue();
		
		if(memberEntryId != null){
			transaction.getMemberEntriesStaging().setMemberEntryId(memberEntryId);
		}
		transaction.getMember().setMemberId(memberId);		
		transaction.setPaymentAmount(integerAmount.toString());
		transaction.setTotalAmount(total.toString());
		transaction.setCouponDiscountedAmount(discount.toString());
		transaction.setCoupon(coupon);
		transaction.setTransactionDate(new Timestamp((new Date()).getTime()));

		Long transactionId = paymentDao.addOrUpdateATransaction(transaction);

		slf4jLogger.debug("Exiting function {}", "generateTransactionId");

		return transactionId.toString();

	}

	public String generateExecutiveTransactionId(Long memberId, Long memberEntryId, Float amount, Float total, Float discount, String couponCode, Long executiveId, String executivePaymentStatus){

		slf4jLogger.debug("Entering function {}", "generateExecutiveTransactionId");

		Transaction transaction = new Transaction();
		
		Coupon coupon = knottingServicesService.fetchActiveCoupon(couponCode);

		Float floatAmount = Float.parseFloat(amount.toString());
		Integer integerAmount = floatAmount.intValue();
		
		if(memberEntryId != null){
			transaction.getMemberEntriesStaging().setMemberEntryId(memberEntryId);
		}
		transaction.getMember().setMemberId(memberId);		
		transaction.setPaymentAmount(integerAmount.toString());
		transaction.setTotalAmount(total.toString());
		transaction.setCouponDiscountedAmount(discount.toString());
		transaction.setCoupon(coupon);
		transaction.setTransactionDate(new Timestamp((new Date()).getTime()));
		transaction.getExecutive().setMemberId(executiveId);
		transaction.setExecutivePaymentStatus(executivePaymentStatus);

		Long transactionId = paymentDao.addOrUpdateATransaction(transaction);

		slf4jLogger.debug("Exiting function {}", "generateExecutiveTransactionId");

		return transactionId.toString();

	}

	public String generateNoPaymentTransactionId(Long memberId, Long memberEntryId, Float amount, Float total, Float discount, String couponCode){

		slf4jLogger.debug("Entering function {}", "generateNoPaymentTransactionId");

		Transaction transaction = new Transaction();
		
		Coupon coupon = knottingServicesService.fetchActiveCoupon(couponCode);

		Float floatAmount = Float.parseFloat(amount.toString());
		Integer integerAmount = floatAmount.intValue();
		
		if(memberEntryId != null){
			transaction.getMemberEntriesStaging().setMemberEntryId(memberEntryId);
		}
		transaction.getMember().setMemberId(memberId);		
		transaction.setPaymentAmount(integerAmount.toString());
		transaction.setTotalAmount(total.toString());
		transaction.setCouponDiscountedAmount(discount.toString());
		transaction.setCoupon(coupon);
		transaction.setTransactionDate(new Timestamp((new Date()).getTime()));

		Long transactionId = paymentDao.addOrUpdateATransaction(transaction);

		slf4jLogger.debug("Exiting function {}", "generateNoPaymentTransactionId");

		return transactionId.toString();

	}

	public Transaction updateTransaction(String transactionId, String pgTransactionId, String paymentMode, String paymentStatus, String discount, String errorCode, String pgType, String bankReferenceNumber, String pgStatus){

		slf4jLogger.debug("Entering function {}", "updateTransaction");

		Transaction transaction = paymentDao.fetchTransaction(Long.valueOf(transactionId));

		transaction.setPgTransactionId(pgTransactionId);
		transaction.setPaymentMode(paymentMode);
		transaction.setPaymentStatus(paymentStatus);
		transaction.setDiscount(discount);
		transaction.setErrorCode(errorCode);
		transaction.setPgType(pgType);
		transaction.setBankReferenceNumber(bankReferenceNumber);
		transaction.setPgStatus(pgStatus);

		paymentDao.addOrUpdateATransaction(transaction);

		slf4jLogger.debug("Exiting function {}", "updateTransaction");

		return transaction;

	}

	public String generateHashString(String transactionId, String amount, String firstName, String email){

		slf4jLogger.debug("Entering function {}", "generateHashString");

		String paymentKey = environment.getProperty("payment.key");
		String paymentSalt = environment.getProperty("payment.salt");
		String productInfo = environment.getProperty("payment.product.info");

		StringBuffer hashString = new StringBuffer();

		hashString.append(paymentKey).append(paymentHashSeperator).append(transactionId).append(paymentHashSeperator);
		hashString.append(amount).append(paymentHashSeperator).append(productInfo).append(paymentHashSeperator);
		hashString.append(firstName).append(paymentHashSeperator).append(email).append(paymentHashSeperator);
		hashString.append("").append(paymentHashSeperator).append("").append(paymentHashSeperator);
		hashString.append("").append(paymentHashSeperator).append("").append(paymentHashSeperator);
		hashString.append("").append(paymentHashSeperator).append("").append(paymentHashSeperator);
		hashString.append("").append(paymentHashSeperator).append("").append(paymentHashSeperator);
		hashString.append("").append(paymentHashSeperator).append("").append(paymentHashSeperator).append(paymentSalt);	

		String hash = hashCalculator(paymentHashEncodingType,hashString.toString());

		slf4jLogger.debug("Exiting function {}", "generateHashString");

		return hash;

	}

	public boolean decodeHashString(String hash, String status, String email, String firstName, String amount, String transactionId){

		slf4jLogger.debug("Entering function {}", "decodeHashString");

		String paymentKey = environment.getProperty("payment.key");
		String paymentSalt = environment.getProperty("payment.salt");
		String productInfo = environment.getProperty("payment.product.info");

		StringBuffer hashString = new StringBuffer();

		hashString.append(paymentSalt).append(paymentHashSeperator).append(status).append(paymentHashSeperator);
		hashString.append("").append(paymentHashSeperator).append("").append(paymentHashSeperator);
		hashString.append("").append(paymentHashSeperator).append("").append(paymentHashSeperator);
		hashString.append("").append(paymentHashSeperator).append("").append(paymentHashSeperator);
		hashString.append("").append(paymentHashSeperator).append("").append(paymentHashSeperator);
		hashString.append("").append(paymentHashSeperator).append("").append(paymentHashSeperator);
		hashString.append(email).append(paymentHashSeperator).append(firstName).append(paymentHashSeperator);
		hashString.append(productInfo).append(paymentHashSeperator).append(amount).append(paymentHashSeperator);
		hashString.append(transactionId).append(paymentHashSeperator).append(paymentKey);

		String generatedHash = hashCalculator(paymentHashEncodingType,hashString.toString());

		slf4jLogger.debug("Exiting function {}", "decodeHashString");

		return (generatedHash.equals(hash)?true:false);

	}
	
	public Transaction fetchTransaction(String transactionId){

		slf4jLogger.debug("Entering function {}", "fetchTransaction");

		Transaction transaction = paymentDao.fetchTransaction(Long.valueOf(transactionId));

		slf4jLogger.debug("Exiting function {}", "fetchTransaction");
		
		return transaction;
		
	}



}
