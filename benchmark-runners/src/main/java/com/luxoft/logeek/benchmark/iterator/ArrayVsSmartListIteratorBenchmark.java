package com.luxoft.logeek.benchmark.iterator;

import com.intellij.util.containers.SmartList;
import com.luxoft.logeek.utils.OriginalSmartList;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Fork(jvmArgsAppend = {"-XX:+UseParallelGC", "-Xms1g", "-Xmx1g"})
public class ArrayVsSmartListIteratorBenchmark {

    @Benchmark
    public void iterateOverArrayList(Lists lists, Blackhole bh) {
        for (Integer integer : lists.arrayList) {
            bh.consume(integer.intValue());
        }
    }

    @Benchmark
    public void iterateOverEnhancedSmartList(Lists lists, Blackhole bh) {
        for (Integer integer : lists.smartList) {
            bh.consume(integer.intValue());
        }
    }

    @Benchmark
    public void iterateOverOriginalSmartList(Lists lists, Blackhole bh) {
        for (Integer integer : lists.originalSmartList) {
            bh.consume(integer.intValue());
        }
    }

    @State(Scope.Benchmark)
    public static class Lists {
        ArrayList<Integer> arrayList;
        SmartList<Integer> smartList;
        OriginalSmartList<Integer> originalSmartList;

        @Param({"10", "100", "1000"})
        int size;

        @Setup
        public void setup() {
            List<Integer> ints = IntStream
                    .range(0, size)
                    .boxed()
                    .collect(toList());
            arrayList = new ArrayList<>(ints);
            smartList = new SmartList<>(ints);
            originalSmartList = new OriginalSmartList<>(ints);
        }
    }
}

