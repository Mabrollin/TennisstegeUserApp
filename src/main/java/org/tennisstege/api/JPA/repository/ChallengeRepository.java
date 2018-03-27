package org.tennisstege.api.JPA.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.tennisstege.api.JPA.entitymodell.Challenge;
import org.tennisstege.api.JPA.entitymodell.User;

public interface ChallengeRepository extends MongoRepository<Challenge, String> {
	Optional<Challenge> findById(String id);
	
}
