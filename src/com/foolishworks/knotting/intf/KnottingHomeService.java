package com.foolishworks.knotting.intf;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.foolishworks.knotting.entity.MemberServices;
import com.foolishworks.knotting.entity.Services;

public interface KnottingHomeService {

	public List<MemberServices> fetchActiveServices();
	public Set<String> fetchCitiesList(Long servicesId);
	public List<String> fetchRangeList(Long servicesId, String cityName);
	public List<MemberServices> fetchRecentlyAddedServices();
	public Map<String, Boolean> analyzePriceRange(List<MemberServices> memberServicesList, int lowerRange, int upperRange);
	public List<Services> fetchServicesById(List<Long> servicesId);
	public Integer fetchCheapestSubscriptionRate();
	
}
