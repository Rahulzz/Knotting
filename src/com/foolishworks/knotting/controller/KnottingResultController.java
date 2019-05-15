package com.foolishworks.knotting.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ObjectWriter;
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
import com.foolishworks.knotting.entity.ServiceDetails;
import com.foolishworks.knotting.intf.KnottingHomeService;
import com.foolishworks.knotting.intf.KnottingResultService;
import com.foolishworks.knotting.intf.KnottingServicesService;
import com.foolishworks.knotting.utils.MailerUtil;

@Controller
@PropertySource({"classpath:display_message.properties"})
public class KnottingResultController extends KnottingConstants {

	@Autowired
	private Environment environment;

	@Autowired
	private KnottingResultService knottingResultService;

	@Autowired
	private KnottingServicesService knottingServicesService;

	@Autowired
	private KnottingHomeService knottingHomeService;

	@Autowired
	private MailerUtil mailerUtil;

	private final Logger slf4jLogger = LoggerFactory.getLogger(KnottingResultController.class);

	@RequestMapping("/result")
	public String displayResultPage(HttpServletRequest request, ModelMap map, @RequestParam(value="service", required=true) String serviceCode, @RequestParam(value="city", required=true) String city, @RequestParam(value="range", required=true) String range, @RequestParam(value="urgency", required=true) String urgency, @RequestParam(value="filter", required=false) String filter, @RequestParam(value="sort", required=false) String sort) {

		slf4jLogger.debug("Entering function {}", "displayResultPage");

		try{
			Long serviceId = knottingResultService.fetchServiceIdByCode(serviceCode);

			if(serviceId != null){
				Long vendorCount = knottingResultService.totalVendorCount(serviceId, city, range, urgency, filter);
				List<MemberServices> memberServicesList = fetchMemberServiceList(serviceId, city, range, urgency, filter, sort, 0, maxResultPerFetch);
				List<MemberServices> activeServices = knottingHomeService.fetchActiveServices();
				List<ServiceDetails> serviceDetails = knottingResultService.fetchServiceDetails(serviceId, memberServicesList);

				/* Menu component. Do not delete*/
				map.addAttribute("allServices", knottingServicesService.fetchAvailableListOfServices());

				map.addAttribute("memberModel", new Member());
				map.addAttribute("resultServices", memberServicesList);
				map.addAttribute("serviceDetails", knottingResultService.fetchServiceRecord(serviceId));
				map.addAttribute("totalRecords", vendorCount);
				map.addAttribute("firstRecord", 0);
				map.addAttribute("lastRecord", memberServicesList.size());

				map.addAttribute("criteriaService", serviceCode);
				map.addAttribute("criteriaCity", city);
				map.addAttribute("criteriaRange", knottingResultService.priceRangeTitle(serviceId, range));
				map.addAttribute("criteriaUrgency", urgency);
				map.addAttribute("criteriaFilter", filter);
				map.addAttribute("badgeCount", (filter != null && !filterUndefined.equals(filter))?filter.split(";").length:null);
				map.addAttribute("servicesList", activeServices);
				map.addAttribute("citiesList", knottingHomeService.fetchCitiesList(serviceId));
				map.addAttribute("rangeList", knottingHomeService.fetchRangeList(serviceId, city));
				map.addAttribute("filterList", serviceDetails);	
			}
			else{
				request.getSession().setAttribute("error", "Invalid Url");
				return "redirect:/";
			}
		}
		catch(Exception exception){
			request.getSession().setAttribute(errorMessage, environment.getProperty("exception_error_message"));
			mailerUtil.mailExceptionDetails("result", exception.getMessage(), ExceptionUtils.getFullStackTrace(exception), request.getSession().getAttribute(sessionUserId), request.getSession().getAttribute(sessionUserType));
			return "redirect:/";
		}

		slf4jLogger.debug("Exiting function {}", "displayResultPage");

		return "result";
	}

