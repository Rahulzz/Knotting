package com.foolishworks.knotting.intf;

import java.util.List;

import org.json.JSONException;

import com.foolishworks.knotting.entity.MemberServices;
import com.foolishworks.knotting.entity.ServiceDetails;

public interface KnottingVendorServiceService {
	
	public MemberServices fetchMemberService(Long servicesId);
	public MemberServices fetchMemberService(String serviceCode);
	public List<ServiceDetails> fetchServiceDetails(Long servicesId);
	public boolean sendServiceUrlToUser(String from, String to, String serviceCode) throws JSONException;
	public String buildVendorDescription(MemberServices memberServices);
	
}
