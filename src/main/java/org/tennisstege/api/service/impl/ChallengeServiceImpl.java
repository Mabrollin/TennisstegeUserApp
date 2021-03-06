package org.tennisstege.api.service.impl;

import java.time.Duration;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tennisstege.api.JPA.entitymodell.Challenge;
import org.tennisstege.api.JPA.entitymodell.Ladder;
import org.tennisstege.api.JPA.entitymodell.MatchOutcome;
import org.tennisstege.api.JPA.entitymodell.Record;
import org.tennisstege.api.JPA.entitymodell.Score;
import org.tennisstege.api.JPA.repository.ChallengeRepository;
import org.tennisstege.api.JPA.repository.RecordRepository;
import org.tennisstege.api.body.request.ChallengeUpdate;
import org.tennisstege.api.service.ChallengeService;
import org.tennisstege.api.service.LadderService;
import org.tennisstege.api.service.RatingService;

@Service
public class ChallengeServiceImpl implements ChallengeService {

	@Autowired
	private ChallengeRepository challengeRepository;
	
	@Autowired
	private RecordRepository recordRepository;
	
	@Autowired
	private RatingService ratingService;
	
	@Autowired
	private LadderService ladderService;

	@Override
	public Record recordChallenge(Challenge challengeToRecord, Score score, MatchOutcome matchOutcome, Duration duration) {
		Record record = new Record(challengeToRecord, score, LocalDateTime.now(), matchOutcome, duration, null);
		recordRepository.save(record);
		// TODO add score-change to record?
		
		Ladder ladder = record.getLadder();
		ladder.getRecords().add(record);
		
		ratingService.update(record);
		
		ladder.getChallenges().remove(challengeToRecord);
		ladderService.save(record.getLadder());
		
		challengeRepository.delete(challengeToRecord);
		
		
		return record;
	}

	@Override
	public Challenge removeChallenge(Challenge challengeToRemove) {
		challengeRepository.delete(challengeToRemove);
		return challengeToRemove;
	}

	@Override
	public Challenge updateChallenge(Challenge challengeToUpdate, ChallengeUpdate update) {
		challengeToUpdate.setCreationDate(update.getDate());
		return challengeToUpdate;
	}

	@Override
	public Record cancelChallenge(Challenge challengeToCancel, String reason) {
		Record record = new Record(challengeToCancel, null, LocalDateTime.now(), MatchOutcome.CANCELLED, null, reason);
		recordRepository.save(record);
		challengeRepository.delete(challengeToCancel);
		return record;
	}

	@Override
	public Challenge newChallenge(Challenge challenge) {

		challengeRepository.save(challenge);
		challenge.getLadder().getChallenges().add(challenge);
		ladderService.save(challenge.getLadder());
		return challenge;

	}

	@Override
	public Challenge getChallenge(String id) {
		return challengeRepository.findOne(id);
	}

}
