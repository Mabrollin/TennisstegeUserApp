package org.tennisstege.api.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.tennisstege.api.JPA.entitymodell.Ladder;
import org.tennisstege.api.JPA.entitymodell.LadderPlayer;
import org.tennisstege.api.JPA.entitymodell.User;
import org.tennisstege.api.body.request.NewLadderDTO;
import org.tennisstege.api.body.response.LadderPlayerDTO;
import org.tennisstege.api.body.response.LadderRepresentationDTO;
import org.tennisstege.api.body.response.SImpleUserDTO;
import org.tennisstege.api.rest.mapper.LadderMapper;
import org.tennisstege.api.rest.mapper.LadderPlayerMapper;
import org.tennisstege.api.rest.mapper.LadderRepresentationMapper;
import org.tennisstege.api.service.LadderService;
import org.tennisstege.api.service.PlayerService;
import org.tennisstege.api.service.UserService;
import org.tennisstege.api.validator.LadderValidator;

@RestController
@RequestMapping(value = "/ladder")
public class LadderRestController {

	@Autowired
	private LadderMapper ladderMapper;

	@Autowired
	private LadderValidator ladderValidator;
	
	@Autowired
	private LadderRepresentationMapper ladderRepresentationMapper;
	
	@Autowired
	private LadderPlayerMapper ladderPlayerMapper;
	
	@Autowired
	private LadderService ladderService;
	
	@Autowired
	private PlayerService playerService;
	
	LadderRestController() {
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{ladderName}")
	@ResponseBody
	ResponseEntity<LadderRepresentationDTO> getLadder(@PathVariable String ladderName) {
		Optional<Ladder> ladder = ladderService.findByName(ladderName);
		if (ladder.isPresent()) {
			return new ResponseEntity<>(ladderRepresentationMapper.mapToDTO(ladder.get()), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}
	@RequestMapping(method = RequestMethod.GET, value = "/{ladderName}/getPlayers")
	@ResponseBody
	ResponseEntity<List<LadderPlayerDTO>> getUsers(@PathVariable String ladderName) {
		Optional<Ladder> ladder = ladderService.findByName(ladderName);
		if (ladder.isPresent()) {
			Set<LadderPlayer> players = ladder.get().getPlayers();
			List<LadderPlayerDTO> playerDTOList = new ArrayList<>();
			players.forEach(player -> playerDTOList.add(ladderPlayerMapper.mapToDTO(player)));
			return new ResponseEntity<>(playerDTOList, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/addPlayer")
	ResponseEntity<?> addPlayer(@RequestBody LadderPlayerDTO playerForm) {
		LadderPlayer player = ladderPlayerMapper.mapToEntity(playerForm);
		LadderPlayerDTO response = ladderPlayerMapper.mapToDTO(playerService.save(player));
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/new")
	ResponseEntity<?> createLadder(@RequestBody NewLadderDTO ladderForm, BindingResult bindingResult) {
		Ladder ladder = ladderMapper.mapToEntity(ladderForm);
		ladderValidator.validate(ladder, bindingResult);
		if (bindingResult.hasErrors()) {
			return new ResponseEntity<>(bindingResult, HttpStatus.BAD_REQUEST);
		} else {
			NewLadderDTO response = ladderMapper.mapToDTO(ladderService.save(ladder));
			return new ResponseEntity<>(response, HttpStatus.CREATED);

		}
	}

}
