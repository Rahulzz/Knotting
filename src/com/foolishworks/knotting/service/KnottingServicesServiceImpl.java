package com.foolishworks.knotting.service;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;
import java.util.UUID;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.joda.time.DateTime;
import org.joda.time.Days;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.foolishworks.knotting.constants.KnottingConstants;
import com.foolishworks.knotting.entity.Coupon;
import com.foolishworks.knotting.entity.Member;
import com.foolishworks.knotting.entity.MemberEntries;
import com.foolishworks.knotting.entity.MemberEntriesBuffer;
import com.foolishworks.knotting.entity.MemberEntriesStaging;
import com.foolishworks.knotting.entity.MemberServices;
import com.foolishworks.knotting.entity.MemberServicesBuffer;
import com.foolishworks.knotting.entity.MemberServicesStaging;
import com.foolishworks.knotting.entity.RegistrationBuffer;
import com.foolishworks.knotting.entity.SequenceEntriesStaging;
import com.foolishworks.knotting.entity.ServiceDetails;
import com.foolishworks.knotting.entity.Services;
import com.foolishworks.knotting.entity.Subscription;
import com.foolishworks.knotting.entity.SubscriptionPayment;
import com.foolishworks.knotting.entity.Transaction;
import com.foolishworks.knotting.intf.KnottingProfileDao;
import com.foolishworks.knotting.intf.KnottingServicesDao;
import com.foolishworks.knotting.intf.KnottingServicesService;
import com.foolishworks.knotting.intf.KnottingSupportDao;
import com.foolishworks.knotting.intf.PaymentDao;
import com.foolishworks.knotting.model.ServicesModelStaging;
import com.foolishworks.knotting.utils.CloudinaryImageUtil;
import com.foolishworks.knotting.utils.GeneralUtils;
import com.foolishworks.knotting.utils.MailerUtil;

@Service
@Transactional
public class KnottingServicesServiceImpl extends KnottingConstants implements KnottingServicesService {

	@Autowired
	private KnottingServicesDao knottingServicesDao;

	@Autowired
	private PaymentDao paymentDao;

	@Autowired
	private KnottingSupportDao knottingSupportDao;

	@Autowired
	private KnottingProfileDao knottingProfileDao;

	@Autowired
	private MailerUtil mailerUtil;

	@Autowired
	GeneralUtils generalUtils;

	@Autowired
	private CloudinaryImageUtil cloudinaryImageUtil;

	private final Logger slf4jLogger = LoggerFactory.getLogger(KnottingServicesServiceImpl.class);

	public List<Services> fetchAvailableListOfServices(){

		slf4jLogger.debug("Entering function {}", "fetchAvailableListOfServices");

		List<Services> servicesList = knottingServicesDao.fetchAvailableListOfServices();

		slf4jLogger.debug("Exiting function {}", "fetchAvailableListOfServices");

		return servicesList;

	}

	public List<Subscription> fetchAvailableListOfSubscriptions(){

		slf4jLogger.debug("Entering function {}", "fetchAvailableListOfSubscriptions");

		List<Subscription> subscriptionList = knottingServicesDao.fetchAvailableListOfSubscriptions();

		slf4jLogger.debug("Exiting function {}", "fetchAvailableListOfSubscriptions");

		return subscriptionList;

	}

	public List<ServiceDetails> fetchListOfServiceQuestionsStaging(Set<MemberServicesStaging> memberServicesStagingSet){

		slf4jLogger.debug("Entering function {}", "fetchListOfServiceQuestions");

		List<Long> serviceIdList = new ArrayList<Long>();

		for (MemberServicesStaging memberServicesStaging : memberServicesStagingSet) {
			serviceIdList.add(memberServicesStaging.getServices().getServicesId());
		}

		List<ServiceDetails> serviceDetailList = knottingServicesDao.fetchListOfServiceQuestions(serviceIdList);

		slf4jLogger.debug("Exiting function {}", "fetchListOfServiceQuestions");

		return serviceDetailList;

	}

	public List<ServiceDetails> fetchListOfServiceQuestions(Set<MemberServices> memberServicesSet){

		slf4jLogger.debug("Entering function {}", "fetchListOfServiceQuestions");

		List<Long> serviceIdList = new ArrayList<Long>();

		for (MemberServices memberServices : memberServicesSet) {
			serviceIdList.add(memberServices.getServices().getServicesId());
		}

		List<ServiceDetails> serviceDetailList = knottingServicesDao.fetchListOfServiceQuestions(serviceIdList);

		slf4jLogger.debug("Exiting function {}", "fetchListOfServiceQuestions");

		return serviceDetailList;

	}

	public List<ServiceDetails> fetchAvailableListOfServiceQuestions(){

		slf4jLogger.debug("Entering function {}", "fetchAvailableListOfServiceQuestions");

		List<ServiceDetails> serviceDetailList = knottingServicesDao.fetchAvailableListOfServiceQuestions();

		slf4jLogger.debug("Exiting function {}", "fetchAvailableListOfServiceQuestions");

		return serviceDetailList;

	}

	public MemberEntries fetchMemberEntriesRecord(String entryId){

		slf4jLogger.debug("Entering function {}", "fetchMemberEntriesRecord");

		MemberEntries memberEntries = knottingServicesDao.fetchMemberEntriesRecord(Long.parseLong(entryId));

		slf4jLogger.debug("Exiting function {}", "fetchMemberEntriesRecord");

		return memberEntries;

	}

	public MemberEntriesStaging fetchMemberEntriesStagingRecord(String entryStagingId){

		slf4jLogger.debug("Entering function {}", "fetchMemberEntriesStagingRecord");

		MemberEntriesStaging memberEntriesStaging = knottingServicesDao.fetchMemberEntriesStagingRecord(Long.parseLong(entryStagingId));

		slf4jLogger.debug("Exiting function {}", "fetchMemberEntriesStagingRecord");

		return memberEntriesStaging;

	}

