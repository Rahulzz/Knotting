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
import org.springframework.validation.Validator;

import com.foolishworks.knotting.entity.Member;

@Component
@PropertySource("classpath:display_message.properties")
@SuppressWarnings("rawtypes")
public class KnottingRegisterSocialValidator implements Validator {
	
    private final Logger slf4jLogger = LoggerFactory.getLogger(KnottingRegisterSocialValidator.class);

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
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "emailId", "error.emailId", environment.getProperty("register_email_empty"));

		if(!(member.getEmailId() != null && member.getEmailId().isEmpty())) {
			pattern = Pattern.compile(EMAIL_PATTERN);
			matcher = pattern.matcher(member.getEmailId());
			if (!matcher.matches()) {
				errors.rejectValue("emailId", "error.emailId", environment.getProperty("register_email_notemail"));
			}
		}
        
		slf4jLogger.debug("Exiting function {}", "validate");
    }
    
}
