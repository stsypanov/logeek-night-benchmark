package com.luxoft.logeek.benchmark.iterator;

import com.intellij.util.containers.SmartList;
import com.luxoft.logeek.utils.OriginalSmartList;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Fork(jvmArgsAppend = {"-XX:+UseParallelGC", "-Xms4g", "-Xmx4g"})
public class ArrayVsSmartListIterator {

    @Benchmark
    public void iterateOverArrayList(Lists lists, Blackhole bh) {
        for (Integer integer : lists.arrayList) {
            bh.consume(integer);
        }
    }

    @Benchmark
    public void iterateOverEnhancedSmartList(Lists lists, Blackhole bh) {
        for (Integer integer : lists.smartList) {
            bh.consume(integer);
        }
    }

    @Benchmark
    public void iterateOverOriginalSmartList(Lists lists, Blackhole bh) {
        for (Integer integer : lists.originalSmartList) {
            bh.consume(integer);
        }
    }

    @State(Scope.Benchmark)
    public static class Lists {
        ArrayList<Integer> arrayList;
        SmartList<Integer> smartList;
        OriginalSmartList<Integer> originalSmartList;

        @Param({"5", "10", "100", "1000", "10000"})
        int size;

        @Setup
        public void setup() {
            List<Integer> ints = IntStream.range(0, size).boxed().collect(Collectors.toList());
            arrayList = new ArrayList<>(ints);
            smartList = new SmartList<>(ints);
            originalSmartList = new OriginalSmartList<>(ints);
        }
    }
}

