package com.luxoft.logeek.benchmark.sort;


import org.openjdk.jmh.annotations.*;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Fork(jvmArgsAppend = {"-XX:+UseParallelGC", "-Xms1g", "-Xmx1g"})
public class CollectionSortBenchmark {


    @Benchmark
    public List<Integer> sortBeforeAsList(Data data) {
        Arrays.sort(data.ints, Comparator.reverseOrder());
        return Arrays.asList(data.ints);
    }

    @Benchmark
    public List<Integer> asListBeforeSort(Data data) {
        List<Integer> list = Arrays.asList(data.ints);
        list.sort(Comparator.reverseOrder());
        return list;
    }

    @State(Scope.Thread)
    public static class Data {
        @Param({"5", "10", "100", "1000"})
        private int length;

        private Integer[] ints;

        @Setup
        public void setup() {
            ThreadLocalRandom random = ThreadLocalRandom.current();

            ints = random.ints(length).boxed().toArray(Integer[]::new);
        }
    }
}
