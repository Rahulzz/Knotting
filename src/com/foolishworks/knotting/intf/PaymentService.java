package com.foolishworks.knotting.intf;

import com.foolishworks.knotting.entity.Coupon;
import com.foolishworks.knotting.entity.Transaction;

public interface PaymentService {
	
	public String generateHashString(String transactionId, String amount, String firstName, String email);
	public boolean decodeHashString(String hash, String status, String email, String firstName, String amount, String transactionId);
	public Transaction updateTransaction(String transactionId, String pgTransactionId, String paymentMode, String paymentStatus, String discount, String errorCode, String pgType, String bankReferenceNumber, String pgStatus);
	public String generateTransactionId(Long memberId, Long memberEntryId, Float amount, Float total, Float discount, Coupon coupon);
	public String generateExecutiveTransactionId(Long memberId, Long memberEntryId, Float amount, Float total, Float discount, String couponCode, Long executiveId, String executivePaymentStatus);
	public String generateNoPaymentTransactionId(Long memberId, Long memberEntryId, Float amount, Float total, Float discount, String couponCode);
	public Transaction fetchTransaction(String transactionId);

}
