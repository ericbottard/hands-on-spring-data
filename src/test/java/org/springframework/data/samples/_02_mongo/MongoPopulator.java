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

import com.mongodb.gridfs.GridFSFile;
import org.joda.time.DateTime;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.geo.Point;
import org.springframework.data.mongodb.core.index.GeospatialIndex;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.data.samples._02_mongo.domain.Author;
import org.springframework.data.samples._02_mongo.domain.Comment;
import org.springframework.data.samples._02_mongo.domain.Post;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;
import java.util.Random;

import static com.google.common.collect.Lists.newArrayList;

@Component
class MongoPopulator {

    private final GridFsTemplate gridFs;
    private final MongoOperations template;

    // cheap data set
    private final String[] contents = contents();
    private final Comment[] comments = comments();
    private final Author[] authors = authors();
    private final List<String> pictureNames = newArrayList();
    private final String[] filenames = new String[] {
        "/pics/cloudfoundry.png",
        "/pics/super_kiki.jpg",
        "/pics/spring_data.png"
    };

    @Inject
    public MongoPopulator(MongoDbFactory factory, GridFsTemplate gridFs) {
        this.template = new MongoTemplate(factory);
        this.gridFs = gridFs;
    }

    public void init() throws IOException {
        generateAuthors();
        generatePosts();
    }

    public void destroy() {
        template.dropCollection(Post.class);
        template.dropCollection(Author.class);
    }

    private void generateAuthors() {
        template.insertAll(newArrayList(authors));
        template.indexOps(Author.class).ensureIndex(new GeospatialIndex("location"));
    }

    private void generatePosts() throws IOException {
        for (int i = 0; i < 100; i++) {
            Post post = post(contents, comments, i);
            template.insert(post);
            storePictures(post, i%4);
        }
    }

    private Post post(String[] contents, Comment[] comments, int i) throws IOException {
        Post post = new Post(
            authors[i % (authors.length - 1)],
            contents[i % (contents.length - 1)],
            randomDate()
        );
        for (int j = 0; j < new Random().nextInt(6); j++) {
            post.addComment(comments[j % (comments.length - 1)]);
        }
        return post;
    }

    private void storePictures(Post post, int numberOfPics) throws IOException {
        for (int i = 0; i < numberOfPics; i++) {
            String filename = filenames[i];
            String storedFilename = post.getId() + "-" + filename.replace("/pics/", "");
            storeFile(filename, storedFilename);
            post.addPicture(storedFilename);
        }
        template.save(post);
    }

    private void storeFile(String filename, String pictureName) throws IOException {
        InputStream pictureStream = MongoPopulator.class.getResourceAsStream(filename);
        GridFSFile file = gridFs.store(pictureStream, pictureName);
        pictureStream.close();
        pictureNames.add(file.getFilename());
    }
    private Date randomDate() {
        return new DateTime()
            .withYear(2010 + new Random().nextInt(2))
            .withMonthOfYear(1 + new Random().nextInt(12))
            .withDayOfMonth(1 + new Random().nextInt(28))
            .toDate();
    }

    private String[] contents() {
        return new String[] {
            "My money's in that office, right? If she start giving me some bullshit about it ain't there, and we got to go someplace else and get it, I'm gonna shoot you in the head then and there. Then I'm gonna shoot that bitch in the kneecaps, find out where my goddamn money is. She gonna tell me too. Hey, look at me when I'm talking to you, motherfucker. You listen: we go in there, and that nigga Winston or anybody else is in there, you the first motherfucker to get shot. You understand?\n" +
            "Look, just because I don't be givin' no man a foot massage don't make it right for Marsellus to throw Antwone into a glass motherfuckin' house, fuckin' up the way the nigger talks. Motherfucker do that shit to me, he better paralyze my ass, 'cause I'll kill the motherfucker, know what I'm sayin'?\n" +
            "<!-- please do not remove this line -->\n" +
            "<div style=\"display:none;\">\n" +
            "<a href=\"http://slipsum.com\">lorem ipsum</a></div>\n",

            "Now that there is the Tec-9, a crappy spray gun from South Miami. This gun is advertised as the most popular gun in American crime. Do you believe that shit? It actually says that in the little book that comes with it: the most popular gun in American crime. Like they're actually proud of that shit. \n" +
            "Do you see any Teletubbies in here? Do you see a slender plastic tag clipped to my shirt with my name printed on it? Do you see a little Asian child with a blank expression on his face sitting outside on a mechanical helicopter that shakes when you put quarters in it? No? Well, that's what you see at a toy store. And you must think you're in a toy store, because you're here shopping for an infant named Jeb.\n" +
            "<!-- please do not remove this line -->\n" +
            "<div style=\"display:none;\">\n" +
            "<a href=\"http://slipsum.com\">lorem ipsum</a></div>\n",

            "The path of the righteous man is beset on all sides by the iniquities of the selfish and the tyranny of evil men. Blessed is he who, in the name of charity and good will, shepherds the weak through the valley of darkness, for he is truly his brother's keeper and the finder of lost children. And I will strike down upon thee with great vengeance and furious anger those who would attempt to poison and destroy My brothers. And you will know My name is the Lord when I lay My vengeance upon thee.\n" +
            "Your bones don't break, mine do. That's clear. Your cells react to bacteria and viruses differently than mine. You don't get sick, I do. That's also clear. But for some reason, you and I react the exact same way to water. We swallow it too fast, we choke. We get some in our lungs, we drown. However unreal it may seem, we are connected, you and I. We're on the same curve, just on opposite ends.\n" +
            "<!-- please do not remove this line -->\n" +
            "<div style=\"display:none;\">\n" +
            "<a href=\"http://slipsum.com\">lorem ipsum</a></div>\n"
        };
    }

    private Author[] authors() {
        return new Author[] {
            new Author("Florent", "Biville", "flo.b@flobi.org", new Point(0, 50)),
            new Author("Éric", "Bottard", "eric.b@heyrik.biz", new Point(50, 0)),
            new Author("Team", "Spring Data", "team@springdata.org", new Point(50, 50))
        };
    }

    private Comment[] comments() {
        return new Comment[] {
            new Comment("Enlarge it!", new DateTime().withDate(2011, 12, 25).toDate(), "Awesome post, dude!"),
            new Comment("Anonymous", new DateTime().withDate(2012, 1, 1).toDate(), "Happy new year!"),
            new Comment("Posterous", new DateTime().withDate(2012, 7, 7).toDate(), "Well, I strongly do not take position on this"),
            new Comment("Awesome-guy", new DateTime().withDate(2012, 5, 19).toDate(), "That totally rocks punk pandas!"),
            new Comment("James Bond", new DateTime().withDate(2012, 11, 23).toDate(), "Ahahahahahhahahahahaha!"),
            new Comment("Heap Flop", new DateTime().withDate(2012, 8, 5).toDate(), "throw new YourPostSucksAndThisIsNotAnException();"),
            new Comment("Captain Tedlove", new DateTime().withDate(2012, 3, 5).toDate(), "My nickname kicks as much ass as your writing."),
        };
    }
}
