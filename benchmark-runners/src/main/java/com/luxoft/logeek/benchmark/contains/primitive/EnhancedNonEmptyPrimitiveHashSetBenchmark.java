package com.luxoft.logeek.benchmark.contains.primitive;

import com.intellij.util.containers.HashSet;
import org.openjdk.jmh.annotations.*;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import static java.util.Collections.singleton;

/**
 * Measures collection enhancement for HashSet of {@link Integer}
 */
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
public class EnhancedNonEmptyPrimitiveHashSetBenchmark {

    @Benchmark
    public boolean measureContainsAllInNonEmptyJdkHashSet(Data data) {
        return data.jdkHashSet.containsAll(data.integers);
    }

    @Benchmark
    public boolean measureContainsAllInNonEmptyIdeaHashSet(Data data) {
        return data.ideaHashSet.containsAll(data.integers);
    }

    @Benchmark
    public boolean measureContainsInNonEmptyJdkHashSet(Data data) {
        return data.jdkHashSet.contains(data.integer);
    }

    @Benchmark
    public boolean measureContainsInNonEmptyIdeaHashSet(Data data) {
        return data.ideaHashSet.contains(data.integer);
    }

    @State(Scope.Thread)
    public static class Data {
        private Integer integer;

        private java.util.HashSet<Integer> jdkHashSet;
        private HashSet<Integer> ideaHashSet;

        private ArrayList<Integer> integers;

        @Setup
        public void setup() {
            integer = 1;

            integers = new ArrayList<>(singleton(integer));

            jdkHashSet = new java.util.HashSet<>(integers);
            ideaHashSet = new HashSet<>(integers);
        }
    }

}
