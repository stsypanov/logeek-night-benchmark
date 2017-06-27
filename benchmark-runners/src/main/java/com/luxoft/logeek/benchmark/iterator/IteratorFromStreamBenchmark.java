package com.luxoft.logeek.benchmark.iterator;

import com.luxoft.logeek.benchmark.BaseBenchmark;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@State(Scope.Benchmark)
public class IteratorFromStreamBenchmark extends BaseBenchmark {

    private List<Integer> itemList;
    private Set<Integer> itemSet;

    @Param({"10", "100", "1000", "10000", "100000"})
    private int size;

    @Setup(value = Level.Iteration)
    public void init() {
        super.init();
        itemList = random.ints(size).boxed().collect(toList());
        itemSet = new HashSet<>(itemList);
    }


    @Benchmark
    public void measureIteratorFromCollectedList(Blackhole bh) {
        Iterator<String> iterator = itemList.stream()
                .map(Object::toString)
                .collect(toList())
                .iterator();

        while (iterator.hasNext())
            bh.consume(iterator.next());
    }

    @Benchmark
    public void measureIteratorFromStream(Blackhole bh) {
        Iterator<String> iterator = itemList.stream()
                .map(Object::toString)
                .iterator();

        while (iterator.hasNext())
            bh.consume(iterator.next());
    }

    @Benchmark
    public void measureIteratorFromHashSet(Blackhole bh) {
        Iterator<String> iterator = itemSet
                .stream()
                .map(Object::toString)
                .collect(toSet())
                .iterator();
        
        while (iterator.hasNext())
            bh.consume(iterator.next());
    }

    @Benchmark
    public void measureIteratorFromSetStream(Blackhole bh) {
        Iterator<String> iterator = itemSet
                .stream()
                .map(Object::toString)
                .iterator();

        while (iterator.hasNext())
            bh.consume(iterator.next());
    }
}
