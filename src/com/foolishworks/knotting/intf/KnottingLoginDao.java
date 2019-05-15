package com.foolishworks.knotting.intf;

import com.foolishworks.knotting.entity.Member;

public interface KnottingLoginDao {
	
	public Member checkIfUserExist(String loginUsername);	
	public Member loginUser(String loginUsername, String password);
	
}
