package org.springframework.data.samples._02_mongo;

import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.data.samples._02_mongo.domain.Post;

import java.util.List;

public interface CustomPostRepository {

    List<GridFsResource> findImagesByPost(Post post);
}
