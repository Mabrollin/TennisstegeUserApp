package org.tennisstege.api.JPA.entitymodell;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * @author Markus Brolin, This is a tennis score for now but will become
 *         abstract for future implementations of multiple score types
 */
@Entity
@Table(name = "score")
public class Score {

	@Id
	@GeneratedValue
	private Long id;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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
