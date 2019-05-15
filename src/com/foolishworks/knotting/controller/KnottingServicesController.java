package com.foolishworks.knotting.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.foolishworks.knotting.constants.KnottingConstants;
import com.foolishworks.knotting.entity.Coupon;
import com.foolishworks.knotting.entity.Member;
import com.foolishworks.knotting.entity.MemberEntries;
import com.foolishworks.knotting.entity.MemberEntriesStaging;
import com.foolishworks.knotting.entity.MemberServicesStaging;
import com.foolishworks.knotting.entity.RegistrationBuffer;
import com.foolishworks.knotting.entity.SubscriptionPayment;
import com.foolishworks.knotting.entity.Transaction;
import com.foolishworks.knotting.exception.WanderingUserException;
import com.foolishworks.knotting.intf.KnottingCommonService;
import com.foolishworks.knotting.intf.KnottingLoginService;
import com.foolishworks.knotting.intf.KnottingProfileService;
import com.foolishworks.knotting.intf.KnottingServicesService;
import com.foolishworks.knotting.intf.PaymentService;
import com.foolishworks.knotting.model.ServicesModel;
import com.foolishworks.knotting.model.ServicesModelStaging;
import com.foolishworks.knotting.transformer.KnottingTransformer;
import com.foolishworks.knotting.utils.MailerUtil;

@Controller
@PropertySource({"classpath:config.properties", "classpath:display_message.properties"})
public class KnottingServicesController extends KnottingConstants {

	private final Logger slf4jLogger = LoggerFactory.getLogger(KnottingServicesController.class);

	@Autowired
	private Environment environment;

	@Autowired
	private KnottingServicesService knottingServicesService;

	@Autowired
	private KnottingLoginService knottingLoginService;

	@Autowired
	private KnottingCommonService knottingCommonService;
	
	@Autowired
	private KnottingProfileService knottingProfileService;

	@Autowired
	private PaymentService paymentService;

	@Autowired
	private MailerUtil mailerUtil;

	@RequestMapping("/addService")
	public String addService(HttpServletRequest request, ModelMap map) {

		slf4jLogger.debug("Entering function {}", "addService");

		String viewName = "editService";

		try{
			if(request.getSession().getAttribute("userId") == null){
				request.getSession().setAttribute("error", "Your session got expired. Kindly login again");
				return "redirect:/";
			}

			/* Menu component. Do not delete*/
			map.addAttribute("allServices", knottingServicesService.fetchAvailableListOfServices());

			ServicesModelStaging servicesModel = new ServicesModelStaging();

			servicesModel.getMemberEntriesStaging().setPhone1verified(verificationNotDone);
			servicesModel.getMemberEntriesStaging().setEmailVerified(verificationNotDone);
			servicesModel.getMemberEntriesStaging().setEmergencyPhoneNumberVerified(verificationNotDone);

			/* Menu component. Do not delete*/
			map.addAttribute("allServices", knottingServicesService.fetchAvailableListOfServices());
			map.addAttribute("servicesModel", servicesModel);

			map.addAttribute("offeredServices", knottingServicesService.fetchAvailableListOfServices());
			map.addAttribute("serviceQuestions", knottingServicesService.fetchAvailableListOfServiceQuestions());
		}
		catch(Exception exception){
			request.getSession().setAttribute(errorMessage, environment.getProperty("exception_error_message"));
			mailerUtil.mailExceptionDetails("addService", exception.getMessage(), ExceptionUtils.getFullStackTrace(exception), request.getSession().getAttribute(sessionUserId), request.getSession().getAttribute(sessionUserType));
			return "redirect:profile";
		}

		slf4jLogger.debug("Exiting function {}", "addService");

		return viewName;
	}

