package com.foolishworks.knotting.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.foolishworks.knotting.constants.KnottingConstants;
import com.foolishworks.knotting.entity.Member;
import com.foolishworks.knotting.intf.KnottingServicesService;
import com.foolishworks.knotting.utils.MailerUtil;

@Controller
@PropertySource("classpath:display_message.properties")
public class KnottingExtrasController extends KnottingConstants {

	private final Logger slf4jLogger = LoggerFactory.getLogger(KnottingExtrasController.class);

	@Autowired
	private Environment environment;

	@Autowired
	private KnottingServicesService knottingServicesService;

	@Autowired
	private MailerUtil mailerUtil;

	@RequestMapping("privacy")
	public String privacyPolicyScreen(HttpServletRequest request, ModelMap map) {

		slf4jLogger.debug("Entering function {}", "privacyPolicyScreen");

		try{
			/* Menu component. Do not delete*/
			map.addAttribute("allServices", knottingServicesService.fetchAvailableListOfServices());
			map.addAttribute("memberModel", new Member());
		}
		catch(Exception exception){
			request.getSession().setAttribute(errorMessage, environment.getProperty("exception_error_message"));
			mailerUtil.mailExceptionDetails("privacy", exception.getMessage(), ExceptionUtils.getFullStackTrace(exception), request.getSession().getAttribute(sessionUserId), request.getSession().getAttribute(sessionUserType));
			return "redirect:/";
		}

		slf4jLogger.debug("Exiting function {}", "privacyPolicyScreen");

		return "privacy";
	}

	@RequestMapping("terms")
	public String termsScreen(HttpServletRequest request, ModelMap map) {

		slf4jLogger.debug("Entering function {}", "termsScreen");

		try{
			/* Menu component. Do not delete*/
			map.addAttribute("allServices", knottingServicesService.fetchAvailableListOfServices());
			map.addAttribute("memberModel", new Member());
		}
		catch(Exception exception){
			request.getSession().setAttribute(errorMessage, environment.getProperty("exception_error_message"));
			mailerUtil.mailExceptionDetails("terms", exception.getMessage(), ExceptionUtils.getFullStackTrace(exception), request.getSession().getAttribute(sessionUserId), request.getSession().getAttribute(sessionUserType));
			return "redirect:/";
		}

		slf4jLogger.debug("Exiting function {}", "termsScreen");

		return "terms";
	}

	@RequestMapping("contact")
	public String contactScreen(HttpServletRequest request, ModelMap map) {

		slf4jLogger.debug("Entering function {}", "contactScreen");

		try{
			/* Menu component. Do not delete*/
			map.addAttribute("allServices", knottingServicesService.fetchAvailableListOfServices());
			map.addAttribute("memberModel", new Member());
		}
		catch(Exception exception){
			request.getSession().setAttribute(errorMessage, environment.getProperty("exception_error_message"));
			mailerUtil.mailExceptionDetails("contact", exception.getMessage(), ExceptionUtils.getFullStackTrace(exception), request.getSession().getAttribute(sessionUserId), request.getSession().getAttribute(sessionUserType));
			return "redirect:/";
		}

		slf4jLogger.debug("Exiting function {}", "contactScreen");

		return "contact";
	}

	@RequestMapping("team")
	public String teamScreen(HttpServletRequest request, ModelMap map) {

		slf4jLogger.debug("Entering function {}", "teamScreen");

		try{
			/* Menu component. Do not delete*/
			map.addAttribute("allServices", knottingServicesService.fetchAvailableListOfServices());
			map.addAttribute("memberModel", new Member());
		}
		catch(Exception exception){
			request.getSession().setAttribute(errorMessage, environment.getProperty("exception_error_message"));
			mailerUtil.mailExceptionDetails("team", exception.getMessage(), ExceptionUtils.getFullStackTrace(exception), request.getSession().getAttribute(sessionUserId), request.getSession().getAttribute(sessionUserType));
			return "redirect:/";
		}

		slf4jLogger.debug("Exiting function {}", "teamScreen");

		return "team";
	}

	@RequestMapping("emergency")
	public String emergencyScreen(HttpServletRequest request, ModelMap map) {

		slf4jLogger.debug("Entering function {}", "emergencyScreen");

		try{
			/* Menu component. Do not delete*/
			map.addAttribute("allServices", knottingServicesService.fetchAvailableListOfServices());
			map.addAttribute("memberModel", new Member());
		}
		catch(Exception exception){
			request.getSession().setAttribute(errorMessage, environment.getProperty("exception_error_message"));
			mailerUtil.mailExceptionDetails("emergency", exception.getMessage(), ExceptionUtils.getFullStackTrace(exception), request.getSession().getAttribute(sessionUserId), request.getSession().getAttribute(sessionUserType));
			return "redirect:/";
		}

		slf4jLogger.debug("Exiting function {}", "emergencyScreen");

		return "emergency";
	}
}
