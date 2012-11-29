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

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Collection;
import java.util.Date;

import static com.google.common.base.Objects.toStringHelper;
import static com.google.common.collect.Lists.newLinkedList;
import static java.util.Collections.unmodifiableCollection;

@Document(collection = "posts")
public class Post {
    @Id
    private String id;
    @DBRef
    private Author author;
    private String contents;
    private Date creationDate;

    private final Collection<Comment> comments = newLinkedList();
    private final Collection<String> pictures = newLinkedList();

    public Post(Author author, String contents, Date creationDate) {
        this.author = author;
        this.contents = contents;
        this.creationDate = creationDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Post addComment(Comment comment) {
        comments.add(comment);
        return this;
    }

    public Collection<Comment> getComments() {
        return unmodifiableCollection(comments);
    }

    public void addPicture(String storedFilename) {
        pictures.add(storedFilename);
    }

    public Collection<String> getPictures() {
        return unmodifiableCollection(pictures);
    }

    @Override
    public String toString() {
        return toStringHelper(this)                             //
                .add("author", author)                          //
                .add("contents", contents.substring(10) + "...")  //
                .toString();
    }
}
