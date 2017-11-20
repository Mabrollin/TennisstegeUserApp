package org.tennisstege.api.JPA.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.tennisstege.api.JPA.entitymodell.Ladder;

import java.util.Optional;

public interface LadderRepository extends JpaRepository<Ladder, Long> {
	Optional<Ladder> findByName(String name);
	
}
