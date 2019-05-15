package com.foolishworks.knotting.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.exception.ExceptionUtils;
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
import com.foolishworks.knotting.entity.Member;
import com.foolishworks.knotting.entity.MemberEntries;
import com.foolishworks.knotting.intf.KnottingCommonService;
import com.foolishworks.knotting.intf.KnottingProfileService;
import com.foolishworks.knotting.intf.KnottingServicesService;
import com.foolishworks.knotting.utils.MailerUtil;
import com.foolishworks.knotting.validator.KnottingProfilePersonalValidator;

@Controller
@PropertySource({"classpath:display_message.properties"})
public class KnottingProfileController extends KnottingConstants {

	@Autowired
	private Environment environment;

	@Autowired
	private KnottingProfileService knottingProfileService;

	@Autowired
	private KnottingServicesService knottingServicesService;

	@Autowired
	private KnottingCommonService knottingCommonService;

	@Autowired
	private MailerUtil mailerUtil;

	@Autowired
	private KnottingProfilePersonalValidator knottingProfilePersonalValidator;

	private final Logger slf4jLogger = LoggerFactory.getLogger(KnottingProfileController.class);

	@RequestMapping("/profile")
	public String handleProfilePageRequest(HttpServletRequest request, ModelMap map) {

		slf4jLogger.debug("Entering function {}", "handleProfilePageRequest");

		String viewName = "profile";

		try{
			if(request.getSession().getAttribute("userId") == null){
				request.getSession().setAttribute(errorMessage, "Your session got expired. Kindly login again");
				return "redirect:/";
			}

			/* Menu component. Do not delete*/
			map.addAttribute("allServices", knottingServicesService.fetchAvailableListOfServices());

			Long userId = (Long)request.getSession().getAttribute("userId");
			Member member = knottingCommonService.fetchMemberRecord(userId, null, null);		
			List<MemberEntries> approvedEntries = knottingProfileService.fetchApprovedMemberServicesForThisUser(userId);

			map.addAttribute("memberModel", member);		
			map.addAttribute("approvedEntriesListing", approvedEntries);
			map.addAttribute("pendingEntriesListing", knottingProfileService.fetchNotApprovedMemberServicesForThisUser(userId));
			map.addAttribute("upcomingPayments", knottingProfileService.calculateUpcomingPayments(approvedEntries));
			map.addAttribute("profileNote", knottingCommonService.fetchConfigurationByCode(configurationProfileNote));

			knottingProfileService.calculateDaysSinceCreation(member);

			if(request.getSession().getAttribute(successMessage) != null){
				map.addAttribute(successMessage, request.getSession().getAttribute(successMessage));
				request.getSession().removeAttribute(successMessage);
			}
			else if(request.getSession().getAttribute(errorMessage) != null){
				map.addAttribute(errorMessage, request.getSession().getAttribute(errorMessage));
				request.getSession().removeAttribute(errorMessage);
			}
		}
		catch(Exception exception){
			request.getSession().setAttribute(errorMessage, environment.getProperty("exception_error_message"));
			mailerUtil.mailExceptionDetails("profile", exception.getMessage(), ExceptionUtils.getFullStackTrace(exception), request.getSession().getAttribute(sessionUserId), request.getSession().getAttribute(sessionUserType));
			return "redirect:/";
		}

		slf4jLogger.debug("Exiting function {}", "handleProfilePageRequest");

		return viewName;
	}

	@RequestMapping("/saveMembersProfileChanges")
	public String saveMembersProfileChanges(@ModelAttribute("memberModel") Member member, BindingResult result, HttpServletRequest request, ModelMap map) {

		slf4jLogger.debug("Entering function {}", "saveMembersProfileChanges");

		try{
			if(request.getSession().getAttribute("userId") == null){
				request.getSession().setAttribute(errorMessage, "Your session got expired. Kindly login again");
				return "redirect:/";
			}

			Member memberRecord = knottingProfileService.fetchMemberRecord(member.getMemberId());
			memberRecord.setName(member.getName());
			memberRecord.setPhoneNumber(member.getPhoneNumber());
			if(!memberRecord.getPhoneNumber().equals(member.getPhoneNumber())){
				memberRecord.setPhoneVerified(member.getPhoneVerified());
			}
			memberRecord.setEmailId(member.getEmailId());
			if(!memberRecord.getEmailId().equals(member.getEmailId())){
				memberRecord.setEmailVerified(member.getEmailVerified());
			}

			knottingProfileService.savePersonalInfo(memberRecord);

			request.getSession().setAttribute(successMessage, "Your changes have been successfully saved.");
		}
		catch(Exception exception){
			request.getSession().setAttribute(errorMessage, environment.getProperty("exception_error_message"));
			mailerUtil.mailExceptionDetails("saveMembersProfileChanges", exception.getMessage(), ExceptionUtils.getFullStackTrace(exception), request.getSession().getAttribute(sessionUserId), request.getSession().getAttribute(sessionUserType));
			return "redirect:profile";
		}

		slf4jLogger.debug("Exiting function {}", "saveMembersProfileChanges");

		return "redirect:profile";
	}

