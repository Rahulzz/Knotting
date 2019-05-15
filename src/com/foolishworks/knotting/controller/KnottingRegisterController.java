package com.foolishworks.knotting.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.brickred.socialauth.Profile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.foolishworks.knotting.constants.KnottingConstants;
import com.foolishworks.knotting.entity.Member;
import com.foolishworks.knotting.intf.KnottingCommonService;
import com.foolishworks.knotting.intf.KnottingRegisterService;
import com.foolishworks.knotting.utils.MailerUtil;
import com.foolishworks.knotting.utils.SendMessageUtil;
import com.foolishworks.knotting.utils.SocialAuthenticationUtil;
import com.foolishworks.knotting.validator.KnottingRegisterGeneralValidator;
import com.foolishworks.knotting.validator.KnottingRegisterSocialValidator;
import com.mashape.unirest.http.exceptions.UnirestException;

@Controller
@PropertySource({"classpath:display_message.properties", "classpath:oauth_consumer.properties"})
public class KnottingRegisterController extends KnottingConstants {

	@Autowired
	private Environment environment;

	@Autowired
	private KnottingRegisterService knottingRegisterService;

	@Autowired
	private KnottingCommonService knottingCommonService;

	@Autowired
	private SendMessageUtil sendMessageToUser;

	@Autowired
	private SocialAuthenticationUtil socialAuthenticationUtil;

	@Autowired
	private MailerUtil mailerUtil;

	@Autowired
	private KnottingRegisterSocialValidator knottingRegisterSocialValidator;

	@Autowired
	private KnottingRegisterGeneralValidator knottingRegisterGeneralValidator;

	private final Logger slf4jLogger = LoggerFactory.getLogger(KnottingRegisterController.class);

	@RequestMapping("/register")
	public String handleRegisterPageRequest(HttpServletRequest request, ModelMap map) {

		slf4jLogger.debug("Entering function {}", "handleRegisterPageRequest");

		try{
			map.addAttribute("memberModel", new Member());

			if(request.getSession().getAttribute("success") != null){
				map.addAttribute("success", request.getSession().getAttribute("success"));
				request.getSession().removeAttribute("success");
			}
			else if(request.getSession().getAttribute("error") != null){
				map.addAttribute("error", request.getSession().getAttribute("error"));
				request.getSession().removeAttribute("error");
			}
			else if(request.getSession().getAttribute("info") != null){
				map.addAttribute("info", request.getSession().getAttribute("info"));
				request.getSession().removeAttribute("info");
			}
		}
		catch(Exception exception){
			request.getSession().setAttribute(errorMessage, environment.getProperty("exception_error_message"));
			mailerUtil.mailExceptionDetails("register", exception.getMessage(), ExceptionUtils.getFullStackTrace(exception), request.getSession().getAttribute(sessionUserId), request.getSession().getAttribute(sessionUserType));
			return "redirect:/";
		}

		slf4jLogger.debug("Exiting function {}", "handleRegisterPageRequest");

		return "register";
	}

	@RequestMapping("/saveMember")
	public String addMemberToOurSystem(@ModelAttribute("memberModel") Member member, BindingResult result, HttpServletRequest request, ModelMap map) {

		slf4jLogger.debug("Entering function {}", "addMemberToOurSystem");

		Long userId = null;

		try{
			knottingRegisterGeneralValidator.validate(member, result);

			if(result.hasErrors()) {
				request.getSession().setAttribute("error", result.getFieldErrors().get(0).getDefaultMessage());
				return "redirect:/";
			}
			else if(knottingCommonService.verifyIfEmailAlreadyExists(member.getEmailId())){
				request.getSession().setAttribute("error", environment.getProperty("register_email_already_exists"));
				return "redirect:/";
			}
			else{			
				userId = knottingRegisterService.addDirectLoginMember(member);
				request.getSession().setAttribute("userId", userId);
				if(member.getEmailId() != NULLSTRING && member.getEmailId() != BLANK){
					mailerUtil.sendWelcomeMail(member.getEmailId());
					mailerUtil.verifyEmail(verifyEmailForUser, member.getEmailId(), userId);
					request.getSession().setAttribute("info", "Verification email has been sent to "+member.getEmailId());

				}
			}
		}
		catch(Exception exception){
			request.getSession().setAttribute(errorMessage, environment.getProperty("exception_error_message"));
			mailerUtil.mailExceptionDetails("saveMember", exception.getMessage(), ExceptionUtils.getFullStackTrace(exception), request.getSession().getAttribute(sessionUserId), request.getSession().getAttribute(sessionUserType));
			return "redirect:/";
		}

		slf4jLogger.debug("Exiting function {}", "addMemberToOurSystem");

		return "redirect:profile";
	}

