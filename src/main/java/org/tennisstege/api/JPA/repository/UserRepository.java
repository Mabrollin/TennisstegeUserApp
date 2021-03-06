package org.tennisstege.api.JPA.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.tennisstege.api.JPA.entitymodell.User;

public interface UserRepository extends MongoRepository<User, String> {
	Optional<User> findByUsername(String username);
	
}
