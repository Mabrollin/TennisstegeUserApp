package org.tennisstege.api.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.tennisstege.api.JPA.entitymodell.UserContactInfo;
import org.tennisstege.api.service.UserService;

@Component
public class ContactInfoValidator implements Validator {
	private static final int MIN_USERNAME_LEN = 6;
	private static final int MAX_USERNAME_LEN = 32;
	private static final int MIN_PASSWORD_LEN = 8;
	private static final int MAX_PASSWORD_LEN = 32;
	
	@Autowired
    private UserService userService;

	@Override
	public boolean supports(Class<?> argClass) {
		return UserContactInfo.class.equals(argClass);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		UserContactInfo contactInfo = (UserContactInfo) obj;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "NotEmpty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "NotEmpty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "NotEmpty");
     


	}

}
