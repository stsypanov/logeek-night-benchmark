package com.luxoft.logeek.benchmark.collection;

import org.openjdk.jmh.annotations.*;

import java.util.*;
import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Fork(jvmArgsAppend = {"-XX:+UseParallelGC", "-Xms1g", "-Xmx1g"})
public class PatchedIndexOfBenchmark {

    @Benchmark
    public int conventionalIndexOfNull(Data data) {
        return data.conventional.indexOf(data.element);
    }

    @Benchmark
    public int patchedIndexOfNull(Data data) {
        return data.patched.indexOf(data.element);
    }

    @State(Scope.Thread)
    public static class Data {

        List<Integer> conventional;
        List<Integer> patched;

        Integer element;

        @Setup
        @SuppressWarnings("ALL")
        public void setup() {
                element = null;
                conventional = java.util.Arrays.asList(element);
                patched = com.jdk.Arrays.asList(element);
        }

    }
}
