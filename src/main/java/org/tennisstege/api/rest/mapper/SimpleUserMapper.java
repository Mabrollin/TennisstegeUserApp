package org.tennisstege.api.rest.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tennisstege.api.JPA.entitymodell.User;
import org.tennisstege.api.body.request.UserContactInfoDTO;
import org.tennisstege.api.body.response.LadderDTO;
import org.tennisstege.api.body.response.PlayerDTO;
import org.tennisstege.api.body.response.PlayerProfileDTO;
import org.tennisstege.api.service.UserService;

@Component
public class SimpleUserMapper implements Mapper<PlayerProfileDTO, User> {

	@Autowired
	private LadderMapper ladderMapper;
	
	@Autowired
	private ContactInfoMapper contactInfoMapper;
	
	@Autowired
	private UserService userService;

	@Override
	public PlayerProfileDTO mapToDTO(User user) {
		PlayerProfileDTO dto = new PlayerProfileDTO();
		dto.setUsername(user.getUsername());
		user.getUserContactInfo();
		List<LadderDTO> ladderParticipationDTO = user.getLadderParticipations()
				.stream().map(ladder -> ladderMapper.mapToDTO(ladder)).collect(Collectors.toList());
		UserContactInfoDTO contactInfoDTO = contactInfoMapper.mapToDTO(user.getUserContactInfo());
		dto.setLadderParticipation(ladderParticipationDTO);
		dto.setContactInfo(contactInfoDTO);
		return dto;
	}

	@Override
	/**
	 *  Not really a mapper, Using userService.findByUsername
	 */
	public User mapToEntity(PlayerProfileDTO dto) {
		return userService.findByUsername(dto.getUsername()).get();
	}
}
