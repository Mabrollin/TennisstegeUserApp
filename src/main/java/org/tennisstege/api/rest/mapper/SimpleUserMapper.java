package org.tennisstege.api.rest.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tennisstege.api.JPA.entitymodell.User;
import org.tennisstege.api.body.request.UserContactInfoDTO;
import org.tennisstege.api.body.response.LadderPlayerDTO;
import org.tennisstege.api.body.response.SimpleUserDTO;
import org.tennisstege.api.service.UserService;

@Component
public class SimpleUserMapper implements Mapper<SimpleUserDTO, User> {

	@Autowired
	private LadderPlayerMapper ladderPlayerMapper;
	
	@Autowired
	private ContactInfoMapper contactInfoMapper;
	
	@Autowired
	private UserService userService;

	@Override
	public SimpleUserDTO mapToDTO(User user) {
		SimpleUserDTO dto = new SimpleUserDTO();
		dto.setUsername(user.getUsername());
		user.getUserContactInfo();
		List<LadderPlayerDTO> ladderParticipationDTO = new ArrayList<>();
		user.getLadderParticipations().forEach(ladderPlayer ->  ladderParticipationDTO.add(ladderPlayerMapper.mapToDTO(ladderPlayer)));
		UserContactInfoDTO contactInfoDTO = contactInfoMapper.mapToDTO(user.getUserContactInfo());
		dto.setLadderParticipation(ladderParticipationDTO);
		dto.setContactInfo(contactInfoDTO);
		return dto;
	}

	@Override
	/**
	 *  Not really a mapper, Using userService.findByUsername
	 */
	public User mapToEntity(SimpleUserDTO dto) {
		return userService.findByUsername(dto.getUsername()).get();
	}
}
