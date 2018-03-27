package org.tennisstege.api.rest.mapper;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.tennisstege.api.JPA.entitymodell.Challenge;
import org.tennisstege.api.JPA.entitymodell.Ladder;
import org.tennisstege.api.JPA.entitymodell.User;
import org.tennisstege.api.body.response.ChallengeDTO;
import org.tennisstege.api.service.LadderService;
import org.tennisstege.api.service.UserService;

@Component
public class ChallengeMapper implements Mapper<ChallengeDTO, Challenge> {

	@Autowired
	private UserService userService;

	@Autowired
	private LadderService ladderService;

	@Override
	public Challenge mapToEntity(ChallengeDTO body) {
		Ladder ladder = ladderService.findByName(body.getLadderName())
				.orElseThrow(() -> new UsernameNotFoundException(body.getLadderName()));
		User challenger = userService.findByUsername(body.getChallengerPlayerName())
				.orElseThrow(() -> new UsernameNotFoundException(body.getChallengerPlayerName()));
		User challenged = userService.findByUsername(body.getChallengedPlayerName())
				.orElseThrow(() -> new UsernameNotFoundException(body.getChallengedPlayerName()));

		Challenge challenge = new Challenge(challenger, challenged, LocalDateTime.now(), ladder);

		return challenge;
	}

	@Override
	public ChallengeDTO mapToDTO(Challenge challenge) {
		ChallengeDTO challengeDTO = new ChallengeDTO();
		challengeDTO.setId(challenge.getId());
		if (challenge.getChallengedPlayer() != null)
			challengeDTO.setChallengedPlayerName(challenge.getChallengedPlayer().getUsername());
		else
			challengeDTO.setChallengedPlayerName(null);

		if (challenge.getChallengerPlayer() != null)
			challengeDTO.setChallengerPlayerName(challenge.getChallengerPlayer().getUsername());
		else
			challengeDTO.setChallengerPlayerName(null);

		challengeDTO.setCreationDate(challenge.getCreationDate().format(DateTimeFormatter.BASIC_ISO_DATE));

		return challengeDTO;
	}

}