	@RequestMapping("/sendUserVerificationEmail")
	public String sendUserVerificationEmail(HttpServletRequest request, @RequestParam(value="email") String email) {

		slf4jLogger.debug("Entering function {}", "sendUserVerificationEmail");

		Long userId = (Long)request.getSession().getAttribute("userId");

		try{
			knottingProfileService.sendUserVerificationEmail(userId, email);
		}
		catch(Exception exception){
			request.getSession().setAttribute(errorMessage, environment.getProperty("exception_error_message"));
			mailerUtil.mailExceptionDetails("sendUserVerificationEmail", exception.getMessage(), ExceptionUtils.getFullStackTrace(exception), request.getSession().getAttribute(sessionUserId), request.getSession().getAttribute(sessionUserType));
			return "redirect:profile";
		}

		slf4jLogger.debug("Exiting function {}", "sendUserVerificationEmail");

		return "redirect:profile";
	}

	@RequestMapping("/verifyUserEmail")
	public String verifyUserEmail(HttpServletRequest request, ModelMap map, @RequestParam(value = "id", required = false) String userId, @RequestParam(value="email", required=false) String email) {

		slf4jLogger.debug("Entering function {}", "verifyUserEmail");

		try{
			boolean emailVerificationStatus = knottingProfileService.verifyUserEmail(userId, email);
			request.getSession().setAttribute(successMessage, "Your email has been verified successfully.");
		}
		catch(Exception exception){
			request.getSession().setAttribute(errorMessage, environment.getProperty("exception_error_message"));
			mailerUtil.mailExceptionDetails("verifyUserEmail", exception.getMessage(), ExceptionUtils.getFullStackTrace(exception), request.getSession().getAttribute(sessionUserId), request.getSession().getAttribute(sessionUserType));
		}

		slf4jLogger.debug("Exiting function {}", "verifyUserEmail");

		return "redirect:/";
	}

	@RequestMapping("/changeUserPassword")
	public @ResponseBody String changeUserPassword(HttpServletRequest request, @RequestParam(value = "password", required = false) String password) {

		slf4jLogger.debug("Entering function {}", "changeUserPassword");

		String changePasswordStatus = null;

		try{
			Long userId = (Long)request.getSession().getAttribute("userId");
			changePasswordStatus = knottingProfileService.changeUserPassword(userId, password);
		}
		catch(Exception exception){
			request.getSession().setAttribute(errorMessage, environment.getProperty("exception_error_message"));
			mailerUtil.mailExceptionDetails("changeUserPassword", exception.getMessage(), ExceptionUtils.getFullStackTrace(exception), request.getSession().getAttribute(sessionUserId), request.getSession().getAttribute(sessionUserType));
			return "Error";
		}

		slf4jLogger.debug("Exiting function {}", "changeUserPassword");

		return changePasswordStatus;

	}

	@RequestMapping("rateCard")
	public String rateCardScreen(HttpServletRequest request, ModelMap map) {

		slf4jLogger.debug("Entering function {}", "rateCardScreen");

		try{
			if(request.getSession().getAttribute("userId") == null){
				request.getSession().setAttribute(errorMessage, "Your session got expired. Kindly login again");
				return "redirect:/";
			}

			/* Menu component. Do not delete*/
			map.addAttribute("allServices", knottingServicesService.fetchAvailableListOfServices());	
			map.addAttribute("memberModel", new Member());	

			map.addAttribute("subscriptions", knottingServicesService.fetchAvailableListOfSubscriptions());
		}
		catch(Exception exception){
			request.getSession().setAttribute(errorMessage, environment.getProperty("exception_error_message"));
			mailerUtil.mailExceptionDetails("rateCard", exception.getMessage(), ExceptionUtils.getFullStackTrace(exception), request.getSession().getAttribute(sessionUserId), request.getSession().getAttribute(sessionUserType));
			return "redirect:/";
		}

		slf4jLogger.debug("Exiting function {}", "rateCardScreen");

		return "rateCard";
	}

	@RequestMapping("changeStatus")
	public String changeStatus(@RequestParam(value = "status", defaultValue = "false") boolean status, @RequestParam(value = "serviceId") Long serviceId, HttpServletRequest request, ModelMap map) {

		slf4jLogger.debug("Entering function {}", "changeStatus");

		if(request.getSession().getAttribute("userId") == null){
			request.getSession().setAttribute(errorMessage, "Your session got expired. Kindly login again");
			return "redirect:/";
		}

		try{
			knottingProfileService.changeStatus(serviceId, status);
			request.getSession().setAttribute(successMessage, "Changes saved successfully.");
		}
		catch(Exception exception){
			request.getSession().setAttribute(errorMessage, environment.getProperty("exception_error_message"));
			mailerUtil.mailExceptionDetails("changeStatus", exception.getMessage(), ExceptionUtils.getFullStackTrace(exception), request.getSession().getAttribute(sessionUserId), request.getSession().getAttribute(sessionUserType));
			return "redirect:profile";
		}

		slf4jLogger.debug("Exiting function {}", "changeStatus");

		return "redirect:profile";
	}

}
