package com.luxoft.logeek.benchmark.contains;

import org.openjdk.jmh.annotations.*;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * Measures collection enhancement for HashSet containing simple DTO
 */
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Fork(jvmArgsAppend = {"-XX:+UseParallelGC", "-Xms4g", "-Xmx4g"})
public class EnhancedEmptyHashSetBenchmark {

    @Benchmark
    public boolean measureContainsAllInEmptyJdkHashSet(Data data) {
        return data.jdkHashSet.containsAll(data.dtoCollection);
    }

    @Benchmark
    public boolean measureContainsAllInEmptyIdeaHashSet(Data data) {
        return data.ideaHashSet.containsAll(data.dtoCollection);
    }

    @Benchmark
    public boolean measureContainsInEmptyJdkHashSet(Data data) {
        return data.jdkHashSet.contains(data.dto);
    }

    @Benchmark
    public boolean measureContainsInEmptyIdeaHashSet(Data data) {
        return data.ideaHashSet.contains(data.dto);
    }

    @State(Scope.Thread)
    public static class Data {
        private Dto dto;

        private Set<Dto> jdkHashSet;
        private Set<Dto> ideaHashSet;

        private Collection<Dto> dtoCollection;

        @Setup
        public void setup() {
            dto = new Dto(1, 2);

            jdkHashSet = new java.util.HashSet<>();
            ideaHashSet = new com.luxoft.logeek.collections.HashSet<>();

            dtoCollection = Collections.singleton(dto);
        }
    }

    private static class Dto {
        private final int integer;
        private final long aLong;


        private Dto(int integer, long aLong) {
            this.integer = integer;
            this.aLong = aLong;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Dto dto = (Dto) o;

            return integer == dto.integer && aLong == dto.aLong;
        }

        @Override
        public int hashCode() {
            int result = integer;
            result = 31 * result + (int) (aLong ^ (aLong >>> 32));
            return result;
        }
    }
}
