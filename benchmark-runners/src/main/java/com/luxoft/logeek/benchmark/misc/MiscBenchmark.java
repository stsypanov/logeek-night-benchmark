package com.luxoft.logeek.benchmark.misc;

import org.openjdk.jmh.annotations.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

import static java.util.Collections.*;
import static java.util.stream.Collectors.*;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
public class MiscBenchmark {

    @Benchmark
    public int measureBooleanGetter(Data data) {
        return returnIntForBoolean(data.getBooleanValue());
    }

    @Benchmark
    public int measureNullabilityCheck(Data data) {
        return returnIntForBoolean(data.nullable == null);
    }

    @Benchmark
    public int measureContainsInHashSet(Data data) {
        return returnIntForBoolean(data.hashSet.contains(data.value));
    }

    @Benchmark
    public int measureContainsInArrayList(Data data) {
        return returnIntForBoolean(data.arrayList.contains(data.value));
    }

    //@CompilerControl(CompilerControl.Mode.DONT_INLINE)
    private int returnIntForBoolean(boolean b) {
        if (b) {
            return 1;
        }
        return 2;
    }

    @State(Scope.Thread)
    public static class Data {
        private ThreadLocalRandom random;

        boolean booleanValue;
        Object nullable;
        Integer value;

        Set<Integer> hashSet;
        List<Integer> arrayList;

        @Setup
        public void setup() {
            value = 42;
            hashSet = new HashSet<>(singleton(value));
            arrayList = IntStream.range(0, 9).boxed().collect(toCollection(ArrayList::new));
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
