package org.springframework.data.samples._02_mongo.util;

import org.fest.assertions.core.Condition;

import static com.google.common.collect.Iterables.all;
import static org.fest.util.Preconditions.checkNotNull;
import static org.springframework.data.samples._02_mongo.util.EndsWithAnyOfPredicate.endWithAnyOf;

public class StringsEndsWithAnyOfCondition extends Condition<Iterable<String>> {

    private final String[] ends;

    private StringsEndsWithAnyOfCondition(String... ends) {
        checkNotNull(ends);
        this.ends = ends;
    }

    public static StringsEndsWithAnyOfCondition eachEndsWithAnyOf(String... ends) {
        return new StringsEndsWithAnyOfCondition(ends);
    }

    @Override
    public boolean matches(Iterable<String> values) {
        return all(values, endWithAnyOf(ends));
    }
}
