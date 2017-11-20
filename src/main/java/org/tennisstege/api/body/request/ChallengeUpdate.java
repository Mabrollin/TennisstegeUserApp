package org.tennisstege.api.body.request;

import java.time.LocalDateTime;

public class ChallengeUpdate {
	private LocalDateTime date;

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}
}
