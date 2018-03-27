package org.tennisstege.api.body.response;

import java.util.List;

import org.tennisstege.api.JPA.entitymodell.Ladder;
import org.tennisstege.api.body.request.UserContactInfoDTO;

public class PlayerProfileDTO {

	private List<LadderDTO> ladderParticipation;
	private String username;
	private UserContactInfoDTO contactInfo;
	public UserContactInfoDTO getContactInfo() {
		return contactInfo;
	}

	public void setContactInfo(UserContactInfoDTO contactInfo) {
		this.contactInfo = contactInfo;
	}

	public List<LadderDTO> getLadderParticipation() {
		return ladderParticipation;
	}

	public void setLadderParticipation(List<LadderDTO> ladderParticipation) {
		this.ladderParticipation = ladderParticipation;
	}


	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
}
