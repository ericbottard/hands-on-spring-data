package org.springframework.data.samples._02_mongo;

import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.data.samples._02_mongo.domain.Post;

import java.util.List;

class PostRepositoryImpl implements CustomPostRepository {

    /*
     * This implementation will be automatically discovered by
     * Spring Data and added to the matching Spring Data repository.
     * In other words, you don't have to annotate this class with
     * @Component (or its specializing annotations).
     *
     * Within this class, you can benefit from Dependency Injection of beans
     * defined by the application context.
     */

    @Override
    public List<GridFsResource> findImagesByPost(Post post) {
        throw new UnsupportedOperationException("TODO");
    }
}
