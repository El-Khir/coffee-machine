package com.clv.utils;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class CollectionUtils {

    private CollectionUtils() {
    }

    public static <T> Set<T> createImmutableSet(T...items) {
        Set<T> intermediateSet = new HashSet<>();
        for(T item :items ) {
            intermediateSet.add(item);
        }
        return Collections.unmodifiableSet(intermediateSet);
    }
}
