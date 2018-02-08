package com.luxoft.logeek.benchmark.stream;


import org.openjdk.jmh.annotations.*;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

import static java.util.Arrays.asList;
import static java.util.Arrays.copyOf;
import static java.util.stream.Collectors.toCollection;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Fork(jvmArgsAppend = {"-XX:+UseParallelGC", "-Xms1g", "-Xmx1g"})
public class AllMatchVsContainsAllBenchmark {

    @Benchmark
    public boolean streamAllMatch(Data data) {
        return data.set.stream().allMatch(data.collection::contains);
    }

    @Benchmark
    public boolean collectionContainsAll(Data data) {
        return data.collection.containsAll(data.set);
    }

    @State(Scope.Thread)
    public static class Data {

        @Param({"10", "100", "1000"})
        private int count;

        @Param({"ArrayList", "HashSet"})
        private String collectionType;

        private HashSet<Integer> set;
        private Collection<Integer> collection;

        @Setup
        public void setup() {
            if ("ArrayList".equals(collectionType)) {
                collection = IntStream.range(0, count).boxed().collect(toCollection(ArrayList::new));
                set = new HashSet<>(halfOfOriginalCollection(collection));
            } else {
                collection = IntStream.range(0, count).boxed().collect(toCollection(HashSet::new));
                set = new HashSet<>(halfOfOriginalCollection(collection));
            }
        }

        private List<Integer> halfOfOriginalCollection(Collection<Integer> original) {
            Integer[] array = original.toArray(new Integer[0]);
            Integer[] integers = copyOf(array, original.size() / 2);
            return asList(integers);
        }
    }
}
