package org.tennisstege.api.rest.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tennisstege.api.JPA.entitymodell.Ladder;
import org.tennisstege.api.JPA.entitymodell.Player;
import org.tennisstege.api.body.request.NewLadderDTO;
import org.tennisstege.api.body.response.PlayerDTO;
import org.tennisstege.api.controller.UserNotFoundException;
import org.tennisstege.api.service.UserService;

@Component
public class NewLadderMapper implements Mapper<NewLadderDTO, Ladder> {
	
	@Autowired
	UserService userService;
	
	@Override
	public Ladder mapToEntity(NewLadderDTO body) {
		Ladder ladder = new Ladder(
				body.getLadderName(),
				userService.findByUsername(body.getCreator()).orElseThrow(() -> new UserNotFoundException(body.getCreator())),
				body.getLadderType(), 
				body.getVisability(), 
				body.getJoinability());
		return ladder;
	}

	@Override
	public NewLadderDTO mapToDTO(Ladder ladder) {
		NewLadderDTO dto = new NewLadderDTO();
		dto.setLadderName(ladder.getName());
		dto.setCreator(ladder.getAdmins().stream().toString());
		dto.setLadderType(ladder.getLadderType());
		dto.setVisability(ladder.getVisability());
		dto.setJoinability(ladder.getJoinability());
		return dto;
	}
}
