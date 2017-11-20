package org.tennisstege.api.body.response;

public class ChallengeDTO {
	private Long id;
	private String challengerPlayerName;
	private String challengedPlayerName;
	private String creationDate;
	private String ladderName;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getChallengerPlayerName() {
		return challengerPlayerName;
	}
	public void setChallengerPlayerName(String challengerPlayerName) {
		this.challengerPlayerName = challengerPlayerName;
	}
	public String getChallengedPlayerName() {
		return challengedPlayerName;
	}
	public void setChallengedPlayerName(String challengedPlayerName) {
		this.challengedPlayerName = challengedPlayerName;
	}
	public String getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(String date) {
		this.creationDate = date;
	}
	public String getLadderName() {
		return ladderName;
	}
	public void setLadderName(String ladderName) {
		this.ladderName = ladderName;
	}
}
