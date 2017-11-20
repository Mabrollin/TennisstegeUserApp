package org.tennisstege.api.controller;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.tennisstege.api.JPA.entitymodell.Challenge;
import org.tennisstege.api.JPA.entitymodell.MatchOutcome;
import org.tennisstege.api.JPA.entitymodell.Record;
import org.tennisstege.api.JPA.entitymodell.Score;
import org.tennisstege.api.body.request.ChallengeUpdate;
import org.tennisstege.api.body.request.ChallengeUpdateDTO;
import org.tennisstege.api.body.request.NewChallengeDTO;
import org.tennisstege.api.body.request.RecordDTO;
import org.tennisstege.api.body.response.ChallengeDTO;
import org.tennisstege.api.rest.mapper.ChallengeMapper;
import org.tennisstege.api.rest.mapper.NewChallengeMapper;
import org.tennisstege.api.rest.mapper.RecordMapper;
import org.tennisstege.api.rest.mapper.ScoreMapper;
import org.tennisstege.api.rest.mapper.UpdateChallengeMapper;
import org.tennisstege.api.service.ChallengeService;

@RestController
@RequestMapping(value = "/challenge")
public class ChallengeRestController {
	@Autowired
	ChallengeService challengeService;

	@Autowired
	private RecordMapper recordMapper;

	@Autowired
	private NewChallengeMapper newChallengeMapper;

	@Autowired
	private ChallengeMapper challengeMapper;

	@Autowired
	private ScoreMapper scoreMapper;

	@Autowired
	private UpdateChallengeMapper updateChallengeMapper;

	ChallengeRestController() {

	}

	@RequestMapping(method = RequestMethod.POST, value = "/new")
	ResponseEntity<NewChallengeDTO> newChallenge(@RequestBody NewChallengeDTO challengeForm) {

		Challenge challenge = newChallengeMapper.mapToEntity(challengeForm);

		NewChallengeDTO response = newChallengeMapper.mapToDTO(challengeService.newChallenge(challenge));
		return new ResponseEntity<>(response, HttpStatus.CREATED);

	}

	@RequestMapping(method = RequestMethod.PUT, value = "/{challengeId}/update")
	ResponseEntity<ChallengeDTO> updateChallenge(@PathVariable ChallengeUpdateDTO updateForm,
			@RequestParam Long challengeId) {

		Challenge challengeToUpdate = challengeService.getChallenge(challengeId);
		ChallengeUpdate update = updateChallengeMapper.mapToEntity(updateForm);
		ChallengeDTO response = challengeMapper.mapToDTO(challengeService.updateChallenge(challengeToUpdate, update));
		// TODO validate and error handling
		return new ResponseEntity<ChallengeDTO>(response, HttpStatus.OK);

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{challengeId}/remove")
	ResponseEntity<ChallengeDTO> removeChallenge(@PathVariable Long challengeId) {
		Challenge challengeToRemove = challengeService.getChallenge(challengeId);
		challengeService.removeChallenge(challengeToRemove);
		ChallengeDTO response = challengeMapper.mapToDTO(challengeToRemove);
		// TODO validate and error handling
		return new ResponseEntity<ChallengeDTO>(response, HttpStatus.OK);

	}

	@RequestMapping(method = RequestMethod.PUT, value = "/{challengeId}/cancel")
	ResponseEntity<RecordDTO> cancelChallenge(@PathVariable Long challengeId, @RequestBody String reason) {

		Challenge challengeToCancel = challengeService.getChallenge(challengeId);
		Record record = challengeService.cancelChallenge(challengeToCancel, reason);
		RecordDTO response = recordMapper.mapToDTO(record);
		// TODO validate and error handling
		return new ResponseEntity<RecordDTO>(response, HttpStatus.OK);

	}

	@RequestMapping(method = RequestMethod.PUT, value = "/{challengeId}/record")
	ResponseEntity<Object> recordChallenge(@PathVariable Long challengeId, @RequestBody RecordDTO recordBody) {

		Score score = scoreMapper.mapToEntity(recordBody.getScore());
		MatchOutcome matchOutcome = recordBody.getMatchOutcome();
		Duration duration = Duration.parse(recordBody.getDuration());
		Challenge challenge = challengeService.getChallenge(challengeId);

		Record record = challengeService.recordChallenge(challenge, score, matchOutcome, duration);
		RecordDTO response = recordMapper.mapToDTO(record);
		// TODO validate and error handling
		return new ResponseEntity<>(response, HttpStatus.OK);

	}

	@RequestMapping(method = RequestMethod.GET, value = "/{challengeId}")
	ResponseEntity<ChallengeDTO> updateChallenge(@PathVariable Long challengeId) {

		Challenge challenge = challengeService.getChallenge(challengeId);
		ChallengeDTO response = challengeMapper.mapToDTO(challenge);
		// TODO validate and error handling
		return new ResponseEntity<>(response, HttpStatus.OK);

	}

}
