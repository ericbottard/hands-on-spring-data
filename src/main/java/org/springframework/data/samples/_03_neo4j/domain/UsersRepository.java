package org.springframework.data.samples._03_neo4j.domain;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.neo4j.repository.RelationshipOperationsRepository;

import java.util.List;

public interface UsersRepository extends GraphRepository<User>,
		RelationshipOperationsRepository<User> {

	/**
	 * Suggest new friends for a given user u if they posted tweets tagged with
	 * the same tag(s) as tweets posted by u (but excluding those tagged as
	 * 'cloudfoundry', as this is how the dataset was constructed -- hence all
	 * tweets are tagged 'cloudfoundry')
	 * 
	 * @param username
	 *            the user for which we're trying to suggest new friends
	 * @return a list of User entities that the user should follow
	 */
	@Query("START u=node:User(name={0}) "
			+ "MATCH u<-[:sender]-tweet-[:TAG]->tag, "
			+ "tag<-[:TAG]-othertweet-[:sender]->otheruser "
			+ "WHERE otheruser <> u AND tag.tag<>'cloudfoundry' AND NOT(u-[:FOLLOWS]->otheruser)"
			+ "RETURN distinct(otheruser)")
	List<User> suggestFriends(String username);

	User findByName(String string);

	
}