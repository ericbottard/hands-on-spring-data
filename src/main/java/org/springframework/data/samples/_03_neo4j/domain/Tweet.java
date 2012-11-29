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
package org.springframework.data.samples._03_neo4j.domain;

import org.springframework.data.neo4j.annotation.*;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * @author mh
 * @since 24.07.12
 */
@NodeEntity
public class Tweet {
    @GraphId Long id;

    @Indexed(unique=true) Long tweetId;

    String text;

    public String getText() {
		return text;
	}

	@Fetch User sender;
    @Fetch @RelatedTo(type="TAG") Collection<Tag> tags=new HashSet<Tag>();
    @Fetch @RelatedTo(type="MENTION") private Set<User> mentions=new HashSet<User>();

    public Tweet() {
    }

    public Tweet(long tweetId, User sender, String text) {
        this.tweetId = tweetId;
        this.sender = sender;
        this.text = text;
    }

    public void addMention(User mention) {
        this.mentions.add(mention);
    }
    public Long getId() {
        return id;
    }

    public Long getTweetId() {
        return tweetId;
    }

    public User getSender() {
        return sender;
    }

    @Override
    public String toString() {
        return "Tweet " + tweetId +
                ": " + text  +
                " by " + sender;
    }

    public Set<User> getMentions() {
        return mentions;
    }

    public Collection<Tag> getTags() {
        return tags;
    }

    public void addTag(Tag tag) {
        tags.add(tag);
    }

}
