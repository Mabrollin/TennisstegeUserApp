package org.tennisstege.api.service;

import java.util.Optional;

import org.tennisstege.api.JPA.entitymodell.Ladder;
import org.tennisstege.api.JPA.entitymodell.User;
import org.tennisstege.api.JPA.entitymodell.UserContactInfo;

public interface UserService {
    User save(User user);

    Optional<User> findByUsername(String username);

	UserContactInfo updateContactInfo(User user, UserContactInfo infoForm);

}