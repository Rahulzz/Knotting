package com.foolishworks.knotting.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
public class KnottingRegisterGeneralValidator {

	private final Logger slf4jLogger = LoggerFactory.getLogger(KnottingRegisterGeneralValidator.class);

	@Autowired
	private Environment environment;

	private Pattern pattern;  
	private Matcher matcher;  

	private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"  
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$"; 

	public boolean supports(Class clazz) {
		return Member.class.isAssignableFrom(clazz);
	}

	public void validate(Object target, Errors errors)
	{		
		slf4jLogger.debug("Entering function {}", "validate");

		Member member = (Member)target;

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "error.name", environment.getProperty("register_name_empty"));

		if(!(member.getEmailId() != null && member.getEmailId().isEmpty())) {
			pattern = Pattern.compile(EMAIL_PATTERN);
			matcher = pattern.matcher(member.getEmailId());
			if (!matcher.matches()) {
				errors.rejectValue("emailId", "error.emailId", environment.getProperty("register_email_notemail"));
			}
		}

		if(member.getPhoneNumber() == null || member.getPhoneNumber().toString().equals("")){
			errors.rejectValue("phoneNumber", "error.phoneNumber", environment.getProperty("register_phone_empty"));
		}

		if(member.getPhoneNumber() != null && !member.getPhoneNumber().toString().isEmpty() && (member.getPhoneNumber().toString().length() != 10)){
			errors.rejectValue("phoneNumber", "error.phoneNumber", environment.getProperty("register_phone_notphone"));
		}

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userPassword", "error.userPassword", environment.getProperty("register_pwd_empty"));
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "confirmUserPassword", "error.confirmUserPassword", environment.getProperty("register_confpwd_empty"));

		if(!(member.getUserPassword().equals(member.getConfirmUserPassword()))){
			errors.rejectValue("userPassword", "error.userPassword", environment.getProperty("register_pwd_match"));
		}

		slf4jLogger.debug("Exiting function {}", "validate");
	}

}
