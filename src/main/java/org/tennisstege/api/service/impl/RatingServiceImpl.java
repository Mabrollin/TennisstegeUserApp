package org.tennisstege.api.service.impl;

import org.springframework.stereotype.Service;
import org.tennisstege.api.JPA.entitymodell.Ladder;
import org.tennisstege.api.JPA.entitymodell.Player;
import org.tennisstege.api.JPA.entitymodell.Record;
import org.tennisstege.api.service.RatingService;

@Service
public class RatingServiceImpl implements RatingService {
	private static final Player NONE = null;
	private final Double K_FACTOR = 32.0;

	@Override
	public void update(Record record) {
		Player winner = NONE, loser = NONE;
		Ladder ladder = record.getLadder();
		Player challengedPlayer = ladder.getPlayerMap().get(record.getChallengedPlayer().getUsername());
		Player challengerPlayer = ladder.getPlayerMap().get(record.getChallengerPlayer().getUsername());
		switch (record.getMatchOutcome()) {
		case CANCELLED:
			break;
		case CHALLANGED_WON:
			winner = challengedPlayer;
			loser = challengerPlayer;
			break;
		case CHALLANGER_WON:
			winner = challengerPlayer;
			loser = challengedPlayer;
			break;
		case DRAW:
			winner = NONE;
			loser = NONE;
			return;
		default:
			throw new RuntimeException("Error finding matchOutcome in RatingService.update");
		}

		if (ladder.isPositionUpdateable())
			updatePosition(winner, loser, ladder);
		if (ladder.isRatingUpdateable())
			updateRating(challengerPlayer, challengedPlayer, winner, loser, ladder);

	}

	private void updatePosition(Player winner, Player loser, Ladder ladder) {
		if (winner != null && loser != null) {
			int winnerIndex = ladder.positionOf(winner);
			int loserIndex = ladder.positionOf(loser);
			if (winnerIndex > loserIndex) {
				ladder.getPositionList().remove(winnerIndex);
				ladder.getPositionList().add(loserIndex, winner.getUser().getUsername());
			}

		}
	}

	private void updateRating(Player player1, Player player2, Player winner, Player loser,
			Ladder ladder) {
		Double winnerTransformedRating = toTransformatedRating(winner.getRating());
		Double loserTransformedRating = toTransformatedRating(winner.getRating());
		Double transformedSum = winnerTransformedRating + loserTransformedRating;

		Double winnerExpectedTransformedRating = winnerTransformedRating / transformedSum;
		Double loserExpectedTransformedRating = loserTransformedRating / transformedSum;

		winner.addRating((long) (K_FACTOR * (1.0 - winnerExpectedTransformedRating)));
		loser.addRating((long) (K_FACTOR * (0.0 - loserExpectedTransformedRating)));

	}

	private Double toTransformatedRating(Long rating) {
		double exp = rating / 400.0;
		return Math.pow(10, exp);
	}

}