	@RequestMapping("/socialRegister")
	public String registerUsingSocialId(@RequestParam(value="id") String loginType, HttpServletRequest request, ModelMap map) {

		slf4jLogger.debug("Entering function {}", "registerUsingSocialId");

		String successUrl= environment.getProperty("socialauth_sucess_url_register");
		String url = null;

		try{
			url = socialAuthenticationUtil.getUrlForSocialAuthentication(loginType, successUrl, request.getSession());
		}
		catch(Exception exception){
			request.getSession().setAttribute(errorMessage, environment.getProperty("exception_error_message"));
			mailerUtil.mailExceptionDetails("socialRegister", exception.getMessage(), ExceptionUtils.getFullStackTrace(exception), request.getSession().getAttribute(sessionUserId), request.getSession().getAttribute(sessionUserType));
			return "redirect:/";
		}

		slf4jLogger.debug("Exiting function {}", "registerUsingSocialId");

		return "redirect:"+url;
	}

	@RequestMapping("/socialRegisterSuccess")
	public String retrieveDatFromSocialRegister(HttpServletRequest request, ModelMap map) {

		slf4jLogger.debug("Entering function {}", "retrieveDatFromSocialRegister");

		Long userId = null;

		try{			
			Profile profile = socialAuthenticationUtil.getProfileDataFromSocialNetwork(request);
			Member member = new Member();
			member.setName(profile.getFirstName()+" "+profile.getLastName());
			member.setEmailId(profile.getEmail());

			DataBinder binder = new DataBinder(member);
			binder.setValidator(knottingRegisterSocialValidator);
			binder.validate();
			BindingResult result = binder.getBindingResult();

			if(result.hasErrors()) {
				request.getSession().setAttribute("error", result.getFieldErrors().get(0).getDefaultMessage());
				return "redirect:/";
			}
			else if(knottingCommonService.verifyIfEmailAlreadyExists(member.getEmailId())){
				request.getSession().setAttribute("error", environment.getProperty("register_email_already_exists"));
				return "redirect:/";
			}
			else{				
				userId = knottingRegisterService.addSocialLoginMember(member);
				request.getSession().setAttribute(sessionUserId, userId);
				request.getSession().setAttribute(sessionUserType, member.getUserType());
				
				member = null;
			}
		}
		catch(Exception exception){
			request.getSession().setAttribute(errorMessage, environment.getProperty("exception_error_message"));
			mailerUtil.mailExceptionDetails("socialRegisterSuccess", exception.getMessage(), ExceptionUtils.getFullStackTrace(exception), request.getSession().getAttribute(sessionUserId), request.getSession().getAttribute(sessionUserType));
			return "redirect:/";
		}

		slf4jLogger.debug("Exiting function {}", "retrieveDatFromSocialRegister");

		return "redirect:profile";
	}

	@RequestMapping(value = "/sendOtp", method = RequestMethod.POST)
	public @ResponseBody String sendOtpforPhoneConfirmation(HttpServletRequest request, @RequestParam(value="phoneNumber") String phoneNumber){

		slf4jLogger.debug("Entering function {}", "sendOtpforPhoneConfirmation");

		String json = null;

		try{
			json = sendMessageToUser.sendOneTimePassword(phoneNumber);
		}
		catch(UnirestException exception){
			slf4jLogger.error("UnirestException in sendOtpforPhoneConfirmation function {}", exception.getMessage());
			exception.printStackTrace();
		}
		catch(Exception exception){
			request.getSession().setAttribute(errorMessage, environment.getProperty("exception_error_message"));
			mailerUtil.mailExceptionDetails("sendOtp", exception.getMessage(), ExceptionUtils.getFullStackTrace(exception), request.getSession().getAttribute(sessionUserId), request.getSession().getAttribute(sessionUserType));
		}

		slf4jLogger.debug("Exiting function {}", "sendOtpforPhoneConfirmation");

		return json;
	}

	@RequestMapping(value = "/sendOtpForEmergency", method = RequestMethod.POST)
	public @ResponseBody String sendOtpforEmergencyPhoneConfirmation(HttpServletRequest request, @RequestParam(value="phoneNumber") String phoneNumber){

		slf4jLogger.debug("Entering function {}", "sendOtpforEmergencyPhoneConfirmation");

		String json = null;

		try{
			json = sendMessageToUser.sendOneTimePasswordForEmergency(phoneNumber);
		}
		catch(UnirestException exception){
			slf4jLogger.error("UnirestException in sendOtpforEmergencyPhoneConfirmation function {}", exception.getMessage());
			exception.printStackTrace();
		}
		catch(Exception exception){
			request.getSession().setAttribute(errorMessage, environment.getProperty("exception_error_message"));
			mailerUtil.mailExceptionDetails("sendOtpForEmergency", exception.getMessage(), ExceptionUtils.getFullStackTrace(exception), request.getSession().getAttribute(sessionUserId), request.getSession().getAttribute(sessionUserType));
		}

		slf4jLogger.debug("Exiting function {}", "sendOtpforEmergencyPhoneConfirmation");

		return json;
	}

