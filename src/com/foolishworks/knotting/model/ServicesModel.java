package com.foolishworks.knotting.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.foolishworks.knotting.entity.MemberEntries;
import com.foolishworks.knotting.entity.MemberServices;

public class ServicesModel implements Serializable {

	private static final long serialVersionUID = 221840380752296802L;

	private MemberEntries memberEntriesStaging;
	private List<MemberServices> memberServicesList;
	private String total;
	private String discount;
	private String coupon;

	public MemberEntries getMemberEntriesStaging() {
		if(memberEntriesStaging == null){
			memberEntriesStaging = new MemberEntries();
		}
		return memberEntriesStaging;
	}
	
	public void setMemberEntriesStaging(MemberEntries memberEntriesStaging) {
		this.memberEntriesStaging = memberEntriesStaging;
	}

	public List<MemberServices> getMemberServicesList() {
		if(memberServicesList == null){
			memberServicesList = new ArrayList<MemberServices>();
		}
		return memberServicesList;
	}

	public void setMemberServicesList(
			List<MemberServices> memberServicesList) {
		this.memberServicesList = memberServicesList;
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	public String getDiscount() {
		return discount;
	}

	public void setDiscount(String discount) {
		this.discount = discount;
	}

	public String getCoupon() {
		return coupon;
	}

	public void setCoupon(String coupon) {
		this.coupon = coupon;
	}

}
