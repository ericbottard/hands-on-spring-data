package org.springframework.data.samples._03_neo4j;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.fest.assertions.api.Assertions.extractProperty;
import static org.neo4j.cypherdsl.CypherQuery.lookup;
import static org.neo4j.cypherdsl.CypherQuery.node;
import static org.neo4j.cypherdsl.CypherQuery.start;

import java.util.List;

import javax.inject.Inject;

import org.fest.assertions.core.Condition;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.neo4j.cypherdsl.grammar.Execute;
import org.springframework.data.samples._03_neo4j.domain.Follows;
import org.springframework.data.samples._03_neo4j.domain.Tweet;
import org.springframework.data.samples._03_neo4j.domain.TweetsRepository;
import org.springframework.data.samples._03_neo4j.domain.User;
import org.springframework.data.samples._03_neo4j.domain.UsersRepository;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(value = "classpath:applicationContext-neo4j.xml")
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class Neo4jTest {

	@Inject
	private TweetsRepository tweetsRepository;

	@Inject
	private UsersRepository userRepository;

	@Test
	public void magic_finder_using_a_propery() {

		// Lookup a user given his/her name
		User eric = userRepository.findByName("ebottard");

		assertThat(eric.getId()).isEqualTo(108);
	}

	@Test
	public void writing_a_cypher_query() {

		List<User> friends = userRepository.suggestFriends("crichardson");

		assertThat(extractProperty("name", String.class).from(friends))
				.containsOnly( //
						"CloudElements1", "ossdoc", "andypiper", "bru" //
				);

	}

	@Test
	public void creating_relationships() {

		// Lookup the user whose name is "andypiper"
		User andy = userRepository.findByName("andypiper");

		// Find all the people he should be following
		List<User> newFriends = userRepository.suggestFriends(andy.getName());

		for (User u : newFriends) {
			// Actually make him follow them
			userRepository.createRelationshipBetween(andy, u, Follows.class,
					"FOLLOWS");
		}

		// Query the database again
		List<User> newFriendsAfter = userRepository.suggestFriends(andy
				.getName());

		// Then there should be not new suggested friends
		assertThat(newFriendsAfter).isEmpty();

		// Then changes will roll back...

	}

	@Test
	public void querying_with_dot_properties() {

		// Find tweets given their sender name. Look for tweets sent by "ebottard"
		List<Tweet> tweets = tweetsRepository.findBySenderName("ebottard");

		
		// Actually, there was only one, and it contains the word "japanese".
		assertThat(tweets.get(0).getText()).containsIgnoringCase("japanese");
	}

	@Test
	public void using_cypher_dsl() {
		// A DSL query that is equivalent to
		// START devoxxTag=node:Tag(tag="devoxx")
		// MATCH devoxxTag<-[:TAG]-tweet
		// RETURN tweet
		Execute query = start(lookup("devoxxTag", "Tag", "tag", "devoxx"))//
				.match(node("devoxxTag").in("TAG").node("tweet"))//
				.returns(node("tweet"));
		
		// Execute the query and return the results as List
		List<Tweet> tweets = tweetsRepository.query(query, null).as(List.class);
		
		// Verify that, of all the tweets returned, 3 contain the word "hoodie"
		assertThat(extractProperty("text").ofType(String.class).from(tweets)).haveExactly(3, new Condition<String>() {
			@Override
			public boolean matches(String value) {
				return value.contains("hoodie");
			}
		});
		
	}

}
