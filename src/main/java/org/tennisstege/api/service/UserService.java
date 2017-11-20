package org.tennisstege.api.service;

import java.util.Optional;

import org.tennisstege.api.JPA.entitymodell.User;
import org.tennisstege.api.JPA.entitymodell.UserContactInfo;

public interface UserService {
    User save(User user);

    Optional<User> findByUsername(String username);

	UserContactInfo update(User user, UserContactInfo infoForm);

	String login(String username, String password);
}