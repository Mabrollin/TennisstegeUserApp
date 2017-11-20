package org.tennisstege.api.JPA.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.tennisstege.api.JPA.entitymodell.User;

public interface UserRepository extends JpaRepository<User, Long> {
	Optional<User> findByUsername(String username);
	
}
