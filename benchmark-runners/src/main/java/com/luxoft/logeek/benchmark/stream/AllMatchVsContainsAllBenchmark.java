package com.luxoft.logeek.benchmark.stream;


import org.openjdk.jmh.annotations.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

import static java.util.Arrays.asList;
import static java.util.Arrays.copyOf;
import static java.util.stream.Collectors.toCollection;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;

@SuppressWarnings("unchecked")
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Fork(jvmArgsAppend = {"-XX:+UseParallelGC", "-Xms4g", "-Xmx4g"})
public class AllMatchVsContainsAllBenchmark {

    @Benchmark
    public boolean measureStreamAllMatch(Data data) {
        return data.collection.stream().allMatch(data.anotherCollection::contains);
    }

    @Benchmark
    public boolean measureCollectionContainsAll(Data data) {
        return data.anotherCollection.containsAll(data.collection);
    }

    @State(Scope.Thread)
    public static class Data {
        private static final int arrayList = 1;

        @Param({"1", "10", "100", "1000"})
        private int count;

        @Param({"1", "2"})// 1 for ArrayList 2 for HashSet
        private int collectionType;

        private Collection<Integer> collection;
        private Collection anotherCollection;

        @Setup
        public void setup() {
            if (collectionType == arrayList) {
                anotherCollection = IntStream.range(0, count).boxed().collect(toCollection(ArrayList::new));
                collection = new HashSet(asList(copyOf(anotherCollection.toArray(), anotherCollection.size() / 2)));
            } else {
                anotherCollection = IntStream.range(0, count).boxed().collect(toSet());
                collection = new HashSet(asList(copyOf(anotherCollection.toArray(), anotherCollection.size() / 2)));
            }
        }
    }
}
