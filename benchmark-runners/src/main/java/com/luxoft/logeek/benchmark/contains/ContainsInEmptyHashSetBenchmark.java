package com.luxoft.logeek.benchmark.contains;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.util.Collection;
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

    @State(Scope.Thread)
    public static class Data {
        String str;
        Collection collection;

        Set<String> jdkHashSet;
        Set<String> customHashSet;

        @Setup
        public void setup() {
            str = "1";

            collection = singletonList("1");

            jdkHashSet = new java.util.HashSet<>();
            customHashSet = new com.luxoft.logeek.benchmark.contains.HashSet<>();
        }

    }
}
//java -jar benchmarks.jar ContainsInEmptyHashSetBenchmark -f 10 -wi 100 -i 100 -prof gc

