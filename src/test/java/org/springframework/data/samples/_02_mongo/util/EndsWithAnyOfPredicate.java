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
