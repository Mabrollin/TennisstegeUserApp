package org.tennisstege.api.service;

import java.util.Optional;

import org.tennisstege.api.JPA.entitymodell.Ladder;

public interface LadderService {
    Ladder save(Ladder ladder);

    Optional<Ladder> findByName(String name);
}