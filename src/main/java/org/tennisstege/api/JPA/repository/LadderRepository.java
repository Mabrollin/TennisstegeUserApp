package org.tennisstege.api.JPA.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.tennisstege.api.JPA.entitymodell.Ladder;

public interface LadderRepository extends MongoRepository<Ladder, Long> {
	Optional<Ladder> findByName(String name);

	List<Ladder> findByNameLike(String name);
	
}
