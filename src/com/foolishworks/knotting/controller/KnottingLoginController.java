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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.foolishworks.knotting.constants.KnottingConstants;
import com.foolishworks.knotting.entity.Member;
import com.foolishworks.knotting.intf.KnottingCommonService;
import com.foolishworks.knotting.intf.KnottingLoginService;
import com.foolishworks.knotting.utils.MailerUtil;
import com.foolishworks.knotting.utils.SendMessageUtil;
import com.foolishworks.knotting.utils.SocialAuthenticationUtil;
import com.foolishworks.knotting.validator.KnottingLoginValidator;

@Controller
@PropertySource({"classpath:display_message.properties"})
public class KnottingLoginController extends KnottingConstants {

	@Autowired
	private Environment environment;

	@Autowired
	private KnottingLoginService knottingLoginService;

	@Autowired
	private KnottingCommonService knottingCommonService;

	@Autowired
	private SocialAuthenticationUtil socialAuthenticationUtil;

	@Autowired
	private SendMessageUtil sendMessageToUser;

	@Autowired
	private KnottingLoginValidator knottingLoginValidator;

	@Autowired
	private MailerUtil mailerUtil;

	private final Logger slf4jLogger = LoggerFactory.getLogger(KnottingLoginController.class);

	@RequestMapping("/loginMember")
	public String checkAndLoginUser(@ModelAttribute("memberModel") Member member, BindingResult result, HttpServletRequest request, ModelMap map) {

		slf4jLogger.debug("Entering function {}", "checkAndLoginUser");

		try{
			knottingLoginValidator.validate(member, result);
			if(result.hasErrors()) {
				request.getSession().setAttribute(errorMessage, result.getFieldErrors().get(0).getDefaultMessage());
				return "redirect:/";
			}

			Member retrievedMember =  knottingLoginService.checkIfUserExist(member.getLoginUsername());
			if(retrievedMember != null && retrievedMember.getSocialAuth() != null && retrievedMember.getSocialAuth().equals(socialAuthNo)){
				retrievedMember = null;
				retrievedMember =  knottingLoginService.loginUser(member.getLoginUsername(), member.getUserPassword());
				if(retrievedMember == null){
					request.getSession().setAttribute(errorMessage, environment.getProperty("login_pwd_invalid"));
					return "redirect:/";
				}
				request.getSession().setAttribute(sessionUserId, retrievedMember.getMemberId());
				request.getSession().setAttribute(sessionUserType, retrievedMember.getUserType());

				retrievedMember = null;
			}
			else if(retrievedMember != null && retrievedMember.getSocialAuth() != null && retrievedMember.getSocialAuth().equals(socialAuthYes)){
				request.getSession().setAttribute(errorMessage, environment.getProperty("login_socialauth_member"));
				return "redirect:/";
			}
			else if(retrievedMember != null && (memberTypeSupport.equals(retrievedMember.getUserType()) || memberTypeAdmin.equals(retrievedMember.getUserType()))){
				retrievedMember = null;
				retrievedMember =  knottingLoginService.loginUser(member.getLoginUsername(), member.getUserPassword());
				if(retrievedMember == null){
					request.getSession().setAttribute(errorMessage, environment.getProperty("login_pwd_invalid"));
					return "redirect:/";
				}
				request.getSession().setAttribute(sessionUserId, retrievedMember.getMemberId());
				request.getSession().setAttribute(sessionUserType, retrievedMember.getUserType());
				
				retrievedMember = null;

				return "redirect:supportHome";
			}
			else{
				request.getSession().setAttribute(errorMessage, environment.getProperty("login_no_record"));
				return "redirect:/";
			}
		}
		catch(Exception exception){
			request.getSession().setAttribute(errorMessage, environment.getProperty("exception_error_message"));
			mailerUtil.mailExceptionDetails("loginMember", exception.getMessage(), ExceptionUtils.getFullStackTrace(exception), request.getSession().getAttribute(sessionUserId), request.getSession().getAttribute(sessionUserType));
			return "redirect:/";
		}

		slf4jLogger.debug("Exiting function {}", "checkAndLoginUser");

		return "redirect:profile";
	}

