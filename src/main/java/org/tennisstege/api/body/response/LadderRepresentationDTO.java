package org.tennisstege.api.body.response;

import java.util.List;

import org.tennisstege.api.body.request.RecordDTO;

public class LadderRepresentationDTO {
	private String ladderName;
	private List<PlayerDTO> players;
	private List<ChallengeDTO> challenges;
	private List<RecordDTO> records;
	public String getLadderName() {
		return ladderName;
	}
	public void setLadderName(String ladderName) {
		this.ladderName = ladderName;
	}
	public List<PlayerDTO> getPlayers() {
		return players;
	}
	public void setPlayers(List<PlayerDTO> players) {
		this.players = players;
	}
	public void setChallenges(List<ChallengeDTO> challenges) {
		this.challenges = challenges;
		
	}
	public List<ChallengeDTO> getChallenges() {
		return challenges;
	}
	public List<RecordDTO> getRecords() {
		return records;
	}
	public void setRecords(List<RecordDTO> records) {
		this.records = records;
	}
	
}