	@RequestMapping("/viewApprovedService")
	public String viewApprovedService(HttpServletRequest request, ModelMap map, @RequestParam("serviceid") String serviceId) {

		slf4jLogger.debug("Entering function {}", "viewApprovedService");

		String viewName = "viewService";

		try{

			if(request.getSession().getAttribute("userId") == null){
				request.getSession().setAttribute("error", "Your session got expired. Kindly login again");
				return "redirect:/";
			}

			/* Menu component. Do not delete*/
			map.addAttribute("allServices", knottingServicesService.fetchAvailableListOfServices());

			Long userId = (Long)request.getSession().getAttribute("userId");

			MemberEntries memberEntries = knottingServicesService.fetchMemberEntriesRecord(serviceId);

			if(memberEntries != null && !memberEntries.getMember().getMemberId().equals(userId)){
				throw new WanderingUserException("User trying to access a service which does not belong to him.");
			}

			knottingServicesService.calculateDaysSinceCreation(memberEntries);	
			knottingServicesService.prepareThumbnailImage(memberEntries);

			map.addAttribute("servicesModel", memberEntries);
			map.addAttribute("servicesQuestions", knottingServicesService.fetchListOfServiceQuestions(memberEntries.getServicesRecords()));
			map.addAttribute("emergencyYes", knottingCommonService.fetchConfigurationByCode(configurationServiceEmergencyYes));
			map.addAttribute("emergencyNo", knottingCommonService.fetchConfigurationByCode(configurationServiceEmergencyNo));
		}
		catch(Exception exception){
			request.getSession().setAttribute(errorMessage, environment.getProperty("exception_error_message"));
			mailerUtil.mailExceptionDetails("viewApprovedService", exception.getMessage(), ExceptionUtils.getFullStackTrace(exception), request.getSession().getAttribute(sessionUserId), request.getSession().getAttribute(sessionUserType));
			return "redirect:profile";
		}

		slf4jLogger.debug("Exiting function {}", "viewApprovedService");

		return viewName;
	}

	@RequestMapping("/viewPendingService")
	public String viewPendingService(HttpServletRequest request, ModelMap map, @RequestParam("serviceid") String serviceId) {

		slf4jLogger.debug("Entering function {}", "viewPendingService");

		String viewName = "viewService";

		try{

			if(request.getSession().getAttribute("userId") == null){
				request.getSession().setAttribute("error", "Your session got expired. Kindly login again");
				return "redirect:/";
			}

			/* Menu component. Do not delete*/
			map.addAttribute("allServices", knottingServicesService.fetchAvailableListOfServices());

			Long userId = (Long)request.getSession().getAttribute("userId");

			MemberEntriesStaging memberEntriesStaging = knottingServicesService.fetchMemberEntriesStagingRecord(serviceId);

			if(memberEntriesStaging != null && !memberEntriesStaging.getMember().getMemberId().equals(userId)){
				throw new WanderingUserException("User trying to access a service which does not belong to him.");
			}

			knottingServicesService.calculateDaysSinceCreation(memberEntriesStaging);	
			knottingServicesService.prepareThumbnailImage(memberEntriesStaging);

			map.addAttribute("servicesModel", memberEntriesStaging);
			map.addAttribute("servicesQuestions", knottingServicesService.fetchListOfServiceQuestionsStaging(memberEntriesStaging.getServicesRecords()));
			map.addAttribute("emergencyYes", knottingCommonService.fetchConfigurationByCode(configurationServiceEmergencyYes));
			map.addAttribute("emergencyNo", knottingCommonService.fetchConfigurationByCode(configurationServiceEmergencyNo));	
		}
		catch(Exception exception){
			request.getSession().setAttribute(errorMessage, environment.getProperty("exception_error_message"));
			mailerUtil.mailExceptionDetails("viewPendingService", exception.getMessage(), ExceptionUtils.getFullStackTrace(exception), request.getSession().getAttribute(sessionUserId), request.getSession().getAttribute(sessionUserType));
			return "redirect:profile";
		}

		slf4jLogger.debug("Exiting function {}", "viewPendingService");

		return viewName;
	}

