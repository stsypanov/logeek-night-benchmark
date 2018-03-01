package com.luxoft.logeek.benchmark.collection;

import org.openjdk.jmh.annotations.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

import static java.util.Arrays.asList;
import static java.util.Arrays.copyOf;
import static java.util.stream.Collectors.toCollection;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
public class RemoveIfVsRetainAllBenchmark {

    @Benchmark
    public boolean retainAll(Data data) {
        Collection<Integer> collection = data.collection;
        Collection<Integer> anotherCollection = data.anotherCollection;

        return collection.retainAll(anotherCollection);
    }

    @Benchmark
    public boolean removeIf(Data data) {
        Collection<Integer> collection = data.collection;
        Collection<Integer> anotherCollection = data.anotherCollection;

        return collection.removeIf(integer -> !anotherCollection.contains(integer));
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
