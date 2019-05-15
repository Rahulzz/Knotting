package com.foolishworks.knotting.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.foolishworks.knotting.entity.Configuration;
import com.foolishworks.knotting.entity.Member;
import com.foolishworks.knotting.intf.KnottingCommonDao;
import com.foolishworks.knotting.intf.KnottingCommonService;

@Service
@Transactional
public class KnottingCommonServiceImpl implements KnottingCommonService {

	@Autowired
	private KnottingCommonDao knottingCommonDao;

	private final Logger slf4jLogger = LoggerFactory.getLogger(KnottingCommonServiceImpl.class);

	public Member fetchMemberRecord(Long userId, String mailId, Long phoneNumber){

		slf4jLogger.debug("Entering function {}", "fetchMemberRecord");

		Member member = null;
		member = knottingCommonDao.fetchMemberRecord(userId, mailId, phoneNumber);

		slf4jLogger.debug("Exiting function {}", "fetchMemberRecord");

		return member;

	}

	public boolean verifyIfPhoneAlreadyExists(Long phoneNumber){

		slf4jLogger.debug("Entering function {}", "verifyIfPhoneAlreadyExists");

		Integer recordCount = knottingCommonDao.verifyIfPhoneAlreadyExists(phoneNumber);
		boolean recordExists = (recordCount.intValue() > 0)?true:false;

		slf4jLogger.debug("Exiting function {}", "verifyIfPhoneAlreadyExists");

		return recordExists;
	}

	public boolean verifyIfEmailAlreadyExists(String emailId){

		slf4jLogger.debug("Entering function {}", "verifyIfEmailAlreadyExists");

		Integer recordCount = knottingCommonDao.verifyIfEmailAlreadyExists(emailId);
		boolean recordExists = (recordCount.intValue() > 0)?true:false;

		slf4jLogger.debug("Exiting function {}", "verifyIfEmailAlreadyExists");

		return recordExists;
	}
	
	public Configuration fetchConfigurationByCode(String configCode) {

		slf4jLogger.debug("Entering function {}", "verifyIfEmailAlreadyExists");
		
		Configuration configuration = knottingCommonDao.fetchConfigurationByCode(configCode);

		slf4jLogger.debug("Exiting function {}", "verifyIfEmailAlreadyExists");

		return configuration;
		
	}

}
