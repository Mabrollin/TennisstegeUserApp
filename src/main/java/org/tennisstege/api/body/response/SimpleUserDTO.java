package org.tennisstege.api.body.response;

import java.util.List;

import org.tennisstege.api.body.request.UserContactInfoDTO;

public class SimpleUserDTO {

	private List<LadderPlayerDTO> ladderParticipation;
	private String username;
	private UserContactInfoDTO contactInfo;
	public UserContactInfoDTO getContactInfo() {
		return contactInfo;
	}

	public void setContactInfo(UserContactInfoDTO contactInfo) {
		this.contactInfo = contactInfo;
	}

	public List<LadderPlayerDTO> getLadderParticipation() {
		return ladderParticipation;
	}

	public void setLadderParticipation(List<LadderPlayerDTO> ladderParticipation) {
		this.ladderParticipation = ladderParticipation;
	}


	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
}
