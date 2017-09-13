package com.luxoft.logeek.benchmark.sort;

import com.luxoft.logeek.benchmark.BenchmarkBase;
import com.luxoft.logeek.sort.ArraysStub;
import org.openjdk.jmh.annotations.*;

import java.util.Arrays;

public class SortingBenchmark extends BenchmarkBase {

    private Long[] longs;

    @Setup
    public void setup() {
        super.init();
    }

    @Setup(Level.Invocation)
    public void createNewArray() {
        longs = new Long[]{random.nextLong(), random.nextLong()};
    }

    @TearDown(Level.Invocation)
    public void afterEachInvocation() {
        Long long0 = longs[0];
        Long long1 = longs[1];
        if (long0 > long1) {
            throw new RuntimeException("Array must be sorted but was: " + long1 + ", " + long1);
        }
    }

    @Benchmark
    public Long[] measureEnhancedSortWithoutComparator() {
        ArraysStub.sort(longs);
        return longs;
    }

    @Benchmark
    public Long[] measureConventionalSortWithoutComparator() {
        Arrays.sort(longs);
        return longs;
    }
}

