package com.luxoft.logeek.benchmark.iterator;

import com.luxoft.logeek.benchmark.BenchmarkBase;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.util.*;
import java.util.concurrent.TimeUnit;

import static java.util.stream.Collectors.toCollection;
import static java.util.stream.Collectors.toList;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
public class IteratorFromStreamBenchmark extends BenchmarkBase {

    private Collection<Integer> items;

    @Param({"10", "100", "1000"})
    private int size;

    @Param({"list", "set"})
    private String collectionType;

    @Setup
    public void init() {
        super.init();
        if ("list".equals(collectionType)) {
            items = random.ints(size).boxed().collect(toCollection(ArrayList::new));
        } else {
            items = random.ints(size).boxed().collect(toCollection(HashSet::new));
        }
    }

    @Benchmark
    public void measureIteratorFromCollectedList(Blackhole bh) {
        Iterator<String> iterator = items.stream()
                .map(Object::toString)
                .collect(toList())
                .iterator();

        while (iterator.hasNext())
            bh.consume(iterator.next());
    }

    @Benchmark
    public void measureIteratorFromStream(Blackhole bh) {
        Iterator<String> iterator = items.stream()
                .map(Object::toString)
                .iterator();

        while (iterator.hasNext())
            bh.consume(iterator.next());
    }

    @Benchmark
    public void measureForEach(Blackhole bh) {
        items.stream()
                .map(Object::toString)
                .forEach(bh::consume);
    }
}
