package com.foolishworks.knotting.intf;

import java.util.List;

import com.foolishworks.knotting.entity.MemberServices;
import com.foolishworks.knotting.entity.ServiceDetails;

public interface KnottingVendorServiceDao {
	
	public MemberServices fetchMemberService(Long servicesId);
	public MemberServices fetchMemberService(String serviceCode);
	public List<ServiceDetails> fetchServiceDetails(Long servicesId);
	
}
