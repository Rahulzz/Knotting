
package com.foolishworks.knotting.intf;

import java.util.List;

import com.foolishworks.knotting.entity.MemberServices;

public interface KnottingResultDao {

	public Long totalVendorCount(String query);
	public List<MemberServices> fetchResultData(String query, int firstRecord, int lastRecord);
	public Long fetchServiceIdByCode(String serviceCode);
	
}
