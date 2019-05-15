package com.foolishworks.knotting.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.foolishworks.knotting.constants.KnottingConstants;
import com.foolishworks.knotting.entity.MemberServices;
import com.foolishworks.knotting.entity.Services;
import com.foolishworks.knotting.intf.KnottingHomeDao;
import com.foolishworks.knotting.intf.KnottingHomeService;
import com.foolishworks.knotting.intf.KnottingServicesDao;

@Service
@Transactional
public class KnottingHomeServiceImpl extends KnottingConstants implements KnottingHomeService {

	@Autowired
	KnottingHomeDao knottingHomeDao;
	
	@Autowired
	KnottingServicesDao knottingServicesDao;

	private final Logger slf4jLogger = LoggerFactory.getLogger(KnottingHomeServiceImpl.class);

	public List<MemberServices> fetchActiveServices(){

		slf4jLogger.debug("Entering function {}", "fetchActiveServices");

		slf4jLogger.debug("Exiting function {}", "fetchActiveServices");
		
		return knottingHomeDao.fetchActiveServices();
	}

	public Set<String> fetchCitiesList(Long servicesId){

		slf4jLogger.debug("Entering function {}", "fetchCitiesList");
		
		List<MemberServices> memberServicesList = knottingHomeDao.fetchEntriesBasedOnService(servicesId);
		
		Set<String> citiesList = new HashSet<String>();
		
		for(MemberServices memberServices : memberServicesList){
			String[] citiesArray = memberServices.getMemberEntries().getAdditionalAreasServiced().split(",");
			for(String city : citiesArray){
				citiesList.add(city);
			}
		}

		slf4jLogger.debug("Exiting function {}", "fetchCitiesList");
		
		return citiesList;
	}

	public List<String> fetchRangeList(Long servicesId, String cityName){

		slf4jLogger.debug("Entering function {}", "fetchRangeList");
		
		List<MemberServices> memberServicesList = knottingHomeDao.fetchEntriesBasedOnServiceAndCity(servicesId, cityName);
		
		Services services = knottingServicesDao.fetchServices(servicesId);
		
		int lowerRange = Integer.parseInt(services.getLowerPrice());
		int upperRange = Integer.parseInt(services.getHigherPrice());
		
		List<String> rangeList = new ArrayList<String>();
		
		Map<String, Boolean> rangeStatus = analyzePriceRange(memberServicesList, lowerRange, upperRange);
		
		rangeList.add(priceRangeAll);
		
		if(rangeStatus.get(priceRangeCheap)){
			rangeList.add(priceRangeCheap+" (lesser than "+lowerRange+" INR)");
		}
		if(rangeStatus.get(priceRangeModerate)){
			rangeList.add(priceRangeModerate+" ("+lowerRange+" INR to "+upperRange+" INR)");
		}
		if(rangeStatus.get(priceRangeCostly)){
			rangeList.add(priceRangeCostly+" (more than "+upperRange+" INR)");
		}

		slf4jLogger.debug("Exiting function {}", "fetchRangeList");
		
		return rangeList;
	}
	
	public List<MemberServices> fetchRecentlyAddedServices(){

		slf4jLogger.debug("Entering function {}", "fetchRangeList");
		
		List<MemberServices> memberServicesList = knottingHomeDao.fetchRecentlyAddedServices();

		slf4jLogger.debug("Exiting function {}", "fetchRangeList");
		
		return memberServicesList;
		
	}
	
	public Map<String, Boolean> analyzePriceRange(List<MemberServices> memberServicesList, int lowerRange, int upperRange){

		slf4jLogger.debug("Entering function {}", "analyzePriceRange");
		
		Map<String, Boolean> rangeStatus = new HashMap<String, Boolean>();
		
		rangeStatus.put(priceRangeCheap, Boolean.FALSE);
		rangeStatus.put(priceRangeModerate, Boolean.FALSE);
		rangeStatus.put(priceRangeCostly, Boolean.FALSE);
		
		for(MemberServices memberServices : memberServicesList){
			int priceRange = Integer.parseInt(memberServices.getPriceRange());			
			if(priceRange < lowerRange){
				rangeStatus.put(priceRangeCheap, Boolean.TRUE);
			}
			else if(priceRange >= lowerRange && priceRange <= upperRange){
				rangeStatus.put(priceRangeModerate, Boolean.TRUE);
			}
			else if(priceRange > upperRange){
				rangeStatus.put(priceRangeCostly, Boolean.TRUE);
			}
		}

		slf4jLogger.debug("Exiting function {}", "analyzePriceRange");
		
		return rangeStatus;
		
	}

	public List<Services> fetchServicesById(List<Long> servicesId){

		slf4jLogger.debug("Entering function {}", "fetchServicesById");
		
		List<Services> services = new ArrayList<Services>();
		
		for(Long serviceId : servicesId){
			services.add(knottingServicesDao.fetchServices(serviceId));
		}

		slf4jLogger.debug("Exiting function {}", "fetchServicesById");
		
		return services;
	}

	public Integer fetchCheapestSubscriptionRate(){

		slf4jLogger.debug("Entering function {}", "fetchCheapestSubscriptionRate");

		Integer cheapestSubscription = knottingHomeDao.fetchCheapestSubscriptionRate();

		slf4jLogger.debug("Exiting function {}", "fetchCheapestSubscriptionRate");

		return cheapestSubscription;
	}
	
}
