package com.foolishworks.knotting.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.foolishworks.knotting.entity.MemberEntriesStaging;
import com.foolishworks.knotting.entity.MemberServicesStaging;

public class ServicesModelStaging implements Serializable {

	private static final long serialVersionUID = -6483831484612120918L;

	private MemberEntriesStaging memberEntriesStaging;
	private List<MemberServicesStaging> memberServicesStagingList;
	private String total;
	private String discount;
	private String coupon;

	public MemberEntriesStaging getMemberEntriesStaging() {
		if(memberEntriesStaging == null){
			memberEntriesStaging = new MemberEntriesStaging();
		}
		return memberEntriesStaging;
	}
	
	public void setMemberEntriesStaging(MemberEntriesStaging memberEntriesStaging) {
		this.memberEntriesStaging = memberEntriesStaging;
	}
	
	public List<MemberServicesStaging> getMemberServicesStagingList() {
		if(memberServicesStagingList == null){
			memberServicesStagingList = new ArrayList<MemberServicesStaging>();
		}
		return memberServicesStagingList;
	}
	
	public void setMemberServicesStagingList(
			List<MemberServicesStaging> memberServicesStagingList) {
		this.memberServicesStagingList = memberServicesStagingList;
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
