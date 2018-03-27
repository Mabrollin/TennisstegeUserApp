package org.tennisstege.api.JPA.entitymodell;

import java.time.Duration;
import java.time.LocalDateTime;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.springframework.data.mongodb.core.mapping.DBRef;

public class Record {

	@Id
	@GeneratedValue
	private String id;
	
	@DBRef
	private User ChallengerPlayer;

	@DBRef
	private User ChallengedPlayer;

	@GeneratedValue
	private LocalDateTime created;

	private Duration duration;

	private MatchOutcome matchOutcome;

	private Score score;

	private String reason;

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	@DBRef
	private Ladder ladder;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public User getChallengerPlayer() {
		return ChallengerPlayer;
	}

	public void setChallengerPlayer(User ChallengerPlayer) {
		this.ChallengerPlayer = ChallengerPlayer;
	}

	public User getChallengedPlayer() {
		return ChallengedPlayer;
	}

	public void setChallengedPlayer(User ChallengedPlayer) {
		this.ChallengedPlayer = ChallengedPlayer;
	}

	public LocalDateTime getCreated() {
		return created;
	}

	public void setCreated(LocalDateTime created) {
		this.created = created;
	}

	public Duration getDuration() {
		return duration;
	}

	public void setDuration(Duration duration) {
		this.duration = duration;
	}

	public MatchOutcome getMatchOutcome() {
		return matchOutcome;
	}

	public void setMatchOutcome(MatchOutcome matchOutcome) {
		this.matchOutcome = matchOutcome;
	}

	public Score getScore() {
		return score;
	}

	public void setScore(Score score) {
		this.score = score;
	}

	public Ladder getLadder() {
		return ladder;
	}

	public void setLadder(Ladder ladder) {
		this.ladder = ladder;
	}

	public Record(User ChallengerPlayer, User ChallengedPlayer, LocalDateTime created,
			Duration duration, MatchOutcome matchOutcome, Score score, Ladder ladder, String reason) {
		super();
		this.ChallengerPlayer = ChallengerPlayer;
		this.ChallengedPlayer = ChallengedPlayer;
		this.created = created;
		this.duration = duration;
		this.matchOutcome = matchOutcome;
		this.score = score;
		this.ladder = ladder;
		this.reason = reason;
	}

	Record() { // jpa only
	}

	public Record(Challenge challenge, Score score, LocalDateTime created, MatchOutcome matchOutcome,
			Duration duration, String reason) {
		setChallengedPlayer(challenge.getChallengedPlayer());
		setChallengerPlayer(challenge.getChallengerPlayer());
		setLadder(challenge.getLadder());
		setCreated(created);
		setMatchOutcome(matchOutcome);
		setDuration(duration);
		setScore(score);
		setReason(reason);
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Match between ").append(ChallengerPlayer).append(" and ").append(ChallengedPlayer);
		switch (matchOutcome) {
		case CANCELLED:
			sb.append("was cancelled due to ").append(reason);
			break;
		case CHALLANGED_WON:
			sb.append("was won by: ").append(this.ChallengedPlayer);
			break;
		case CHALLANGER_WON:
			sb.append("was won by: ").append(this.ChallengerPlayer);
			break;
		case DRAW:
			sb.append("was a draw");
			break;
		default:
			break;

		}
		if (duration != null) {
			sb.append("lasted ").append(duration.toString());
		}
		if (score != null) {
			sb.append("score: ").append(score.toString());
		}
		return sb.toString();
	}

}
