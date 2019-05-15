package com.foolishworks.knotting.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.brickred.socialauth.AuthProvider;
import org.brickred.socialauth.Profile;
import org.brickred.socialauth.SocialAuthConfig;
import org.brickred.socialauth.SocialAuthManager;
import org.brickred.socialauth.util.SocialAuthUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class SocialAuthenticationUtil {

	private final Logger slf4jLogger = LoggerFactory.getLogger(SocialAuthenticationUtil.class);
	
	public String getUrlForSocialAuthentication(String loginType, String successUrl, HttpSession session) throws Exception{

		slf4jLogger.debug("Entering function {}", "getUrlForSocialAuthentication");
		
		SocialAuthConfig config = SocialAuthConfig.getDefault();
		config.load();
		SocialAuthManager manager = new SocialAuthManager();
		manager.setSocialAuthConfig(config);
		String url = manager.getAuthenticationUrl(loginType, successUrl);
		session.setAttribute("authManager", manager);

		slf4jLogger.debug("Entering function {}", "getUrlForSocialAuthentication");
		
		return url;
	}
	
	public Profile getProfileDataFromSocialNetwork(HttpServletRequest request) throws Exception{

		slf4jLogger.debug("Entering function {}", "getProfileDataFromSocialNetwork");
		
		SocialAuthManager manager = (SocialAuthManager)request.getSession().getAttribute("authManager");
		AuthProvider provider = manager.connect(SocialAuthUtil.getRequestParametersMap(request));
		Profile profile = provider.getUserProfile();

		slf4jLogger.debug("Entering function {}", "getProfileDataFromSocialNetwork");
		
		return profile;
	}

}
