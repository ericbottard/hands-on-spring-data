# HANDS ON SPRING DATA

## Prérequis

 * [JDK 6+](http://www.oracle.com/technetwork/java/javase/downloads/index.html)
 * [Maven](http://maven.apache.org/download.html)
 * [Mongo DB](http://www.mongodb.org/downloads)

## Préambule

L'atelier se décompose en une suite d'exercices eux-mêmes composées d'une série de tests à faire passer au vert.
Les tests sont à compléter, ainsi qu'éventuellement d'autres classes de l'exercice associé (Repository...).

Cet atelier a été testé avec Eclipse, mais n'importe quel IDE fera l'affaire. Si vous souhaitez bénéficier d'un support top-moumoute de Maven et SpringData, nous vous recommandons chaudement d'utiliser [SpringSource ToolSuite](http://www.springsource.org/sts). Importez le projet en tant que "Projet Maven existant".
 
Le seul point d'achoppement réside dans l'intégration de [QueryDSL](http://www.querydsl.com/) avec votre IDE. Les fichiers de configuration nécessaires à Eclipse sont versionnés (utilisateurs d'autres IDE: ces étapes de configuration peuvent varier).


## Exercice 1: JPA

[Documentation Spring Data/JPA](http://static.springsource.org/spring-data/data-jpa/docs/1.2.0.RELEASE/reference/html/)

L'application enregistre des adresses de clients.
Le domaine métier est modélisé comme suit :

![Domaine métier](https://raw.github.com/ericbottard/hands-on-spring-data/master/src/etc/doc/diagram-customers.png)


### TODO

Le jeu de données ainsi que la configuration de l'application sont déjà en place.
Il ne vous reste qu'à compléter les tests de la classe `JpaTest` (pensez à modifier `CustomersRepository`). Celle-ci s'appuye sur une base de données H2 embarquée.

   1. Trouver un client par son ID (ID 42)
   1. Récupérer tous les clients
   1. Obtenir une liste paginée de clients (2nde page [les pages sont indexées à partir de 0], 5 clients par page)
   1. Obtenir les clients dont le nom suit un prédicat, défini via des "explicit queries"
   1. Trouver des clients par nom et par ville, en s'appuyant sur QueryDSL (CustomersRepository doit maintenant étendre `QueryDslPredicateExecutor`)

## Exercice 2: Mongo DB

[Documentation Spring Data/Mongo DB](http://static.springsource.org/spring-data/mongodb/docs/1.1.x/reference/html/)

Cette application modélise des posts de blog, lesquels sont rédigés par des auteurs (Author) et commentés (Comment).
Le modèle est illustré ici :

![Domaine métier](https://raw.github.com/ericbottard/hands-on-spring-data/master/src/etc/doc/diagram-blog.png)


### TODO

Ici encore, la configuration de l'application et le jeu de données vous sont fournis.
Complétez la classe de test `MongoTest`.

   1. Trouver des posts dont le texte contient une séquence (ici: "Miami")
   1. Trouver tous les auteurs dans un rayon de 70 (cercle centré sur l'origine : [0,0]) dont le nom de famille commence par "Biv"
   1. Trouver toutes les images d'un post, via le repository custom `PostRepositoryImpl`. Votre implémentation se basera sur `GridFsTemplate`, lequel exécutera une instance de `org.springframework.data.mongodb.core.query.Query`. Vous pouvez la construire avec des objets _criteria_ (ex: `org.springframework.data.mongodb.gridfs.GridFsCriteria.whereFilename` appartient à `post.getPictures()`).


## Exercice 3: Neo4J

[Documentation Spring Data/Neo4J](http://static.springsource.org/spring-data/neo4j/docs/2.1.0.RELEASE/reference/html/)

(Inspiré du [travail de Michael Hunger](https://github.com/jexp/sdn-twitter-graph).)

Tout le monde connaît Twitter. Il paraîtrait même que Twitter a migré vers la base de données Neo4J! 
Nous avons utilisé l'application de Michael afin de récupérer les tweets mentionnant #cloudfoundry (http://www.cloudfoundry.com) et de persister le résultat. Le jeu de données vous est donc fourni.

Le domaine métier s'articule comme suit :

![Domaine métier](https://raw.github.com/ericbottard/hands-on-spring-data/master/src/etc/doc/diagram-tweets.png)


### TODO

Modifiez la classe `Neo4JTest` et faites passer les tests au vert !

   1. Trouver un utilisateur par son nom (ici : @ebottard, càd "ebottard")
   1. Rédiger la requête permettant de retrouver des tweets par le nom de leur auteur, afin de récupérer les tweets d'un utilisateur (ici : @ebottard, càd "ebottard")
   1. Retrouver les suggestions de comptes à suivre pour un utilisateur (ici : Chris Richardson (@crichardson, càd "crichardson")), en utilisant une requête Cypher
   1. Faire suivre ces suggestions à un compte utilisateur (ici : Andy Piper (@andypiper, càd "andypiper")), en se basant sur la requête précédente
   1. Trouvez tous les tweets mentionnant un tag (ici : #devoxx), en utilisant le DSL Java de Cypher



## Bonus: Spring Data REST

   1. Exécuter `mvn tomcat:run`
   1. Ouvrir/curl/utiliser Spring Shell sur 'localhost:8080/hands-on-springdata'
   1. Explorer :)
