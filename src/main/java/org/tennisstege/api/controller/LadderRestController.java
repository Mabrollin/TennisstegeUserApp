package org.tennisstege.api.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.tennisstege.api.JPA.entitymodell.Ladder;
import org.tennisstege.api.JPA.entitymodell.Player;
import org.tennisstege.api.body.request.NewLadderDTO;
import org.tennisstege.api.body.response.LadderRepresentationDTO;
import org.tennisstege.api.body.response.PlayerDTO;
import org.tennisstege.api.rest.mapper.LadderRepresentationMapper;
import org.tennisstege.api.rest.mapper.NewLadderMapper;
import org.tennisstege.api.rest.mapper.PlayerMapper;
import org.tennisstege.api.service.LadderService;
import org.tennisstege.api.service.PlayerService;
import org.tennisstege.api.validator.LadderValidator;

@RestController
@RequestMapping(value = "/ladder")
public class LadderRestController {
	
	@Autowired
	private NewLadderMapper newLadderMapper;

	@Autowired
	private LadderValidator ladderValidator;
	
	@Autowired
	private LadderRepresentationMapper ladderRepresentationMapper;
	
	@Autowired
	private PlayerMapper ladderPlayerMapper;
	
	@Autowired
	private LadderService ladderService;
	
	@Autowired
	private PlayerService playerService;
	
	LadderRestController() {
	}

	public class UserAlreadyExistsInLadderException extends RuntimeException {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1619695157014732622L;
		
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

	@RequestMapping(method = RequestMethod.PUT, value = "/{ladderName}/addPlayer")
	ResponseEntity<?> addPlayer(@PathVariable String ladderName, @RequestBody PlayerDTO playerForm) {
		Optional<Ladder> ladder = ladderService.findByName(ladderName);
		Player player = ladderPlayerMapper.mapToEntity(playerForm);
		if(ladder.isPresent()){
			playerService.addPlayerToLadder(ladder.get(), player);
			return new ResponseEntity<>(HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(ladderName, HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping(method = RequestMethod.POST)
	ResponseEntity<?> createLadder(@RequestBody NewLadderDTO ladderForm, BindingResult bindingResult) {
		String principalName = SecurityContextHolder.getContext().getAuthentication().getName();
		if(!principalName.equals(ladderForm.getCreator())){
			return new ResponseEntity<>(ladderForm.getCreator(), HttpStatus.UNAUTHORIZED);
		}
		
		ladderValidator.validate(ladderForm, bindingResult);
		if (bindingResult.hasErrors()) {
			return new ResponseEntity<>(bindingResult, HttpStatus.BAD_REQUEST);
		}
		
		Ladder ladder = newLadderMapper.mapToEntity(ladderForm);
		Player player = new Player(ladder.getAdmins().iterator().next(), 1500l);
		playerService.addPlayerToLadder(ladder, player);
		NewLadderDTO response = newLadderMapper.mapToDTO(ladderService.save(ladder));
		return new ResponseEntity<>(response, HttpStatus.CREATED);

	}
	@RequestMapping(method = RequestMethod.GET, value = "/search")
	ResponseEntity<List<String>> getLadderNames(@RequestBody String name) {
		List<Ladder> result = ladderService.searchByName(name);
		List<String> response = result.stream().map(ladder -> ladder.getName()).collect(Collectors.toList());
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

}
