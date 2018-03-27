package org.tennisstege.api.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.tennisstege.api.JPA.entitymodell.Ladder;
import org.tennisstege.api.JPA.entitymodell.Player;
import org.tennisstege.api.JPA.repository.LadderRepository;
import org.tennisstege.api.service.LadderService;

@Service
public class LadderServiceImpl implements LadderService {

	private final long DEFAULT_MAX_SIZE = 8;
	@Autowired
	private LadderRepository ladderRepository;

	@Override
	public Ladder save(Ladder ladder) {
		ladder.setMaxSize(DEFAULT_MAX_SIZE);
        ladderRepository.save(ladder);
        return ladder;
	}

	@Override
	public Optional<Ladder> findByName(String name) {
		return ladderRepository.findByName(name);
	}

	@Override
	public List<Ladder> searchByName(String name) {
		return ladderRepository.findByNameLike(name);
	}

}