	@RequestMapping("/serviceResult")
	public String displayServiceResultPage(HttpServletRequest request, ModelMap map, @RequestParam(value="service", required=true) String serviceCode, @RequestParam(value="filter", required=false) String filter, @RequestParam(value="sort", required=false) String sort) {

		slf4jLogger.debug("Entering function {}", "displayServiceResultPage");

		try{
			Long serviceId = knottingResultService.fetchServiceIdByCode(serviceCode);

			if(serviceId != null){			
				Long vendorCount = knottingResultService.totalVendorCount(serviceId, "", "", "", filter);
				List<MemberServices> memberServicesList = fetchMemberServiceList(serviceId, "", "", "", filter, sort, 0, maxResultPerFetch);
				List<ServiceDetails> serviceDetails = knottingResultService.fetchServiceDetails(serviceId, memberServicesList);

				/* Menu component. Do not delete*/
				map.addAttribute("allServices", knottingServicesService.fetchAvailableListOfServices());

				map.addAttribute("memberModel", new Member());
				map.addAttribute("serviceList", memberServicesList);
				map.addAttribute("serviceDetails", knottingResultService.fetchServiceRecord(serviceId));
				map.addAttribute("totalRecords", vendorCount);
				map.addAttribute("firstRecord", 0);
				map.addAttribute("lastRecord", memberServicesList.size());
				map.addAttribute("criteriaFilter", filter);
				map.addAttribute("badgeCount", (filter != null && !filterUndefined.equals(filter))?filter.split(";").length:null);
				map.addAttribute("filterList", serviceDetails);	
			}
			else{
				request.getSession().setAttribute("error", "Invalid Url");
				return "redirect:/";
			}
		}
		catch(Exception exception){
			request.getSession().setAttribute(errorMessage, environment.getProperty("exception_error_message"));
			mailerUtil.mailExceptionDetails("serviceResult", exception.getMessage(), ExceptionUtils.getFullStackTrace(exception), request.getSession().getAttribute(sessionUserId), request.getSession().getAttribute(sessionUserType));
			return "redirect:/";
		}

		slf4jLogger.debug("Exiting function {}", "displayServiceResultPage");

		return "serviceResult";
	}

	@RequestMapping("/fetchNextPageResult")
	public @ResponseBody String fetchNextPageResult(HttpServletRequest request, @RequestParam("service") String service, @RequestParam(value="city", required=false) String city, @RequestParam(value="range", required=false) String range, @RequestParam(value="urgency", required=false) String urgency, @RequestParam(value="filter", required=false) String filter, @RequestParam(value="sort", required=false) String sort, @RequestParam("totalRecords") String totalRecordsParam, @RequestParam("firstRecord") String firstRecordParam) {

		slf4jLogger.debug("Entering function {}", "fetchNextPageResult");

		String vendorList = null;

		try {
			Long serviceId = Long.valueOf(service);
			int totalRecords = Integer.parseInt(totalRecordsParam);
			int firstRecord = Integer.parseInt(firstRecordParam);
			int lastRecord = ((firstRecord + maxResultPerFetch) > totalRecords)?totalRecords:(firstRecord + maxResultPerFetch);

			List<MemberServices> memberServicesList = fetchMemberServiceList(serviceId, city, range, urgency, (!filterUndefined.equals(filter)?filter:null), (!filterUndefined.equals(sort)?sort:null), firstRecord, lastRecord);

			ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();

			vendorList = ow.writeValueAsString(memberServicesList);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		catch(Exception exception){
			request.getSession().setAttribute(errorMessage, environment.getProperty("exception_error_message"));
			mailerUtil.mailExceptionDetails("fetchNextPageResult", exception.getMessage(), ExceptionUtils.getFullStackTrace(exception), request.getSession().getAttribute(sessionUserId), request.getSession().getAttribute(sessionUserType));
		}

		slf4jLogger.debug("Exiting function {}", "fetchNextPageResult");

		return vendorList;

	}

	private List<MemberServices> fetchMemberServiceList(Long serviceId, String cityName, String range, String urgency, String filter, String sort, int firstRecord, int lastRecord) throws Exception{

		slf4jLogger.debug("Entering function {}", "fetchMemberServiceList");

		List<MemberServices> memberServicesList = knottingResultService.fetchResultData(serviceId, cityName, range, urgency, filter, sort, firstRecord, lastRecord);

		slf4jLogger.debug("Exiting function {}", "fetchMemberServiceList");

		return memberServicesList;
	}

	@RequestMapping("/vendorCallRequest")
	public @ResponseBody String vendorCallRequest(HttpServletRequest request, @RequestParam(value = "service", required = true) String service, @RequestParam(value = "city", required = true) String city, @RequestParam(value = "range", required = true) String range, @RequestParam(value = "urgency", required = true) String urgency, @RequestParam(value = "phoneNumber", required = true) String phoneNumber) {

		slf4jLogger.debug("Entering function {}", "vendorCallRequest");

		boolean status = false;

		try {			
			Long serviceId = knottingResultService.fetchServiceIdByCode(service);			
			knottingResultService.processVendorInformRequest(phoneNumber, serviceId, city, range, urgency);
			status = true;
		}
		catch(Exception exception){
			request.getSession().setAttribute(errorMessage, environment.getProperty("exception_error_message"));
			mailerUtil.mailExceptionDetails("vendorCallRequest", exception.getMessage(), ExceptionUtils.getFullStackTrace(exception), request.getSession().getAttribute(sessionUserId), request.getSession().getAttribute(sessionUserType));
		}

		slf4jLogger.debug("Exiting function {}", "vendorCallRequest");

		return String.valueOf(status);

	}

}
