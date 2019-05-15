package com.foolishworks.knotting.intf;

import java.util.List;

import com.foolishworks.knotting.entity.Coupon;
import com.foolishworks.knotting.entity.Member;
import com.foolishworks.knotting.entity.MemberEntries;
import com.foolishworks.knotting.entity.MemberEntriesStaging;

public interface KnottingSupportDao {
	
	public List<MemberEntriesStaging> fetchPendingEntries();
	public void deleteAllMembberServiceRecords(Long memberEntryId);
	public void addOrUpdateMemberEntry(MemberEntries memberEntries);
	public void deleteStagingEntry(MemberEntriesStaging memberEntriesStaging);
	public List<Coupon> fetchCoupons();
	public Coupon fetchCoupon();
	public void addOrUpdateCoupon(Coupon coupon);
	public List<Member> fetchMembers();
	
}
