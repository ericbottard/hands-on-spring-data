# HANDS ON SPRING DATA

## Ready to rumble?

 * [JDK 6+](http://www.oracle.com/technetwork/java/javase/downloads/index.html)
 * [Maven](http://maven.apache.org/download.html)
 * [Mongo DB](http://www.mongodb.org/downloads)

## Exercise 1: JPA

Basically, the application stores customer locations.
The domain is modeled as follows:

![Customers Data Model](https://raw.github.com/ericbottard/hands-on-spring-data/master/src/etc/doc/diagram-customers.png)


### TODO

The required setup and data set are already provided.
Please complete class `JpaTest`. This test suite relies on an embedded H2 database.

   1. Find a customer by its ID
   1. Find all customers
   1. Get paginated customer results
   1. Find customers with name compound predicate using explicit queries
   1. Find by name and city, using QueryDSL

## Exercise 2: Mongo DB

The toy application here models blog posts, written by authors and commented,
as illustrated here:

![Blog Data Model](https://raw.github.com/ericbottard/hands-on-spring-data/master/src/etc/doc/diagram-blog.png)


### TODO

Again, the data set and the setup are taken care for you.
Similarly, please fix the failing tests of `MongoTest`.

   1. Find blog posts by their contents
   1. Find authors within a radius of 70 (center: [0,0]).
   1. Find all pictures of a post, via a custom repository implementation


## Exercise 3: Neo4J

(Derived from [Michael Hunger work](https://github.com/jexp/sdn-twitter-graph).)

Everyone knows about Twitter. Rumor has it Twitter has moved to Neo4J! 
Their model is defined as follows:

![Tweets Data Model](https://raw.github.com/ericbottard/hands-on-spring-data/master/src/etc/doc/diagram-tweets.png)


### TODO

Open `Neo4JTest`and go green!

   1. Find a user by name.
   1. Retrieve Chris Richardson account suggestions, using an explicit Cypher query.
   1. Make Andy Piper follow his account suggestions, based on your previous query.
   1. Find tweets by sender name.
   1. Find all tweets tagging Devoxx, using Cypher Java DSL.



## Bonus: Spring Data REST

   1. Run `mvn tomcat:run`
   1. Open/curl 'localhost:8080/hands-on-springdata'
   1. Explore :)
