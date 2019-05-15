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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.foolishworks.knotting.constants.KnottingConstants;
import com.foolishworks.knotting.entity.Member;
import com.foolishworks.knotting.entity.MemberServices;
import com.foolishworks.knotting.entity.ServiceDetails;
import com.foolishworks.knotting.intf.KnottingServicesService;
import com.foolishworks.knotting.intf.KnottingVendorServiceService;
import com.foolishworks.knotting.utils.CloudinaryImageUtil;
import com.foolishworks.knotting.utils.MailerUtil;

@Controller
@PropertySource({"classpath:display_message.properties"})
public class KnottingVendorServiceController extends KnottingConstants {

	@Autowired
	private Environment environment;

	private final Logger slf4jLogger = LoggerFactory.getLogger(KnottingVendorServiceController.class);

	@Autowired
	private KnottingServicesService knottingServicesService;

	@Autowired
	private KnottingVendorServiceService knottingVendorServiceService;

	@Autowired
	private CloudinaryImageUtil cloudinaryImageUtil;

	@Autowired
	private MailerUtil mailerUtil;

	@RequestMapping("/details/{who}")
	public String displayVendorDetails(HttpServletRequest request, ModelMap map, @PathVariable(value="who", required=true) String serviceCode) {

		slf4jLogger.debug("Entering function {}", "displayVendorDetails");

		try {
			
			if(request.getSession().getAttribute("error") != null){
				map.addAttribute("error", request.getSession().getAttribute("error"));
				request.getSession().removeAttribute("error");
			}
			if(request.getSession().getAttribute("success") != null){
				map.addAttribute("success", request.getSession().getAttribute("success"));
				request.getSession().removeAttribute("success");
			}

			MemberServices memberServices = knottingVendorServiceService.fetchMemberService(serviceCode);

			if(memberServices != null){
				/* change image url to transformed image url */
				knottingServicesService.prepareThumbnailImage(memberServices);
				
				//calculate days since creation
				knottingServicesService.calculateDaysSinceCreation(memberServices.getMemberEntries());

				List<ServiceDetails> serviceDetails = knottingVendorServiceService.fetchServiceDetails(memberServices.getServices().getServicesId());

				/* Menu component. Do not delete*/
				map.addAttribute("allServices", knottingServicesService.fetchAvailableListOfServices());
				map.addAttribute("memberModel", new Member());
				map.addAttribute("service", memberServices);		
				map.addAttribute("servicesQuestions", serviceDetails);
				
				/* Page component - Do not delete */
				map.addAttribute("title", memberServices.getMemberEntries().getName());	
				map.addAttribute("description", knottingVendorServiceService.buildVendorDescription(memberServices));				
				if(memberServices.getImagePath1Thumbnail() != null){
					map.addAttribute("favicon", memberServices.getImagePath1Thumbnail());
				}
			}
			else{
				request.getSession().setAttribute("error", "Could not find the vendor.");
				return "redirect:/";
			}
		}
		catch(Exception exception){
			request.getSession().setAttribute(errorMessage, environment.getProperty("exception_error_message"));
			mailerUtil.mailExceptionDetails("vendorService", exception.getMessage(), ExceptionUtils.getFullStackTrace(exception), request.getSession().getAttribute(sessionUserId), request.getSession().getAttribute(sessionUserType));
			return "redirect:/";
		}

		slf4jLogger.debug("Exiting function {}", "displayVendorDetails");

		return "vendorService";
	}

	@RequestMapping("/sendServiceUrlToUser")
	public @ResponseBody String sendServiceUrlToUser(HttpServletRequest request, @RequestParam(value = "from", required = true) String from, @RequestParam(value = "to", required = true) String to, @RequestParam(value = "serviceId", required = true) String serviceId) {

		slf4jLogger.debug("Entering function {}", "sendServiceUrlToUser");

		String successStatus = null;
		
		try {
			knottingVendorServiceService.sendServiceUrlToUser(from, to, serviceId);
		}
		catch(Exception exception){
			request.getSession().setAttribute(errorMessage, environment.getProperty("exception_error_message"));
			mailerUtil.mailExceptionDetails("sendServiceUrlToUser", exception.getMessage(), ExceptionUtils.getFullStackTrace(exception), request.getSession().getAttribute(sessionUserId), request.getSession().getAttribute(sessionUserType));
		}

		slf4jLogger.debug("Exiting function {}", "sendServiceUrlToUser");

		return successStatus;

	}

}
