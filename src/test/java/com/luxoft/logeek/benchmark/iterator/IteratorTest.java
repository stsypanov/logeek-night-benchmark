package com.luxoft.logeek.benchmark.iterator;

import org.junit.Test;

import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;

public class IteratorTest {

    @Test
    public void equals() throws Exception {
        List<Integer> items = new Random().ints(100).boxed().collect(Collectors.toList());

        Iterator<String> iterator1 = items.stream().map(Object::toString).collect(Collectors.toList()).iterator();

        Iterator<String> iterator2 = items.stream().map(Object::toString).iterator();

        while (iterator1.hasNext() && iterator2.hasNext()) {
            assertEquals(iterator1.next(), iterator2.next());
        }
    }

}
