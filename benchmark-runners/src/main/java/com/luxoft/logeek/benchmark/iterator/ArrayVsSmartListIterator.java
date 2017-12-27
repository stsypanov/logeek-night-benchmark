package com.luxoft.logeek.benchmark.iterator;

import com.luxoft.logeek.collections.SmartList;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Fork(jvmArgsAppend = {"-XX:+UseParallelGC", "-Xms4g", "-Xmx4g"})
public class ArrayVsSmartListIterator {

    @Benchmark
    public void iteratorFromArrayList(Lists lists, Blackhole bh) {
        Iterator<Integer> iterator = lists.arrayList.iterator();
        while (iterator.hasNext()) {
            bh.consume(iterator.next());
        }
    }

    @Benchmark
    public void iteratorFromSmartList(Lists lists, Blackhole bh) {
        Iterator<Integer> iterator = lists.smartList.iterator();
        while (iterator.hasNext()) {
            bh.consume(iterator.next());
        }
    }

    @State(Scope.Thread)
    public static class Lists {
        ArrayList<Integer> arrayList;
        SmartList<Integer> smartList;

        @Param({"5", "10", "100", "1000", "10000"})
        int size;

        @Setup
        public void setup() {
            arrayList = IntStream.range(0, size).boxed().collect(Collectors.toCollection(ArrayList::new));
            smartList = IntStream.range(0, size).boxed().collect(Collectors.toCollection(SmartList::new));

        }
    }
}

