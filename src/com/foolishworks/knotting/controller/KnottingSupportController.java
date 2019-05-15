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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.foolishworks.knotting.constants.KnottingConstants;
import com.foolishworks.knotting.entity.Coupon;
import com.foolishworks.knotting.entity.Member;
import com.foolishworks.knotting.intf.KnottingSupportService;
import com.foolishworks.knotting.utils.MailerUtil;

@Controller
@PropertySource({"classpath:display_message.properties"})
public class KnottingSupportController extends KnottingConstants {

	@Autowired
	private Environment environment;

	@Autowired
	private KnottingSupportService knottingSupportService;

	@Autowired
	private MailerUtil mailerUtil;

	private final Logger slf4jLogger = LoggerFactory.getLogger(KnottingSupportController.class);

	@RequestMapping("supportHome")
	public String supportHome(HttpServletRequest request, ModelMap map) {

		slf4jLogger.debug("Entering function {}", "supportHome");

		try {
			if(request.getSession().getAttribute(sessionUserId) == null || request.getSession().getAttribute(sessionUserType) == null){
				request.getSession().setAttribute(errorMessage, "Your session got expired. Kindly login again");
				return "redirect:/";
			}

			String userType = (String)request.getSession().getAttribute(sessionUserType);

			if(memberTypeSupport.equals(userType) || memberTypeAdmin.equals(userType)){

			}
			else{
				request.getSession().setAttribute(errorMessage, "You do not have access to this page.");
				return "redirect:/";
			}
		}
		catch(Exception exception){
			request.getSession().setAttribute(errorMessage, environment.getProperty("exception_error_message"));
			mailerUtil.mailExceptionDetails("supportHome", exception.getMessage(), ExceptionUtils.getFullStackTrace(exception), request.getSession().getAttribute(sessionUserId), request.getSession().getAttribute(sessionUserType));
			return "redirect:supportHome";
		}

		slf4jLogger.debug("Exiting function {}", "supportHome");

		return "redirect:supportEntries";
	}

	@RequestMapping("supportEntries")
	public String supportEntries(HttpServletRequest request, ModelMap map) {

		slf4jLogger.debug("Entering function {}", "supportEntries");

		try {
			if(request.getSession().getAttribute(sessionUserId) == null || request.getSession().getAttribute(sessionUserType) == null){
				request.getSession().setAttribute(errorMessage, "Your session got expired. Kindly login again");
				return "redirect:/";
			}

			String userType = (String)request.getSession().getAttribute(sessionUserType);

			if(memberTypeSupport.equals(userType) || memberTypeAdmin.equals(userType)){
				map.addAttribute("pendingEntries", knottingSupportService.fetchPendingEntries());
			}
			else{
				request.getSession().setAttribute(errorMessage, "You do not have access to this page.");
				return "redirect:/";
			}
		}
		catch(Exception exception){
			request.getSession().setAttribute(errorMessage, environment.getProperty("exception_error_message"));
			mailerUtil.mailExceptionDetails("supportEntries", exception.getMessage(), ExceptionUtils.getFullStackTrace(exception), request.getSession().getAttribute(sessionUserId), request.getSession().getAttribute(sessionUserType));
			return "redirect:supportHome";
		}

		slf4jLogger.debug("Exiting function {}", "supportEntries");

		return "supportEntries";
	}

	@RequestMapping("supportEntryDetails")
	public String supportEntryDetails(HttpServletRequest request, ModelMap map, @RequestParam("id") Long entryId) {

		slf4jLogger.debug("Entering function {}", "supportEntryDetails");

		try {
			if(request.getSession().getAttribute(sessionUserId) == null || request.getSession().getAttribute(sessionUserType) == null){
				request.getSession().setAttribute(errorMessage, "Your session got expired. Kindly login again");
				return "redirect:/";
			}

			String userType = (String)request.getSession().getAttribute(sessionUserType);

			if(memberTypeSupport.equals(userType) || memberTypeAdmin.equals(userType)){
				map.addAttribute("pendingEntry", knottingSupportService.fetchPendingEntry(entryId));
			}
			else{
				request.getSession().setAttribute(errorMessage, "You do not have access to this page.");
				return "redirect:/";
			}
		}
		catch(Exception exception){
			request.getSession().setAttribute(errorMessage, environment.getProperty("exception_error_message"));
			mailerUtil.mailExceptionDetails("supportEntryDetails", exception.getMessage(), ExceptionUtils.getFullStackTrace(exception), request.getSession().getAttribute(sessionUserId), request.getSession().getAttribute(sessionUserType));
			return "redirect:supportHome";
		}

		slf4jLogger.debug("Exiting function {}", "supportEntryDetails");

		return "supportEntryDetails";
	}

