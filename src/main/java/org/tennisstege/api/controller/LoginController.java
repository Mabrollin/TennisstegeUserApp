package org.tennisstege.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.tennisstege.api.JPA.entitymodell.User;
import org.tennisstege.api.body.request.NewUserDTO;
import org.tennisstege.api.rest.mapper.NewUserMapper;
import org.tennisstege.api.service.UserService;
import org.tennisstege.api.validator.NewUserValidator;

@RestController
public class LoginController {
	@Autowired
	private UserService userService;

	@Autowired
	private NewUserValidator userValidator;

	@Autowired
	private NewUserMapper newUserMapper;

	@RequestMapping(value = "/sign-up", method = RequestMethod.POST)
	public ResponseEntity<Object> signUp(@RequestBody NewUserDTO userForm, BindingResult bindingResult) {

		User user = newUserMapper.mapToEntity(userForm);
		userValidator.validate(user, bindingResult);

		if (bindingResult.hasErrors()) {
			return new ResponseEntity<>(bindingResult.getFieldErrors().toString(), HttpStatus.BAD_REQUEST);
		}

		NewUserDTO response = newUserMapper.mapToDTO(userService.save(user));

		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(@RequestParam("username") String username, @RequestParam("password") String password) {
		return userService.login(username, password);

	}

}
