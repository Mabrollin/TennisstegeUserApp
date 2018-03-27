package org.tennisstege.api.rest.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tennisstege.api.JPA.entitymodell.Player;
import org.tennisstege.api.JPA.entitymodell.User;
import org.tennisstege.api.body.response.PlayerDTO;
import org.tennisstege.api.controller.UserNotFoundException;
import org.tennisstege.api.service.LadderService;
import org.tennisstege.api.service.UserService;

@Component
public class PlayerMapper implements Mapper<PlayerDTO, Player> {
	
	@Autowired
	UserService userService;
	
	@Autowired
	LadderService ladderService;

	@Override
	public PlayerDTO mapToDTO(Player ladderPlayer) {
		PlayerDTO ladderPlayerDTO = new PlayerDTO();

		ladderPlayerDTO.setUsername(ladderPlayer.getUser().getUsername());
		ladderPlayerDTO.setRating(ladderPlayer.getRating());
		return ladderPlayerDTO;
	}

	@Override
	public Player mapToEntity(PlayerDTO dto) throws UserNotFoundException {
		
		User user = userService.findByUsername(dto.getUsername()).orElseThrow(()->new UserNotFoundException(dto.getUsername()));
		Long rating = dto.getRating();
		Player ladderPlayer = new Player(user, rating);
		return ladderPlayer;
	}

}
