package com.foolishworks.knotting.validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

import com.foolishworks.knotting.entity.Member;

@Component
@PropertySource("classpath:display_message.properties")
@SuppressWarnings("rawtypes")
public class KnottingLoginValidator {

	private final Logger slf4jLogger = LoggerFactory.getLogger(KnottingLoginValidator.class);

	@Autowired
	private Environment environment;

	public boolean supports(Class clazz) {
		return Member.class.isAssignableFrom(clazz);
	}

	public void validate(Object target, Errors errors)
	{		
		slf4jLogger.debug("Entering function {}", "validate");

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "loginUsername", "error.loginUsername", environment.getProperty("login_username_empty"));
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userPassword", "error.userPassword", environment.getProperty("login_password_empty"));

		slf4jLogger.debug("Exiting function {}", "validate");
	}

}
