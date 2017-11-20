package org.tennisstege.api.JPA.entitymodell;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "challenge")
public class Challenge {
	@Id
	@GeneratedValue
	private Long id;
	@ManyToOne(optional = false)
	@JoinColumn(name = "challenger_id", nullable = false)
	private LadderPlayer challengerPlayer;

	@ManyToOne(optional = false)
	@JoinColumn(name = "challenged_id", nullable = false)
	private LadderPlayer challengedPlayer;

	private LocalDateTime creationDate;

	@ManyToOne(optional = false)
	@JoinColumn(name = "ladder_id", nullable = false)
	private Ladder ladder;

	public Long getId() {
		return id;
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

	public void setId(Long id) {
		this.id = id;
	}

	public void setId(long id) {
		this.id = id;
	}

	Challenge() { // jpa only
	}

	public Challenge(LadderPlayer challengerPlayer, LadderPlayer challengedPlayer, LocalDateTime creationDate, Ladder ladder) {
		this.challengerPlayer = challengerPlayer;
		this.challengedPlayer = challengedPlayer;
		this.creationDate = creationDate;
		this.ladder = ladder;
	}
}
