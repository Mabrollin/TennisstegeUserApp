package org.tennisstege.api.controller;

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
import org.tennisstege.api.body.request.UserContactInfoDTO;
import org.tennisstege.api.body.response.SimpleUserDTO;
import org.tennisstege.api.rest.mapper.ContactInfoMapper;
import org.tennisstege.api.rest.mapper.SimpleUserMapper;
import org.tennisstege.api.service.UserService;

@RestController
@RequestMapping(value = "/user")
public class UserRestController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private ContactInfoMapper contactInfoMapper;
	
	@Autowired
	private SimpleUserMapper simpleUserMapper;

	UserRestController() {

	}

	@RequestMapping(method = RequestMethod.GET, value = "{username}")
	public ResponseEntity<SimpleUserDTO> getContactInfo(@PathVariable String username) {
		String principalName = SecurityContextHolder.getContext().getAuthentication().getName();
		if(!principalName.equals(username)){
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
		Optional<User> user = userService.findByUsername(username);
		if (user.isPresent()) {
			SimpleUserDTO response = simpleUserMapper.mapToDTO(user.get());
			return new ResponseEntity<>(response, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(method = RequestMethod.PUT, value = "{username}/updateContactInfo")
	public ResponseEntity<UserContactInfoDTO> updateContactInfo(@RequestBody UserContactInfoDTO infoForm,
			@PathVariable String username) {
		String principalName = SecurityContextHolder.getContext().getAuthentication().getName();
		if(!principalName.equals(username)){
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
		Optional<User> user = userService.findByUsername(username);
		if (user.isPresent()) {
			UserContactInfo update = contactInfoMapper.mapToEntity(infoForm);
			return new ResponseEntity<UserContactInfoDTO>(contactInfoMapper.mapToDTO(userService.updateContactInfo(user.get(), update)), HttpStatus.OK);
		} else {
			return new ResponseEntity<UserContactInfoDTO>(HttpStatus.NOT_FOUND);
		}

	}


}
