package com.luxoft.logeek.benchmark.array;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
public class ArrayVsAsListIterationBenchmark {

    @Benchmark
    public void iterateOverArray(ArrayHolder holder, Blackhole bh) {
        Integer[] array = holder.array;
        for (Integer integer : array) {
            bh.consume(integer);
        }
    }

    @Benchmark
    public void iterateOverArraysAsList(ArrayHolder holder, Blackhole bh) {
        Integer[] array = holder.array;
        List<Integer> integers = Arrays.asList(array);
        for (Integer integer : integers) {
            bh.consume(integer);
        }
    }

    @State(Scope.Thread)
    public static class ArrayHolder {
        Integer[] array;

        @Param({"1", "5", "10", "100", "1000"})
        int length;

        @Setup
        public void setup() {
            array = IntStream.range(0, length).boxed().toArray(Integer[]::new);

        }
    }
}
