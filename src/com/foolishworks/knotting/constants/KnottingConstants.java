package com.foolishworks.knotting.constants;

public class KnottingConstants {

//	public static final String domainPath = "http://localhost:8080/Knotting/";
	public static final String domainPath = "http://www.knotting.in/";

//	public static final String cloudinaryName = "drnxx2ap0";
//	public static final String cloudinaryKey = "228518823269731";
//	public static final String cloudinarySecret = "p26WaJAF7xKEPTaPG6UHSjEBJyE";
	public static final String cloudinaryName = "knottingprod";
	public static final String cloudinaryKey = "161921185628662";
	public static final String cloudinarySecret = "1J_s3HkMqxcAZnkLhYJrf2-3Ldk";

	public static final String NULLSTRING = null;
	public static final String BLANK = "";

	public static final String exceptionMailbox = "exception@knotting.in";
	public static final String newServiceMailbox = "newservice@knotting.in";

	public static final String errorMessage = "error";
	public static final String successMessage = "success";
	public static final String sessionUserId = "userId";	
	public static final String sessionUserType = "userType";

	public static final String memberTypeVendor = "VENDOR";
	public static final String memberTypeExecutive = "EXEC";
	public static final String memberTypeSupport = "SUPPORT";
	public static final String memberTypeAdmin = "ADMIN";

	public static final Integer serviceMaxImageCount = 20;

	public static final String socialAuthNo = "N";
	public static final String socialAuthYes = "Y";

	public static final String verificationNotDone = "N";
	public static final String verificationDone = "Y";

	public static final String couponNotActive = "N";
	public static final String couponActive = "Y";

	public static final String encryptionPassword = "F00li5hW0rK5kN0tt1NG";
	
	public static final String verifyEmailForUser = "verifyUserEmail";
	public static final String verifyEmailForServices = "verifyServiceEmail";

	public static final String gotoPayment = "P";	
	public static final String saveAlone = "S";	
	public static final String executivePayment = "E";	
	public static final String paymentWaivedOff = "C";	

	public static final String paymentHashEncodingType = "SHA-512";	
	public static final String paymentHashSeperator = "|";

	public static final String paymentDataServiceCount = "serviceCount";
	public static final String paymentDataServiceExpiry = "serviceExpiry";
	public static final String paymentDataServiceAmount = "serviceAmount";
	public static final String paymentDataServiceList = "serviceList";
	public static final String paymentDataDiscountAmount = "discountAmount";
	public static final String paymentDataFinalAmount = "finalAmount";
	public static final String subscriptionServiceId = "Id";
	public static final String subscriptionServiceCode = "Code";
	public static final String subscriptionServiceName = "Name";	
	public static final String subscriptionServiceCost = "Cost";	
	public static final String subscriptionServiceExpiry = "Expiry";	
	public static final String subscriptionServiceStatus = "Status";	
	public static final String subscriptionServicePayment = "Payment";
	public static final String subscriptionServiceStatusActive = "active";
	public static final String subscriptionServiceStatusInActive = "notactive";
	public static final String subscriptionServicePaymentPaid = "paid";
	public static final String subscriptionServicePaymentUnPaid = "unpaid";	

	public static final String executivePaymentStatusCollectedFromUser = "COLLECTED";		

	public static final String serverRootFolderName = "knotting";
	public static final String serverDocPathFolderName = "docpath";
	public static final String serverServiceImageFolderName = "services";	

	public static final String imageTransformationDelimiter = "upload/";
	public static final String transformationAlgorithm = "t_knotting_thumbnail";
	public static final String transformationSeparator = "/";

	public static final int maxResultPerFetch = 20;
	public static final int maxResultInformVendor = 50;
	public static final String emergencyName = "emergency";	
	public static final String emergencyCode = "Y";	

	public static final String priceRangeAll = "All";	
	public static final String priceRangeCheap = "Cheap";	
	public static final String priceRangeModerate = "Moderate";	
	public static final String priceRangeCostly = "Costly";
	public static final String lowerRangeKey = "LOWER";
	public static final String higherRangeKey = "HIGHER";	

	public static final String detailsFieldTypeInput = "input";
	public static final String detailsFieldTypeDropDown = "dropdown";
	public static final String detailsFieldTypeCheckBox = "checkbox";	

	public static final String filterUndefined = "undefined";
	public static final String filterFieldTypeRange = "range";
	public static final String filterFieldTypeChoice = "choice";

	public static final String sortTypeCostAscending = "cio";
	public static final String sortTypeCostDescending = "cdo";
	public static final String sortTypeExperienceAscending = "eio";
	public static final String sortTypeExperienceDescending = "edo";	

	public static final String serviceActive = "Y";
	public static final String serviceInActive = "N";	

	public static final String configurationHomeBanner = "HOME-BANNER-AD";
	public static final String configurationProfileNote = "PROFILE-NOTE";
	public static final String configurationServiceEmergencyYes = "SERVICE-EMERGENCY-YES";
	public static final String configurationServiceEmergencyNo = "SERVICE-EMERGENCY-NO";
	public static final String configurationHomeDisplayRecentsSection = "HOME-DISPLAY-RECENTS";
	public static final String configurationHomeDisplayMostViewedSection = "HOME-DISPLAY-MOST-VIEWED";
	public static final String configurationHomeMostViewedList = "HOME-MOST-VIEWED-LIST";

	public static final String couponDataTotalAmount = "totalAmount";
	public static final String couponDataCouponCode = "couponCode";
	public static final String couponDataDiscountamount = "discountAmount";
	public static final String couponDataFinalAmount = "finalAmount";
	public static final String couponDataError = "couponError";	

	public static final String imageUrlPrefix = "http:";	
	public static final String encodingPrefix = ";base64,";	
	public static final String imageTypePrefix = "image/";	
	public static final String tempFilePrefix = "cloudinary";		

	public static final String supportApproveEntry = "APPROVE";	
	public static final String supportRejectEntry = "REJECT";	

	public static final int maxPasswordLength = 10;
}
