package com.foolishworks.knotting.intf;

import java.util.List;

import com.foolishworks.knotting.entity.MemberServices;
import com.foolishworks.knotting.entity.ServiceDetails;
import com.foolishworks.knotting.entity.Services;
import com.mashape.unirest.http.exceptions.UnirestException;

public interface KnottingResultService {
	
	public Long totalVendorCount(Long serviceId, String cityName, String range, String urgency, String filter);
	public List<MemberServices> fetchResultData(Long serviceId, String cityName, String range, String urgency, String filter, String sort, int firstRecord, int lastRecord);
	public Services fetchServiceRecord(Long serviceId);
	public void processVendorInformRequest(String phoneNumber, Long serviceId, String cityName, String range, String urgency) throws UnirestException;
	public String priceRangeTitle(Long servicesId, String range);
	public List<ServiceDetails> fetchServiceDetails(Long servicesId, List<MemberServices> memberServicesList) throws Exception;
	public Long fetchServiceIdByCode(String serviceCode);
	
}
