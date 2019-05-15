package com.foolishworks.knotting.validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import com.foolishworks.knotting.entity.Member;

@Component
@PropertySource("classpath:display_message.properties")
@SuppressWarnings("rawtypes")
public class KnottingProfilePersonalValidator {

	private final Logger slf4jLogger = LoggerFactory.getLogger(KnottingProfilePersonalValidator.class);

	@Autowired
	private Environment environment;

	public boolean supports(Class clazz) {
		return Member.class.isAssignableFrom(clazz);
	}

	public void validate(Object target, Errors errors)
	{		
		slf4jLogger.debug("Entering function {}", "validate");

		Member member = (Member)target;

		if(member.getChangeUserPassword().length() > 0 && !(member.getChangeUserPassword().equals(member.getConfirmUserPassword()))){
			errors.rejectValue("changeUserPassword", "error.changeUserPassword", environment.getProperty("register_pwd_match"));
		}

		slf4jLogger.debug("Exiting function {}", "validate");
	}

}
