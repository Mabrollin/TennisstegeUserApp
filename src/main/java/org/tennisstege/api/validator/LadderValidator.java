package org.tennisstege.api.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.tennisstege.api.JPA.entitymodell.Ladder;
import org.tennisstege.api.body.request.NewLadderDTO;
import org.tennisstege.api.service.LadderService;

@Component
public class LadderValidator implements Validator {
	private static final int MIN_NAME_LEN = 6;
	private static final int MAX_NAME_LEN = 32;
	
	@Autowired
    private LadderService ladderService;

	@Override
	public boolean supports(Class<?> argClass) {
		return NewLadderDTO.class.equals(argClass);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		NewLadderDTO ladder = (NewLadderDTO) obj;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "ladderName", "NotEmpty");
        if (ladder.getLadderName().length() < MIN_NAME_LEN || ladder.getLadderName().length() > MAX_NAME_LEN) {
            errors.rejectValue("ladderName", "Size.ladderForm.name");
        }
        if (ladderService.findByName(ladder.getLadderName()).isPresent()) {
            errors.rejectValue("ladderName", "Duplicate.ladderForm.name");
        }

	}

}
