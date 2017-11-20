package org.tennisstege.api.JPA.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.tennisstege.api.JPA.entitymodell.Challenge;
import org.tennisstege.api.JPA.entitymodell.User;

public interface ChallengeRepository extends JpaRepository<Challenge, Long> {
	Optional<Challenge> findById(Long id);
	
}
