package org.tennisstege.api.JPA.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.tennisstege.api.JPA.entitymodell.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

	Optional<Role> findByName(String string);
}
