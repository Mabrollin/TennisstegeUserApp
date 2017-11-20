package org.tennisstege.api.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.tennisstege.api.JPA.entitymodell.Ladder;
import org.tennisstege.api.JPA.repository.LadderRepository;
import org.tennisstege.api.service.LadderService;

@Service
public class LadderServiceImpl implements LadderService {

	@Autowired
	private LadderRepository ladderRepository;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public Ladder save(Ladder ladder) {
		ladder.setPassword(bCryptPasswordEncoder.encode(ladder.getPassword()));
        ladderRepository.save(ladder);
        return ladder;
	}

	@Override
	public Optional<Ladder> findByName(String name) {
		return ladderRepository.findByName(name);
	}

}
