package org.springframework.data.samples._03_neo4j.domain;

import org.springframework.data.neo4j.annotation.EndNode;
import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.RelationshipEntity;
import org.springframework.data.neo4j.annotation.StartNode;

/**
 * @author mh
 * @since 26.07.12
 */
@RelationshipEntity
public class Follows {
    @GraphId Long id;
    @StartNode User follower;
    @EndNode User user;
}
