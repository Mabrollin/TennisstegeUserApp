package org.tennisstege.api.rest.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.stereotype.Component;
import org.tennisstege.api.JPA.entitymodell.Score;
import org.tennisstege.api.body.request.ScoreDTO;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class ScoreMapper implements Mapper<ScoreDTO, Score> {
	

	
	JacksonJsonParser parser = new JacksonJsonParser();
	
	ObjectMapper mapper = new ObjectMapper();

	@SuppressWarnings("unchecked")
	@Override
	public ScoreDTO mapToDTO(Score score) {
		ScoreDTO dto = new ScoreDTO();
		List<Object> list = parser.parseList(score.getSetsAndBalls());
		List<List<Integer>> setsAndGames = new ArrayList<>();
		
		for(Object row : list){
			setsAndGames.add((List<Integer>) row);
		}
		
		
		dto.setSetsAndGames(setsAndGames);
		return dto;
	}

	@Override
	public Score mapToEntity(ScoreDTO dto) {
		Score score = new Score();
		try {
			score.setSetsAndBalls(mapper.writeValueAsString(dto.getSetsAndGames()));
		} catch (JsonProcessingException e) {
			// TODO rethrow?k
			e.printStackTrace();
		}
		return score;
	}
}
