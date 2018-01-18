package com.luxoft.logeek.benchmark.iterator;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Fork(jvmArgsAppend = {"-XX:+UseParallelGC", "-Xms2g", "-Xmx2g"})
@SuppressWarnings({"SimplifyStreamApiCallChains", "WhileLoopReplaceableByForEach"})
public class IteratorFromStreamBenchmark {

    @Benchmark
    public void iteratorFromCollectedList(Data data, Blackhole bh) {
        Iterator<Integer> iterator = data.items.stream()
                .collect(toList())//todo add case for toSet()
                .iterator();

        while (iterator.hasNext())
            bh.consume(iterator.next());
    }

    @Benchmark
    public void iteratorFromStream(Data data, Blackhole bh) {
        Iterator<Integer> iterator = data.items.stream()
                .iterator();

        while (iterator.hasNext())
            bh.consume(iterator.next().intValue());
    }

    @Benchmark
    public void forEach(Data data, Blackhole bh) {
        data.items.stream()
                .forEach(integer -> bh.consume(integer.intValue()));
    }

    @State(Scope.Thread)
    public static class Data {
        Collection<Integer> items;

        @Param({"10", "100", "1000"})
        int size;

        @Param({"ArrayList", "HashSet"})
        String collection;

        @Setup
        public void init() {
            if ("ArrayList".equals(collection)) {
                items = new ArrayList<>(items());
            } else {
                items = new HashSet<>(items());
            }
        }

        private List<Integer> items() {
            return IntStream.range(0, size).boxed().collect(toList());
        }
    }
}
