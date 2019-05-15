package com.foolishworks.knotting.intf;

import java.util.List;

import com.foolishworks.knotting.entity.Member;
import com.foolishworks.knotting.entity.MemberEntries;
import com.foolishworks.knotting.entity.MemberEntriesStaging;
import com.foolishworks.knotting.entity.MemberServices;

public interface KnottingProfileDao {

	public void savePersonalInfo(Member member);
	public List<MemberEntries> fetchApprovedMemberServicesForThisUser(Long userId);
	public List<MemberEntriesStaging> fetchNotApprovedMemberServicesForThisUser(Long userId);
	public Member fetchMemberRecord(Long memberId);
	public Long addOrUpdateServices(MemberServices memberServices);
	
}
