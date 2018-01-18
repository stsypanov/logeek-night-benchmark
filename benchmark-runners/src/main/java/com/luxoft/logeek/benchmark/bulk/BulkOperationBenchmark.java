package com.luxoft.logeek.benchmark.bulk;

import org.openjdk.jmh.annotations.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Fork(jvmArgsAppend = {"-XX:+UseParallelGC", "-Xms2g", "-Xmx2g"})
@SuppressWarnings({"UseBulkOperation", "UnnecessaryLocalVariable"})
public class BulkOperationBenchmark {

    @Benchmark
    public Collection<Integer> oneByOne(Data data) {
        Collection<Integer> newCollection = data.freshCollection();
        for (Integer item : data.items) {
            newCollection.add(item);
        }
        return newCollection;
    }

    @Benchmark
    public Collection<Integer> addAll(Data data) {
        Collection<Integer> newCollection = data.freshCollection();
        newCollection.addAll(data.items);
        return newCollection;
    }

    @Benchmark
    public Collection<Integer> constructor(Data data) {
        Collection<Integer> newCollection = data.freshCollection(data.items);
        return newCollection;
    }

    @State(Scope.Thread)
    public static class Data {
        @Param({"ArrayList", "HashSet"})
        private String collection;

        @Param({"10", "100", "1000"})
        private int size;

        private List<Integer> items;

        @Setup
        public void init() {
            items = IntStream
                    .range(0, size)
                    .boxed()
                    .collect(toList());
        }

        Collection<Integer> freshCollection() {
            return "ArrayList".equals(collection)
                    ? new ArrayList<>()
                    : new HashSet<>();
        }

        Collection<Integer> freshCollection(Collection<Integer> items) {
            return "ArrayList".equals(collection)
                    ? new ArrayList<>(items)
                    : new HashSet<>(items);
        }
    }
}
