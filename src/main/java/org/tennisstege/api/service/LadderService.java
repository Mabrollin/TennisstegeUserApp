package org.tennisstege.api.service;

import java.util.List;
import java.util.Optional;

import org.tennisstege.api.JPA.entitymodell.Ladder;
import org.tennisstege.api.JPA.entitymodell.Player;

public interface LadderService {
    Ladder save(Ladder ladder);

    Optional<Ladder> findByName(String name);

	List<Ladder> searchByName(String name);
}