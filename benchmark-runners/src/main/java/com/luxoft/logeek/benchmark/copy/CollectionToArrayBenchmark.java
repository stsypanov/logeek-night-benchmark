package com.luxoft.logeek.benchmark.copy;

import org.openjdk.jmh.annotations.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Fork(jvmArgsAppend = {"-XX:+UseParallelGC", "-Xms2g", "-Xmx2g"})
public class CollectionToArrayBenchmark {

    @Benchmark
    public Integer[] emptyArray(Data data) {
        return data.items.toArray(new Integer[0]);
    }

    @Benchmark
    public Integer[] sizedArray(Data data) {
        int size = data.items.size();
        return data.items.toArray(new Integer[size]);
    }

    @State(Scope.Thread)
    public static class Data {

        @Param({"10", "100", "1000"})
        int size;

        @Param({"ArrayList", "HashSet"})
        String collection;

        Collection<Integer> items;

        @Setup
        public void init() {
            if ("ArrayList".equals(collection)) {
                items = new ArrayList<>(items());
            } else {
                items = new HashSet<>(items());
            }
        }

        private List<Integer> items() {
            return IntStream.range(0, size)
                    .boxed()
                    .collect(toList());
        }
    }
}
