package com.luxoft.logeek.benchmark.iterator;

import com.intellij.util.containers.SmartList;
import org.openjdk.jmh.annotations.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Fork(jvmArgsAppend = {"-XX:+UseParallelGC", "-Xms4g", "-Xmx4g"})
public class IteratorFromListBenchmark {

    @Benchmark
    public Iterator<Integer> iteratorFromEmptyArrayList(Lists lists) {
        return lists.emptyArrayList.iterator();
    }

    @Benchmark
    public Iterator<Integer> iteratorFromEmptySmartList(Lists lists) {
        return lists.emptySmartList.iterator();
    }

    @Benchmark
    public Iterator<Integer> iteratorFromSingletonArrayList(Lists lists) {
        return lists.singletonArrayList.iterator();
    }

    @Benchmark
    public Iterator<Integer> iteratorFromSingletonSmartList(Lists lists) {
        return lists.singletonSmartList.iterator();
    }

    @Benchmark
    public Iterator<Integer> iteratorFromArrayList(Lists lists) {
        return lists.arrayList.iterator();
    }

    @Benchmark
    public Iterator<Integer> iteratorFromSmartList(Lists lists) {
        return lists.smartList.iterator();
    }

    @State(Scope.Thread)
    public static class Lists {
        SmartList<Integer> emptySmartList;
        ArrayList<Integer> emptyArrayList;

        SmartList<Integer> singletonSmartList;
        ArrayList<Integer> singletonArrayList;

        SmartList<Integer> smartList;
        ArrayList<Integer> arrayList;


        @Setup
        public void setup() {
            emptySmartList = new SmartList<>();
            emptyArrayList = new ArrayList<>();

            singletonSmartList = new SmartList<>(1);
            singletonArrayList = new ArrayList<>(singletonList(1));

            smartList = new SmartList<>(1, 2);
            arrayList = new ArrayList<>(asList(1, 2));
        }
    }

}
