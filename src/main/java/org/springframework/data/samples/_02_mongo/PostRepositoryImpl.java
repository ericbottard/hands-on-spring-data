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

import com.google.common.base.Function;
import com.mongodb.gridfs.GridFSDBFile;
import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.data.samples._02_mongo.domain.Post;

import javax.inject.Inject;
import java.util.List;

import static com.google.common.collect.Lists.transform;
import static org.springframework.data.mongodb.core.query.Query.query;
import static org.springframework.data.mongodb.gridfs.GridFsCriteria.whereFilename;

class PostRepositoryImpl implements CustomPictureRepository {

    private static final Function<GridFSDBFile,GridFsResource> intoResources = new Function<GridFSDBFile, GridFsResource>() {
        @Override
        public GridFsResource apply(GridFSDBFile file) {
            return new GridFsResource(file);
        }
    };

    @Inject
    private GridFsTemplate fileTemplate;

    @Override
    public List<GridFsResource> findPicturesByPost(Post post) {
        List<GridFSDBFile> dbFiles = fileTemplate.find(query(whereFilename().in(post.getPictures())));
        return transform(dbFiles, intoResources);
    }
}
