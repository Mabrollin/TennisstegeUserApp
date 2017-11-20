package org.tennisstege.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.tennisstege.api.JPA.repository.LadderRepository;
import org.tennisstege.api.JPA.repository.UserRepository;

@SpringBootApplication
@EnableAutoConfiguration
public class TennisstegeApplication{
	@Autowired
	UserRepository userRepository;

	@Autowired
	LadderRepository ladderRepository;


	public static void main(String[] args) {
		SpringApplication.run(TennisstegeApplication.class, args);
	}
	
	

}