	@RequestMapping("/editApprovedService")
	public String editApprovedService(HttpServletRequest request, ModelMap map, @RequestParam("serviceid") String serviceId) {

		slf4jLogger.debug("Entering function {}", "editApprovedService");

		String viewName = "editService";

		try{

			if(request.getSession().getAttribute("userId") == null){
				request.getSession().setAttribute("error", "Your session got expired. Kindly login again");
				return "redirect:/";
			}

			/* Menu component. Do not delete*/
			map.addAttribute("allServices", knottingServicesService.fetchAvailableListOfServices());

			Long userId = (Long)request.getSession().getAttribute("userId");

			ServicesModel servicesModel = new ServicesModel();
			MemberEntries memberEntries = knottingServicesService.fetchMemberEntriesRecord(serviceId);
			servicesModel.setMemberEntriesStaging(memberEntries);

			if(memberEntries != null && !memberEntries.getMember().getMemberId().equals(userId)){
				throw new WanderingUserException("User trying to access a service which does not belong to him.");
			}

			map.addAttribute("servicesModel", servicesModel);
			map.addAttribute("offeredServices", knottingServicesService.fetchAvailableListOfServices());
			map.addAttribute("serviceQuestions", knottingServicesService.fetchAvailableListOfServiceQuestions());
		}
		catch(Exception exception){
			request.getSession().setAttribute(errorMessage, environment.getProperty("exception_error_message"));
			mailerUtil.mailExceptionDetails("editApprovedService", exception.getMessage(), ExceptionUtils.getFullStackTrace(exception), request.getSession().getAttribute(sessionUserId), request.getSession().getAttribute(sessionUserType));
			return "redirect:profile";
		}

		slf4jLogger.debug("Exiting function {}", "editApprovedService");

		return viewName;
	}

	@RequestMapping("/editPendingService")
	public String editPendingService(HttpServletRequest request, ModelMap map, @RequestParam("serviceid") String serviceId) {

		slf4jLogger.debug("Entering function {}", "editPendingService");

		String viewName = "editService";

		try{

			if(request.getSession().getAttribute("userId") == null){
				request.getSession().setAttribute("error", "Your session got expired. Kindly login again");
				return "redirect:/";
			}

			/* Menu component. Do not delete*/
			map.addAttribute("allServices", knottingServicesService.fetchAvailableListOfServices());

			Long userId = (Long)request.getSession().getAttribute("userId");

			ServicesModelStaging servicesModel = new ServicesModelStaging();
			MemberEntriesStaging memberEntriesStaging = knottingServicesService.fetchMemberEntriesStagingRecord(serviceId);
			servicesModel.setMemberEntriesStaging(memberEntriesStaging);

			if(memberEntriesStaging != null && !memberEntriesStaging.getMember().getMemberId().equals(userId)){
				throw new WanderingUserException("User trying to access a service which does not belong to him.");
			}

			map.addAttribute("servicesModel", servicesModel);
			map.addAttribute("offeredServices", knottingServicesService.fetchAvailableListOfServices());
			map.addAttribute("serviceQuestions", knottingServicesService.fetchAvailableListOfServiceQuestions());
		}
		catch(Exception exception){
			request.getSession().setAttribute(errorMessage, environment.getProperty("exception_error_message"));
			mailerUtil.mailExceptionDetails("editPendingService", exception.getMessage(), ExceptionUtils.getFullStackTrace(exception), request.getSession().getAttribute(sessionUserId), request.getSession().getAttribute(sessionUserType));
			return "redirect:profile";
		}

		slf4jLogger.debug("Exiting function {}", "editPendingService");

		return viewName;
	}

