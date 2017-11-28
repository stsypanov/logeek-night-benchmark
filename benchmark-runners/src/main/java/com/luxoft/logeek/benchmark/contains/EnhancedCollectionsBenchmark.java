package com.luxoft.logeek.benchmark.contains;

import com.luxoft.logeek.collections.SmartList;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.util.*;
import java.util.concurrent.TimeUnit;

import static java.util.Collections.*;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Fork(jvmArgsAppend = {"-XX:+UseParallelGC", "-Xms4g", "-Xmx4g"})
public class EnhancedCollectionsBenchmark {

    @Benchmark
    public boolean measureContainsInEmptyJdkHashSet(Data data) {
        return data.emptyJdkHashSet.contains(data.integer);
    }

    @Benchmark
    public boolean measureContainsInEmptyIdeaHashSet(Data data) {
        return data.emptyIdeaHashSet.contains(data.integer);
    }

    @Benchmark
    public boolean measureContainsAllInEmptyJdkHashSet(Data data) {
        return data.emptyJdkHashSet.containsAll(data.integers);
    }

    @Benchmark
    public boolean measureContainsAllInEmptyIdeaHashSet(Data data) {
        return data.emptyIdeaHashSet.containsAll(data.integers);
    }

    @Benchmark
    public void measureClearEmptyJdkHashSet(Data data, Blackhole bh) {
        data.emptyJdkHashSet.clear();
        bh.consume(data.emptyJdkHashSet);
    }

    @Benchmark
    public void measureClearEmptyIdeaHashSet(Data data, Blackhole bh) {
        data.emptyIdeaHashSet.clear();
        bh.consume(data.emptyIdeaHashSet);
    }

    @Benchmark
    public boolean measureSmartListEnhancedContainsAll(Data data) {
        return data.emptySmartList.containsAll(data.integers);
    }

    @Benchmark
    public boolean measureSmartListContainsAll(Data data) {
        return data.emptySmartList._containsAll(data.integers);
    }

    @Benchmark
    public Integer measureGetFromEmptyJdkHashMap(Data data) {
        return data.emptyJdkMap.get(data.integer);
    }

    @Benchmark
    public Integer measureGetFromEmptyIdeaHashMap(Data data) {
        return data.emptyIdeaMap.get(data.integer);
    }

    @Benchmark
    public boolean measureContainsKeyEmptyJdkHashMap(Data data) {
        return data.emptyJdkMap.containsKey(data.integer);
    }

    @Benchmark
    public boolean measureContainsKeyEmptyIdeaHashMap(Data data) {
        return data.emptyIdeaMap.containsKey(data.integer);
    }

    @Benchmark
    public boolean measureContainsValueEmptyJdkHashMap(Data data) {
        return data.emptyJdkMap.containsValue(data.integer);
    }

    @Benchmark
    public boolean measureContainsValueEmptyIdeaHashMap(Data data) {
        return data.emptyIdeaMap.containsValue(data.integer);
    }

    @Benchmark
    public Integer measureRemoveFromEmptyJdkHashMap(Data data) {
        return data.emptyJdkMap.remove(data.integer);
    }
    @Benchmark
    public Integer measureRemoveFromEmptyIdeaHashMap(Data data) {
        return data.emptyIdeaMap.remove(data.integer);
    }

    @State(Scope.Thread)
    public static class Data {
        @Param({"0", "1"})
        Integer integer;

        Set<Integer> emptyJdkHashSet;
        Set<Integer> emptyIdeaHashSet;
        SmartList<Integer> emptySmartList;
        Collection<Integer> integers;
        Map<Integer, Integer> emptyJdkMap;
        Map<Integer, Integer> emptyIdeaMap;

        @Setup
        public void setup() {
            integer = integer == 1
                    ? integer
                    : null;

            emptyJdkHashSet = new java.util.HashSet<>();
            emptyIdeaHashSet = new com.luxoft.logeek.collections.HashSet<>();

            emptySmartList = new com.luxoft.logeek.collections.SmartList<>();
            integers = new java.util.ArrayList<>(singleton(integer));

            emptyJdkMap = new java.util.HashMap<>();
            emptyIdeaMap = new com.luxoft.logeek.collections.HashMap<>();
        }

    }
}

