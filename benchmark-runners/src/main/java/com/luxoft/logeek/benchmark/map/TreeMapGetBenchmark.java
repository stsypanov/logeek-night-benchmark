package com.luxoft.logeek.benchmark.map;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
public class TreeMapGetBenchmark {

    @Benchmark
    public void getFromConventional(Blackhole bh, Data data) {
        for (Integer key : data.keys) {
            bh.consume(data.conventional.get(key));
        }
    }

    @Benchmark
    public void getFromPatched(Blackhole bh, Data data) {
        for (Integer key : data.keys) {
            bh.consume(data.patched.get(key));
        }
    }

    @State(Scope.Thread)
    public static class Data {

        @Param({"10", "100", "1000"})
        private int size;

        private com.jdk.TreeMap<Integer, Integer> conventional;
        private com.jdk.patched.TreeMap<Integer, Integer> patched;

        private List<Integer> keys;

        @Setup
        public void setup() {
            keys = IntStream
                    .range(0, size)
                    .boxed()
                    .collect(Collectors.toList());

            Collections.shuffle(keys);

            conventional = new com.jdk.TreeMap<>();
            patched = new com.jdk.patched.TreeMap<>();

            keys.forEach(integer -> {
                patched.put(integer, integer + 1);
                conventional.put(integer, integer + 1);
            });
        }

    }
}