	@RequestMapping("/proceedToPay")
	public String proceedToPay(HttpServletRequest request, ModelMap map) {

		slf4jLogger.debug("Entering function {}", "proceedToPay");

		String viewName = "proceedToPay";

		try{

			if(request.getSession().getAttribute("userId") == null){
				request.getSession().setAttribute("error", "Your session got expired. Kindly login again");
				return "redirect:/";
			}

			/* Menu component. Do not delete*/
			map.addAttribute("allServices", knottingServicesService.fetchAvailableListOfServices());

			Long userId = (Long)request.getSession().getAttribute("userId");
			String amount = null;
			RegistrationBuffer buffer = null;

			if(request.getSession().getAttribute("bufferId") != null){
				Long bufferId = (Long)request.getSession().getAttribute("bufferId");
				buffer = knottingServicesService.fetchBufferRecord(bufferId);
			}
			if(request.getSession().getAttribute("amount") != null){
				amount = request.getSession().getAttribute("amount").toString();
			}

			String firstName = buffer.getMemberEntriesBuffer().getName();
			String email = buffer.getMemberEntriesBuffer().getEmailAddress();
			String phone = buffer.getMemberEntriesBuffer().getPhone1().toString();

			JSONObject paymentJsonObject = fetchDetailsForPaymentGateway(userId, buffer.getMemberEntriesBuffer().getMemberEntryId(), amount, firstName, email, buffer.getTotalAmount(), buffer.getDiscountAmount(), buffer.getCoupon());

			map.addAttribute("url", environment.getProperty("payment.url"));
			map.addAttribute("key", environment.getProperty("payment.key"));
			map.addAttribute("hash", paymentJsonObject.get("hash"));
			map.addAttribute("txnid", paymentJsonObject.get("transactionId"));
			map.addAttribute("serviceProvider", environment.getProperty("payment.service.provider"));
			map.addAttribute("amount", amount);
			map.addAttribute("firstname", firstName);
			map.addAttribute("email", email);
			map.addAttribute("phone", phone);
			map.addAttribute("productinfo", environment.getProperty("payment.product.info"));
			map.addAttribute("surl", environment.getProperty("payment.success.url"));
			map.addAttribute("furl", environment.getProperty("payment.failure.url"));
		}
		catch(Exception exception){
			request.getSession().setAttribute(errorMessage, environment.getProperty("exception_error_message"));
			mailerUtil.mailExceptionDetails("proceedToPay", exception.getMessage(), ExceptionUtils.getFullStackTrace(exception), request.getSession().getAttribute(sessionUserId), request.getSession().getAttribute(sessionUserType));
			return "redirect:profile";
		}

		slf4jLogger.debug("Exiting function {}", "proceedToPay");

		return viewName;
	}

