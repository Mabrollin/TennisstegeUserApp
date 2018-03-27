package org.tennisstege.api.JPA.entitymodell;

import java.time.LocalDateTime;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.springframework.data.mongodb.core.mapping.DBRef;

public class Challenge {
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((challengedPlayer == null) ? 0 : challengedPlayer.hashCode());
		result = prime * result + ((challengerPlayer == null) ? 0 : challengerPlayer.hashCode());
		result = prime * result + ((creationDate == null) ? 0 : creationDate.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((ladder == null) ? 0 : ladder.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Challenge other = (Challenge) obj;
		if (challengedPlayer == null) {
			if (other.challengedPlayer != null)
				return false;
		} else if (!challengedPlayer.equals(other.challengedPlayer))
			return false;
		if (challengerPlayer == null) {
			if (other.challengerPlayer != null)
				return false;
		} else if (!challengerPlayer.equals(other.challengerPlayer))
			return false;
		if (creationDate == null) {
			if (other.creationDate != null)
				return false;
		} else if (!creationDate.equals(other.creationDate))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (ladder == null) {
			if (other.ladder != null)
				return false;
		} else if (!ladder.equals(other.ladder))
			return false;
		return true;
	}

	@Id
	@GeneratedValue
	private String id;
	
	@DBRef
	private User challengerPlayer;

	@DBRef
	private User challengedPlayer;

	private LocalDateTime creationDate;

	@DBRef(lazy = true)
	private Ladder ladder;

	public String getId() {
		return id;
	}
	
	public User getChallengerPlayer() {
		return challengerPlayer;
	}

	public void setChallengerPlayer(User challengerPlayer) {
		this.challengerPlayer = challengerPlayer;
	}

	public User getChallengedPlayer() {
		return challengedPlayer;
	}

	public void setChallengedPlayer(User challengedPlayer) {
		this.challengedPlayer = challengedPlayer;
	}

	public LocalDateTime getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(LocalDateTime creationDate) {
		this.creationDate = creationDate;
	}

	public Ladder getLadder() {
		return ladder;
	}

	public void setLadder(Ladder ladder) {
		this.ladder = ladder;
	}


	public void setId(String id) {
		this.id = id;
	}

	public Challenge(User challengerPlayer, User challengedPlayer, LocalDateTime creationDate, Ladder ladder) {
		this.challengerPlayer = challengerPlayer;
		this.challengedPlayer = challengedPlayer;
		this.creationDate = creationDate;
		this.ladder = ladder;
	}
}
