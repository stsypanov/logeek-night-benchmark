package com.luxoft.logeek.benchmark.contains;

import com.intellij.util.containers.SmartList;
import org.openjdk.jmh.annotations.*;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import static java.util.Collections.singleton;

/**
 * Measures collection enhancement for empty SmartList of {@link Dto}
 */
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
public class EnhancedEmptySmartListBenchmark {

    @Benchmark
    public boolean measureEnhancedContainsAll(Data data) {
        return data.emptySmartList.containsAll(data.dtos);
    }

    @Benchmark
    public boolean measureConventionalContainsAll(Data data) {
        return data.emptySmartList._containsAll(data.dtos);
    }

    @State(Scope.Thread)
    public static class Data {
        private ArrayList<Dto> dtos;
        private SmartList<Dto> emptySmartList;

        @Setup
        public void setup() {
            emptySmartList = new SmartList<>();
            dtos = new ArrayList<>(singleton(new Dto(1)));
        }
    }
}
