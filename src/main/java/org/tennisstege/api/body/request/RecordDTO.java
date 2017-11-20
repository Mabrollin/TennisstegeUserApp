package org.tennisstege.api.body.request;

import org.tennisstege.api.JPA.entitymodell.MatchOutcome;

public class RecordDTO {
	private ScoreDTO score;
	private String duration;
	private MatchOutcome matchOutcome;
	public ScoreDTO getScore() {
		return score;
	}
	public void setScore(ScoreDTO score) {
		this.score = score;
	}
	public String getDuration() {
		return duration;
	}
	public void setDuration(String duration) {
		this.duration = duration;
	}
	public MatchOutcome getMatchOutcome() {
		return matchOutcome;
	}
	public void setMatchOutcome(MatchOutcome matchOutcome) {
		this.matchOutcome = matchOutcome;
	}
}
