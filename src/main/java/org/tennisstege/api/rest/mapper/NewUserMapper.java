package org.tennisstege.api.rest.mapper;

import org.springframework.stereotype.Component;
import org.tennisstege.api.JPA.entitymodell.User;
import org.tennisstege.api.JPA.entitymodell.UserContactInfo;
import org.tennisstege.api.body.request.NewUserDTO;

@Component
public class NewUserMapper implements Mapper<NewUserDTO, User> {

	@Override
	public NewUserDTO mapToDTO(User user) {
		NewUserDTO newUserDTO = new NewUserDTO();
		newUserDTO.setUsername(user.getUsername());
		//passwords are returned as null	
		UserContactInfo userContactInfo = user.getUserContactInfo();
		newUserDTO.setFirstName(userContactInfo.getFirstName());
		newUserDTO.setLastName(userContactInfo.getLastName());
		newUserDTO.setEmail(userContactInfo.getEmail());
		newUserDTO.setPhoneNumber(userContactInfo.getPhoneNumber());
		
		return newUserDTO;
	}

	@Override
	public User mapToEntity(NewUserDTO dto) {

		User User = new User(dto.getUsername(), dto.getPassword());

		UserContactInfo userContactInfo = new UserContactInfo();
		userContactInfo.setFirstName(dto.getFirstName());
		userContactInfo.setLastName(dto.getLastName());
		userContactInfo.setPhoneNumber(dto.getPhoneNumber());
		userContactInfo.setEmail(dto.getEmail());
		User.setUserContactInfo(userContactInfo);

		return User;
	}
}
