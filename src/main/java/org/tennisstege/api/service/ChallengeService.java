package org.tennisstege.api.service;

import java.time.Duration;

import org.tennisstege.api.JPA.entitymodell.Challenge;
import org.tennisstege.api.JPA.entitymodell.MatchOutcome;
import org.tennisstege.api.JPA.entitymodell.Record;
import org.tennisstege.api.JPA.entitymodell.Score;
import org.tennisstege.api.body.request.ChallengeUpdate;

public interface ChallengeService {
    Challenge newChallenge(Challenge challenge);
	Challenge removeChallenge(Challenge challengeToRemove);
	Challenge updateChallenge(Challenge challengeToUpdate, ChallengeUpdate update);
	Record cancelChallenge(Challenge challengeToCancel, String reason);
	Record recordChallenge(Challenge challengeToRecord, Score score, MatchOutcome matchOutcome, Duration duration);
	Challenge getChallenge(Long challengeId);
}