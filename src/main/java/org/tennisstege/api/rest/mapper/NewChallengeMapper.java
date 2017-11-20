package org.tennisstege.api.rest.mapper;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.tennisstege.api.JPA.entitymodell.Challenge;
import org.tennisstege.api.JPA.entitymodell.Ladder;
import org.tennisstege.api.JPA.entitymodell.LadderPlayer;
import org.tennisstege.api.body.request.NewChallengeDTO;
import org.tennisstege.api.service.LadderService;
import org.tennisstege.api.service.PlayerService;
import org.tennisstege.api.service.UserService;

@Component
public class NewChallengeMapper implements Mapper<NewChallengeDTO, Challenge> {

	@Autowired
	private PlayerService ladderPlayerService;

	@Autowired
	private LadderService ladderService;

	@Override
	public Challenge mapToEntity(NewChallengeDTO body) {
		Ladder ladder = ladderService.findByName(body.getLadder())
				.orElseThrow(() -> new UsernameNotFoundException(body.getLadder()));
		LadderPlayer challenger = ladderPlayerService.findByLadderAndUsername(ladder, body.getChallengerPlayer())
				.orElseThrow(() -> new UsernameNotFoundException(body.getChallengerPlayer()));
		LadderPlayer challenged = ladderPlayerService.findByLadderAndUsername(ladder, body.getChallengedPlayer())
				.orElseThrow(() -> new UsernameNotFoundException(body.getChallengedPlayer()));

		Challenge challenge = new Challenge(challenger, challenged, LocalDateTime.now(), ladder);

		return challenge;
	}

	@Override
	public NewChallengeDTO mapToDTO(Challenge challenge) {
		NewChallengeDTO newChallengeDTO = new NewChallengeDTO();
		if (challenge.getChallengedPlayer() != null)
			newChallengeDTO.setChallengedPlayer(challenge.getChallengedPlayer().getPlayer().getUsername());
		else
			newChallengeDTO.setChallengedPlayer(null);

		if (challenge.getChallengerPlayer() != null)
			newChallengeDTO.setChallengerPlayer(challenge.getChallengerPlayer().getPlayer().getUsername());
		else
			newChallengeDTO.setChallengerPlayer(null);

		newChallengeDTO.setDate(challenge.getCreationDate().format(DateTimeFormatter.BASIC_ISO_DATE));

		return newChallengeDTO;
	}

}
