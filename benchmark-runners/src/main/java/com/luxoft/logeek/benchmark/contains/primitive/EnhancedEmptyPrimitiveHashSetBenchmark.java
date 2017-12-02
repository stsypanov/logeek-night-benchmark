package com.luxoft.logeek.benchmark.contains.primitive;

import org.openjdk.jmh.annotations.*;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import static java.util.Collections.singleton;

/**
 * Measures collection enhancement for empty HashSet of {@link Integer}
 */
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
public class EnhancedEmptyPrimitiveHashSetBenchmark {

    @Benchmark
    public boolean measureContainsAllInEmptyJdkHashSet(Data data) {
        return data.emptyJdkHashSet.containsAll(data.integers);
    }

    @Benchmark
    public boolean measureContainsAllInEmptyIdeaHashSet(Data data) {
        return data.emptyIdeaHashSet.containsAll(data.integers);
    }

    @Benchmark
    public boolean measureContainsInEmptyJdkHashSet(Data data) {
        return data.emptyJdkHashSet.contains(data.integer);
    }

    @Benchmark
    public boolean measureContainsInEmptyIdeaHashSet(Data data) {
        return data.emptyIdeaHashSet.contains(data.integer);
    }

    @State(Scope.Thread)
    public static class Data {
        private Integer integer;

        private java.util.HashSet<Integer> emptyJdkHashSet;
        private com.luxoft.logeek.collections.HashSet<Integer> emptyIdeaHashSet;

        private ArrayList<Integer> integers;

        @Setup
        public void setup() {
            integer = 1;

            integers = new ArrayList<>(singleton(integer));

            emptyJdkHashSet = new java.util.HashSet<>();
            emptyIdeaHashSet = new com.luxoft.logeek.collections.HashSet<>();
        }
    }

}
