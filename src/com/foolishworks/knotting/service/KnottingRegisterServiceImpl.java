package com.foolishworks.knotting.service;

import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.foolishworks.knotting.constants.KnottingConstants;
import com.foolishworks.knotting.entity.Member;
import com.foolishworks.knotting.intf.KnottingRegisterDao;
import com.foolishworks.knotting.intf.KnottingRegisterService;
import com.foolishworks.knotting.utils.GeneralUtils;

@Service
@Transactional
public class KnottingRegisterServiceImpl extends KnottingConstants implements KnottingRegisterService {

	@Autowired
	private KnottingRegisterDao knottingRegisterDao;

	@Autowired
	private GeneralUtils generalUtils;

	private final Logger slf4jLogger = LoggerFactory.getLogger(KnottingRegisterServiceImpl.class);

	public Long addDirectLoginMember(Member member) throws NoSuchAlgorithmException {

		slf4jLogger.debug("Entering function {}", "addDirectLoginMember");

		Timestamp todaysDate = new Timestamp(new Date().getTime());

		Long userId = null;

		member.setUserPassword(generalUtils.encryptString(member.getUserPassword()));
		member.setUserType(memberTypeVendor);
		member.setSocialAuth(socialAuthNo);
		member.setCreatedDate(todaysDate);
		member.setLastUpdatedDate(todaysDate);
		member.setEmailVerified(verificationNotDone);
		member.setPhoneVerified(verificationDone);

		userId = knottingRegisterDao.addMemberToOurSystem(member);

		if(userId != null){
			member.setCreatedBy(userId);
			member.setLastUpdatedBy(userId);
			addUpdateByInfo(member);
		}

		slf4jLogger.debug("Exiting function {}", "addDirectLoginMember");

		return userId;
	}

	public Long addSocialLoginMember(Member member){

		slf4jLogger.debug("Entering function {}", "addSocialLoginMember");

		Timestamp todaysDate = new Timestamp(new Date().getTime());

		Long userId = null;

		member.setUserType(memberTypeVendor);
		member.setSocialAuth(socialAuthYes);
		member.setCreatedDate(todaysDate);
		member.setLastUpdatedDate(todaysDate);
		member.setEmailVerified(verificationNotDone);
		member.setPhoneVerified(verificationNotDone);

		userId = knottingRegisterDao.addMemberToOurSystem(member);

		if(userId != null){
			member.setCreatedBy(userId);
			member.setLastUpdatedBy(userId);
			addUpdateByInfo(member);
		}

		slf4jLogger.debug("Exiting function {}", "addSocialLoginMember");

		return userId;
	}

	public void addUpdateByInfo(Member member){

		slf4jLogger.debug("Entering function {}", "addUpdateByInfo");

		knottingRegisterDao.addUpdateByInfo(member);

		slf4jLogger.debug("Exiting function {}", "addUpdateByInfo");

	}

}
