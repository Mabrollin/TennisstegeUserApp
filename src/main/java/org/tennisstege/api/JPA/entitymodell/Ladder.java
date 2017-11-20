package org.tennisstege.api.JPA.entitymodell;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Ladder {

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "ladder")
	private Set<LadderPlayer> players;

	public Set<LadderPlayer> getPlayers() {
		return players;
	}

	public void setPlayers(Set<LadderPlayer> players) {
		this.players = players;
	}

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "ladder")
	private Set<Challenge> challenges;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "ladder")
	private Set<Record> records;

	@Id
	@GeneratedValue
	private Long id;
	public String password;
	public String name;

	public Long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public Ladder(String name, String password) {
		this.players = new HashSet<LadderPlayer>();
		this.challenges = new HashSet<>();
		this.records = new HashSet<>();
		this.name = name;
		this.password = password;
	}

	public Set<Record> getRecords() {
		return records;
	}

	public void setRecords(Set<Record> records) {
		this.records = records;
	}

	public void setId(Long id) {
		this.id = id;
	}

	Ladder() { // jpa only
	}

	public Set<Challenge> getChallenges() {
		return challenges;
	}

	public void setChallenges(Set<Challenge> challenges) {
		this.challenges = challenges;
	}
}
