package org.springframework.data.samples._03_neo4j.domain;

import org.springframework.data.neo4j.repository.CypherDslRepository;
import org.springframework.data.neo4j.repository.GraphRepository;

import java.util.List;

public interface TweetsRepository extends GraphRepository<Tweet>, CypherDslRepository<Tweet> {
	
	List<Tweet> findBySenderName(String username);

}
