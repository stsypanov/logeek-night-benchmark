package com.luxoft.logeek.benchmark.iterator;

import com.intellij.util.containers.SmartList;
import com.luxoft.logeek.utils.OriginalSmartList;
import org.openjdk.jmh.annotations.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * При использовании внутри циклов выражения
 * bh.consume(Object) растёт погрешность вычисления.
 * Это же будет наблюдаться при использовании
 * bh.consume(Integer.intValue())
 */
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Fork(jvmArgsAppend = {"-XX:+UseParallelGC", "-Xms4g", "-Xmx4g"})
public class ArrayVsSmartListIterator {

    @Benchmark
    public int iterateOverArrayList(Lists lists) {
        int i = 0;
        for (Integer integer : lists.arrayList) {
            i += integer;
        }
        return i ;
    }

    @Benchmark
    public int iterateOverEnhancedSmartList(Lists lists) {
        int i = 0;
        for (Integer integer : lists.smartList) {
            i += integer;
        }
        return i ;
    }

    @Benchmark
    public int iterateOverOriginalSmartList(Lists lists) {
        int i = 0;
        for (Integer integer : lists.originalSmartList) {
            i += integer;
        }
        return i ;
    }

    @State(Scope.Benchmark)
    public static class Lists {
        Integer integer = 1;
        ArrayList<Integer> arrayList;
        SmartList<Integer> smartList;
        OriginalSmartList<Integer> originalSmartList;

        @Param({"1", "5", "10", "100", "1000"})
        int size;

        @Setup
        public void setup() {
            List<Integer> ints = IntStream
                    .range(0, size)
                    .boxed()
                    .map(i -> integer)
                    .collect(Collectors.toList());
            arrayList = new ArrayList<>(ints);
            smartList = new SmartList<>(ints);
            originalSmartList = new OriginalSmartList<>(ints);
        }
    }
}

