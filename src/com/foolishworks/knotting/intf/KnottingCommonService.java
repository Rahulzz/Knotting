package com.foolishworks.knotting.intf;

import com.foolishworks.knotting.entity.Configuration;
import com.foolishworks.knotting.entity.Member;

public interface KnottingCommonService {
	
	public Member fetchMemberRecord(Long userId, String mailId, Long phoneNumber);
	public boolean verifyIfPhoneAlreadyExists(Long phoneNumber);
	public boolean verifyIfEmailAlreadyExists(String emailId);
	public Configuration fetchConfigurationByCode(String configCode);

}
