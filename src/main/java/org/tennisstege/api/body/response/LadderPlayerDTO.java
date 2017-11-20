package org.tennisstege.api.body.response;

public class LadderPlayerDTO {
	private String username;
	private String ladderName;
	private Long rating;

	public Long getRating() {
		return rating;
	}

	public void setRating(Long rating) {
		this.rating = rating;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getLadderName() {
		return ladderName;
	}

	public void setLadderName(String ladderName) {
		this.ladderName = ladderName;
	}
}
