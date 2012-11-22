package org.springframework.data.samples._03_neo4j.domain;

import org.springframework.data.neo4j.annotation.*;

import java.util.HashSet;
import java.util.Set;

/**
 * @author mh
 * @since 24.07.12
 */
@NodeEntity
public class User {
    @GraphId Long id;

    @Indexed(unique=true) String name;

    public User() {
    }

    public User(String user) {
        this.name = user;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "@"+ name;
    }
}
