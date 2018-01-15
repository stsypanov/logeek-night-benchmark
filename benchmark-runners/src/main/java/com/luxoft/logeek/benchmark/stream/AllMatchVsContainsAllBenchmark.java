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
@Fork(jvmArgsAppend = {"-XX:+UseParallelGC", "-Xms2g", "-Xmx2g"})
public class AllMatchVsContainsAllBenchmark {

    @Benchmark
    public boolean streamAllMatch(Data data) {
        return data.collection.stream().allMatch(data.anotherCollection::contains);
    }

    @Benchmark
    public boolean collectionContainsAll(Data data) {
        return data.anotherCollection.containsAll(data.collection);
    }

    @State(Scope.Thread)
    public static class Data {

        @Param({"10", "100", "1000"})
        private int count;

        @Param({"ArrayList", "HashSet"})
        private String collectionType;

        private Collection<Integer> collection;
        private Collection<Integer> anotherCollection;

        @Setup
        public void setup() {
            if ("ArrayList".equals(collectionType)) {
                anotherCollection = IntStream.range(0, count).boxed().collect(toCollection(ArrayList::new));
                collection = new HashSet<>(halfOfOriginalCollection(anotherCollection));
            } else {
                anotherCollection = IntStream.range(0, count).boxed().collect(toCollection(HashSet::new));
                collection = new HashSet<>(halfOfOriginalCollection(anotherCollection));
            }
        }

        private List<Integer> halfOfOriginalCollection(Collection<Integer> original) {
            Integer[] integers = copyOf(original.toArray(new Integer[0]), original.size() / 2);
            return asList(integers);
        }
    }
}
