package com.luxoft.logeek.benchmark.contains;

import com.luxoft.logeek.utils.SmartList;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import static java.util.Collections.singletonList;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
public class ContainsInEmptyHashSetBenchmark {

    @Benchmark
    public boolean measureContainsInJdkHashSet(Data data) {
        return data.jdkHashSet.contains(data.str);
    }

    @Benchmark
    public boolean measureContainsInCustomHashSet(Data data) {
        return data.customHashSet.contains(data.str);
    }

    @Benchmark
    public boolean measureContainsAllInJdkHashSet(Data data) {
        return data.jdkHashSet.containsAll(data.collection);
    }

    @Benchmark
    public boolean measureContainsAllInCustomHashSet(Data data) {
        return data.customHashSet.containsAll(data.collection);
    }

    @Benchmark
    public void measureClearJdkHashSet(Data data, Blackhole bh) {
        data.jdkHashSet.clear();
        bh.consume(data.jdkHashSet);
    }

    @Benchmark
    public void measureClearCustomHashSet(Data data, Blackhole bh) {
        data.customHashSet.clear();
        bh.consume(data.customHashSet);
    }

    @Benchmark
    public boolean measureSmartListContainsAll(Data data) {
        return a(data);
    }

    @Benchmark
    public boolean _measureSmartListContainsAll(Data data) {
        return b(data);
    }

    @CompilerControl(CompilerControl.Mode.DONT_INLINE)
    private boolean a(Data data) {
        return data.smartList.containsAll(data.integers);
    }

    @CompilerControl(CompilerControl.Mode.DONT_INLINE)
    private boolean b(Data data) {
        return data.smartList._containsAll(data.integers);
    }

    @Benchmark
    public Integer measureGetFromJdkHashMap(Data data) {
        return data.jdkMap.get(data.integer);
    }

    @Benchmark
    public Integer measureGetFromIdeaHashMap(Data data) {
        return data.ideaMap.get(data.integer);
    }

    @Benchmark
    public boolean measureContainsKeyJdkHashMap(Data data) {
        return data.jdkMap.containsKey(data.integer);
    }

    @Benchmark
    public boolean measureContainsKeyIdeaHashMap(Data data) {
        return data.ideaMap.containsKey(data.integer);
    }

    @Benchmark
    public boolean measureContainsValueJdkHashMap(Data data) {
        return data.jdkMap.containsValue(data.integer);
    }

    @Benchmark
    public boolean measureContainsValueIdeaHashMap(Data data) {
        return data.ideaMap.containsValue(data.integer);
    }

    @Benchmark
    public Integer measureRemoveFromJdkHashMap(Data data) {
        return data.jdkMap.remove(data.integer);
    }
    @Benchmark
    public Integer measureRemoveFromIdeaHashMap(Data data) {
        return data.ideaMap.remove(data.integer);
    }

    @State(Scope.Thread)
    public static class Data {
        String str;
        Collection collection;

        Set<String> jdkHashSet;
        Set<String> customHashSet;
        Integer integer;
        SmartList<Integer> smartList;
        Collection<Integer> integers;
        java.util.HashMap<Integer, Integer> jdkMap;
        HashMap<Integer, Integer> ideaMap;

        @Setup
        public void setup() {
            str = "1";

            collection = singletonList("1");

            jdkHashSet = new java.util.HashSet<>();
            customHashSet = new com.luxoft.logeek.benchmark.contains.HashSet<>();

            integer = 1;

            smartList = new SmartList<>();
            integers = new ArrayList<>(Collections.singleton(integer));

            jdkMap = new java.util.HashMap<>();
            ideaMap = new HashMap<>();
        }

    }
}
//java -jar benchmarks.jar ContainsInEmptyHashSetBenchmark -f 10 -wi 100 -i 100 -prof gc

