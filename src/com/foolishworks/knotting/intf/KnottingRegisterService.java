package com.foolishworks.knotting.intf;

import java.security.NoSuchAlgorithmException;

import com.foolishworks.knotting.entity.Member;

public interface KnottingRegisterService {
	
	public Long addDirectLoginMember(Member member) throws NoSuchAlgorithmException;
	public Long addSocialLoginMember(Member member);
	public void addUpdateByInfo(Member member);

}
