package org.tennisstege.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.tennisstege.api.JPA.entitymodell.Role;
import org.tennisstege.api.JPA.repository.RoleRepository;

@Component
public class DataLoader implements ApplicationRunner {

    private RoleRepository roleRepository;

    @Autowired
    public DataLoader(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public void run(ApplicationArguments args) {
    	//creates role 'USER'
    	if(roleRepository.findByName("USER").isPresent()){
    		System.out.println("User Role exist");
    	}
    	else {
    		System.out.println("User Role doesn't exist");
    		System.out.println("Creating User Role...");
    		roleRepository.save(new Role("USER"));
    		System.out.println("User Role created succesfully!");
    	}
    }
}