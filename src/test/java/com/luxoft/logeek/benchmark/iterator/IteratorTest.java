package com.luxoft.logeek.benchmark.iterator;

import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;
import static org.junit.Assert.assertEquals;

public class IteratorTest {

    private List<Integer> items;

    @Before
    public void setUp() throws Exception {
        items = new Random().ints(100).boxed().collect(toList());
    }

    @Test
    public void equals() throws Exception {

        Iterator<String> iterator1 = items.stream().map(Object::toString).collect(toList()).iterator();

        Iterator<String> iterator2 = items.stream().map(Object::toString).iterator();

        while (iterator1.hasNext() && iterator2.hasNext()) {
            assertEquals(iterator1.next(), iterator2.next());
        }
    }

    @Test
    public void setEquals() throws Exception {
        Iterator<String> iterator1 = new HashSet<>(items)
                .stream()
                .map(Object::toString)
                .collect(toSet())
                .iterator();

        Iterator<String> iterator2 = new HashSet<>(items)
                .stream()
                .map(Object::toString)
                .iterator();

        while (iterator1.hasNext() && iterator2.hasNext()) {
            assertEquals(iterator1.next(), iterator2.next());
        }
    }
}
