package com.luxoft.logeek.benchmark.array;

import org.openjdk.jmh.annotations.*;

import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
public class ManualArrayCopyVsSystemBenchmark {

    @Benchmark
    @SuppressWarnings("ManualArrayCopy")
    public Object manualArrayCopy(ArrayHolder holder) {
        Integer[] array = holder.array;

        Integer[] copy = new Integer[array.length];
        for (int i = 0; i < array.length; i++) {
            copy[i] = array[i];
        }

        return copy;
    }

    @Benchmark
    public Object system(ArrayHolder holder) {
        Integer[] array = holder.array;

        Integer[] copy = new Integer[array.length];
        System.arraycopy(array, 0, copy, 0, array.length);

        return copy;
    }

    @State(Scope.Thread)
    public static class ArrayHolder {
        Integer[] array;

        @Param({"0", "10", "100", "1000"})
        int length;

        @Setup
        public void setup() {
            array = IntStream
                    .range(0, length)
                    .boxed()
                    .toArray(Integer[]::new);
        }
    }
}
