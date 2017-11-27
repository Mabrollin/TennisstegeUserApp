package org.tennisstege.api.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.tennisstege.api.JPA.entitymodell.User;
import org.tennisstege.api.JPA.entitymodell.UserContactInfo;
import org.tennisstege.api.JPA.repository.RoleRepository;
import org.tennisstege.api.JPA.repository.UserRepository;
import org.tennisstege.api.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public User save(User user) {
		if(user.getId() != null && userRepository.exists(user.getId())){
			userRepository.save(user);
		}
		else{//new user
			user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
			user.getRoles().add(roleRepository.findByName("USER").get());
			userRepository.save(user);
		}
		return user;
	}

	@Override
	public Optional<User> findByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	@Override
	public UserContactInfo updateContactInfo(User user, UserContactInfo userContactInfo) {
		user.updateUserContactInfo(userContactInfo);
		userRepository.save(user);
		return user.getUserContactInfo();
	}
	@Override
	public String login(String username, String password){
		Optional<User> user = findByUsername(username); 
		if (user.isPresent()){
			if(bCryptPasswordEncoder.matches(password, user.get().getPassword()))
				return "ok";
			else
				return "wrong password";
		}
		else return "user not found";
	}
}
	