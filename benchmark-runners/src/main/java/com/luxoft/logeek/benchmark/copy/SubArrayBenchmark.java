package com.luxoft.logeek.benchmark.copy;

import org.openjdk.jmh.annotations.*;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Fork(jvmArgsAppend = {"-XX:+UseParallelGC", "-Xms2g", "-Xmx2g"})
public class SubArrayBenchmark {

    @Benchmark
    public Integer[] measureSubArrayViaSubList(Data data) {
        return Arrays.asList(data.array).subList(0, data.to).toArray(new Integer[0]);
    }

    @Benchmark
    public Integer[] measureSubArrayViaArraysCopyOf(Data data) {
        return Arrays.copyOf(data.array, data.to);
    }

    @Benchmark
    public List<Integer> measureSubListViaArraysAsList(Data data) {
        return Arrays.asList(data.array).subList(0, data.to);
    }

    @Benchmark
    public List<Integer> measureSubListViaArraysCopyOf(Data data) {
        return Arrays.asList(Arrays.copyOf(data.array, data.to));
    }

    @State(Scope.Thread)
    public static class Data {
        @Param({"5", "10", "100"})
        private int size;

        Integer[] array;
        int to;

        @Setup
        public void setup() {
            array = IntStream.range(0, size).boxed().toArray(Integer[]::new);
            to = size / 2;
        }
    }
}
