package org.tennisstege.api.service;

import org.tennisstege.api.JPA.entitymodell.Ladder;
import org.tennisstege.api.JPA.entitymodell.Player;

public interface PlayerService {

	void addPlayerToLadder(Ladder ladder, Player player);
}