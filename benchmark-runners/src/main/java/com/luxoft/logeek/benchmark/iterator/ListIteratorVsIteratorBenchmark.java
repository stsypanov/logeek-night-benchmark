package com.luxoft.logeek.benchmark.iterator;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

@State(Scope.Thread)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Fork(jvmArgsAppend = {"-XX:+UseParallelGC", "-Xms1g", "-Xmx1g"})
public class ListIteratorVsIteratorBenchmark {

    @Param({"10", "100", "1000", "10000"})
    private int size;

    private List<Object> list;

    @Setup
    public void setup() {
        Object[] objects = IntStream
                .range(0, size)
                .boxed()
                .map(i -> new Object())
                .toArray();

        list = Arrays.asList(objects);
    }

    @Benchmark
    public void iterator(Blackhole bh) {
        Iterator<Object> iterator = list.iterator();
        while (iterator.hasNext()) {
            bh.consume(iterator.next());
        }
    }

    @Benchmark
    public void listIterator(Blackhole bh) {
        Iterator<Object> iterator = list.listIterator();
        while (iterator.hasNext()) {
            bh.consume(iterator.next());
        }
    }
}
