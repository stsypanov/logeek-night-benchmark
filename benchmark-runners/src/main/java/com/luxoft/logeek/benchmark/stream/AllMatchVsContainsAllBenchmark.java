package com.luxoft.logeek.benchmark.stream;


import org.openjdk.jmh.annotations.*;

import java.util.Collection;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

import static java.util.Arrays.asList;
import static java.util.Arrays.copyOf;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;

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
        return data.collection.containsAll(data.anotherCollection);
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
                collection = IntStream.range(0, count).boxed().collect(toList());
                anotherCollection = asList(copyOf(collection.toArray(), collection.size() / 2));
            } else {
                collection = IntStream.range(0, count).boxed().collect(toSet());
                anotherCollection = asList(copyOf(collection.toArray(), collection.size() / 2));
            }
        }
    }
}
