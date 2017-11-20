package org.tennisstege.api.rest.mapper;

import java.time.Duration;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tennisstege.api.JPA.entitymodell.Record;
import org.tennisstege.api.JPA.entitymodell.Score;
import org.tennisstege.api.body.request.RecordDTO;

@Component
public class RecordMapper implements Mapper<RecordDTO, Record> {

	@Autowired
	private ScoreMapper scoreMapper;

	@Override
	public RecordDTO mapToDTO(Record record) {
		RecordDTO dto = new RecordDTO();
		dto.setDuration(record.getDuration() != null ? record.getDuration().toString() : null);
		dto.setMatchOutcome(record.getMatchOutcome());
		dto.setScore(record.getScore() != null ? scoreMapper.mapToDTO(record.getScore()) : null);
		return dto;
	}

	@Override
	public Record mapToEntity(RecordDTO dto) {
		Score score = scoreMapper.mapToEntity(dto.getScore());
		Record record = new Record(null, score, LocalDateTime.now(), dto.getMatchOutcome(),
				Duration.parse(dto.getDuration()), null);
		return record;
	}
}
