package org.tennisstege.api.JPA.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.tennisstege.api.JPA.entitymodell.Record;

public interface RecordRepository extends JpaRepository<Record, Long> {
	
	
}
