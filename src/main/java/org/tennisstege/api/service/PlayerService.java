package org.tennisstege.api.service;

import java.util.Optional;

import org.tennisstege.api.JPA.entitymodell.Ladder;
import org.tennisstege.api.JPA.entitymodell.LadderPlayer;

public interface PlayerService {

    Optional<LadderPlayer> findByLadderAndUsername(Ladder ladder, String username);

	LadderPlayer save(LadderPlayer player);
}