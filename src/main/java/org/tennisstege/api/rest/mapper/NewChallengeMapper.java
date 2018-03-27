package org.tennisstege.api.rest.mapper;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.tennisstege.api.JPA.entitymodell.Challenge;
import org.tennisstege.api.JPA.entitymodell.Ladder;
import org.tennisstege.api.JPA.entitymodell.User;
import org.tennisstege.api.body.request.NewChallengeDTO;
import org.tennisstege.api.service.LadderService;
import org.tennisstege.api.service.PlayerService;
import org.tennisstege.api.service.UserService;

@Component
public class NewChallengeMapper implements Mapper<NewChallengeDTO, Challenge> {

	@Autowired
	private UserService userService;

	@Autowired
	private LadderService ladderService;

	@Override
	public Challenge mapToEntity(NewChallengeDTO body) {
		Ladder ladder = ladderService.findByName(body.getLadder())
				.orElseThrow(() -> new LadderNotFoundException());
		User challenger = userService.findByUsername(body.getChallengerPlayer())
				.orElseThrow(() -> new UsernameNotFoundException(body.getChallengerPlayer()));
		User challenged = userService.findByUsername(body.getChallengedPlayer())
				.orElseThrow(() -> new UsernameNotFoundException(body.getChallengedPlayer()));

		Challenge challenge = new Challenge(challenger, challenged, LocalDateTime.parse(body.getDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")), ladder);

		return challenge;
	}

	@Override
	public NewChallengeDTO mapToDTO(Challenge challenge) {
		NewChallengeDTO newChallengeDTO = new NewChallengeDTO();
		if (challenge.getChallengedPlayer() != null)
			newChallengeDTO.setChallengedPlayer(challenge.getChallengedPlayer().getUsername());
		else
			newChallengeDTO.setChallengedPlayer(null);

		if (challenge.getChallengerPlayer() != null)
			newChallengeDTO.setChallengerPlayer(challenge.getChallengerPlayer().getUsername());
		else
			newChallengeDTO.setChallengerPlayer(null);

		newChallengeDTO.setDate(challenge.getCreationDate().format(DateTimeFormatter.BASIC_ISO_DATE));

		return newChallengeDTO;
	}

}
