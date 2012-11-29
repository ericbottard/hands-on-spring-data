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
package org.springframework.data.samples._02_mongo.domain;

import com.google.common.base.Objects;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.geo.Point;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexed;
import org.springframework.data.mongodb.core.mapping.Document;

import static com.google.common.base.Objects.equal;
import static com.google.common.base.Objects.toStringHelper;

@Document(collection = "authors")
public class Author {
    @Id
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    @GeoSpatialIndexed
    private Point location;

    public Author(String firstName, String lastName, String email, Point location) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.location = location;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Point getLocation() {
        return location;
    }

    public void setLocation(Point location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return toStringHelper(this)             //
                .add("firstName", firstName)    //
                .add("lastName", lastName)      //
                .add("email", email)            //
                .add("location", location)      //
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Author author = (Author) o;

        return equal(author.getFirstName(), getFirstName())         //
                && equal(author.getLastName(), getLastName())       //
                && equal(author.getEmail(), getEmail())             //
                && equal(author.getLocation(), getLocation());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getFirstName(), getLastName(), getEmail(), getLocation());
    }
}