	@RequestMapping("/saveServices")
	public String saveServices(@ModelAttribute("servicesModel") ServicesModelStaging servicesModel, BindingResult result, HttpServletRequest request, ModelMap map, @RequestParam(value = "saveType", required = true) String saveType, @RequestParam(value = "amount", required = true) String amount, @RequestParam(value = "executivePhoneNumber", required = false) String executivePhoneNumber) {

		slf4jLogger.debug("Entering function {}", "saveServices");

		String viewName = "redirect:profile";

		try{

			if(request.getSession().getAttribute("userId") == null){
				request.getSession().setAttribute("error", "Your session got expired. Kindly login again");
				return "redirect:/";
			}

			Long userId = (Long)request.getSession().getAttribute("userId");

			servicesModel.getMemberEntriesStaging().getMember().setMemberId(userId);

			knottingServicesService.createImageCopyInServer(userId, servicesModel);

			if(saveType.equals(gotoPayment)){

				knottingServicesService.deleteAllBufferforUser(userId);

				if(request.getSession().getAttribute("bufferId") != null){
					request.getSession().removeAttribute("bufferId");
				}
				if(request.getSession().getAttribute("amount") != null){
					request.getSession().removeAttribute("amount");
				}

				Long bufferId = knottingServicesService.createAndSaveBufferRecord(servicesModel);

				request.getSession().setAttribute("bufferId", bufferId);
				request.getSession().setAttribute("amount", amount);

				viewName = "redirect:proceedToPay";
			}
			else if(saveType.equals(executivePayment)){
				Member member =  knottingLoginService.checkIfUserExist(executivePhoneNumber);
				String transactionId = paymentService.generateExecutiveTransactionId(userId, servicesModel.getMemberEntriesStaging().getMemberEntryId(), Float.parseFloat(amount), Float.parseFloat(servicesModel.getTotal()), Float.parseFloat(servicesModel.getDiscount()), servicesModel.getCoupon(), member.getMemberId(), executivePaymentStatusCollectedFromUser);
				saveEntry(request, servicesModel, transactionId);
				request.getSession().setAttribute("success", "Your service was created successfully.");
				
				Transaction transaction = paymentService.fetchTransaction(transactionId);
				member = knottingProfileService.fetchMemberRecord(servicesModel.getMemberEntriesStaging().getMember().getMemberId());
				sendNewEntryMail(member, servicesModel, transaction);
			}
			else if(saveType.equals(saveAlone)){
				saveEntry(request, servicesModel, null);
				request.getSession().setAttribute("success", "Your service was created successfully.");
			}
			else if(saveType.equals(paymentWaivedOff)){
				String transactionId = paymentService.generateNoPaymentTransactionId(userId, servicesModel.getMemberEntriesStaging().getMemberEntryId(), Float.parseFloat(amount), Float.parseFloat(servicesModel.getTotal()), Float.parseFloat(servicesModel.getDiscount()), servicesModel.getCoupon());
				saveEntry(request, servicesModel, transactionId);
				request.getSession().setAttribute("success", "Your service was created successfully.");
				
				Transaction transaction = paymentService.fetchTransaction(transactionId);
				Member member = knottingProfileService.fetchMemberRecord(servicesModel.getMemberEntriesStaging().getMember().getMemberId());
				sendNewEntryMail(member, servicesModel, transaction);
			}
		}
		catch(Exception exception){
			request.getSession().setAttribute(errorMessage, environment.getProperty("exception_error_message"));
			mailerUtil.mailExceptionDetails("saveServices", exception.getMessage(), ExceptionUtils.getFullStackTrace(exception), request.getSession().getAttribute(sessionUserId), request.getSession().getAttribute(sessionUserType));
			return "redirect:profile";
		}

		slf4jLogger.debug("Exiting function {}", "saveServices");

		return viewName;
	}

	@RequestMapping("/fetchSubscriptionFeesAndDetails")
	public @ResponseBody String fetchSubscriptionFeesAndDetails(HttpServletRequest request, @RequestParam(value = "memberEntryId", required = false) String memberEntryId, @RequestParam(value = "serviceidlist", required = true) String serviceIdList, @RequestParam(value = "coupon", required = false) String couponCode) {

		slf4jLogger.debug("Entering function {}", "fetchSubscriptionFeesAndDetails");

		JSONObject subscriptionDetails = null;

		try{
			Long memberEntry = (memberEntryId != null && !memberEntryId.equals(""))?Long.valueOf(memberEntryId):null;
			subscriptionDetails = knottingServicesService.prepareSubscriptionPaymentData(memberEntry, Arrays.asList(serviceIdList.split(",")), couponCode);
		}
		catch(Exception exception){
			request.getSession().setAttribute(errorMessage, environment.getProperty("exception_error_message"));
			mailerUtil.mailExceptionDetails("fetchSubscriptionFeesAndDetails", exception.getMessage(), ExceptionUtils.getFullStackTrace(exception), request.getSession().getAttribute(sessionUserId), request.getSession().getAttribute(sessionUserType));
		}

		slf4jLogger.debug("Exiting function {}", "fetchSubscriptionFeesAndDetails");

		return subscriptionDetails.toString();

	}

