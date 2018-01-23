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

    /**
     * todo investigate why this is faster than empty
     * Report: Array.asList().subList()
     * (new ArrayList()).subList().toArray();
     */
    @Benchmark
    public Integer[] subArrayBySubList_sizedArray(Data data) {
        List<Integer> subList = Arrays.asList(data.array).subList(0, data.to);
        return subList.toArray(new Integer[subList.size()]);
    }

    @Benchmark
    public Integer[] subArrayBySubList_emptyArray(Data data) {
        List<Integer> subList = Arrays.asList(data.array).subList(0, data.to);
        return subList.toArray(new Integer[0]);
    }

    @Benchmark
    public Integer[] subArrayByArraysCopyOf(Data data) {
        return Arrays.copyOf(data.array, data.to);
    }

    @State(Scope.Thread)
    public static class Data {
        @Param({"10", "100", "1000"})
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
