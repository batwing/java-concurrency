package com.java.recruitment.tsystems;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

public class ImmutableCollection {
    public ImmutableCollection() {
        Map<Integer, String> immutableMap = Collections.singletonMap(10,"test");
        List<String> units= Arrays.asList("One", "Two", "Three", "Four");
        units = Collections.unmodifiableList(units);

        Set<String> immutableSet= new HashSet<>(Arrays.asList("One", "Two", "Three", "Four"));
        immutableSet = Collections.unmodifiableSet(immutableSet);
    }
}
