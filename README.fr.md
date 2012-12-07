# HANDS ON SPRING DATA

## Prérequis

 * [JDK 6+](http://www.oracle.com/technetwork/java/javase/downloads/index.html)
 * [Maven](http://maven.apache.org/download.html)
 * [Mongo DB](http://www.mongodb.org/downloads)

## Exercice 1: JPA

L'application enregistre des adresses de clients.
Le domaine métier est modélisé comme suit :

![Domaine métier](https://raw.github.com/ericbottard/hands-on-spring-data/master/src/etc/doc/diagram-customers.png)


### TODO

Le jeu de données ainsi que la configuration de l'application sont déjà en place.
Il ne vous reste qu'à compléter les tests de la classe `JpaTest`. Celle-ci s'appuye sur une base de données H2 embarquée.

   1. Trouver un client par son ID (ID 42)
   1. Récupérer tous les clients
   1. Obtenir une liste paginée de clients (2nde page, 5 clients par page) 
   1. Obtenir les clients dont le nom suit un prédicat, défini via des "explicit queries"
   1. Trouver des clients par nom et par ville, en s'appuyant sur QueryDSL

## Exercice 2: Mongo DB

Cette application modélise des posts de blog, lesquels sont rédigés par des auteurs (Author) et commentés (Comment).
Le modèle est illustré ici :

![Domaine métier](https://raw.github.com/ericbottard/hands-on-spring-data/master/src/etc/doc/diagram-blog.png)


### TODO

Ici encore, la configuration de l'application et le jeu de données vous sont fournis.
Complétez la classe de test `MongoTest`.

   1. Trouver des posts dont le texte contient une séquence (ici: "Miami")
   1. Trouver tous les auteurs dans un rayon de 70 (cercle centré sur l'origine : [0,0])
   1. Trouver toutes les images d'un post, via une implémentation de repository spécifique


## Exercice 3: Neo4J

(Inspiré du [travail de Michael Hunger](https://github.com/jexp/sdn-twitter-graph).)

Tout le monde connaît Twitter. Il paraîtrait même que Twitter a migré vers la base de données Neo4J! 
Nous avons utilisé l'application de Michael afin de récupérer les tweets mentionnant #cloudfoundry (http://www.cloudfoundry.com) et de persister le résultat. Le jeu de données vous est donc fourni.

Le domaine métier s'articule comme suit :

![Domaine métier](https://raw.github.com/ericbottard/hands-on-spring-data/master/src/etc/doc/diagram-tweets.png)


### TODO

Modifiez la classe `Neo4JTest` et faites passer les tests au vert !

   1. Trouver un utilisateur par son nom (ici : @ebottard)
   1. Retrouver les suggestions de comptes à suivre pour un utilisateur (ici : Chris Richardson (@crichardson)), en utilisant une requête Cypher
   1. Faire suivre ces suggestions à un compte utilisateur (ici : Andy Piper (@andypiper)), en se basant sur la requête précédente
   1. Rédiger la requête permettant de retrouver des tweets par le nom de leur auteur, afin de récupérer les tweets d'un utilisateur (ici : @ebottard)
   1. Trouvez tous les tweets mentionnant un tag (ici : #devoxx), en utilisant le DSL Java de Cypher



## Bonus: Spring Data REST

   1. Exécuter `mvn tomcat:run`
   1. Ouvrir/curl 'localhost:8080/hands-on-springdata'
   1. Explorer :)
