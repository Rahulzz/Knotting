package com.foolishworks.knotting.service;

import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.util.Date;

import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.foolishworks.knotting.constants.KnottingConstants;
import com.foolishworks.knotting.entity.Member;
import com.foolishworks.knotting.intf.KnottingLoginDao;
import com.foolishworks.knotting.intf.KnottingLoginService;
import com.foolishworks.knotting.intf.KnottingProfileDao;
import com.foolishworks.knotting.utils.GeneralUtils;
import com.foolishworks.knotting.utils.MailerUtil;
import com.foolishworks.knotting.utils.SendMessageUtil;
import com.mashape.unirest.http.exceptions.UnirestException;

@Service
@Transactional
public class KnottingLoginServiceImpl extends KnottingConstants implements KnottingLoginService {

	@Autowired
	private KnottingLoginDao knottingLoginDao;

	@Autowired
	private KnottingProfileDao knottingProfileDao;

	@Autowired
	private GeneralUtils generalUtils;
	
	@Autowired
	private MailerUtil mailerUtil;

	@Autowired
	private SendMessageUtil messageUtil;

	private final Logger slf4jLogger = LoggerFactory.getLogger(KnottingLoginServiceImpl.class);

	public Member checkIfUserExist(String loginUsername){

		slf4jLogger.debug("Entering function {}", "checkIfUserExist");

		Member member = null;
		member = knottingLoginDao.checkIfUserExist(loginUsername);

		slf4jLogger.debug("Exiting function {}", "checkIfUserExist");

		return member;
	}

	public Member loginUser(String loginUsername, String password) throws NoSuchAlgorithmException {

		slf4jLogger.debug("Entering function {}", "loginUser");

		Member member = null;     
		member = knottingLoginDao.loginUser(loginUsername, generalUtils.encryptString(password));

		slf4jLogger.debug("Exiting function {}", "loginUser");

		return member;
	}

	public String sendNewPasswordToUser(String phoneNumber) throws UnirestException, NoSuchAlgorithmException, JSONException{

		slf4jLogger.debug("Entering function {}", "sendNewPasswordToUser");	

		String response = "";

		Member member = knottingLoginDao.checkIfUserExist(phoneNumber);

		if(member != null){
			if(socialAuthNo.equals(member.getSocialAuth())){
				String password = generalUtils.generateRandomNumericPassword(maxPasswordLength);
				String encryptedPassword = generalUtils.encryptString(password);

				member.setUserPassword(encryptedPassword);
				member.setLastUpdatedBy(member.getMemberId());
				member.setLastUpdatedDate(new Timestamp((new Date()).getTime()));

				knottingProfileDao.savePersonalInfo(member);
				
				String smsResponse = messageUtil.sendNewPasswordSmsToUser(phoneNumber, member.getName(), password);

				JSONObject msgResponse = new JSONObject(smsResponse);
				
				if(!"Error".equals(msgResponse.getString("Status"))){
					response = "SUCCESS:Your new password has been sent to "+phoneNumber;
				}
				else{
					mailerUtil.mailExceptionDetails("forgotPassword", "Unable to send SMS to "+phoneNumber, smsResponse, null, null);
					response = "FAILURE:Unable to SMS the new password. Kindly contact our support team.";
				}

			}
			else{
				response = "FAILURE:This number belongs to a user who signed up using Google or Facebook. Please use the same mode for login.";
			}
		}
		else{
			response = "FAILURE:This number is not part of our system. Kindly register a new account.";
		}

		slf4jLogger.debug("Exiting function {}", "sendNewPasswordToUser");

		return response;		

	}

}
