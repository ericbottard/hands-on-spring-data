/*
* Copyright 2012 the original author or authors.
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
* http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/
package org.springframework.data.samples._03_neo4j;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.neo4j.repository.RelationshipOperationsRepository;
import org.springframework.data.samples._03_neo4j.domain.User;

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