	@RequestMapping(value = "/sendOtpToTeam", method = RequestMethod.POST)
	public @ResponseBody String sendOtpforTeamMemberPhoneConfirmation(HttpServletRequest request, @RequestParam(value="phoneNumber") String phoneNumber){

		slf4jLogger.debug("Entering function {}", "sendOtpforTeamMemberPhoneConfirmation");

		String json = null;

		try{
			json = sendMessageToUser.sendOneTimePasswordToTeam(phoneNumber);
		}
		catch(UnirestException exception){
			slf4jLogger.error("UnirestException in sendOtpforTeamMemberPhoneConfirmation function {}", exception.getMessage());
			exception.printStackTrace();
		}
		catch(Exception exception){
			request.getSession().setAttribute(errorMessage, environment.getProperty("exception_error_message"));
			mailerUtil.mailExceptionDetails("sendOtpToTeam", exception.getMessage(), ExceptionUtils.getFullStackTrace(exception), request.getSession().getAttribute(sessionUserId), request.getSession().getAttribute(sessionUserType));
		}

		slf4jLogger.debug("Exiting function {}", "sendOtpforTeamMemberPhoneConfirmation");

		return json;
	}

	@RequestMapping(value = "/verifyOtp", method = RequestMethod.POST)
	public @ResponseBody String verifyOtpforPhoneRegistration(HttpServletRequest request, @RequestParam(value="twoFactorOtpKey") String twoFactorOtpKey, @RequestParam(value="twoFactorOtpValue") String twoFactorOtpValue){

		slf4jLogger.debug("Entering function {}", "verifyOtpforPhoneRegistration");

		String json = null;

		try{
			json = sendMessageToUser.verifyUsersOneTimePassword(twoFactorOtpKey, twoFactorOtpValue);
		}
		catch(UnirestException exception){
			slf4jLogger.error("UnirestException in verifyOtpforPhoneRegistration function {}", exception.getMessage());
			exception.printStackTrace();
		}
		catch(Exception exception){
			request.getSession().setAttribute(errorMessage, environment.getProperty("exception_error_message"));
			mailerUtil.mailExceptionDetails("verifyOtp", exception.getMessage(), ExceptionUtils.getFullStackTrace(exception), request.getSession().getAttribute(sessionUserId), request.getSession().getAttribute(sessionUserType));
		}

		slf4jLogger.debug("Exiting function {}", "verifyOtpforPhoneRegistration");

		return json;
	}

	@RequestMapping(value = "/checkPhoneAvailability", method = RequestMethod.POST)
	public @ResponseBody String checkPhoneAvailability(HttpServletRequest request, @RequestParam(value="phoneNumber") String phoneNumber){

		slf4jLogger.debug("Entering function {}", "checkPhoneAvailability");
		
		String numberAvailable = "AVL";
		String numberNotAvailable = "NTAVL";

		try{
			if(knottingCommonService.verifyIfPhoneAlreadyExists(Long.parseLong(phoneNumber))){
				return numberNotAvailable;
			}
		}
		catch(Exception exception){
			request.getSession().setAttribute(errorMessage, environment.getProperty("exception_error_message"));
			mailerUtil.mailExceptionDetails("checkPhoneAvailability", exception.getMessage(), ExceptionUtils.getFullStackTrace(exception), request.getSession().getAttribute(sessionUserId), request.getSession().getAttribute(sessionUserType));
		}

		slf4jLogger.debug("Exiting function {}", "checkPhoneAvailability");

		return numberAvailable;
	}

	@RequestMapping(value = "/checkEmailAvailability", method = RequestMethod.POST)
	public @ResponseBody String checkEmailAvailability(HttpServletRequest request, @RequestParam(value="emailId") String emailId){

		slf4jLogger.debug("Entering function {}", "checkEmailAvailability");

		String emailAvailable = "AVL";
		String emailNotAvailable = "NTAVL";

		try{
			if(knottingCommonService.verifyIfEmailAlreadyExists(emailId)){
				return emailNotAvailable;
			}
		}
		catch(Exception exception){
			request.getSession().setAttribute(errorMessage, environment.getProperty("exception_error_message"));
			mailerUtil.mailExceptionDetails("checkEmailAvailability", exception.getMessage(), ExceptionUtils.getFullStackTrace(exception), request.getSession().getAttribute(sessionUserId), request.getSession().getAttribute(sessionUserType));
		}

		slf4jLogger.debug("Exiting function {}", "checkEmailAvailability");

		return emailAvailable;
	}

}
