package org.tennisstege.api.JPA.entitymodell;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "user")
public class User {
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy="player")
	private Set<LadderPlayer> ladderParticipations;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "user_contact_info_id")
	private UserContactInfo userContactInfo;
	
	@ManyToMany
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles;
	
	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	
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

	public String getPasswordConfirm() {
		return passwordConfirm;
	}

	public void setPasswordConfirm(String passwordConfirm) {
		this.passwordConfirm = passwordConfirm;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String password;
	public String passwordConfirm;
	public String username;

	public User(String name, String password) {
		this.username = name;
		this.password = password;
		this.userContactInfo = new UserContactInfo();
		this.roles = new HashSet<>();
		
	}

	User() { // jpa only
	}

	public User(User input) {
		this.ladderParticipations = new HashSet<>();
		this.username = input.username;
		this.password = input.password;
		this.userContactInfo = input.userContactInfo;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public UserContactInfo getUserContactInfo() {
		return userContactInfo;
	}

	public void setUserContactInfo(UserContactInfo userContactInfo) {
		this.userContactInfo = userContactInfo;
	}

	public List<String> getLadderNames() {
		List<String> names = new ArrayList<>();
		ladderParticipations.forEach(ladderParticipation -> names.add(ladderParticipation.getLadder().name));
		return names;
	}

	public void updateUserContactInfo(UserContactInfo userContactInfo) {
		this.userContactInfo.updateWith(userContactInfo);
		
	}
}
