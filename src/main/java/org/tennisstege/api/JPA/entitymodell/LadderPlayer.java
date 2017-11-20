package org.tennisstege.api.JPA.entitymodell;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ladder_player")
public class LadderPlayer {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	public LadderPlayer(User user, Ladder ladder, Long rating) {
		this.player = user;
		this.ladder = ladder;
		this.rating = rating;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@ManyToOne(optional = false)
	@JoinColumn(name = "user_id", nullable = false)
	private User player;
	@ManyToOne
	@JoinColumn(name = "ladder_id", nullable = false)
	private Ladder ladder;
	
	private Long rating;

	public User getPlayer() {
		return player;
	}

	public void setPlayer(User player) {
		this.player = player;
	}

	public Ladder getLadder() {
		return ladder;
	}

	public void setLadder(Ladder ladder) {
		this.ladder = ladder;
	}

	public Long getRating() {
		return rating;
	}

	public void setRating(Long rating) {
		this.rating = rating;
	}

	public void addRating(Long rating) {
		this.rating += rating;

	}
	LadderPlayer(){//JPA only
		
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ladder == null) ? 0 : ladder.hashCode());
		result = prime * result + ((player == null) ? 0 : player.hashCode());
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
		LadderPlayer other = (LadderPlayer) obj;
		if (ladder == null) {
			if (other.ladder != null)
				return false;
		} else if (!ladder.equals(other.ladder))
			return false;
		if (player == null) {
			if (other.player != null)
				return false;
		} else if (!player.equals(other.player))
			return false;
		return true;
	}
}
