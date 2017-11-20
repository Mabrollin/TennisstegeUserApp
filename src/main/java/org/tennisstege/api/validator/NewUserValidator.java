package org.tennisstege.api.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.tennisstege.api.JPA.entitymodell.User;
import org.tennisstege.api.service.UserService;

@Component
public class NewUserValidator implements Validator {
	private static final int MIN_USERNAME_LEN = 6;
	private static final int MAX_USERNAME_LEN = 32;
	private static final int MIN_PASSWORD_LEN = 8;
	private static final int MAX_PASSWORD_LEN = 32;
	
	@Autowired
    private UserService userService;

	@Override
	public boolean supports(Class<?> argClass) {
		return User.class.equals(argClass);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		User user = (User) obj;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "NotEmpty");
        if (user.getUsername().length() < MIN_USERNAME_LEN || user.getUsername().length() > MAX_USERNAME_LEN) {
            errors.rejectValue("username", "Size.userForm.username");
        }
        if (userService.findByUsername(user.getUsername()).isPresent()) {
            errors.rejectValue("username", "Duplicate.userForm.username");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty");
        if (user.getPassword().length() < MIN_PASSWORD_LEN || user.getPassword().length() > MAX_PASSWORD_LEN) {
            errors.rejectValue("password", "Size.userForm.password");
        }

//        if (!user.getPasswordConfirm().equals(user.getPassword())) {
//            errors.rejectValue("passwordConfirm", "Diff.userForm.passwordConfirm");
//        }


	}

}
