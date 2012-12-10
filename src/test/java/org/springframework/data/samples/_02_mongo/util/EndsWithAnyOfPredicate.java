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
package org.springframework.data.samples._02_mongo.util;

import com.google.common.base.Predicate;

import static com.google.common.collect.Lists.newArrayList;

public class EndsWithAnyOfPredicate implements Predicate<String> {

    private final Iterable<String> ends;

    private EndsWithAnyOfPredicate(Iterable<String> ends) {
        this.ends = ends;
    }

    public static EndsWithAnyOfPredicate endWithAnyOf(String... ends) {
        return new EndsWithAnyOfPredicate(newArrayList(ends));
    }

    @Override
    public boolean apply(String input) {
        for(String end : ends) {
            if(input.endsWith(end)) {
                return true;
            }
        }
        return false;
    }
}
