# HANDS ON SPRING DATA

## Ready to rumble?

 * [JDK 6+](http://www.oracle.com/technetwork/java/javase/downloads/index.html)
 * [Maven](http://maven.apache.org/download.html)
 * [Mongo DB](http://www.mongodb.org/downloads)

## Exercise 1: JPA (between 5 and 30 min)

Basically, the application stores customer locations.
The domain is modeled as follows:

![Customers Data Model](https://github.com/ericbottard/hands-on-spring-data/src/etc/doc/diagram-customers.png)

Please complete class `JpaTest`. This test suite relies on an embedded H2 database.
The required setup and data set are already provided.

## Exercise 2: Mongo DB

The toy application here models blog posts, written by authors and commented,
as illustrated here:

![Blog Data Model](https://github.com/ericbottard/hands-on-spring-data/src/etc/doc/diagram-blog.png)

Similarly, please fix the failing tests of `MongoTest`.
Again, the data set and the setup are taken care for you.

## Exercise 3: Neo4J

Everyone knows about Twitter. The twittosphere has now been persisted into a graph:

![Tweets Data Model](https://github.com/ericbottard/hands-on-spring-data/src/etc/doc/diagram-tweets.png)

Open `Neo4JTest`and go green!
