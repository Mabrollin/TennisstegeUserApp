package org.tennisstege.api.rest.mapper;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Component;
import org.tennisstege.api.body.request.ChallengeUpdate;
import org.tennisstege.api.body.request.ChallengeUpdateDTO;

@Component
public class UpdateChallengeMapper implements Mapper<ChallengeUpdateDTO, ChallengeUpdate> {
	private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

	@Override
	public ChallengeUpdate mapToEntity(ChallengeUpdateDTO body) {
		ChallengeUpdate update = new ChallengeUpdate();
		update.setDate(LocalDateTime.parse(body.getDate(), formatter));
		return update;
	}

	@Override
	public ChallengeUpdateDTO mapToDTO(ChallengeUpdate update) {
		ChallengeUpdateDTO dto = new ChallengeUpdateDTO();
		dto.setDate(update.getDate().format(formatter));
		return dto;
	}

}
