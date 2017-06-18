package com.luxoft.logeek.benchmark.iterator;

import com.luxoft.logeek.benchmark.BenchmarkBase;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@State(Scope.Benchmark)
public class IteratorFromStreamBenchmark extends BenchmarkBase {

    private List<Integer> items;

    @Param({"10", "100", "1000", "10000", "100000"})
    private int size;

    @Setup(value = Level.Iteration)
    public void init() {
        super.init();
        items = new Random().ints(size).boxed().collect(Collectors.toList());
    }


    @Benchmark
    public void measureIteratorFromListCollectedFormStream(Blackhole bh) {

        Iterator<String> iterator = items.stream()
                .map(Object::toString)
                .collect(Collectors.toList())
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
}
