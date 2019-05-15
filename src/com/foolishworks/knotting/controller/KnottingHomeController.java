package com.foolishworks.knotting.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.json.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.foolishworks.knotting.constants.KnottingConstants;
import com.foolishworks.knotting.entity.Member;
import com.foolishworks.knotting.entity.MemberServices;
import com.foolishworks.knotting.intf.KnottingCommonService;
import com.foolishworks.knotting.intf.KnottingHomeService;
import com.foolishworks.knotting.intf.KnottingResultService;
import com.foolishworks.knotting.intf.KnottingServicesService;
import com.foolishworks.knotting.utils.CloudinaryImageUtil;
import com.foolishworks.knotting.utils.GeneralUtils;
import com.foolishworks.knotting.utils.MailerUtil;

@Controller
@PropertySource("classpath:display_message.properties")
public class KnottingHomeController extends KnottingConstants {

	private final Logger slf4jLogger = LoggerFactory.getLogger(KnottingHomeController.class);

	@Autowired
	private Environment environment;

	@Autowired
	private KnottingHomeService knottingHomeService;

	@Autowired
	private KnottingServicesService knottingServicesService;

	@Autowired
	private KnottingCommonService knottingCommonService;

	@Autowired
	private KnottingResultService knottingResultService;

	@Autowired
	private CloudinaryImageUtil cloudinaryImageUtil;

	@Autowired
	private GeneralUtils generalUtils;

	@Autowired
	private MailerUtil mailerUtil;

	@RequestMapping("/")
	public String handleHomePageRequest(HttpServletRequest request, ModelMap map) {

		slf4jLogger.debug("Entering function {}", "handleHomePageRequest");

		try{
			if(request.getSession().getAttribute(errorMessage) != null){
				map.addAttribute(errorMessage, request.getSession().getAttribute(errorMessage));
				request.getSession().removeAttribute(errorMessage);
			}
			if(request.getSession().getAttribute(successMessage) != null){
				map.addAttribute(successMessage, request.getSession().getAttribute(successMessage));
				request.getSession().removeAttribute(successMessage);
			}

			List<MemberServices> activeServices = knottingHomeService.fetchActiveServices();
			List<MemberServices> recentServices = knottingHomeService.fetchRecentlyAddedServices();

			/* change image url to transformed image url */
			for(MemberServices memberServices : recentServices){
				memberServices.setImagePath1Thumbnail(cloudinaryImageUtil.thumbnailUrl(memberServices.getImagePath1()));
			}

			/* Menu component. Do not delete*/
			map.addAttribute("allServices", knottingServicesService.fetchAvailableListOfServices());

			map.addAttribute("memberModel", new Member());
			map.addAttribute("serviceList", activeServices);
			map.addAttribute("recents", recentServices);
			map.addAttribute("homeBanner", knottingCommonService.fetchConfigurationByCode(configurationHomeBanner));
			map.addAttribute("displayMostViewed", knottingCommonService.fetchConfigurationByCode(configurationHomeDisplayMostViewedSection));
			map.addAttribute("displayRecents", knottingCommonService.fetchConfigurationByCode(configurationHomeDisplayRecentsSection));
			map.addAttribute("cheapestSubscription", knottingHomeService.fetchCheapestSubscriptionRate());

			List<Long> servicesId = generalUtils.convertStringToLongList(knottingCommonService.fetchConfigurationByCode(configurationHomeMostViewedList).getConfigField1());
			map.addAttribute("freqServices", knottingHomeService.fetchServicesById(servicesId));
		}
		catch(Exception exception){
			request.getSession().setAttribute(errorMessage, environment.getProperty("exception_error_message"));
			mailerUtil.mailExceptionDetails("home", exception.getMessage(), ExceptionUtils.getFullStackTrace(exception), request.getSession().getAttribute(sessionUserId), request.getSession().getAttribute(sessionUserType));
			return "redirect:/";
		}

		slf4jLogger.debug("Exiting function {}", "handleHomePageRequest");

		return "home";
	}

	@RequestMapping("/fetchCityListForFilter")
	public @ResponseBody String fetchCityListForFilter(HttpServletRequest request, @RequestParam(value = "service", required = true) String service) {

		slf4jLogger.debug("Entering function {}", "fetchCityListForFilter");

		JSONArray subscriptionDetails = null;

		try{
			Long serviceId = knottingResultService.fetchServiceIdByCode(service);

			subscriptionDetails = new JSONArray(knottingHomeService.fetchCitiesList(serviceId));
		}
		catch(Exception exception){
			request.getSession().setAttribute(errorMessage, environment.getProperty("exception_error_message"));
			mailerUtil.mailExceptionDetails("fetchCityListForFilter", exception.getMessage(), ExceptionUtils.getFullStackTrace(exception), request.getSession().getAttribute(sessionUserId), request.getSession().getAttribute(sessionUserType));
		}

		slf4jLogger.debug("Exiting function {}", "fetchCityListForFilter");

		return subscriptionDetails.toString();

	}

	@RequestMapping("/fetchRangeListForFilter")
	public @ResponseBody String fetchRangeListForFilter(HttpServletRequest request, @RequestParam(value = "service", required = true) String service, @RequestParam(value = "city", required = true) String city) {

		slf4jLogger.debug("Entering function {}", "fetchRangeListForFilter");

		JSONArray rangeDetails = null;

		try{
			Long serviceId = knottingResultService.fetchServiceIdByCode(service);

			rangeDetails = new JSONArray(knottingHomeService.fetchRangeList(serviceId, city));
		}
		catch(Exception exception){
			request.getSession().setAttribute(errorMessage, environment.getProperty("exception_error_message"));
			mailerUtil.mailExceptionDetails("fetchRangeListForFilter", exception.getMessage(), ExceptionUtils.getFullStackTrace(exception), request.getSession().getAttribute(sessionUserId), request.getSession().getAttribute(sessionUserType));
		}

		slf4jLogger.debug("Exiting function {}", "fetchRangeListForFilter");

		return rangeDetails.toString();

	}
}
