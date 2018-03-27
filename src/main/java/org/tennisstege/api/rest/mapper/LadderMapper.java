package org.tennisstege.api.rest.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tennisstege.api.JPA.entitymodell.Ladder;
import org.tennisstege.api.body.response.LadderDTO;
import org.tennisstege.api.service.LadderService;

@Component
public class LadderMapper implements Mapper<LadderDTO, Ladder> {
	
	@Autowired
	LadderService ladderService;

	@Override
	/**
	 * Not really a mapper
	 */
	public Ladder mapToEntity(LadderDTO body) {
		return ladderService.findByName(body.getName()).orElse(null);
	}

	@Override
	public LadderDTO mapToDTO(Ladder ladder) {
		LadderDTO dto = new LadderDTO();
		dto.setName(ladder.getName());
		dto.setId(ladder.getId());
		return dto;
	}
}
