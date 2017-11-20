package org.tennisstege.api.body.request;

public class NewChallengeDTO {
	private String date;
	private String challengerPlayer;
	private String challengedPlayer;
	private String ladder;
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getChallengerPlayer() {
		return challengerPlayer;
	}
	public void setChallengerPlayer(String challengerPlayer) {
		this.challengerPlayer = challengerPlayer;
	}
	public String getChallengedPlayer() {
		return challengedPlayer;
	}
	public void setChallengedPlayer(String challengedPlayer) {
		this.challengedPlayer = challengedPlayer;
	}	
	public String getLadder() {
		return ladder;
	}
	public void setLadder(String ladder) {
		this.ladder = ladder;
	}
	
}
