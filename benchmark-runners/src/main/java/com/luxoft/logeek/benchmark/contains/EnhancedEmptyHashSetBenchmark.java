package com.luxoft.logeek.benchmark.contains;

import com.intellij.util.containers.HashSet;
import org.openjdk.jmh.annotations.*;

import java.util.ArrayList;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import static java.util.Collections.singleton;

/**
 * Measures collection enhancement for empty HashSet of {@link Dto}
 */
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
public class EnhancedEmptyHashSetBenchmark {

    @Benchmark
    public boolean measureContainsAllInEmptyJdkHashSet(Data data) {
        return data.emptyJdkHashSet.containsAll(data.dtos);
    }

    @Benchmark
    public boolean measureContainsAllInEmptyIdeaHashSet(Data data) {
        return data.emptyIdeaHashSet.containsAll(data.dtos);
    }

    @Benchmark
    public boolean measureContainsInEmptyJdkHashSet(Data data) {
        return data.emptyJdkHashSet.contains(data.dto);
    }

    @Benchmark
    public boolean measureContainsInEmptyIdeaHashSet(Data data) {
        return data.emptyIdeaHashSet.contains(data.dto);
    }

    @State(Scope.Thread)
    public static class Data {
        private Dto dto;

        private Set<Dto> emptyJdkHashSet;
        private Set<Dto> emptyIdeaHashSet;

        private ArrayList<Dto> dtos;

        @Setup
        public void setup() {
            dto = new Dto(1);

            dtos = new ArrayList<>(singleton(dto));

            emptyJdkHashSet = new java.util.HashSet<>();
            emptyIdeaHashSet = new HashSet<>();
        }
    }
}
