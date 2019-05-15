package com.foolishworks.knotting.intf;

import java.util.List;

import com.foolishworks.knotting.entity.MemberServices;

public interface KnottingHomeDao {

	public List<MemberServices> fetchActiveServices();
	public List<MemberServices> fetchEntriesBasedOnService(Long servicesId);
	public List<MemberServices> fetchEntriesBasedOnServiceAndCity(Long servicesId, String cityName);
	public List<MemberServices> fetchRecentlyAddedServices();
	public Integer fetchCheapestSubscriptionRate();
	
}
