package org.springframework.data.samples._03_neo4j.domain;

import java.util.List;

import org.springframework.data.neo4j.repository.CypherDslRepository;
import org.springframework.data.neo4j.repository.GraphRepository;

public interface TweetsRepository extends GraphRepository<Tweet>, CypherDslRepository<Tweet> {
	
	List<Tweet> findBySenderName(String username);

}
