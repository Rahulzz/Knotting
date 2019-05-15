package com.foolishworks.knotting.intf;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.foolishworks.knotting.entity.Coupon;
import com.foolishworks.knotting.entity.Member;
import com.foolishworks.knotting.entity.MemberEntriesStaging;

public interface KnottingSupportService {
	
	public List<MemberEntriesStaging> fetchPendingEntries();
	public MemberEntriesStaging fetchPendingEntry(Long entryId);
	public boolean applyActionOnStagingRecord(HttpServletRequest request, Long entryId, String actionType, String rejectionDetail) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, IOException;
	public List<Coupon> fetchCoupons();
	public Coupon fetchCoupon();
	public List<Member> fetchMembers();
	
}
