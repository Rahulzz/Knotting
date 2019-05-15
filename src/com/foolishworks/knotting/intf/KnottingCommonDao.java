package com.foolishworks.knotting.intf;

import com.foolishworks.knotting.entity.Configuration;
import com.foolishworks.knotting.entity.Member;

public interface KnottingCommonDao {
	
	public Member fetchMemberRecord(Long userId, String mailId, Long phoneNumber);
	public Integer verifyIfPhoneAlreadyExists(Long phoneNumber);
	public Integer verifyIfEmailAlreadyExists(String emailId);
	public Configuration fetchConfigurationByCode(String configCode);
	
}