	public boolean addOrUpdateMemberEntry(ServicesModelStaging servicesModel, String transactionId) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, IOException{

		slf4jLogger.debug("Entering function {}", "addOrUpdateMemberEntry");

		if(servicesModel.getMemberEntriesStaging().getMemberEntryId() != null){
			MemberEntriesStaging memberEntriesStaging = knottingServicesDao.fetchMemberEntriesStagingByEntryId(servicesModel.getMemberEntriesStaging().getMemberEntryId());
			if(memberEntriesStaging != null){
				removeServiceImages(memberEntriesStaging);
				knottingSupportDao.deleteStagingEntry(memberEntriesStaging);
			}
		}

		// Manually assign id to the entry staging record
		Long entryId = fetchEntriesStagingId();

		servicesModel.getMemberEntriesStaging().setMemberEntryStagingId(entryId);

		//Assign sequence id for new entries
		if(servicesModel.getMemberEntriesStaging().getMemberEntryId() == null){			
			servicesModel.getMemberEntriesStaging().setMemberEntryId(entryId);
			//TODO send new services mail
		}

		knottingServicesDao.addOrUpdateMemberEntry(servicesModel.getMemberEntriesStaging());

		DateTime todaysDate = new DateTime();

		if(entryId != null){
			//			if(servicesModel.getMemberEntriesStaging().getMemberEntryId() != null){
			//				entryId = servicesModel.getMemberEntriesStaging().getMemberEntryId();
			//			}

			if(transactionId != null){

				/* Update transaction record with entry id */
				Transaction transaction = paymentDao.fetchTransaction(Long.valueOf(transactionId));
				transaction.getMemberEntriesStaging().setMemberEntryId(entryId);
				paymentDao.addOrUpdateATransaction(transaction);

				createSubscriptionPaymentRecords(servicesModel, entryId, new Long(transactionId));

			}

			/* Update member entry with subscription details */
			DateTime leastExpiryDate = new DateTime().plusYears(1);
			int totalSubscriptionamount = 0;

			List<SubscriptionPayment> subscriptionPayments = knottingServicesDao.fetchSubscriptionPayment(entryId);
			for(SubscriptionPayment subscriptionPayment : subscriptionPayments){
				totalSubscriptionamount += knottingServicesDao.fetchSubscription(subscriptionPayment.getServices().getServicesId()).getSubscriptionRate();
				if(todaysDate.isAfter(subscriptionPayment.getTermStartDate().getTime()) && todaysDate.isBefore(subscriptionPayment.getTermEndDate().getTime())){
					DateTime tempDate = new DateTime(subscriptionPayment.getTermEndDate());
					if(leastExpiryDate != null){
						if(tempDate.isBefore(leastExpiryDate.getMillis())){
							leastExpiryDate = tempDate;
						}
					}
					else{
						leastExpiryDate = tempDate;
					}
				}
			}

			servicesModel.getMemberEntriesStaging().setSubscriptionAmount(totalSubscriptionamount);
			servicesModel.getMemberEntriesStaging().setSubscriptionTerm(new Long(1));
			servicesModel.getMemberEntriesStaging().setSubscriptionExpiry(new Timestamp(leastExpiryDate.getMillis()));

			//update staging member entry id as member entry id in case the member entry id is null
			//			if(servicesModel.getMemberEntriesStaging().getMemberEntryId() == null){
			//				servicesModel.getMemberEntriesStaging().setMemberEntryId(entryId);
			//			}

			for(MemberServicesStaging memberServicesStaging : servicesModel.getMemberServicesStagingList()){
				if(memberServicesStaging.getServiceStatus() == null){
					memberServicesStaging.setServiceStatus(serviceActive);
				}
				memberServicesStaging.setMemberEntriesStaging(servicesModel.getMemberEntriesStaging());
				servicesModel.getMemberEntriesStaging().getServicesRecords().add(memberServicesStaging);
			}

			Member member = knottingProfileDao.fetchMemberRecord(servicesModel.getMemberEntriesStaging().getMember().getMemberId());

			if(member != null && member.getEmailId().equals(servicesModel.getMemberEntriesStaging().getEmailAddress())){
				servicesModel.getMemberEntriesStaging().setEmailVerified(verificationDone);
			}

			knottingServicesDao.addOrUpdateMemberEntry(servicesModel.getMemberEntriesStaging());

			/* send verification email */
			if(servicesModel.getMemberEntriesStaging().getEmailAddress() != null && !BLANK.equals(servicesModel.getMemberEntriesStaging().getEmailAddress().trim()) && servicesModel.getMemberEntriesStaging().getEmailVerified().equals(verificationNotDone)){
				try{
					mailerUtil.verifyEmail(verifyEmailForServices, servicesModel.getMemberEntriesStaging().getEmailAddress(), entryId);
				}
				catch(Exception exception){
					exception.printStackTrace();
					slf4jLogger.debug("Exception while sending mail in function {}", "addOrUpdateMemberEntry");
				}
			}

			return true;
		}

		slf4jLogger.debug("Exiting function {}", "addOrUpdateMemberEntry");

		return false;

	}

	public Map<String, Object> fetchSubscriptionFeesAndDetails(String serviceIdArray[]){

		slf4jLogger.debug("Entering function {}", "fetchSubscriptionFeesAndDetails");

		Map<String, Object> subscriptionDetails = new HashMap<String, Object>();
		List<Long> serviceIdList = new ArrayList<Long>();
		int serviceAmount = 0;

		for(String serviceId : serviceIdArray){
			serviceIdList.add(new Long(serviceId));
		}

		List<Subscription> subscriptionList = knottingServicesDao.fetchSelectiveSubscriptions(serviceIdList);		
		subscriptionDetails.put("servicePricing", subscriptionList);
		subscriptionDetails.put("serviceCount", subscriptionList.size());

		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.YEAR, 1); 
		Date nextYear = cal.getTime();
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd, MMM, yy");
		dateFormat.setTimeZone(TimeZone.getTimeZone("IST"));
		subscriptionDetails.put("serviceExpiry", dateFormat.format(nextYear));

		for(Subscription subscription : subscriptionList){
			serviceAmount += subscription.getSubscriptionRate().intValue();
		}

		subscriptionDetails.put("serviceAmount", serviceAmount);		

		slf4jLogger.debug("Exiting function {}", "fetchSubscriptionFeesAndDetails");

