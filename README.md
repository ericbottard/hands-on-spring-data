# HANDS ON SPRING DATA

## Ready to rumble?

 * [JDK 6+](http://www.oracle.com/technetwork/java/javase/downloads/index.html)
 * [Maven](http://maven.apache.org/download.html)
 * [Mongo DB](http://www.mongodb.org/downloads)

## Foreword

All exercises consist in making their corresponding test suite pass.
This will require some adaptation within the tests themselves as well as other related classes (Repository...).

This Hands On has been tested with Eclipse, but any other IDE should work. We recommend using the [SpringSource ToolSuite](http://www.springsource.org/sts) distro, which has Maven support and SpringData awareness built-in. You should import the project as an "Existing Maven project". 
 
The main gotcha is having [QueryDSL](http://www.querydsl.com/) work in your IDE. Configuration files for eclipse are included, for other IDEs, your mileage may vary.
 

## Exercise 1: JPA

Basically, the application stores customer locations.
The domain is modeled as follows:

![Customers Data Model](https://raw.github.com/ericbottard/hands-on-spring-data/master/src/etc/doc/diagram-customers.png)


### TODO

The required setup and data set are already provided.
Please complete classes `JpaTest` and `CustomersRepository`. This test suite relies on an embedded H2 database.

   1. Find a customer by its ID (lookup user with id 42)
   1. Find all customers
   1. Get paginated customer results (2nd page [page indices are 0-based], 5 people per page)
   1. Find customers with name compound predicate using explicit queries
   1. Find by name and city, using QueryDSL (CustomersRepository must extend `QueryDslPredicateExecutor`)

## Exercise 2: Mongo DB

The toy application here models blog posts, written by authors and commented,
as illustrated here:

![Blog Data Model](https://raw.github.com/ericbottard/hands-on-spring-data/master/src/etc/doc/diagram-blog.png)


### TODO

Again, the data set and the setup are taken care of for you.
Similarly, please fix the failing tests of `MongoTest`.

   1. Find blog posts by their contents ("Miami")
   1. Find authors within a radius of 70 (center: [0,0]) whose last name starts with "Biv"
   1. Find all pictures of a post, via custom repository implementation `PostRepositoryImpl`. Your implementation will rely on `GridFsTemplate` to execute an instance of `org.springframework.data.mongodb.core.query.Query`. You can construct one out of criteria objects (e.g.: `org.springframework.data.mongodb.gridfs.GridFsCriteria.whereFilename` belonging in `post.getPictures()`).

## Exercise 3: Neo4J

(Derived from [Michael Hunger's work](https://github.com/jexp/sdn-twitter-graph).)

Everyone knows about Twitter. Rumor has it Twitter has moved to Neo4J! 
We used Michael's application to query for some tweets about #cloudfoundry (http://www.cloudfoundry.com) and saved the results. The dataset is provided for you.
The model is defined as follows:

![Tweets Data Model](https://raw.github.com/ericbottard/hands-on-spring-data/master/src/etc/doc/diagram-tweets.png)


### TODO

Open `Neo4JTest`and go green!

   1. Find a user (@ebottard i.e. "ebottard") by name
   1. Retrieve Chris Richardson (@crichardson i.e. "crichardson") account suggestions, using an explicit Cypher query
   (see Javadoc in `Neo4JTest` for complete specs about that)
   1. Make Andy Piper (@andypiper i.e. "andypiper") follow his account suggestions, based on your previous query
   1. Craft a query that will allow you to find tweets by sender name, and use it to retrieve @ebottard's tweets
   1. Find all tweets tagged with #devoxx, using Cypher Java DSL



## Bonus: Spring Data REST

   1. Run `mvn tomcat:run`
   1. Open/curl/use Spring Shell to 'localhost:8080/hands-on-springdata'
   1. Explore :)
