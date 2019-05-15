package com.foolishworks.knotting.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

@Component
@PropertySource("classpath:config.properties")
public class SendMessageUtil {

	@Autowired
	private Environment environment;

	private final Logger slf4jLogger = LoggerFactory.getLogger(SendMessageUtil.class);

	public String sendOneTimePassword(String phoneNumber) throws UnirestException{

		slf4jLogger.debug("Entering function {}", "sendOneTimePassword");

		HttpResponse<String> response = Unirest.post("https://2factor.in/API/V1/"+environment.getProperty("2factor_sms_api_key")+"/SMS/"+phoneNumber+"/AUTOGEN/OTP-User-VerifyMobile").asString();
		String deliveryResponse = response.getBody();

		slf4jLogger.debug("Exiting function {}", "sendOneTimePassword");

		return deliveryResponse;
	}

	public String sendOneTimePasswordForEmergency(String phoneNumber) throws UnirestException{

		slf4jLogger.debug("Entering function {}", "sendOneTimePasswordForEmergency");

		HttpResponse<String> response = Unirest.post("https://2factor.in/API/V1/"+environment.getProperty("2factor_sms_api_key")+"/SMS/"+phoneNumber+"/AUTOGEN/OTP-User-VerifyMobile-Emergency").asString();
		String deliveryResponse = response.getBody();

		slf4jLogger.debug("Exiting function {}", "sendOneTimePasswordForEmergency");

		return deliveryResponse;
	}

	public String sendOneTimePasswordToTeam(String phoneNumber) throws UnirestException{

		slf4jLogger.debug("Entering function {}", "sendOneTimePasswordToTeam");

		HttpResponse<String> response = Unirest.post("https://2factor.in/API/V1/"+environment.getProperty("2factor_sms_api_key")+"/SMS/"+phoneNumber+"/AUTOGEN/OTP-User-VerifyMobile-Team").asString();
		String deliveryResponse = response.getBody();

		slf4jLogger.debug("Exiting function {}", "sendOneTimePasswordToTeam");

		return deliveryResponse;
	}

	public String verifyUsersOneTimePassword(String sessionKey, String oneTimePassword) throws UnirestException{

		slf4jLogger.debug("Entering function {}", "verifyUsersOneTimePassword");

		HttpResponse<String> response = Unirest.post("https://2factor.in/API/V1/"+environment.getProperty("2factor_sms_api_key")+"/SMS/VERIFY/"+sessionKey+"/"+oneTimePassword+"").asString();
		String deliveryResponse = response.getBody();

		slf4jLogger.debug("Exiting function {}", "verifyUsersOneTimePassword");

		return deliveryResponse;
	}

	public String sendShareInfoSmsToUser(String phoneNumber, String name, String serviceName, String sharePhone, String shareAddress, String url) throws UnirestException{

		slf4jLogger.debug("Entering function {}", "sendShareInfoSmsToUser");

		HttpResponse<String> response = Unirest.post("http://2factor.in/API/V1/"+environment.getProperty("2factor_sms_api_key")+"/ADDON_SERVICES/SEND/TSMS").body("{\"From\": \""+environment.getProperty("2factor_sms_sender_id")+"\", \"To\": \""+phoneNumber+"\", \"TemplateName\": \""+environment.getProperty("2factor_sms_transaction_share_template")+"\", \"VAR1\": \""+name+"\", \"VAR2\": \""+serviceName+"\", \"VAR3\": \""+sharePhone+"\", \"VAR4\": \""+shareAddress+"\", \"VAR5\": \""+url+"\"}").asString();
		String deliveryResponse = response.getBody();

		slf4jLogger.debug("Exiting function {}", "sendShareInfoSmsToUser");

		return deliveryResponse;
	}

	public String sendVendorInformSmsToUser(String phoneNumber, String serviceName, String userPhoneNumber) throws UnirestException{

		slf4jLogger.debug("Entering function {}", "sendVendorInformSmsToUser");

		HttpResponse<String> response = Unirest.post("http://2factor.in/API/V1/"+environment.getProperty("2factor_sms_api_key")+"/ADDON_SERVICES/SEND/TSMS").body("{\"From\": \""+environment.getProperty("2factor_sms_sender_id")+"\", \"To\": \""+phoneNumber+"\", \"TemplateName\": \""+environment.getProperty("2factor_sms_transaction_inform_template")+"\", \"VAR1\": \""+serviceName+"\", \"VAR2\": \""+userPhoneNumber+"\"}").asString();
		String deliveryResponse = response.getBody();

		slf4jLogger.debug("Exiting function {}", "sendVendorInformSmsToUser");

		return deliveryResponse;
	}

	public String sendNewPasswordSmsToUser(String phoneNumber, String name, String password) throws UnirestException{

		slf4jLogger.debug("Entering function {}", "sendNewPasswordSmsToUser");

		HttpResponse<String> response = Unirest.post("http://2factor.in/API/V1/"+environment.getProperty("2factor_sms_api_key")+"/ADDON_SERVICES/SEND/TSMS").body("{\"From\": \""+environment.getProperty("2factor_sms_sender_id")+"\", \"To\": \""+phoneNumber+"\", \"TemplateName\": \""+environment.getProperty("2factor_sms_transaction_password_template")+"\", \"VAR1\": \""+name+"\", \"VAR2\": \""+password+"\"}").asString();
		String deliveryResponse = response.getBody();

		slf4jLogger.debug("Exiting function {}", "sendNewPasswordSmsToUser");

		return deliveryResponse;
	}

	public String sendPromotionalSmsToNumber(String phoneNumber, String smsBody) throws UnirestException{

		slf4jLogger.debug("Entering function {}", "sendPromotionalSmsToNumber");

		HttpResponse<String> response = Unirest.post("http://2factor.in/API/V1/"+environment.getProperty("2factor_sms_api_key")+"/ADDON_SERVICES/SEND/PSMS").body("{\"From\": \""+environment.getProperty("2factor_sms_sender_id")+"\",\"To\": \""+phoneNumber+"\", \"Msg\": \""+smsBody+"\"}").asString();
		String deliveryResponse = response.getBody();

		slf4jLogger.debug("Exiting function {}", "sendPromotionalSmsToNumber");

		return deliveryResponse;
	}

}
