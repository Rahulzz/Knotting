package com.foolishworks.knotting.service;

import java.util.List;

import org.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.foolishworks.knotting.constants.KnottingConstants;
import com.foolishworks.knotting.entity.MemberServices;
import com.foolishworks.knotting.entity.ServiceDetails;
import com.foolishworks.knotting.intf.KnottingVendorServiceDao;
import com.foolishworks.knotting.intf.KnottingVendorServiceService;
import com.foolishworks.knotting.utils.SendMessageUtil;
import com.foolishworks.knotting.utils.UrlShortenerUtil;
import com.mashape.unirest.http.exceptions.UnirestException;

@Service
@Transactional
public class KnottingVendorServiceServiceImpl extends KnottingConstants implements KnottingVendorServiceService {

	@Autowired
	private KnottingVendorServiceDao knottingVendorServiceDao;

	@Autowired
	private SendMessageUtil sendMessageToUser;
	
	@Autowired
	private UrlShortenerUtil urlShortenerUtil;

	private final Logger slf4jLogger = LoggerFactory.getLogger(KnottingVendorServiceServiceImpl.class);

	public MemberServices fetchMemberService(Long servicesId){

		slf4jLogger.debug("Entering function {}", "fetchMemberService");

		slf4jLogger.debug("Exiting function {}", "fetchMemberService");

		return knottingVendorServiceDao.fetchMemberService(servicesId);

	}

	public MemberServices fetchMemberService(String serviceCode){

		slf4jLogger.debug("Entering function {}", "fetchMemberService");

		slf4jLogger.debug("Exiting function {}", "fetchMemberService");

		return knottingVendorServiceDao.fetchMemberService(serviceCode);

	}

	public List<ServiceDetails> fetchServiceDetails(Long servicesId){

		slf4jLogger.debug("Entering function {}", "fetchServiceDetails");

		slf4jLogger.debug("Exiting function {}", "fetchServiceDetails");

		return knottingVendorServiceDao.fetchServiceDetails(servicesId);

	}

	public boolean sendServiceUrlToUser(String from, String to, String serviceCode) throws JSONException{

		slf4jLogger.debug("Entering function {}", "sendServiceUrlToUser");

		boolean msgStatus = false;

		MemberServices memberServices = knottingVendorServiceDao.fetchMemberService(serviceCode);

		try {
			String longUrl = domainPath+"details/"+memberServices.getMemberServiceCode();
			String shortUrl = urlShortenerUtil.shortenUrl(longUrl);
			sendMessageToUser.sendShareInfoSmsToUser(to, memberServices.getMemberEntries().getName(), memberServices.getServices().getName(), memberServices.getMemberEntries().getPhone1().toString(), memberServices.getMemberEntries().getLocationAddress(), shortUrl);

			msgStatus = true;
		}
		catch (UnirestException e) {
			e.printStackTrace();
		}

		slf4jLogger.debug("Exiting function {}", "sendServiceUrlToUser");

		return msgStatus;

	}

	public String buildVendorDescription(MemberServices memberServices){

		slf4jLogger.debug("Entering function {}", "buildVendorDescription");

		StringBuilder description = new StringBuilder();

		description.append(memberServices.getMemberEntries().getName());
		description.append(", a passionate team of youngsters making way up the ladder in the ");
		description.append(memberServices.getServices().getName());
		description.append(" industry. They have ");
		description.append(memberServices.getExperience());
		description.append(" year(s) of experience and ");

		String[] cities = memberServices.getMemberEntries().getAdditionalAreasServiced().split(",");

		if(cities.length == 1){
			description.append("work exclusively on weddings in ");
			description.append(cities[0]);
			description.append(" city.");
		}
		else{
			description.append("cover the cities of ");
			for(int i = 0; i < cities.length; i++){
				description.append(cities[i]);
				if(i == (cities.length - 2)) {
					description.append(" and ");
				}
				else if(i < (cities.length - 1)) {
					description.append(", ");
				}
			}
			description.append(" for wedding services.");
		}


		slf4jLogger.debug("Exiting function {}", "buildVendorDescription");

		return description.toString();
	}

}
