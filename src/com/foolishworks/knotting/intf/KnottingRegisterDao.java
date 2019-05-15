package com.foolishworks.knotting.intf;

import com.foolishworks.knotting.entity.Member;

public interface KnottingRegisterDao {

	public Long addMemberToOurSystem(Member member);
	public void addUpdateByInfo(Member member);
	
}
