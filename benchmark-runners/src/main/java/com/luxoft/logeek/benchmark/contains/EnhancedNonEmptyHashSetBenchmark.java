package com.luxoft.logeek.benchmark.contains;

import org.openjdk.jmh.annotations.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import static java.util.Collections.singleton;

/**
 * Measures collection enhancement for HashSet of {@link Dto}
 */
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
public class EnhancedNonEmptyHashSetBenchmark {

    @Benchmark
    public boolean measureContainsAllInNonEmptyJdkHashSet(Data data) {
        return data.jdkHashSet.containsAll(data.dtos);
    }

    @Benchmark
    public boolean measureContainsAllInNonEmptyIdeaHashSet(Data data) {
        return data.ideaHashSet.containsAll(data.dtos);
    }

    @Benchmark
    public boolean measureContainsInNonEmptyJdkHashSet(Data data) {
        return data.jdkHashSet.contains(data.dto);
    }

    @Benchmark
    public boolean measureContainsInNonEmptyIdeaHashSet(Data data) {
        return data.ideaHashSet.contains(data.dto);
    }

    @State(Scope.Thread)
    public static class Data {
        private Dto dto;

        private java.util.HashSet<Dto> jdkHashSet;
        private com.luxoft.logeek.collections.HashSet<Dto> ideaHashSet;

        private ArrayList<Dto> dtos;

        @Setup
        public void setup() {
            dto = new Dto(1);

            Set<Dto> one = singleton(dto);

            dtos = new ArrayList<>(one);

            jdkHashSet = new java.util.HashSet<>(one);
            ideaHashSet = new com.luxoft.logeek.collections.HashSet<>(one);
        }
    }
}