		return subscriptionDetails;

	}

	public Integer calculateSubscriptionFees(List<MemberServicesStaging> memberServicesStagings){

		slf4jLogger.debug("Entering function {}", "calculateSubscriptionFees");

		List<Long> serviceIdList = new ArrayList<Long>();
		int serviceAmount = 0;

		for(MemberServicesStaging memberServicesStaging : memberServicesStagings){
			serviceIdList.add(memberServicesStaging.getServices().getServicesId());
		}

		List<Subscription> subscriptionList = knottingServicesDao.fetchSelectiveSubscriptions(serviceIdList);		

		for(Subscription subscription : subscriptionList){
			serviceAmount += subscription.getSubscriptionRate().intValue();
		}	

		slf4jLogger.debug("Exiting function {}", "calculateSubscriptionFees");

		return serviceAmount;

	}

	public boolean verifyServiceEmail(String id, String email){

		slf4jLogger.debug("Entering function {}", "verifyServiceEmail");

		boolean emailVerificationStatus = true;

		String unencryptedId = generalUtils.decryptStringUsingPassword(id);
		String unencryptedEmail = generalUtils.decryptStringUsingPassword(email);

		MemberEntriesStaging memberEntriesStaging = knottingServicesDao.fetchMemberEntriesStagingRecord(Long.parseLong(unencryptedId));

		emailVerificationStatus = (memberEntriesStaging.getEmailAddress().equals(unencryptedEmail))?true:false;

		if(emailVerificationStatus){
			Timestamp currentTimestamp = new Timestamp((new Date()).getTime());
			memberEntriesStaging.setLastUpdatedDate(currentTimestamp);
			memberEntriesStaging.setEmailVerified(verificationDone);

			knottingServicesDao.addOrUpdateMemberEntry(memberEntriesStaging);
		}

		slf4jLogger.debug("Exiting function {}", "verifyServiceEmail");

		return emailVerificationStatus;

	}

	public JSONObject prepareSubscriptionPaymentData(Long memberEntryId, List<String> servicesIdList, String couponCode){

		slf4jLogger.debug("Entering function {}", "prepareSubscriptionPaymentData");

		JSONObject paymentdata = new JSONObject();

		DateTime todaysDate = new DateTime();
		DateTime leastExpiryDate = null;
		Date expiryDate = null;
		Integer discountAmount = null;	
		int unPaidServices = 0;
		int totalPayableAmount = 0;	
		int finalAmount = 0;	
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd, MMM, yyyy");

		try{

			JSONArray serviceSubscriptionArray = new JSONArray();

			if(memberEntryId != null){

				List<SubscriptionPayment> subscriptionPaymentList = knottingServicesDao.fetchSubscriptionPayment(memberEntryId);

				for(SubscriptionPayment subscriptionPayment : subscriptionPaymentList){
					if(todaysDate.isAfter(subscriptionPayment.getTermStartDate().getTime()) && todaysDate.isBefore(subscriptionPayment.getTermEndDate().getTime())){
						JSONObject serviceSubscription = new JSONObject();

						DateTime tempDate = new DateTime(subscriptionPayment.getTermEndDate());
						if(leastExpiryDate != null){
							if(tempDate.isBefore(leastExpiryDate.getMillis())){
								leastExpiryDate = tempDate;
							}
						}
						else{
							leastExpiryDate = tempDate;
						}

						serviceSubscription.put(subscriptionServiceId, subscriptionPayment.getServices().getServicesId().toString());
						serviceSubscription.put(subscriptionServiceCode, subscriptionPayment.getServices().getServiceCode());
						serviceSubscription.put(subscriptionServiceName, subscriptionPayment.getServices().getName());
						serviceSubscription.put(subscriptionServiceCost, subscriptionPayment.getPaymentAmount());
						serviceSubscription.put(subscriptionServiceExpiry, dateFormat.format(subscriptionPayment.getTermEndDate()));
						serviceSubscription.put(subscriptionServiceStatus, subscriptionServiceStatusInActive);
						serviceSubscription.put(subscriptionServicePayment, subscriptionServicePaymentPaid);				
						serviceSubscriptionArray.put(serviceSubscription);

					}
				}
			}

			for(String servicesId : servicesIdList){
				Services services = knottingServicesDao.fetchServices(Long.valueOf(servicesId));

				boolean matchedService = false;
				for(int i=0; i<serviceSubscriptionArray.length(); i++){
					JSONObject serviceSubscription = serviceSubscriptionArray.getJSONObject(i);
					if(serviceSubscription.get(subscriptionServiceId).equals(services.getServicesId().toString())){
						matchedService = true;	
						serviceSubscription.put(subscriptionServiceStatus, subscriptionServiceStatusActive);
					}
				}

				if(!matchedService){
					JSONObject serviceSubscription = new JSONObject();
					Subscription subscription = knottingServicesDao.fetchSubscription(services.getServicesId());

					unPaidServices++;
					totalPayableAmount += subscription.getSubscriptionRate();

					serviceSubscription.put(subscriptionServiceId, services.getServicesId().toString());
					serviceSubscription.put(subscriptionServiceCode, services.getServiceCode());
					serviceSubscription.put(subscriptionServiceName, services.getName());
					serviceSubscription.put(subscriptionServiceCost, subscription.getSubscriptionRate().toString());
					serviceSubscription.put(subscriptionServiceExpiry, dateFormat.format(todaysDate.plusYears(1).toDate()));
					serviceSubscription.put(subscriptionServiceStatus, subscriptionServiceStatusActive);
					serviceSubscription.put(subscriptionServicePayment, subscriptionServicePaymentUnPaid);		
					serviceSubscriptionArray.put(serviceSubscription);
				}
			}

			finalAmount = totalPayableAmount;
			expiryDate = (leastExpiryDate != null && totalPayableAmount == 0)?leastExpiryDate.toDate():todaysDate.plusYears(1).toDate();

			if(couponCode != null && !"".equals(couponCode)){
				discountAmount = applyCoupon(totalPayableAmount, couponCode);
				if(discountAmount != null && discountAmount > 0 && discountAmount <= totalPayableAmount){
					finalAmount = totalPayableAmount - discountAmount;
					paymentdata.put(paymentDataDiscountAmount, (new Integer(discountAmount)).toString());
					paymentdata.put(paymentDataFinalAmount, (new Integer(finalAmount)).toString());
				}
			}
			else{
				paymentdata.put(paymentDataDiscountAmount, 0);
				paymentdata.put(paymentDataFinalAmount, totalPayableAmount);
			}

			paymentdata.put(paymentDataServiceCount, (new Integer(unPaidServices)).toString());
			paymentdata.put(paymentDataServiceAmount, (new Integer(totalPayableAmount)).toString());
			paymentdata.put(paymentDataServiceExpiry, dateFormat.format(expiryDate));
			paymentdata.put(paymentDataServiceList, serviceSubscriptionArray);

		}
		catch(Exception exception){
			exception.printStackTrace();
			slf4jLogger.error("Exception in prepareSubscriptionPaymentData function {}", exception.getMessage());
		}

		slf4jLogger.debug("Exiting function {}", "prepareSubscriptionPaymentData");

		return paymentdata;

	}

	public boolean saveEntry(Long userId, ServicesModelStaging servicesModel, String transactionId) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException{

		slf4jLogger.debug("Entering function {}", "saveEntry");

		Timestamp currentTimestamp = new Timestamp((new Date()).getTime());

		String rootPath = System.getProperty("catalina.home");

		Method method;

		/* Set the userid */
		if(servicesModel.getMemberEntriesStaging().getMember().getMemberId() == null){
			servicesModel.getMemberEntriesStaging().getMember().setMemberId(userId);
		}

		/* Upload proof of establishment */
		File docPathFolder = new File(rootPath + File.separator + serverRootFolderName + File.separator + userId + File.separator + serverDocPathFolderName);
		if(docPathFolder.exists()){
			if(docPathFolder.listFiles().length > 0){
				if(servicesModel.getMemberEntriesStaging().getDocumentPath() != null && !servicesModel.getMemberEntriesStaging().getDocumentPath().equals("")){
					cloudinaryImageUtil.deleteImage(servicesModel.getMemberEntriesStaging().getDocumentPath());
				}	
				for(File file : docPathFolder.listFiles()){
					servicesModel.getMemberEntriesStaging().setDocumentPath(cloudinaryImageUtil.uploadServiceImage(file));
				}
			}
		}

		for(MemberServicesStaging memberServicesStaging : servicesModel.getMemberServicesStagingList()){
			
			/* Create the service code */
			if(memberServicesStaging.getMemberServiceCode() == null){
				String memberServiceCode = servicesModel.getMemberEntriesStaging().getName().toLowerCase().replace(" ", "-");
				memberServicesStaging.setMemberServiceCode(memberServiceCode);
			}

			boolean updatingFile = false;

			MemberEntriesStaging memberEntriesStaging = null;

			if(servicesModel.getMemberEntriesStaging().getMemberEntryId() != null){
				memberEntriesStaging = knottingServicesDao.fetchMemberEntriesStagingRecord(servicesModel.getMemberEntriesStaging().getMemberEntryId());
				if(memberEntriesStaging != null){
					for(MemberServicesStaging servicesStaging : memberEntriesStaging.getServicesRecords()){
						if(servicesStaging.getServices().getServicesId().equals(memberServicesStaging.getServices().getServicesId())){
							updatingFile = true;
						}
					}
				}
			}

			//Remove images from all staging services as old staging services records will be deleted before new records are created.
			if(memberEntriesStaging != null){
				removeServiceImages(memberEntriesStaging);
			}

			File serviceImageFolder = new File(rootPath + File.separator + serverRootFolderName + File.separator + userId + File.separator + serverServiceImageFolderName + File.separator + memberServicesStaging.getServices().getServicesId());
			if(serviceImageFolder.exists()){
				int counter = 0;

				if(serviceImageFolder.listFiles().length > 0){					
					for(File file : serviceImageFolder.listFiles()){
						counter++;
						if(counter <= serviceMaxImageCount){
							method = memberServicesStaging.getClass().getMethod("setImagePath"+counter, String.class);
							method.invoke(memberServicesStaging, cloudinaryImageUtil.uploadServiceImage(file));
						}
					}
				}
			}

			/* Set Audit Fields */
			memberServicesStaging.setLastUpdatedBy(userId);
			memberServicesStaging.setLastUpdatedDate(currentTimestamp);

			if(updatingFile){				
				memberServicesStaging.setCreatedBy(memberServicesStaging.getCreatedBy());
				memberServicesStaging.setCreatedDate(memberServicesStaging.getCreatedDate());				
			}
			else{
				memberServicesStaging.setCreatedBy(userId);
				memberServicesStaging.setCreatedDate(currentTimestamp);
			}

		}

		/* Set Audit Fields */
		if(servicesModel.getMemberEntriesStaging().getCreatedBy() == null){
			servicesModel.getMemberEntriesStaging().setCreatedBy(userId);
			servicesModel.getMemberEntriesStaging().setCreatedDate(currentTimestamp);
		}
		servicesModel.getMemberEntriesStaging().setLastUpdatedBy(userId);
		servicesModel.getMemberEntriesStaging().setLastUpdatedDate(currentTimestamp);

		addOrUpdateMemberEntry(servicesModel, transactionId);

		/* Remove temp image folder in server */
		File serviceTempFolder = new File(rootPath + File.separator + serverRootFolderName + File.separator + userId);
		generalUtils.deleteFolder(serviceTempFolder);

		slf4jLogger.debug("Exiting function {}", "saveEntry");

		return true;

	}

	public void createImageCopyInServer(Long memberId, ServicesModelStaging servicesModel) throws IOException {

		slf4jLogger.debug("Entering function {}", "createImageCopyInServer");

		String rootPath = System.getProperty("catalina.home");

		MultipartFile document = servicesModel.getMemberEntriesStaging().getMultipartFile();

		if(document != null){
			/* Remove temp image folder in server */
			File serviceTempFolder = new File(rootPath + File.separator + serverRootFolderName + File.separator + memberId);
			generalUtils.deleteFolder(serviceTempFolder);

			File docPathFolder = new File(rootPath + File.separator + serverRootFolderName + File.separator + memberId + File.separator + serverDocPathFolderName);
			if(!docPathFolder.exists()){
				docPathFolder.mkdirs();
			}

			try{
				File estalbishmentDocument = new File(docPathFolder.getAbsoluteFile() + File.separator + document.getOriginalFilename());
				BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(estalbishmentDocument));
				bufferedOutputStream.write(document.getBytes());
				bufferedOutputStream.close();
			}
			catch(FileNotFoundException fileNotFoundException){
				slf4jLogger.error("FileNotFoundException in function {}","createImageCopyInServer");
			}
			catch(Exception exception){
				slf4jLogger.error("Exception in function {}","createImageCopyInServer");
				exception.printStackTrace();
			}
		}

		for(MemberServicesStaging memberServicesStaging : servicesModel.getMemberServicesStagingList()){

			String[] dataImages = memberServicesStaging.getDataImages();

			if(dataImages != null){
				for(String dataUrl : dataImages){
					if(!"".equals(dataUrl)){
						File serviceImageFolder = new File(rootPath + File.separator + serverRootFolderName + File.separator + memberId + File.separator + serverServiceImageFolderName + File.separator + memberServicesStaging.getServices().getServicesId());
						if(!serviceImageFolder.exists()){
							serviceImageFolder.mkdirs();
						}
						try{
							if(!dataUrl.startsWith(KnottingConstants.imageUrlPrefix)){
								String imageType = dataUrl.substring((dataUrl.indexOf(KnottingConstants.imageTypePrefix) + KnottingConstants.imageTypePrefix.length()), dataUrl.indexOf(KnottingConstants.encodingPrefix));				
								int contentStartIndex = dataUrl.indexOf(KnottingConstants.encodingPrefix) + KnottingConstants.encodingPrefix.length();
								byte[] imageData = Base64.decodeBase64(dataUrl.substring(contentStartIndex));
								File file = new File(serviceImageFolder.getAbsoluteFile() + File.separator + UUID.randomUUID()+"."+imageType);
								BufferedOutputStream osf = new BufferedOutputStream(new FileOutputStream(file));
								osf.write(imageData);
								osf.close();
							}
							else{
								String[] imageUrlParts = dataUrl.split("\\.");
								String imageType = imageUrlParts[imageUrlParts.length - 1];
								File file = new File(serviceImageFolder.getAbsoluteFile() + File.separator + UUID.randomUUID()+"."+imageType);
								URL url = new URL(dataUrl);
								FileUtils.copyURLToFile(url, file);
							}
						}
						catch(FileNotFoundException fileNotFoundException){
							slf4jLogger.error("FileNotFoundException in function {}","createImageCopyInServer");
						}
						catch(Exception exception){
							slf4jLogger.error("Exception in function {}","createImageCopyInServer");
							exception.printStackTrace();
						}
					}
				}
			}

		}

		slf4jLogger.debug("Exiting function {}", "createImageCopyInServer");

	}

	private void removeServiceImages(MemberEntriesStaging memberEntriesStaging) throws IOException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException{

		slf4jLogger.debug("Exiting function {}", "removeServiceImages");

		for(MemberServicesStaging servicesStaging : memberEntriesStaging.getServicesRecords()){
			for(int counter=1; counter <=serviceMaxImageCount; counter++){
				Method method = servicesStaging.getClass().getMethod("getImagePath"+counter);
				String imageUrl = (String)method.invoke(servicesStaging);
				if(imageUrl != null){
					cloudinaryImageUtil.deleteImage(imageUrl);
					method = servicesStaging.getClass().getMethod("setImagePath"+counter, String.class);
					method.invoke(servicesStaging, KnottingConstants.NULLSTRING);
				}
			}
		}

		slf4jLogger.debug("Exiting function {}", "removeServiceImages");
	}

	public void calculateDaysSinceCreation(MemberEntriesStaging memberEntriesStaging){

		slf4jLogger.debug("Entering function {}", "calculateDaysSinceCreation");

		DateTime todayDate = new DateTime();

		Long daysSinceCreation = new Long(Days.daysBetween(new DateTime(memberEntriesStaging.getCreatedDate()), todayDate).getDays());

		memberEntriesStaging.setDaysSinceCreation(daysSinceCreation);

		slf4jLogger.debug("Exiting function {}", "calculateDaysSinceCreation");

	}

	public void calculateDaysSinceCreation(MemberEntries memberEntries){

		slf4jLogger.debug("Entering function {}", "calculateDaysSinceCreation");

		DateTime todayDate = new DateTime();

		Long daysSinceCreation = new Long(Days.daysBetween(new DateTime(memberEntries.getCreatedDate()), todayDate).getDays());

		memberEntries.setDaysSinceCreation(daysSinceCreation);

		slf4jLogger.debug("Exiting function {}", "calculateDaysSinceCreation");

	}

	public void prepareThumbnailImage(MemberEntries memberEntries){

		slf4jLogger.debug("Entering function {}", "prepareThumbnailImage");

		for(MemberServices memberServices : memberEntries.getServicesRecords()){
			/* change image url to transformed image url */
			if(memberServices.getImagePath1() != null && memberServices.getImagePath1().length() > 0){
				memberServices.setImagePath1Thumbnail(cloudinaryImageUtil.thumbnailUrl(memberServices.getImagePath1()));
			}
			if(memberServices.getImagePath2() != null && memberServices.getImagePath2().length() > 0){
				memberServices.setImagePath2Thumbnail(cloudinaryImageUtil.thumbnailUrl(memberServices.getImagePath2()));
			}
			if(memberServices.getImagePath3() != null && memberServices.getImagePath3().length() > 0){
				memberServices.setImagePath3Thumbnail(cloudinaryImageUtil.thumbnailUrl(memberServices.getImagePath3()));
			}
			if(memberServices.getImagePath4() != null && memberServices.getImagePath4().length() > 0){
				memberServices.setImagePath4Thumbnail(cloudinaryImageUtil.thumbnailUrl(memberServices.getImagePath4()));
			}
			if(memberServices.getImagePath5() != null && memberServices.getImagePath5().length() > 0){
				memberServices.setImagePath5Thumbnail(cloudinaryImageUtil.thumbnailUrl(memberServices.getImagePath5()));
			}
			if(memberServices.getImagePath6() != null && memberServices.getImagePath6().length() > 0){
				memberServices.setImagePath6Thumbnail(cloudinaryImageUtil.thumbnailUrl(memberServices.getImagePath6()));
			}
			if(memberServices.getImagePath7() != null && memberServices.getImagePath7().length() > 0){
				memberServices.setImagePath7Thumbnail(cloudinaryImageUtil.thumbnailUrl(memberServices.getImagePath7()));
			}
			if(memberServices.getImagePath8() != null && memberServices.getImagePath8().length() > 0){
				memberServices.setImagePath8Thumbnail(cloudinaryImageUtil.thumbnailUrl(memberServices.getImagePath8()));
			}
			if(memberServices.getImagePath9() != null && memberServices.getImagePath9().length() > 0){
				memberServices.setImagePath9Thumbnail(cloudinaryImageUtil.thumbnailUrl(memberServices.getImagePath9()));
			}
			if(memberServices.getImagePath10() != null && memberServices.getImagePath10().length() > 0){
				memberServices.setImagePath10Thumbnail(cloudinaryImageUtil.thumbnailUrl(memberServices.getImagePath10()));
			}
			if(memberServices.getImagePath11() != null && memberServices.getImagePath11().length() > 0){
				memberServices.setImagePath11Thumbnail(cloudinaryImageUtil.thumbnailUrl(memberServices.getImagePath11()));
			}
			if(memberServices.getImagePath12() != null && memberServices.getImagePath12().length() > 0){
				memberServices.setImagePath12Thumbnail(cloudinaryImageUtil.thumbnailUrl(memberServices.getImagePath12()));
			}
			if(memberServices.getImagePath13() != null && memberServices.getImagePath13().length() > 0){
				memberServices.setImagePath13Thumbnail(cloudinaryImageUtil.thumbnailUrl(memberServices.getImagePath13()));
			}
			if(memberServices.getImagePath14() != null && memberServices.getImagePath14().length() > 0){
				memberServices.setImagePath14Thumbnail(cloudinaryImageUtil.thumbnailUrl(memberServices.getImagePath14()));
			}
			if(memberServices.getImagePath15() != null && memberServices.getImagePath15().length() > 0){
				memberServices.setImagePath15Thumbnail(cloudinaryImageUtil.thumbnailUrl(memberServices.getImagePath15()));
			}
			if(memberServices.getImagePath16() != null && memberServices.getImagePath16().length() > 0){
				memberServices.setImagePath16Thumbnail(cloudinaryImageUtil.thumbnailUrl(memberServices.getImagePath16()));
			}
			if(memberServices.getImagePath17() != null && memberServices.getImagePath17().length() > 0){
				memberServices.setImagePath17Thumbnail(cloudinaryImageUtil.thumbnailUrl(memberServices.getImagePath17()));
			}
			if(memberServices.getImagePath18() != null && memberServices.getImagePath18().length() > 0){
				memberServices.setImagePath18Thumbnail(cloudinaryImageUtil.thumbnailUrl(memberServices.getImagePath18()));
			}
			if(memberServices.getImagePath19() != null && memberServices.getImagePath19().length() > 0){
				memberServices.setImagePath19Thumbnail(cloudinaryImageUtil.thumbnailUrl(memberServices.getImagePath19()));
			}
			if(memberServices.getImagePath20() != null && memberServices.getImagePath20().length() > 0){
				memberServices.setImagePath20Thumbnail(cloudinaryImageUtil.thumbnailUrl(memberServices.getImagePath20()));
			}
		}

		slf4jLogger.debug("Exiting function {}", "prepareThumbnailImage");
	}

	public void prepareThumbnailImage(MemberEntriesStaging memberEntriesStaging){

		slf4jLogger.debug("Entering function {}", "prepareThumbnailImage");

		for(MemberServicesStaging memberServices : memberEntriesStaging.getServicesRecords()){
			/* change image url to transformed image url */
			if(memberServices.getImagePath1() != null && memberServices.getImagePath1().length() > 0){
				memberServices.setImagePath1Thumbnail(cloudinaryImageUtil.thumbnailUrl(memberServices.getImagePath1()));
			}
			if(memberServices.getImagePath2() != null && memberServices.getImagePath2().length() > 0){
				memberServices.setImagePath2Thumbnail(cloudinaryImageUtil.thumbnailUrl(memberServices.getImagePath2()));
			}
			if(memberServices.getImagePath3() != null && memberServices.getImagePath3().length() > 0){
				memberServices.setImagePath3Thumbnail(cloudinaryImageUtil.thumbnailUrl(memberServices.getImagePath3()));
			}
			if(memberServices.getImagePath4() != null && memberServices.getImagePath4().length() > 0){
				memberServices.setImagePath4Thumbnail(cloudinaryImageUtil.thumbnailUrl(memberServices.getImagePath4()));
			}
			if(memberServices.getImagePath5() != null && memberServices.getImagePath5().length() > 0){
				memberServices.setImagePath5Thumbnail(cloudinaryImageUtil.thumbnailUrl(memberServices.getImagePath5()));
			}
			if(memberServices.getImagePath6() != null && memberServices.getImagePath6().length() > 0){
				memberServices.setImagePath6Thumbnail(cloudinaryImageUtil.thumbnailUrl(memberServices.getImagePath6()));
			}
			if(memberServices.getImagePath7() != null && memberServices.getImagePath7().length() > 0){
				memberServices.setImagePath7Thumbnail(cloudinaryImageUtil.thumbnailUrl(memberServices.getImagePath7()));
			}
			if(memberServices.getImagePath8() != null && memberServices.getImagePath8().length() > 0){
				memberServices.setImagePath8Thumbnail(cloudinaryImageUtil.thumbnailUrl(memberServices.getImagePath8()));
			}
			if(memberServices.getImagePath9() != null && memberServices.getImagePath9().length() > 0){
				memberServices.setImagePath9Thumbnail(cloudinaryImageUtil.thumbnailUrl(memberServices.getImagePath9()));
			}
			if(memberServices.getImagePath10() != null && memberServices.getImagePath10().length() > 0){
				memberServices.setImagePath10Thumbnail(cloudinaryImageUtil.thumbnailUrl(memberServices.getImagePath10()));
			}
			if(memberServices.getImagePath11() != null && memberServices.getImagePath11().length() > 0){
				memberServices.setImagePath11Thumbnail(cloudinaryImageUtil.thumbnailUrl(memberServices.getImagePath11()));
			}
			if(memberServices.getImagePath12() != null && memberServices.getImagePath12().length() > 0){
				memberServices.setImagePath12Thumbnail(cloudinaryImageUtil.thumbnailUrl(memberServices.getImagePath12()));
			}
			if(memberServices.getImagePath13() != null && memberServices.getImagePath13().length() > 0){
				memberServices.setImagePath13Thumbnail(cloudinaryImageUtil.thumbnailUrl(memberServices.getImagePath13()));
			}
			if(memberServices.getImagePath14() != null && memberServices.getImagePath14().length() > 0){
				memberServices.setImagePath14Thumbnail(cloudinaryImageUtil.thumbnailUrl(memberServices.getImagePath14()));
			}
			if(memberServices.getImagePath15() != null && memberServices.getImagePath15().length() > 0){
				memberServices.setImagePath15Thumbnail(cloudinaryImageUtil.thumbnailUrl(memberServices.getImagePath15()));
			}
			if(memberServices.getImagePath16() != null && memberServices.getImagePath16().length() > 0){
				memberServices.setImagePath16Thumbnail(cloudinaryImageUtil.thumbnailUrl(memberServices.getImagePath16()));
			}
			if(memberServices.getImagePath17() != null && memberServices.getImagePath17().length() > 0){
				memberServices.setImagePath17Thumbnail(cloudinaryImageUtil.thumbnailUrl(memberServices.getImagePath17()));
			}
			if(memberServices.getImagePath18() != null && memberServices.getImagePath18().length() > 0){
				memberServices.setImagePath18Thumbnail(cloudinaryImageUtil.thumbnailUrl(memberServices.getImagePath18()));
			}
			if(memberServices.getImagePath19() != null && memberServices.getImagePath19().length() > 0){
				memberServices.setImagePath19Thumbnail(cloudinaryImageUtil.thumbnailUrl(memberServices.getImagePath19()));
			}
			if(memberServices.getImagePath20() != null && memberServices.getImagePath20().length() > 0){
				memberServices.setImagePath20Thumbnail(cloudinaryImageUtil.thumbnailUrl(memberServices.getImagePath20()));
			}
		}

		slf4jLogger.debug("Exiting function {}", "prepareThumbnailImage");
	}

	public void prepareThumbnailImage(MemberServices memberServices){

		slf4jLogger.debug("Entering function {}", "prepareThumbnailImage");

		/* change image url to transformed image url */
		if(memberServices.getImagePath1() != null && memberServices.getImagePath1().length() > 0){
			memberServices.setImagePath1Thumbnail(cloudinaryImageUtil.thumbnailUrl(memberServices.getImagePath1()));
		}
		if(memberServices.getImagePath2() != null && memberServices.getImagePath2().length() > 0){
			memberServices.setImagePath2Thumbnail(cloudinaryImageUtil.thumbnailUrl(memberServices.getImagePath2()));
		}
		if(memberServices.getImagePath3() != null && memberServices.getImagePath3().length() > 0){
			memberServices.setImagePath3Thumbnail(cloudinaryImageUtil.thumbnailUrl(memberServices.getImagePath3()));
		}
		if(memberServices.getImagePath4() != null && memberServices.getImagePath4().length() > 0){
			memberServices.setImagePath4Thumbnail(cloudinaryImageUtil.thumbnailUrl(memberServices.getImagePath4()));
		}
		if(memberServices.getImagePath5() != null && memberServices.getImagePath5().length() > 0){
			memberServices.setImagePath5Thumbnail(cloudinaryImageUtil.thumbnailUrl(memberServices.getImagePath5()));
		}
		if(memberServices.getImagePath6() != null && memberServices.getImagePath6().length() > 0){
			memberServices.setImagePath6Thumbnail(cloudinaryImageUtil.thumbnailUrl(memberServices.getImagePath6()));
		}
		if(memberServices.getImagePath7() != null && memberServices.getImagePath7().length() > 0){
			memberServices.setImagePath7Thumbnail(cloudinaryImageUtil.thumbnailUrl(memberServices.getImagePath7()));
		}
		if(memberServices.getImagePath8() != null && memberServices.getImagePath8().length() > 0){
			memberServices.setImagePath8Thumbnail(cloudinaryImageUtil.thumbnailUrl(memberServices.getImagePath8()));
		}
		if(memberServices.getImagePath9() != null && memberServices.getImagePath9().length() > 0){
			memberServices.setImagePath9Thumbnail(cloudinaryImageUtil.thumbnailUrl(memberServices.getImagePath9()));
		}
		if(memberServices.getImagePath10() != null && memberServices.getImagePath10().length() > 0){
			memberServices.setImagePath10Thumbnail(cloudinaryImageUtil.thumbnailUrl(memberServices.getImagePath10()));
		}
		if(memberServices.getImagePath11() != null && memberServices.getImagePath11().length() > 0){
			memberServices.setImagePath11Thumbnail(cloudinaryImageUtil.thumbnailUrl(memberServices.getImagePath11()));
		}
		if(memberServices.getImagePath12() != null && memberServices.getImagePath12().length() > 0){
			memberServices.setImagePath12Thumbnail(cloudinaryImageUtil.thumbnailUrl(memberServices.getImagePath12()));
		}
		if(memberServices.getImagePath13() != null && memberServices.getImagePath13().length() > 0){
			memberServices.setImagePath13Thumbnail(cloudinaryImageUtil.thumbnailUrl(memberServices.getImagePath13()));
		}
		if(memberServices.getImagePath14() != null && memberServices.getImagePath14().length() > 0){
			memberServices.setImagePath14Thumbnail(cloudinaryImageUtil.thumbnailUrl(memberServices.getImagePath14()));
		}
		if(memberServices.getImagePath15() != null && memberServices.getImagePath15().length() > 0){
			memberServices.setImagePath15Thumbnail(cloudinaryImageUtil.thumbnailUrl(memberServices.getImagePath15()));
		}
		if(memberServices.getImagePath16() != null && memberServices.getImagePath16().length() > 0){
			memberServices.setImagePath16Thumbnail(cloudinaryImageUtil.thumbnailUrl(memberServices.getImagePath16()));
		}
		if(memberServices.getImagePath17() != null && memberServices.getImagePath17().length() > 0){
			memberServices.setImagePath17Thumbnail(cloudinaryImageUtil.thumbnailUrl(memberServices.getImagePath17()));
		}
		if(memberServices.getImagePath18() != null && memberServices.getImagePath18().length() > 0){
			memberServices.setImagePath18Thumbnail(cloudinaryImageUtil.thumbnailUrl(memberServices.getImagePath18()));
		}
		if(memberServices.getImagePath19() != null && memberServices.getImagePath19().length() > 0){
			memberServices.setImagePath19Thumbnail(cloudinaryImageUtil.thumbnailUrl(memberServices.getImagePath19()));
		}
		if(memberServices.getImagePath20() != null && memberServices.getImagePath20().length() > 0){
			memberServices.setImagePath20Thumbnail(cloudinaryImageUtil.thumbnailUrl(memberServices.getImagePath20()));
		}

		slf4jLogger.debug("Exiting function {}", "prepareThumbnailImage");
	}

	public JSONObject prepareCouponData(String totalAmount, String couponCode) throws JSONException {

		slf4jLogger.debug("Entering function {}", "prepareCouponData");

		JSONObject couponData = new JSONObject();

		Integer discountAmount = applyCoupon(Integer.parseInt(totalAmount), couponCode);

		couponData.put(couponDataTotalAmount, totalAmount);
		couponData.put(couponDataCouponCode, couponCode);

		if(discountAmount != null){
			couponData.put(couponDataDiscountamount, discountAmount.toString());
			couponData.put(couponDataFinalAmount, (Integer.parseInt(totalAmount) - discountAmount));
		}
		else{
			couponData.put(couponDataDiscountamount, "0");
			couponData.put(couponDataFinalAmount, totalAmount);
			couponData.put(couponDataError, "Invalid Coupon");
		}

		slf4jLogger.debug("Exiting function {}", "prepareCouponData");

		return couponData;

	}

	public Integer applyCoupon(int totalAmount, String couponCode){

		slf4jLogger.debug("Entering function {}", "applyCoupon");

		Integer discountAmount = null;

		DateTime todayDate = new DateTime();
		List<Coupon> coupons = knottingServicesDao.fetchCoupons(couponCode);

		if(coupons != null && coupons.size() > 0){
			for(Coupon coupon : coupons){
				DateTime couponStartDate = new DateTime(coupon.getCouponFromDate());
				DateTime couponExpiryDate = new DateTime(coupon.getCouponExpiryDate());

				if(todayDate.isAfter(couponStartDate) && todayDate.isBefore(couponExpiryDate)){
					if(coupon.getCouponDiscountPercent() != null){
						discountAmount = new Integer(totalAmount * Integer.parseInt(coupon.getCouponDiscountPercent()) / 100);
					}
					else if (coupon.getCouponDiscountAmount() != null) {
						discountAmount = Integer.parseInt(coupon.getCouponDiscountAmount());
					}

					break;
				}
			}
		}
		else{
			return null;
		}

		slf4jLogger.debug("Exiting function {}", "applyCoupon");

		return discountAmount;

	}

	public RegistrationBuffer fetchBufferRecord(Long bufferId){

		slf4jLogger.debug("Entering function {}", "fetchBufferRecord");

		RegistrationBuffer registrationBuffer = knottingServicesDao.fetchRegistrationBuffer(bufferId);

		slf4jLogger.debug("Exiting function {}", "fetchBufferRecord");

		return registrationBuffer;

	}

	public void deleteAllBufferforUser(Long userId){

		slf4jLogger.debug("Entering function {}", "deleteAllBufferforUser");

		knottingServicesDao.deleteAllBufferforUser(userId);

		slf4jLogger.debug("Exiting function {}", "deleteAllBufferforUser");

	}

	public Long createAndSaveBufferRecord(ServicesModelStaging modelStaging){

		slf4jLogger.debug("Entering function {}", "createAndSaveBufferRecord");

		RegistrationBuffer registrationBuffer = new RegistrationBuffer();

		if(modelStaging.getCoupon() != null && !"".equals(modelStaging.getCoupon())){
			List<Coupon> coupons = knottingServicesDao.fetchCoupons(modelStaging.getCoupon());

			DateTime todayDate = new DateTime();

			for(Coupon coupon : coupons){
				DateTime couponStartDate = new DateTime(coupon.getCouponFromDate());
				DateTime couponExpiryDate = new DateTime(coupon.getCouponExpiryDate());
				if(todayDate.isAfter(couponStartDate) && todayDate.isBefore(couponExpiryDate)){
					registrationBuffer.setCoupon(coupon);
					break;
				}
			}
		}

		registrationBuffer.setMember(modelStaging.getMemberEntriesStaging().getMember());
		registrationBuffer.setDiscountAmount(modelStaging.getDiscount());
		registrationBuffer.setTotalAmount(modelStaging.getTotal());

		//Prepare Entries Buffer		
		MemberEntriesBuffer memberEntriesBuffer = new MemberEntriesBuffer();

		memberEntriesBuffer.setMemberEntryId(modelStaging.getMemberEntriesStaging().getMemberEntryId());
		memberEntriesBuffer.setMember(modelStaging.getMemberEntriesStaging().getMember());
		memberEntriesBuffer.setName(modelStaging.getMemberEntriesStaging().getName());
		memberEntriesBuffer.setEmailAddress(modelStaging.getMemberEntriesStaging().getEmailAddress());
		memberEntriesBuffer.setEmailVerified(modelStaging.getMemberEntriesStaging().getEmailVerified());
		memberEntriesBuffer.setLocationAddress(modelStaging.getMemberEntriesStaging().getLocationAddress());
		memberEntriesBuffer.setLocationPoint(modelStaging.getMemberEntriesStaging().getLocationPoint());
		memberEntriesBuffer.setPhone1(modelStaging.getMemberEntriesStaging().getPhone1());
		memberEntriesBuffer.setPhone1verified(modelStaging.getMemberEntriesStaging().getPhone1verified());
		memberEntriesBuffer.setPhone2(modelStaging.getMemberEntriesStaging().getPhone2());
		memberEntriesBuffer.setPhone3(modelStaging.getMemberEntriesStaging().getPhone3());
		memberEntriesBuffer.setPhone4(modelStaging.getMemberEntriesStaging().getPhone4());

		memberEntriesBuffer.setTwitterUrl(modelStaging.getMemberEntriesStaging().getTwitterUrl());
		memberEntriesBuffer.setWebsiteUrl(modelStaging.getMemberEntriesStaging().getWebsiteUrl());
		memberEntriesBuffer.setFacebookUrl(modelStaging.getMemberEntriesStaging().getFacebookUrl());
		memberEntriesBuffer.setInstagramUrl(modelStaging.getMemberEntriesStaging().getInstagramUrl());
		memberEntriesBuffer.setYoutubeUrl(modelStaging.getMemberEntriesStaging().getYoutubeUrl());
		memberEntriesBuffer.setGoogleplusUrl(modelStaging.getMemberEntriesStaging().getGoogleplusUrl());

		memberEntriesBuffer.setEmergencyPhoneNumber(modelStaging.getMemberEntriesStaging().getEmergencyPhoneNumber());
		memberEntriesBuffer.setEmergencyPhoneNumberVerified(modelStaging.getMemberEntriesStaging().getEmergencyPhoneNumberVerified());
		memberEntriesBuffer.setEmergencyRequestAccepted(modelStaging.getMemberEntriesStaging().getEmergencyRequestAccepted());

		memberEntriesBuffer.setAdditionalAreasServiced(modelStaging.getMemberEntriesStaging().getAdditionalAreasServiced());
		memberEntriesBuffer.setDocumentPath(modelStaging.getMemberEntriesStaging().getDocumentPath());
		memberEntriesBuffer.setSubscriptionAmount(modelStaging.getMemberEntriesStaging().getSubscriptionAmount());
		memberEntriesBuffer.setSubscriptionExpiry(modelStaging.getMemberEntriesStaging().getSubscriptionExpiry());
		memberEntriesBuffer.setSubscriptionTerm(modelStaging.getMemberEntriesStaging().getSubscriptionTerm());

		memberEntriesBuffer.setCreatedBy(modelStaging.getMemberEntriesStaging().getCreatedBy());
		memberEntriesBuffer.setCreatedDate(modelStaging.getMemberEntriesStaging().getCreatedDate());
		memberEntriesBuffer.setLastUpdatedBy(modelStaging.getMemberEntriesStaging().getLastUpdatedBy());
		memberEntriesBuffer.setLastUpdatedDate(modelStaging.getMemberEntriesStaging().getLastUpdatedDate());

		Set<MemberServicesBuffer> memberServicesBuffers = new HashSet<MemberServicesBuffer>();

		for(MemberServicesStaging memberServicesStaging : modelStaging.getMemberServicesStagingList()){
			MemberServicesBuffer memberServicesBuffer = new MemberServicesBuffer();

			memberServicesBuffer.setMemberServiceCode(memberServicesStaging.getMemberServiceCode());
			
			memberServicesBuffer.setExperience(memberServicesStaging.getExperience());
			memberServicesBuffer.setPriceRange(memberServicesStaging.getPriceRange());
			memberServicesBuffer.setServices(memberServicesStaging.getServices());

			memberServicesBuffer.setImagePath1(memberServicesStaging.getImagePath1());
			memberServicesBuffer.setImagePath2(memberServicesStaging.getImagePath2());
			memberServicesBuffer.setImagePath3(memberServicesStaging.getImagePath3());
			memberServicesBuffer.setImagePath4(memberServicesStaging.getImagePath4());
			memberServicesBuffer.setImagePath5(memberServicesStaging.getImagePath5());
			memberServicesBuffer.setImagePath6(memberServicesStaging.getImagePath6());
			memberServicesBuffer.setImagePath7(memberServicesStaging.getImagePath7());
			memberServicesBuffer.setImagePath8(memberServicesStaging.getImagePath8());
			memberServicesBuffer.setImagePath9(memberServicesStaging.getImagePath9());
			memberServicesBuffer.setImagePath10(memberServicesStaging.getImagePath10());
			memberServicesBuffer.setImagePath11(memberServicesStaging.getImagePath11());
			memberServicesBuffer.setImagePath12(memberServicesStaging.getImagePath12());
			memberServicesBuffer.setImagePath13(memberServicesStaging.getImagePath13());
			memberServicesBuffer.setImagePath14(memberServicesStaging.getImagePath14());
			memberServicesBuffer.setImagePath15(memberServicesStaging.getImagePath15());
			memberServicesBuffer.setImagePath16(memberServicesStaging.getImagePath16());
			memberServicesBuffer.setImagePath17(memberServicesStaging.getImagePath17());
			memberServicesBuffer.setImagePath18(memberServicesStaging.getImagePath18());
			memberServicesBuffer.setImagePath19(memberServicesStaging.getImagePath19());
			memberServicesBuffer.setImagePath20(memberServicesStaging.getImagePath20());

			memberServicesBuffer.setServiceStatus(memberServicesStaging.getServiceStatus());

			memberServicesBuffer.setServicesCriteria1(memberServicesStaging.getServicesCriteria1());
			memberServicesBuffer.setServicesCriteria2(memberServicesStaging.getServicesCriteria2());
			memberServicesBuffer.setServicesCriteria3(memberServicesStaging.getServicesCriteria3());
			memberServicesBuffer.setServicesCriteria4(memberServicesStaging.getServicesCriteria4());
			memberServicesBuffer.setServicesCriteria5(memberServicesStaging.getServicesCriteria5());
			memberServicesBuffer.setServicesCriteria6(memberServicesStaging.getServicesCriteria6());
			memberServicesBuffer.setServicesCriteria7(memberServicesStaging.getServicesCriteria7());
			memberServicesBuffer.setServicesCriteria8(memberServicesStaging.getServicesCriteria8());
			memberServicesBuffer.setServicesCriteria9(memberServicesStaging.getServicesCriteria9());
			memberServicesBuffer.setServicesCriteria10(memberServicesStaging.getServicesCriteria10());
			memberServicesBuffer.setServicesCriteria11(memberServicesStaging.getServicesCriteria11());
			memberServicesBuffer.setServicesCriteria12(memberServicesStaging.getServicesCriteria12());
			memberServicesBuffer.setServicesCriteria13(memberServicesStaging.getServicesCriteria13());
			memberServicesBuffer.setServicesCriteria14(memberServicesStaging.getServicesCriteria14());
			memberServicesBuffer.setServicesCriteria15(memberServicesStaging.getServicesCriteria15());
			memberServicesBuffer.setServicesCriteria16(memberServicesStaging.getServicesCriteria16());
			memberServicesBuffer.setServicesCriteria17(memberServicesStaging.getServicesCriteria17());
			memberServicesBuffer.setServicesCriteria18(memberServicesStaging.getServicesCriteria18());
			memberServicesBuffer.setServicesCriteria19(memberServicesStaging.getServicesCriteria19());
			memberServicesBuffer.setServicesCriteria20(memberServicesStaging.getServicesCriteria20());
			memberServicesBuffer.setServicesCriteria21(memberServicesStaging.getServicesCriteria21());
			memberServicesBuffer.setServicesCriteria22(memberServicesStaging.getServicesCriteria22());
			memberServicesBuffer.setServicesCriteria23(memberServicesStaging.getServicesCriteria23());
			memberServicesBuffer.setServicesCriteria24(memberServicesStaging.getServicesCriteria24());
			memberServicesBuffer.setServicesCriteria25(memberServicesStaging.getServicesCriteria25());
			memberServicesBuffer.setServicesCriteria26(memberServicesStaging.getServicesCriteria26());
			memberServicesBuffer.setServicesCriteria27(memberServicesStaging.getServicesCriteria27());
			memberServicesBuffer.setServicesCriteria28(memberServicesStaging.getServicesCriteria28());
			memberServicesBuffer.setServicesCriteria29(memberServicesStaging.getServicesCriteria29());
			memberServicesBuffer.setServicesCriteria30(memberServicesStaging.getServicesCriteria30());

			memberServicesBuffer.setMemberEntriesBuffer(memberEntriesBuffer);

			memberServicesBuffer.setCreatedBy(memberServicesStaging.getCreatedBy());
			memberServicesBuffer.setCreatedDate(memberServicesStaging.getCreatedDate());
			memberServicesBuffer.setLastUpdatedBy(memberServicesStaging.getLastUpdatedBy());
			memberServicesBuffer.setLastUpdatedDate(memberServicesStaging.getLastUpdatedDate());

			memberServicesBuffers.add(memberServicesBuffer);
		}

		memberEntriesBuffer.setServicesRecords(memberServicesBuffers);
		registrationBuffer.setMemberEntriesBuffer(memberEntriesBuffer);

		knottingServicesDao.saveRegistrationBuffer(registrationBuffer);

		slf4jLogger.debug("Exiting function {}", "createAndSaveBufferRecord");

		return registrationBuffer.getBufferId();

	}

	private void createSubscriptionPaymentRecords(ServicesModelStaging servicesModel, Long entryId, Long transactionId){

		slf4jLogger.debug("Entering function {}", "createSubscriptionPaymentRecords");

		DateTime todaysDate = new DateTime();

		/* Add Subscription Payment Records */
		for(MemberServicesStaging memberServicesStaging : servicesModel.getMemberServicesStagingList()){
			boolean subscriptionExists = false;
			List<SubscriptionPayment> subscriptionPayments = knottingServicesDao.fetchSubscriptionPayment(entryId, memberServicesStaging.getServices().getServicesId());

			for(SubscriptionPayment subscriptionPayment : subscriptionPayments){
				if(todaysDate.isAfter(subscriptionPayment.getTermStartDate().getTime()) && todaysDate.isBefore(subscriptionPayment.getTermEndDate().getTime())){
					subscriptionExists = true;
					break;
				}
			}

			if(!subscriptionExists){
				//insert subscription payment record
				SubscriptionPayment subscriptionPayment = new SubscriptionPayment();
				subscriptionPayment.getMember().setMemberId(servicesModel.getMemberEntriesStaging().getMember().getMemberId());
				subscriptionPayment.getMemberEntriesStaging().setMemberEntryId(entryId);
				subscriptionPayment.getServices().setServicesId(memberServicesStaging.getServices().getServicesId());
				subscriptionPayment.getTransaction().setTransactionId(transactionId);
				subscriptionPayment.setPaymentAmount(knottingServicesDao.fetchSubscription(memberServicesStaging.getServices().getServicesId()).getSubscriptionRate().toString());
				subscriptionPayment.setTermStartDate(todaysDate.toDate());
				subscriptionPayment.setTermEndDate(todaysDate.plusYears(1).toDate());
				knottingServicesDao.addSubscriptionPayment(subscriptionPayment);
			}
		}

		slf4jLogger.debug("Exiting function {}", "createSubscriptionPaymentRecords");
	}

	public Coupon fetchActiveCoupon(String couponCode){

		slf4jLogger.debug("Entering function {}", "fetchActiveCoupon");

		DateTime todayDate = new DateTime();
		List<Coupon> coupons = knottingServicesDao.fetchCoupons(couponCode);
		Coupon matchingCoupon = null;

		if(coupons != null && coupons.size() > 0){
			for(Coupon coupon : coupons){
				DateTime couponStartDate = new DateTime(coupon.getCouponFromDate());
				DateTime couponExpiryDate = new DateTime(coupon.getCouponExpiryDate());
				if(todayDate.isAfter(couponStartDate) && todayDate.isBefore(couponExpiryDate)){
					matchingCoupon = coupon;
					break;
				}
			}
		}
		else{
			return null;
		}

		slf4jLogger.debug("Exiting function {}", "fetchActiveCoupon");

		return matchingCoupon;

	}

	private Long fetchEntriesStagingId(){

		slf4jLogger.debug("Entering function {}", "fetchEntriesStagingId");

		SequenceEntriesStaging sequence =  knottingServicesDao.fetchEntriesStagingNextNumber();

		Long nextNumber = new Long((sequence.getNextNumber().intValue() + 1));

		sequence.setNextNumber(nextNumber);

		knottingServicesDao.saveEntriesStagingNextNumber(sequence);

		slf4jLogger.debug("Exiting function {}", "fetchEntriesStagingId");

		return nextNumber;

	}

}
