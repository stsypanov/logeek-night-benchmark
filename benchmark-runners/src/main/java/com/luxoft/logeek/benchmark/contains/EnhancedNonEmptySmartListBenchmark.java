package com.luxoft.logeek.benchmark.contains;

import com.luxoft.logeek.collections.SmartList;
import org.openjdk.jmh.annotations.*;

import java.util.ArrayList;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import static java.util.Collections.singleton;

/**
 * Measures collection enhancement for non-empty SmartList of {@link Dto}
 */
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
public class EnhancedNonEmptySmartListBenchmark {

    @Benchmark
    public boolean measureEnhancedContainsAll(Data data) {
        return data.smartList.containsAll(data.dtos);
    }

    @Benchmark
    public boolean measureConventionalContainsAll(Data data) {
        return data.smartList._containsAll(data.dtos);
    }

    @State(Scope.Thread)
    public static class Data {
        private ArrayList<Dto> dtos;
        private SmartList<Dto> smartList;

        @Setup
        public void setup() {
            Set<Dto> one = singleton(new Dto(1));
            smartList = new SmartList<>(one);
            dtos = new ArrayList<>(one);
        }
    }
}
