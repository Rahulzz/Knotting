package com.foolishworks.knotting.intf;

import java.security.NoSuchAlgorithmException;

import org.json.JSONException;

import com.foolishworks.knotting.entity.Member;
import com.mashape.unirest.http.exceptions.UnirestException;

public interface KnottingLoginService {

	public Member checkIfUserExist(String loginUsername);	
	public Member loginUser(String loginUsername, String password) throws NoSuchAlgorithmException;
	public String sendNewPasswordToUser(String phoneNumber) throws UnirestException, NoSuchAlgorithmException, JSONException;

}
