package org.tennisstege.api.JPA.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.tennisstege.api.JPA.entitymodell.Record;

public interface RecordRepository extends MongoRepository<Record, Long> {
	
	
}
