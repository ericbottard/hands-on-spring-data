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

import java.util.Date;

import static com.google.common.base.Objects.toStringHelper;

public class Comment {
    private String author;
    private Date redactionDate;
    private String comment = "";

    public Comment(String author, Date redactionDate, String comment) {
        this.author = author;
        this.redactionDate = redactionDate;
        this.comment = comment;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Date getRedactionDate() {
        return redactionDate;
    }

    public void setRedactionDate(Date redactionDate) {
        this.redactionDate = redactionDate;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return toStringHelper(this)                             //
                .add("author", author)                          //
                .add("date", redactionDate)                     //
                .add("comment", comment.substring(10) + "...")    //
                .toString();
    }
}
