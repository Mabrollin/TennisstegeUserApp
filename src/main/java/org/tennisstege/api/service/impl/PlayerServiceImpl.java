package org.tennisstege.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tennisstege.api.JPA.entitymodell.Ladder;
import org.tennisstege.api.JPA.entitymodell.Player;
import org.tennisstege.api.service.LadderService;
import org.tennisstege.api.service.PlayerService;
import org.tennisstege.api.service.UserService;

@Service
public class PlayerServiceImpl implements PlayerService {

	@Autowired
	LadderService ladderService;
	
	@Autowired
	UserService userService;
	
	@Override
	public void addPlayerToLadder(Ladder ladder, Player player) {
		String username = player.getUser().getUsername();
		ladder.getPlayerMap().put(player.getUser().getUsername(), player);
		ladder.getPositionList().add(username);
		player.getUser().getLadderParticipations().add(ladder);
		ladderService.save(ladder);
		userService.save(player.getUser());
	}

}
