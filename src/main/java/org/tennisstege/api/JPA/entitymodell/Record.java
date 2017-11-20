package org.tennisstege.api.JPA.entitymodell;

import java.time.Duration;
import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "record")
public class Record {

	@Id
	@GeneratedValue
	private Long id;
	@ManyToOne
	@JoinColumn(name = "challenger_id", nullable = false)
	private LadderPlayer challengerPlayer;

	@ManyToOne
	@JoinColumn(name = "challenged_id", nullable = false)
	private LadderPlayer challengedPlayer;

	@GeneratedValue
	private LocalDateTime created;

	private Duration duration;

	private MatchOutcome matchOutcome;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "score_id")
	private Score score;

	private String reason;

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	@ManyToOne
	@JoinColumn(name = "ladder_id", nullable = false)
	private Ladder ladder;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LadderPlayer getChallengerPlayer() {
		return challengerPlayer;
	}

	public void setChallengerPlayer(LadderPlayer challengerPlayer) {
		this.challengerPlayer = challengerPlayer;
	}

	public LadderPlayer getChallengedPlayer() {
		return challengedPlayer;
	}

	public void setChallengedPlayer(LadderPlayer challengedPlayer) {
		this.challengedPlayer = challengedPlayer;
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

	public Record(LadderPlayer challengerPlayer, LadderPlayer challengedPlayer, LocalDateTime created,
			Duration duration, MatchOutcome matchOutcome, Score score, Ladder ladder, String reason) {
		super();
		this.challengerPlayer = challengerPlayer;
		this.challengedPlayer = challengedPlayer;
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
		sb.append("Match between ").append(challengerPlayer).append(" and ").append(challengedPlayer);
		switch (matchOutcome) {
		case CANCELLED:
			sb.append("was cancelled due to ").append(reason);
			break;
		case CHALLANGED_WON:
			sb.append("was won by: ").append(this.challengedPlayer);
			break;
		case CHALLANGER_WON:
			sb.append("was won by: ").append(this.challengerPlayer);
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
