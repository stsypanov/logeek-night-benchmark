package com.luxoft.logeek.benchmark.bulk;

import com.luxoft.logeek.benchmark.BenchmarkBase;
import org.openjdk.jmh.annotations.*;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
public class ArraysBenchmark {

    @Benchmark
    public int measureBinarySearch(Data data) {
        return Arrays.binarySearch(data.array, data.item);
    }

    @Benchmark
    public int measureIndexOf(Data data) {
        return Arrays.asList(data.array).indexOf(data.item);
    }

    @State(Scope.Thread)
    public static class Data {
        @Param({"5", "10", "50", "100", "1000", "10000"})
        private int count;

        @Param({"1", "2", "3", "4", "5"})
        private int position;

        private ThreadLocalRandom random;
        private Long[] array;
        private Long item;

        @Setup
        public void init() {
            random = ThreadLocalRandom.current();
            array = random.longs(count).boxed().toArray(Long[]::new);

            item = array[(count / 5 - 1) * position];
        }
    }
}
