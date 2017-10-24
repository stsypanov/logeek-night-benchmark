package com.luxoft.logeek.benchmark.sort;

import com.luxoft.logeek.sort.ComparableTimSort;
import org.openjdk.jmh.annotations.*;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

@Fork(jvmArgsAppend = {"-XX:+UseParallelGC", "-Xms2g", "-Xmx2g"})
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
public class SortingBenchmark {

    @Benchmark
    public Long[] measureEnhancedSort(DataCopy data) {
        Long[] longs = data.copy;
        ComparableTimSort.sort(longs, 0, longs.length, null, 0, 0);
        return longs;
    }

    @Benchmark
    public Long[] _measureEnhancedSort(DataCopy data) {
        Long[] longs = data.copy;
        ComparableTimSort._sort(longs, 0, longs.length, null, 0, 0);
        return longs;
    }

    @Benchmark
    public Long[] measureConventionalSort(DataCopy data) {
        Long[] longs = data.copy;
        ComparableTimSort.sortOriginal(longs, 0, longs.length, null, 0, 0);
        return longs;
    }

    @State(Scope.Benchmark)
    public static class Data {

        @Param({"1", "2", "3"})
        int count;

        Long[] arr;

        @Setup
        public void setup() {
            arr = new Long[count];
            for (int i = 1; i <= count; i++) {
                arr[i-1] = i * -1L;
            }
        }
    }

    @State(Scope.Thread)
    public static class DataCopy {
        Long[] copy;

        @Setup(Level.Invocation)
        public void setup(Data data) {
            copy = Arrays.copyOf(data.arr, data.arr.length);
        }
    }
}

