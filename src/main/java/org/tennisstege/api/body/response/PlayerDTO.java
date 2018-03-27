package org.tennisstege.api.body.response;

public class PlayerDTO {
	private String username;
	private Long rating;
	private int position;

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

	public int getPosition() {
		return position;
	}

	public void setPosition(int i) {
		this.position = i;
	}

}
