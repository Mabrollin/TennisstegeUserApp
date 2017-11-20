package org.tennisstege.api.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.tennisstege.api.JPA.entitymodell.Ladder;
import org.tennisstege.api.JPA.entitymodell.LadderPlayer;
import org.tennisstege.api.JPA.entitymodell.User;
import org.tennisstege.api.JPA.repository.LadderPlayerRepository;
import org.tennisstege.api.service.PlayerService;
import org.tennisstege.api.service.UserService;

@Service
public class PlayerServiceImpl implements PlayerService {
	@Autowired
	LadderPlayerRepository playerRepository;
	@Autowired
	UserService userService;

	@Override
	public Optional<LadderPlayer> findByLadderAndUsername(Ladder ladder, String username) {
		User player = userService.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username));
		return playerRepository.findByLadderAndPlayer(ladder, player);
	}

	@Override
	public LadderPlayer save(LadderPlayer player) {
	playerRepository.save(player);
		return player;
	}

}