	@RequestMapping("/forgotPassword")
	public @ResponseBody String generateNewPasswordForUser(@RequestParam("phoneNumber") String phoneNumber, HttpServletRequest request, ModelMap map) {

		slf4jLogger.debug("Entering function {}", "generateNewPasswordForUser");
		
		String response = "";

		try{
			response = knottingLoginService.sendNewPasswordToUser(phoneNumber);
		}
		catch(Exception exception){
			request.getSession().setAttribute(errorMessage, environment.getProperty("exception_error_message"));
			mailerUtil.mailExceptionDetails("forgotPassword", exception.getMessage(), ExceptionUtils.getFullStackTrace(exception), request.getSession().getAttribute(sessionUserId), request.getSession().getAttribute(sessionUserType));
			response = "FAILURE:Something went wrong while generating a password for you";
		}

		slf4jLogger.debug("Exiting function {}", "generateNewPasswordForUser");

		return response;
	}

	@RequestMapping("/socialLogin")
	public String loginUsingSocialId(@RequestParam(value="id") String loginType, HttpServletRequest request, ModelMap map) {

		slf4jLogger.debug("Entering function {}", "loginUsingSocialId");

		String successUrl= environment.getProperty("socialauth_sucess_url_login");
		String url = null;

		try{
			url = socialAuthenticationUtil.getUrlForSocialAuthentication(loginType, successUrl, request.getSession());
		}
		catch(Exception exception){
			request.getSession().setAttribute(errorMessage, environment.getProperty("exception_error_message"));
			mailerUtil.mailExceptionDetails("socialLogin", exception.getMessage(), ExceptionUtils.getFullStackTrace(exception), request.getSession().getAttribute(sessionUserId), request.getSession().getAttribute(sessionUserType));
			return "redirect:/";
		}

		slf4jLogger.debug("Exiting function {}", "loginUsingSocialId");

		return "redirect:"+url;
	}

	@RequestMapping("/socialLoginSuccess")
	public String retrieveDatFromSocialLogin(HttpServletRequest request, ModelMap map) {

		slf4jLogger.debug("Entering function {}", "retrieveDatFromSocialLogin");

		try{			
			Profile profile = socialAuthenticationUtil.getProfileDataFromSocialNetwork(request);
			Member member = knottingCommonService.fetchMemberRecord(null, profile.getEmail(), null);
			if(member == null){
				request.getSession().setAttribute(errorMessage, environment.getProperty("login_no_record"));
				return "redirect:/";
			}
			request.getSession().setAttribute(sessionUserId, member.getMemberId());
			request.getSession().setAttribute(sessionUserType, member.getUserType());
			
			member = null;
		}
		catch(Exception exception){
			request.getSession().setAttribute(errorMessage, environment.getProperty("exception_error_message"));
			mailerUtil.mailExceptionDetails("socialLoginSuccess", exception.getMessage(), ExceptionUtils.getFullStackTrace(exception), request.getSession().getAttribute(sessionUserId), request.getSession().getAttribute(sessionUserType));
			return "redirect:/";
		}

		slf4jLogger.debug("Exiting function {}", "retrieveDatFromSocialLogin");

		return "redirect:profile";
	}

	@RequestMapping("/wanderingUser")
	public String wanderingUser(HttpServletRequest request, ModelMap map) {

		slf4jLogger.debug("Entering function {}", "wanderingUser");

		try{
			System.out.println("Wandering User");
		}
		catch(Exception exception){
			request.getSession().setAttribute(errorMessage, environment.getProperty("exception_error_message"));
			mailerUtil.mailExceptionDetails("wanderingUser", exception.getMessage(), ExceptionUtils.getFullStackTrace(exception), request.getSession().getAttribute(sessionUserId), request.getSession().getAttribute(sessionUserType));
			return "redirect:/";
		}

		slf4jLogger.debug("Exiting function {}", "wanderingUser");

		return "wanderingUser";
	}

	@RequestMapping("/logoutUser")
	public String logoutUser(HttpServletRequest request, ModelMap map) {

		slf4jLogger.debug("Entering function {}", "logoutUser");

		try{
			request.getSession().invalidate();
		}
		catch(Exception exception){
			request.getSession().setAttribute(errorMessage, environment.getProperty("exception_error_message"));
			mailerUtil.mailExceptionDetails("logoutUser", exception.getMessage(), ExceptionUtils.getFullStackTrace(exception), request.getSession().getAttribute(sessionUserId), request.getSession().getAttribute(sessionUserType));
			return "redirect:/";
		}

		slf4jLogger.debug("Exiting function {}", "logoutUser");

		return "redirect:/";
	}

}