	@RequestMapping("applyAction")
	public String applyAction(HttpServletRequest request, ModelMap map, @RequestParam("id") Long entryId, @RequestParam("actionType") String actionType, @RequestParam(name="rejectionDetail", required=false) String rejectionDetail) {

		slf4jLogger.debug("Entering function {}", "applyAction");

		try{
			if(request.getSession().getAttribute(sessionUserId) == null || request.getSession().getAttribute(sessionUserType) == null){
				request.getSession().setAttribute(errorMessage, "Your session got expired. Kindly login again");
				return "redirect:/";
			}

			String userType = (String)request.getSession().getAttribute(sessionUserType);

			if(memberTypeSupport.equals(userType) || memberTypeAdmin.equals(userType)){
				if(knottingSupportService.applyActionOnStagingRecord(request, entryId, actionType, rejectionDetail)){
					request.getSession().setAttribute(successMessage, "Successfully saved.");
				}
			}
			else{
				request.getSession().setAttribute(errorMessage, "You do not have access to this page.");
				return "redirect:/";
			}
		}
		catch(Exception exception){
			request.getSession().setAttribute(errorMessage, environment.getProperty("exception_error_message"));
			mailerUtil.mailExceptionDetails("applyAction", exception.getMessage(), ExceptionUtils.getFullStackTrace(exception), request.getSession().getAttribute(sessionUserId), request.getSession().getAttribute(sessionUserType));
			return "redirect:supportHome";
		}

		slf4jLogger.debug("Exiting function {}", "applyAction");

		return "redirect:supportEntries";
	}

	@RequestMapping("supportCoupons")
	public String supportCoupons(HttpServletRequest request, ModelMap map) {

		slf4jLogger.debug("Entering function {}", "supportCoupons");

		try {
			if(request.getSession().getAttribute(sessionUserId) == null || request.getSession().getAttribute(sessionUserType) == null){
				request.getSession().setAttribute(errorMessage, "Your session got expired. Kindly login again");
				return "redirect:/";
			}

			String userType = (String)request.getSession().getAttribute(sessionUserType);

			if(memberTypeSupport.equals(userType) || memberTypeAdmin.equals(userType)){
				List<Coupon> coupons = knottingSupportService.fetchCoupons();
				map.addAttribute("coupons", coupons);
			}
			else{
				request.getSession().setAttribute(errorMessage, "You do not have access to this page.");
				return "redirect:/";
			}
		}
		catch(Exception exception){
			request.getSession().setAttribute(errorMessage, environment.getProperty("exception_error_message"));
			mailerUtil.mailExceptionDetails("supportCoupons", exception.getMessage(), ExceptionUtils.getFullStackTrace(exception), request.getSession().getAttribute(sessionUserId), request.getSession().getAttribute(sessionUserType));
			return "redirect:supportHome";
		}

		slf4jLogger.debug("Exiting function {}", "supportCoupons");

		return "supportCoupons";
	}

	@RequestMapping("changeCouponStatus")
	public String changeCouponStatus(HttpServletRequest request, ModelMap map, @RequestParam("id") Long couponId, @RequestParam("status") String status) {

		slf4jLogger.debug("Entering function {}", "changeCouponStatus");

		try {
			if(request.getSession().getAttribute(sessionUserId) == null || request.getSession().getAttribute(sessionUserType) == null){
				request.getSession().setAttribute(errorMessage, "Your session got expired. Kindly login again");
				return "redirect:/";
			}

			String userType = (String)request.getSession().getAttribute(sessionUserType);

			if(memberTypeSupport.equals(userType) || memberTypeAdmin.equals(userType)){
				
			}
			else{
				request.getSession().setAttribute(errorMessage, "You do not have access to this page.");
				return "redirect:/";
			}
		}
		catch(Exception exception){
			request.getSession().setAttribute(errorMessage, environment.getProperty("exception_error_message"));
			mailerUtil.mailExceptionDetails("supportCoupons", exception.getMessage(), ExceptionUtils.getFullStackTrace(exception), request.getSession().getAttribute(sessionUserId), request.getSession().getAttribute(sessionUserType));
			return "redirect:supportHome";
		}

		slf4jLogger.debug("Exiting function {}", "changeCouponStatus");

		return "supportCoupons";
	}

	@RequestMapping("supportMembers")
	public String supportMembers(HttpServletRequest request, ModelMap map) {

		slf4jLogger.debug("Entering function {}", "supportMembers");

		try {
			if(request.getSession().getAttribute(sessionUserId) == null || request.getSession().getAttribute(sessionUserType) == null){
				request.getSession().setAttribute(errorMessage, "Your session got expired. Kindly login again");
				return "redirect:/";
			}

			String userType = (String)request.getSession().getAttribute(sessionUserType);

			if(memberTypeSupport.equals(userType) || memberTypeAdmin.equals(userType)){
				List<Member> members = knottingSupportService.fetchMembers();
				map.addAttribute("members", members);
			}
			else{
				request.getSession().setAttribute(errorMessage, "You do not have access to this page.");
				return "redirect:/";
			}
		}
		catch(Exception exception){
			request.getSession().setAttribute(errorMessage, environment.getProperty("exception_error_message"));
			mailerUtil.mailExceptionDetails("supportMembers", exception.getMessage(), ExceptionUtils.getFullStackTrace(exception), request.getSession().getAttribute(sessionUserId), request.getSession().getAttribute(sessionUserType));
			return "redirect:supportHome";
		}

		slf4jLogger.debug("Exiting function {}", "supportMembers");

		return "supportMembers";
	}
}
