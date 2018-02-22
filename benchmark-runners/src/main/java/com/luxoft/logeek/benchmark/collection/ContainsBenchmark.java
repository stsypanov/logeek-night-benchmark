package com.luxoft.logeek.benchmark.collection;

import org.openjdk.jmh.annotations.*;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
public class ContainsBenchmark {

    @Benchmark
    public boolean handWritten(Data data) {
        final Integer randomInteger = data.randomInteger;
        final Collection<Integer> collection = data.collection;

        for (Integer integer : collection) {
            if (Objects.equals(integer, randomInteger)) {
                return true;
            }
        }
        return false;
    }

    @Benchmark
    public boolean provided(Data data) {
        final Integer randomInteger = data.randomInteger;
        final Collection<Integer> collection = data.collection;

        return collection.contains(randomInteger);
    }

    @State(Scope.Thread)
    public static class Data {
        Collection<Integer> collection;
        Integer randomInteger;

        private static final String ARRAY_LIST = "ArrayList";
        private static final String HASH_SET = "HashSet";
        private static final String VECTOR = "Vector";

        @Param({ARRAY_LIST, HASH_SET, VECTOR})
        String collectionType;

        @Param({"10", "100", "1000"})
        int size;

        @Setup
        public void setup() {
            collection = IntStream
                    .range(0, size)
                    .boxed()
                    .collect(freshCollector());

            final int index = ThreadLocalRandom.current().nextInt(size);
            randomInteger = new ArrayList<>(collection).get(index);
        }

        Collector<Integer, ?, Collection<Integer>> freshCollector() {
            switch (collectionType) {
                case VECTOR:
                    return Collectors.toCollection(Vector::new);
                case HASH_SET:
                    return Collectors.toCollection(HashSet::new);
                case ARRAY_LIST:
                    return Collectors.toCollection(ArrayList::new);
                default:
                    throw new IllegalArgumentException(collectionType);
            }
        }
    }
}
