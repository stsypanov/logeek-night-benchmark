package com.luxoft.logeek.benchmark.misc;

import org.openjdk.jmh.annotations.*;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

import static java.util.Collections.*;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
public class MiscBenchmark {

    @Benchmark
    public String measureBooleanGetter(Data data) {
        return useBoolean(data.getBooleanValue());
    }

    @Benchmark
    public String measureNullabilityCheck(Data data) {
        return useBoolean(data.nullable == null);
    }

    @Benchmark
    public String measureContains(Data data) {
        return useBoolean(data.set.contains(data.value1));
    }

    @Benchmark
    public String measureDoesNotContain(Data data) {
        return useBoolean(data.set.contains(data.value2));
    }

//    @CompilerControl(CompilerControl.Mode.DONT_INLINE)
    private String useBoolean(boolean b) {
        if (b) {
            return "azaza";
        }
        return "ololo";
    }

    @State(Scope.Thread)
    public static class Data {
        private ThreadLocalRandom random;
        boolean booleanValue;
        Object nullable;

        Integer value1;
        Integer value2;
        Set<Integer> set;

        @Setup
        public void setup() {
            value1 = 42;
            value2 = 43;
            set = new HashSet<>(singleton(value1));
            random = ThreadLocalRandom.current();
            setupIteration();
        }


        @Setup(Level.Iteration)
        public void setupIteration() {
            booleanValue = random.nextInt() % 2 == 0;
            nullable = random.nextInt() % 2 == 0 ? null : new Object();
        }

        boolean getBooleanValue() {
            return booleanValue;
        }
    }
}
