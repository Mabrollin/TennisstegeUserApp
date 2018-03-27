package org.tennisstege.api.JPA.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.tennisstege.api.JPA.entitymodell.Role;

public interface RoleRepository extends MongoRepository<Role, Long> {

	Optional<Role> findByName(String string);
}
