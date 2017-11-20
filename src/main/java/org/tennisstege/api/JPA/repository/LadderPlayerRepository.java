package org.tennisstege.api.JPA.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.tennisstege.api.JPA.entitymodell.Ladder;
import org.tennisstege.api.JPA.entitymodell.LadderPlayer;
import org.tennisstege.api.JPA.entitymodell.User;

public interface LadderPlayerRepository extends JpaRepository<LadderPlayer, Long> {
	Optional<LadderPlayer> findByLadderAndPlayer(Ladder ladder, User player);
}
