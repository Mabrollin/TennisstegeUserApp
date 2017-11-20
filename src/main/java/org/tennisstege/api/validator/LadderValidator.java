package org.tennisstege.api.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.tennisstege.api.JPA.entitymodell.Ladder;
import org.tennisstege.api.service.LadderService;

@Component
public class LadderValidator implements Validator {
	private static final int MIN_NAME_LEN = 6;
	private static final int MAX_NAME_LEN = 32;
	private static final int MIN_PASSWORD_LEN = 8;
	private static final int MAX_PASSWORD_LEN = 32;
	
	@Autowired
    private LadderService ladderService;

	@Override
	public boolean supports(Class<?> argClass) {
		return Ladder.class.equals(argClass);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		Ladder ladder = (Ladder) obj;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "NotEmpty");
        if (ladder.getName().length() < MIN_NAME_LEN || ladder.getName().length() > MAX_NAME_LEN) {
            errors.rejectValue("name", "Size.ladderForm.name");
        }
        if (ladderService.findByName(ladder.getName()).isPresent()) {
            errors.rejectValue("name", "Duplicate.ladderForm.name");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty");
        if (ladder.getPassword().length() < MIN_PASSWORD_LEN || ladder.getPassword().length() > MAX_PASSWORD_LEN) {
            errors.rejectValue("password", "Size.ladderForm.password");
        }

	}

}
