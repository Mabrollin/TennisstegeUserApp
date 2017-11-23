package org.tennisstege.api.security.filter;

public class TokenBody {
	private String token;

	public TokenBody(String token) {
		this.token = token;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
}
