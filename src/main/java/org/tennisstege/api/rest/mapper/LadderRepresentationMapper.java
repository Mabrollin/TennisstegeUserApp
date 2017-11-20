package org.tennisstege.api.rest.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.tennisstege.api.JPA.entitymodell.Ladder;
import org.tennisstege.api.body.request.RecordDTO;
import org.tennisstege.api.body.response.ChallengeDTO;
import org.tennisstege.api.body.response.LadderPlayerDTO;
import org.tennisstege.api.body.response.LadderRepresentationDTO;
import org.tennisstege.api.service.LadderService;

@Component
public class LadderRepresentationMapper implements Mapper<LadderRepresentationDTO, Ladder> {

	@Autowired
	private LadderPlayerMapper ladderPlayerMapper;
	@Autowired
	private ChallengeMapper challengeMapper;
	@Autowired
	private RecordMapper recordMapper;
	
	@Autowired
	private LadderService ladderService;
	

	@Override
	public LadderRepresentationDTO mapToDTO(Ladder ladder) {
		LadderRepresentationDTO ladderRepresentation = new LadderRepresentationDTO();

		String ladderName = ladder.getName();
		ladderRepresentation.setLadderName(ladderName);

		List<LadderPlayerDTO> players = new ArrayList<>();
		List<ChallengeDTO> challenges = new ArrayList<>();
		List<RecordDTO> records = new ArrayList<>();

		ladder.getPlayers().forEach(player -> players.add(ladderPlayerMapper.mapToDTO(player)));
		ladderRepresentation.setPlayers(players);
		
		ladder.getChallenges().forEach(challenge -> challenges.add(challengeMapper.mapToDTO(challenge)));
		ladderRepresentation.setChallenges(challenges);
		
		ladder.getRecords().forEach(record -> records.add(recordMapper.mapToDTO(record)));
		ladderRepresentation.setRecords(records);
		
		return ladderRepresentation;
	}

	/**
	 * NOTICE: not a mapping-method, finds existing ladder with ladderService.
	 */
	@Override
	public Ladder mapToEntity(LadderRepresentationDTO dto) {
		return ladderService.findByName(dto.getLadderName()).orElseThrow(()->new UsernameNotFoundException(dto.getLadderName()));
	}

}
