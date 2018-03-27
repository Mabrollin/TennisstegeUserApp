package org.tennisstege.api.JPA.entitymodell;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * 
 * @author Markus Brolin, This is a tennis score for now but will become
 *         abstract for future implementations of multiple score types
 */
public class Score {

	@Id
	@GeneratedValue
	private String id;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	private String setsAndBalls;

	public String getSetsAndBalls() {
		return setsAndBalls;
	}

	public void setSetsAndBalls(String setsAndBalls) {
		this.setsAndBalls = setsAndBalls;
	}

}
