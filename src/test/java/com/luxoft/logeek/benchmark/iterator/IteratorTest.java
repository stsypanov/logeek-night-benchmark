package com.luxoft.logeek.benchmark.iterator;

import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

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
        Stream<String> stream1 = items.stream().map(Object::toString);
        Stream<String> stream2 = items.stream().map(Object::toString);
        
        Iterator<String> iterator1 = stream1.collect(toList()).iterator();
        Iterator<String> iterator2 = stream2.iterator();

        while (iterator1.hasNext() && iterator2.hasNext()) {
            assertEquals(iterator1.next(), iterator2.next());
        }
    }

    @Test
    public void setEquals() throws Exception {
		Stream<String> stream1 = items.stream().map(Object::toString);
		Stream<String> stream2 = items.stream().map(Object::toString);

		//todo investigate whetjher it's more performant to collect to list and put into new HashSet
		Iterator<String> iterator1 = stream1.collect(toSet()).iterator();
		Iterator<String> iterator2 = stream2.iterator();

        while (iterator1.hasNext() && iterator2.hasNext()) {
            iterator1.next();
            iterator2.next();
        }
    }
}
