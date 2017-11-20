package org.tennisstege.api.JPA.entitymodell;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user_contact_info")
public class UserContactInfo {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String firstName;
	private String lastName;
	private String email;
	private String phoneNumber;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void updateWith(UserContactInfo other) {
		this.firstName = other.firstName != null? other.firstName: this.firstName;
		this.lastName = other.lastName != null? other.lastName: this.lastName;
		this.email = other.email != null? other.email: this.email;
		this.phoneNumber = other.phoneNumber != null? other.phoneNumber: this.phoneNumber;
		
		
		
	}
}
