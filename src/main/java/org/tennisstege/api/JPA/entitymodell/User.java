package org.tennisstege.api.JPA.entitymodell;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import org.springframework.data.mongodb.core.mapping.DBRef;

public class User {
	
	public String password;
	public String username;
	
	@DBRef
	private Set<Ladder> ladderParticipations;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private String id;
	
	private UserContactInfo userContactInfo;
	
	@DBRef
	private Set<Role> roles;
	
	public Set<Ladder> getLadderParticipations() {
		return ladderParticipations;
	}

	public void setLadderParticipations(Set<Ladder> ladderParticipations) {
		this.ladderParticipations = ladderParticipations;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public User(String name, String password) {
		this.username = name;
		this.password = password;
		this.userContactInfo = new UserContactInfo();
		this.roles = new HashSet<>();
		
	}
	
	User(){
		ladderParticipations = new HashSet<>();
		roles = new HashSet<>();
	}

	public User(User input) {
		this.ladderParticipations = new HashSet<>();
		this.username = input.username;
		this.password = input.password;
		this.userContactInfo = input.userContactInfo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
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
		User other = (User) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

	public UserContactInfo getUserContactInfo() {
		return userContactInfo;
	}

	public void setUserContactInfo(UserContactInfo userContactInfo) {
		this.userContactInfo = userContactInfo;
	}


	public void updateUserContactInfo(UserContactInfo userContactInfo) {
		this.userContactInfo.updateWith(userContactInfo);
		
	}
}
