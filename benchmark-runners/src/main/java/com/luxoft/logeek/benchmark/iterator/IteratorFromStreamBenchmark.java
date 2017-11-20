package com.luxoft.logeek.benchmark.iterator;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

import static java.util.stream.Collectors.toCollection;
import static java.util.stream.Collectors.toList;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
public class IteratorFromStreamBenchmark {

    @Benchmark
    public void measureIteratorFromCollectedList(Data data, Blackhole bh) {
        Iterator<String> iterator = data.items.stream()
                .map(Object::toString)
                .collect(toList())//todo add case for toSet()
                .iterator();

        while (iterator.hasNext())
            bh.consume(iterator.next());
    }

    @Benchmark
    public void measureIteratorFromStream(Data data, Blackhole bh) {
        Iterator<String> iterator = data.items.stream()
                .map(Object::toString)
                .iterator();

        while (iterator.hasNext())
            bh.consume(iterator.next());
    }

    @Benchmark
    public void measureForEach(Data data, Blackhole bh) {
        data.items.stream()
                .map(Object::toString)
                .forEach(bh::consume);
    }

    @State(Scope.Thread)
    public static class Data {
        private Collection<Integer> items;

        @Param({"10", "100", "1000"})
        private int size;

        @Param({"list", "set"})
        private String collectionType;

        private ThreadLocalRandom random;

        @Setup
        public void init() {
            random = ThreadLocalRandom.current();

            if ("list".equals(collectionType)) {
                items = random.ints(size).boxed().collect(toCollection(ArrayList::new));
            } else {
                items = random.ints(size).boxed().collect(toCollection(HashSet::new));
            }
        }
    }
}
