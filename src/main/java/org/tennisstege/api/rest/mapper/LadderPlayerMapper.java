package org.tennisstege.api.rest.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tennisstege.api.JPA.entitymodell.Ladder;
import org.tennisstege.api.JPA.entitymodell.LadderPlayer;
import org.tennisstege.api.JPA.entitymodell.User;
import org.tennisstege.api.body.response.LadderPlayerDTO;
import org.tennisstege.api.service.LadderService;
import org.tennisstege.api.service.UserService;

@Component
public class LadderPlayerMapper implements Mapper<LadderPlayerDTO, LadderPlayer> {

	private static final User GUEST = null;

	@Autowired
	UserService userService;
	
	@Autowired
	LadderService ladderService;

	@Override
	public LadderPlayerDTO mapToDTO(LadderPlayer ladderPlayer) {
		LadderPlayerDTO ladderPlayerDTO = new LadderPlayerDTO();

		ladderPlayerDTO.setUsername(ladderPlayer.getPlayer().getUsername());
		ladderPlayerDTO.setLadderName(ladderPlayer.getLadder().getName());
		ladderPlayerDTO.setRating(ladderPlayer.getRating());
		return ladderPlayerDTO;
	}

	@Override
	public LadderPlayer mapToEntity(LadderPlayerDTO dto) throws LadderNotFoundException {
		
		User user = userService.findByUsername(dto.getUsername()).orElse(GUEST);
		Ladder ladder = ladderService.findByName(dto.getLadderName()).orElseThrow(() -> new LadderNotFoundException());
		//TODO generate raing from service
		Long rating = dto.getRating();
		
		LadderPlayer ladderPlayer = new LadderPlayer(user, ladder, rating);
		return ladderPlayer;
	}

}
