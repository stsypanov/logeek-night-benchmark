package com.luxoft.logeek.benchmark.iterator;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Fork(jvmArgsAppend = {"-XX:+UseParallelGC", "-Xms2g", "-Xmx2g", "-XX:+UnlockDiagnosticVMOptions", "-XX:+DebugNonSafepoints"})
@SuppressWarnings({"SimplifyStreamApiCallChains", "WhileLoopReplaceableByForEach"})
public class IteratorFromStreamBenchmark {

    @Benchmark
    public void iteratorFromCollectedList(Data data, Blackhole bh) {
        Collection<Integer> items = data.items;
        Iterator<Long> iterator = items.stream()
                .map(Long::valueOf)
                .collect(toList())//todo add case for toSet()
                .iterator();

        while (iterator.hasNext())
            bh.consume(iterator.next());
    }

    @Benchmark
    public void iteratorFromStream(Data data, Blackhole bh) {
        Collection<Integer> items = data.items;
        Iterator<Long> iterator = items.stream()
                .map(Long::valueOf)
                .iterator();

        while (iterator.hasNext())
            bh.consume(iterator.next());
    }

    @Benchmark
    public void forEach(Data data, Blackhole bh) {
        Collection<Integer> items = data.items;
        items.stream()
                .map(Long::valueOf)
                .forEach(bh::consume);
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
