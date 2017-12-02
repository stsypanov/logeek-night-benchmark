package com.luxoft.logeek.benchmark.contains.primitive;

import com.luxoft.logeek.collections.SmartList;
import org.openjdk.jmh.annotations.*;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import static java.util.Collections.*;

/**
 * Measures collection enhancement for empty SmartList of {@link Integer}
 */
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
public class EnhancedEmptyPrimitiveSmartListBenchmark {

    @Benchmark
    public boolean measureEnhancedContainsAll(Data data) {
        return data.emptySmartList.containsAll(data.integers);
    }

    @Benchmark
    public boolean measureConventionalContainsAll(Data data) {
        return data.emptySmartList._containsAll(data.integers);
    }

    @State(Scope.Thread)
    public static class Data {
        private ArrayList<Integer> integers;
        private SmartList<Integer> emptySmartList;

        @Setup
        public void setup() {
            emptySmartList = new SmartList<>();
            integers = new ArrayList<>(singleton(1));
        }
    }

}
