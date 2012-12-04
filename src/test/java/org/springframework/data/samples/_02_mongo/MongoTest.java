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
package org.springframework.data.samples._02_mongo;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.geo.Circle;
import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.data.samples._02_mongo.domain.Author;
import org.springframework.data.samples._02_mongo.domain.Post;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.inject.Inject;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.fest.assertions.api.Assertions.extractProperty;
import static org.springframework.data.samples._02_mongo.util.StringsEndsWithAnyOfCondition.eachEndsWithAnyOf;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext-mongo.xml")
public class MongoTest {

    @Inject
    private MongoPopulator populator;

    @Inject
    private PostRepository postRepository;

    @Inject
    private AuthorRepository authorRepository;

    @Before
    public void setup() throws IOException {
        // populate! populate!
        populator.init();
    }

    @Test
    public void should_find_posts_containing_Miami() {
        List<Post> postsContainingMiami = postRepository.findByContentsContains("Miami");
        assertThat(postsContainingMiami).hasSize(50);
    }

    @Test
    public void should_find_authors_starting_with_Biv_string_within_the_specified_circle() {

        // combine predicates in magic finder, using geospatial utilities
        Circle where = new Circle(0, 0, 70);
        List<Author> authorsAround = authorRepository.findByLocationWithinAndLastNameStartsWith(where, "Biv");

        // only one should match ...
        assertThat(authorsAround).hasSize(1);
        Author uniqueMatch = authorsAround.iterator().next();

        // ... with the following email
        assertThat(uniqueMatch.getEmail()).isEqualTo("flo.b@flobi.org");
    }

    @Test
    public void should_find_all_pictures_of_a_given_post() {

        Post post = whateverPostWillDo();

        // find all pictures stored in GridFs of the specified post
        List<GridFsResource> pictures = postRepository.findPicturesByPost(post);

        // extract filenames from the collection
        Iterable<String> filenames = extractProperty("filename", String.class).from(pictures);

        // check each of them ends with any of the specified filename ends
        assertThat(filenames).hasSize(2)
                .is(eachEndsWithAnyOf("super_kiki.jpg", "cloudfoundry.png"));
    }

    @After
    public void tearDown() {
        // destrrrroy everything!
        populator.destroy();
    }

    private Post whateverPostWillDo() {
        Iterator<Post> iterator = postRepository.findAll(new Sort(Sort.Direction.ASC, "contents")).iterator();
        iterator.next();
        return iterator.next();
    }

}
