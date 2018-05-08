package com.luxoft.logeek.benchmark.map;

import com.jdk.TreeMap;
import org.openjdk.jmh.annotations.*;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
public class TreeMapPutBenchmark {

    @Benchmark
    public TreeMap<Integer, Integer> putIntoConventional(Data data) {
        TreeMap<Integer, Integer> conventional = new TreeMap<>();
        List<Integer> keys = data.keys;
        List<Integer> values = data.values;
        for (int i = 0, keysSize = keys.size(); i < keysSize; i++) {
            Integer key = keys.get(i);
            Integer value = values.get(i);
            conventional.put(key, value);
        }
        return conventional;
    }

    @Benchmark
    public com.jdk.patched.TreeMap<Integer, Integer> putIntoPatched(Data data) {
        com.jdk.patched.TreeMap<Integer, Integer> patched = new com.jdk.patched.TreeMap<>();
        List<Integer> keys = data.keys;
        List<Integer> values = data.values;
        for (int i = 0, keysSize = keys.size(); i < keysSize; i++) {
            Integer key = keys.get(i);
            Integer value = values.get(i);
            patched.put(key, value);
        }
        return patched;
    }

    @State(Scope.Thread)
    public static class Data {

        @Param({"10", "100", "1000"})
        private int size;

        private List<Integer> keys;
        private List<Integer> values;

        @Setup
        public void setup() {
            keys = IntStream
                    .range(0, size)
                    .boxed()
                    .collect(Collectors.toList());

            values = keys.stream().map(integer -> integer++).collect(Collectors.toList());

            Collections.shuffle(keys);
            Collections.shuffle(values);
        }

    }
}
