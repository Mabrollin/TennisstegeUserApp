package org.tennisstege.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tennisstege.api.JPA.entitymodell.LadderPlayer;
import org.tennisstege.api.JPA.entitymodell.Record;
import org.tennisstege.api.JPA.repository.LadderPlayerRepository;
import org.tennisstege.api.service.RatingService;

@Service
public class RatingServiceImpl implements RatingService {
	private static final LadderPlayer NONE = null;
	@Autowired
	LadderPlayerRepository ladderPlayerRepository;

	@Override
	public void update(Record record) {
		LadderPlayer winner = NONE, loser = NONE;
		switch (record.getMatchOutcome()) {
		case CANCELLED:
			break;
		case CHALLANGED_WON:
			winner = record.getChallengedPlayer();
			loser = record.getChallengerPlayer();
			break;
		case CHALLANGER_WON:
			winner = record.getChallengerPlayer();
			loser = record.getChallengedPlayer();
			break;
		case DRAW:
			winner = NONE;
			loser = NONE;
			break;
		default:
			winner = NONE;
			loser = NONE;
			break;
		}
		if (winner.equals(NONE))
			winner.addRating(25l);
		if (loser.equals(NONE))
			loser.addRating(-25l);

	}

}
