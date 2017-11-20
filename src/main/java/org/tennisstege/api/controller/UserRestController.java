package org.tennisstege.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.tennisstege.api.JPA.entitymodell.User;
import org.tennisstege.api.JPA.entitymodell.UserContactInfo;
import org.tennisstege.api.service.UserService;

@RestController
@RequestMapping(value = "/user")
public class UserRestController {

	@Autowired
	private UserService userService;

	UserRestController() {

	}

	@RequestMapping(method = RequestMethod.GET, value = "{username}/getContactInfo")
	ResponseEntity<UserContactInfo> getContactInfo(@PathVariable String username) {
		String principalName = SecurityContextHolder.getContext().getAuthentication().getName();
		if(!principalName.equals(username)){
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
		Optional<User> user = userService.findByUsername(username);
		if (user.isPresent()) {
			return new ResponseEntity<UserContactInfo>(user.get().getUserContactInfo(), HttpStatus.OK);
		} else {
			return new ResponseEntity<UserContactInfo>(HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(method = RequestMethod.PUT, value = "{username}/updateContactInfo")
	ResponseEntity<UserContactInfo> updateContactInfo(@RequestBody UserContactInfo infoForm,
			@PathVariable String username) {
		//SecurityContextHolder.getContext().getAuthentication().getName();
		Optional<User> user = userService.findByUsername(username);
		if (user.isPresent()) {
			return new ResponseEntity<UserContactInfo>(userService.update(user.get(), infoForm), HttpStatus.OK);
		} else {
			return new ResponseEntity<UserContactInfo>(HttpStatus.NOT_FOUND);
		}

	}

	@RequestMapping(method = RequestMethod.GET, value = "{username}/getLadderNames")
	public List<String> getLadderNames(@PathVariable String username) {
		this.validateUser(username);
		Optional<User> user = userService.findByUsername(username);
		if (user.isPresent()) {
			return user.get().getLadderNames();
		} else {
			return null;
		}
	}

	private void validateUser(String userId) {
		this.userService.findByUsername(userId).orElseThrow(() -> new UserNotFoundException(userId));
	}
}