	@RequestMapping("/applyCoupon")
	public @ResponseBody String applyCoupon(HttpServletRequest request, @RequestParam(value = "amount", required = false) String totalAmount, @RequestParam(value = "coupon", required = false) String couponCode) {

		slf4jLogger.debug("Entering function {}", "applyCoupon");

		JSONObject couponDetails = null;
		try {
			couponDetails = knottingServicesService.prepareCouponData(totalAmount, couponCode);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		catch(Exception exception){
			request.getSession().setAttribute(errorMessage, environment.getProperty("exception_error_message"));
			mailerUtil.mailExceptionDetails("applyCoupon", exception.getMessage(), ExceptionUtils.getFullStackTrace(exception), request.getSession().getAttribute(sessionUserId), request.getSession().getAttribute(sessionUserType));
		}

		slf4jLogger.debug("Exiting function {}", "applyCoupon");

		return couponDetails.toString();

	}

	@RequestMapping("/checkifKnottingExecutive")
	public @ResponseBody String checkifKnottingExecutive(HttpServletRequest request, @RequestParam(value = "phoneNumber", required = false) String phoneNumber) {

		slf4jLogger.debug("Entering function {}", "checkifKnottingExecutive");

		String status = verificationNotDone;

		try{
			Member member = knottingLoginService.checkIfUserExist(phoneNumber);

			if(member == null){
				status = "X";
			}
			else if(member != null && member.getUserType().equals(memberTypeExecutive)){
				status = verificationDone;
			}
		}
		catch(Exception exception){
			request.getSession().setAttribute(errorMessage, environment.getProperty("exception_error_message"));
			mailerUtil.mailExceptionDetails("checkifKnottingExecutive", exception.getMessage(), ExceptionUtils.getFullStackTrace(exception), request.getSession().getAttribute(sessionUserId), request.getSession().getAttribute(sessionUserType));
		}

		slf4jLogger.debug("Exiting function {}", "checkifKnottingExecutive");

		return status;

	}

	@RequestMapping("/verifyServiceEmail")
	public String verifyServiceEmail(HttpServletRequest request, ModelMap map, @RequestParam(value = "id", required = true) String serviceId, @RequestParam(value = "email", required = true) String email) {

		slf4jLogger.debug("Entering function {}", "verifyServiceEmail");

		try{
			boolean emailVerificationStatus = knottingServicesService.verifyServiceEmail(serviceId, email);
		}
		catch(Exception exception){
			request.getSession().setAttribute(errorMessage, environment.getProperty("exception_error_message"));
			mailerUtil.mailExceptionDetails("verifyServiceEmail", exception.getMessage(), ExceptionUtils.getFullStackTrace(exception), request.getSession().getAttribute(sessionUserId), request.getSession().getAttribute(sessionUserType));
			return "redirect:/";
		}

		slf4jLogger.debug("Exiting function {}", "verifyServiceEmail");

		return "verifyEmail";

	}

	public JSONObject fetchDetailsForPaymentGateway(Long userId, Long memberEntryId, String amount, String firstName, String email, String total, String discount, Coupon coupon) throws Exception {

		slf4jLogger.debug("Entering function {}", "fetchDetailsForPaymentGateway");

		JSONObject paymentJsonObject = new JSONObject();

		String transactionId = paymentService.generateTransactionId(userId, memberEntryId, Float.parseFloat(amount), Float.parseFloat(total), Float.parseFloat(discount), coupon);
		String hashString = paymentService.generateHashString(transactionId, amount, firstName, email);
		paymentJsonObject.put("transactionId", transactionId);
		paymentJsonObject.put("hash", hashString);

		slf4jLogger.debug("Exiting function {}", "fetchDetailsForPaymentGateway");

		return paymentJsonObject;

	}

	@RequestMapping("/paymentSuccess")
	public String paymentSuccess(HttpServletRequest request, ModelMap map) {

		slf4jLogger.debug("Entering function {}", "paymentSuccess");

		String viewName = "redirect:profile";

		try{
			if(request.getSession().getAttribute("userId") == null){
				request.getSession().setAttribute("error", "Your session got expired. Kindly login again");
				return "redirect:/";
			}

			ServicesModelStaging servicesModel = null;
			RegistrationBuffer buffer = null;

			if(request.getSession().getAttribute("bufferId") != null){
				Long bufferId = (Long)request.getSession().getAttribute("bufferId");
				buffer = knottingServicesService.fetchBufferRecord(bufferId);
				servicesModel = KnottingTransformer.transformBuffertoStaging(buffer);
			}

			String transactionId = request.getParameter("txnid");

			saveTransactionDetails(request);
			saveEntry(request, servicesModel, transactionId);
			
			Transaction transaction = paymentService.fetchTransaction(transactionId);
			Member member = knottingProfileService.fetchMemberRecord(servicesModel.getMemberEntriesStaging().getMember().getMemberId());
			sendNewEntryMail(member, servicesModel, transaction);

			if(request.getSession().getAttribute("servicesModel") != null){
				request.getSession().removeAttribute("servicesModel");
			}
			if(request.getSession().getAttribute("amount") != null){
				request.getSession().removeAttribute("amount");
			}

			request.getSession().removeAttribute("bufferId");

			request.getSession().setAttribute("success", "Your service was created successfully.");

		}
		catch(Exception exception){
			request.getSession().setAttribute(errorMessage, environment.getProperty("exception_error_message"));
			mailerUtil.mailExceptionDetails("paymentSuccess", exception.getMessage(), ExceptionUtils.getFullStackTrace(exception), request.getSession().getAttribute(sessionUserId), request.getSession().getAttribute(sessionUserType));
			return "redirect:/";
		}

		slf4jLogger.debug("Exiting function {}", "paymentSuccess");

		return viewName;

	}

	@RequestMapping("/paymentFailure")
	public String paymentFailure(HttpServletRequest request, ModelMap map) throws JSONException {

		slf4jLogger.debug("Entering function {}", "paymentFailure");

		String viewName = "paymentFailure";

		try{
			if(request.getSession().getAttribute("userId") == null){
				request.getSession().setAttribute("error", "Your session got expired. Kindly login again");
				return "redirect:/";
			}

			ServicesModelStaging servicesModel = null;
			RegistrationBuffer buffer = null;

			if(request.getSession().getAttribute("bufferId") != null){
				Long bufferId = (Long)request.getSession().getAttribute("bufferId");
				buffer = knottingServicesService.fetchBufferRecord(bufferId);
				servicesModel = KnottingTransformer.transformBuffertoStaging(buffer);

				saveTransactionDetails(request);

				if(servicesModel != null){
					Long memberEntryId = servicesModel.getMemberEntriesStaging().getMemberEntryId();
					List<String> serviceIdList = new ArrayList<String>();

					for(MemberServicesStaging memberServicesStaging : servicesModel.getMemberServicesStagingList()){
						serviceIdList.add(memberServicesStaging.getServices().getServicesId().toString());
					}

					String couponCode = null;

					JSONObject subscriptionDetails = knottingServicesService.prepareSubscriptionPaymentData(memberEntryId, serviceIdList, couponCode);

					map.addAttribute(paymentDataServiceCount, subscriptionDetails.get(paymentDataServiceCount));
					map.addAttribute(paymentDataServiceExpiry, subscriptionDetails.get(paymentDataServiceExpiry));
					map.addAttribute(paymentDataServiceAmount, subscriptionDetails.get(paymentDataServiceAmount));
					map.addAttribute(paymentDataServiceList, subscriptionDetails.get(paymentDataServiceList));
					map.addAttribute(paymentDataDiscountAmount, subscriptionDetails.get(paymentDataDiscountAmount));
					map.addAttribute(paymentDataFinalAmount, subscriptionDetails.get(paymentDataFinalAmount));
				}
			}
			else{
				request.getSession().setAttribute(errorMessage, "You do not have access to this page.");
				return "redirect:/";
			}
		}
		catch(Exception exception){
			request.getSession().setAttribute(errorMessage, environment.getProperty("exception_error_message"));
			mailerUtil.mailExceptionDetails("paymentFailure", exception.getMessage(), ExceptionUtils.getFullStackTrace(exception), request.getSession().getAttribute(sessionUserId), request.getSession().getAttribute(sessionUserType));
			return "redirect:/";
		}

		slf4jLogger.debug("Exiting function {}", "paymentFailure");

		return viewName;

	}

	private void saveTransactionDetails(HttpServletRequest request){

		slf4jLogger.debug("Entering function {}", "saveTransactionDetails");

		String hash = request.getParameter("hash");
		String status = request.getParameter("status");
		String email = request.getParameter("email");
		String firstName = request.getParameter("firstname");
		String amount = request.getParameter("amount");
		String transactionId = request.getParameter("txnid");
		String pgTransactionId = request.getParameter("mihpayid");
		String paymentMode = request.getParameter("mode");
		String paymentStatus = request.getParameter("status");
		String discount = request.getParameter("discount");
		String errorCode = request.getParameter("Error");
		String pgType = request.getParameter("PG_TYPE");
		String bankReferenceNumber = request.getParameter("bank_ref_num");
		String pgStatus = request.getParameter("unmappedstatus");

		boolean safeTransaction = paymentService.decodeHashString(hash, status, email, firstName, amount, transactionId);

		if(safeTransaction){
			paymentService.updateTransaction(transactionId, pgTransactionId, paymentMode, paymentStatus, discount, errorCode, pgType, bankReferenceNumber, pgStatus);
			slf4jLogger.debug("transaction updated successsfully");
		}

		slf4jLogger.debug("Exiting function {}", "saveTransactionDetails");


	}

	private boolean saveEntry(HttpServletRequest request, ServicesModelStaging servicesModel, String transactionId) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException{

		slf4jLogger.debug("Entering function {}", "saveEntry");

		boolean saveStatus = false;

		Long userId = (Long)request.getSession().getAttribute("userId");
		saveStatus = knottingServicesService.saveEntry(userId, servicesModel, transactionId);

		slf4jLogger.debug("Exiting function {}", "saveEntry");

		return saveStatus;

	}

	private void sendNewEntryMail(Member member, ServicesModelStaging servicesModel, Transaction transaction){

		slf4jLogger.debug("Entering function {}", "sendNewEntryMail");

		String entryEmail = servicesModel.getMemberEntriesStaging().getEmailAddress();
		String memberEmail = member.getEmailId();
		String phoneNumber = servicesModel.getMemberEntriesStaging().getPhone1().toString();
		
		if((entryEmail != null && !entryEmail.trim().equals("")) || (memberEmail != null && !memberEmail.trim().equals(""))){
			String finalCost = transaction.getTotalAmount();
			String couponDiscount = (transaction.getCoupon().getCouponCode() != null)?("("+transaction.getCouponDiscountedAmount()+" INR discount towards coupon "+transaction.getCoupon().getCouponCode()+")"):"";
			
			StringBuilder serviceList = new StringBuilder();
			
			if(transaction.getSubscriptionPayments().size() > 1){
				int serviceCounter = 1;
				serviceList.append("for services - ");
				
				for(SubscriptionPayment subscriptionPayment : transaction.getSubscriptionPayments()){
					if(serviceCounter != 1 && serviceCounter != transaction.getSubscriptionPayments().size()){
						serviceList.append(", ");
					}
					else if(serviceCounter == transaction.getSubscriptionPayments().size()){
						serviceList.append(" and ");
					}
					serviceList.append(subscriptionPayment.getServices().getName());
					serviceCounter++;
				}
			}
			else{
				serviceList.append("for service - "+transaction.getSubscriptionPayments().iterator().next().getServices().getName());
			}

			if(entryEmail != null && !entryEmail.trim().equals("")){
				mailerUtil.sendNewEntryMail(phoneNumber, entryEmail, finalCost, couponDiscount, serviceList.toString());
			}
			if(memberEmail != null && !memberEmail.trim().equals("")){
				mailerUtil.sendNewEntryMail(phoneNumber, memberEmail, finalCost, couponDiscount, serviceList.toString());
			}
		}

		slf4jLogger.debug("Exiting function {}", "sendNewEntryMail");

	}
}
