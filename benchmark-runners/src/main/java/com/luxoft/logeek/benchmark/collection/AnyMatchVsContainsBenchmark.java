package com.luxoft.logeek.benchmark.collection;

import org.openjdk.jmh.annotations.*;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Fork(jvmArgsAppend = {"-XX:+UseParallelGC", "-Xms1g", "-Xmx1g"})
public class AnyMatchVsContainsBenchmark {

    @Benchmark
    public boolean anyMatch(Data data) {
        Integer integer = data.integer;
        return Arrays.stream(data.integers).anyMatch(existing -> existing.equals(integer));
    }

    @Benchmark
    public boolean contains(Data data) {
        Integer integer = data.integer;
        return Arrays.asList(data.integers).contains(integer);
    }

    @State(Scope.Thread)
    public static class Data {
        Integer integer;
        Integer[] integers;

        @Param({"10", "100", "1000"})
        int size;

        @Setup
        public void setup() {
            integers = IntStream
                    .range(0, size)
                    .boxed()
                    .toArray(Integer[]::new);

            integer = integers[size / 2];
        }
    }
}
