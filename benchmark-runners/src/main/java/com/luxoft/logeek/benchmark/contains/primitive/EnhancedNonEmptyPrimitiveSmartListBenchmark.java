package com.luxoft.logeek.benchmark.contains.primitive;

import com.luxoft.logeek.collections.SmartList;
import org.openjdk.jmh.annotations.*;

import java.util.ArrayList;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import static java.util.Collections.singleton;

/**
 * Measures collection enhancement for non-empty SmartList of {@link Integer}
 */
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
public class EnhancedNonEmptyPrimitiveSmartListBenchmark {

    @Benchmark
    public boolean measureEnhancedContainsAll(Data data) {
        return data.smartList.containsAll(data.integers);
    }

    @Benchmark
    public boolean measureConventionalContainsAll(Data data) {
        return data.smartList._containsAll(data.integers);
    }

    @State(Scope.Thread)
    public static class Data {
        private ArrayList<Integer> integers;
        private SmartList<Integer> smartList;

        @Setup
        public void setup() {
            Set<Integer> one = singleton(1);
            smartList = new SmartList<>(one);
            integers = new ArrayList<>(one);
        }
    }

}
