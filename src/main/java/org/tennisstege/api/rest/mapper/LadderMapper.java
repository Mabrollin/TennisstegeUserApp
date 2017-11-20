package org.tennisstege.api.rest.mapper;

import org.springframework.stereotype.Component;
import org.tennisstege.api.JPA.entitymodell.Ladder;
import org.tennisstege.api.JPA.entitymodell.LadderPlayer;
import org.tennisstege.api.body.request.NewLadderDTO;
import org.tennisstege.api.body.response.LadderPlayerDTO;

@Component
public class LadderMapper implements Mapper<NewLadderDTO, Ladder> {
	
	@Override
	public Ladder mapToEntity(NewLadderDTO body) {
		Ladder ladder = new Ladder(
				body.getName(),
				body.getPassword());
		
		return ladder;
	}

	@Override
	public NewLadderDTO mapToDTO(Ladder ladder) {
		NewLadderDTO dto = new NewLadderDTO();
		dto.setName(ladder.getName());
		dto.setPassword("********");
		return dto;
	}
}
