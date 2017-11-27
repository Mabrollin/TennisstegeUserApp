package org.tennisstege.api.rest.mapper;

import org.springframework.stereotype.Component;
import org.tennisstege.api.JPA.entitymodell.UserContactInfo;
import org.tennisstege.api.body.request.UserContactInfoDTO;

@Component
public class ContactInfoMapper implements Mapper<UserContactInfoDTO, UserContactInfo> {

	@Override
	public UserContactInfoDTO mapToDTO(UserContactInfo entity) {
		UserContactInfoDTO dto = new UserContactInfoDTO();
		dto.setFirstName(entity.getFirstName());
		dto.setLastName(entity.getLastName());
		dto.setPhoneNumber(entity.getPhoneNumber());
		dto.setEmail(entity.getEmail());
		return dto;
	}

	@Override
	public UserContactInfo mapToEntity(UserContactInfoDTO dto) {
		UserContactInfo entity = new UserContactInfo();
		entity.setFirstName(dto.getFirstName());
		entity.setLastName(dto.getLastName());
		entity.setPhoneNumber(dto.getPhoneNumber());
		entity.setEmail(dto.getEmail());
		return entity;
	}



}
