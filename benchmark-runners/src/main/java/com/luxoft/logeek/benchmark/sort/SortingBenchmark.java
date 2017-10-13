package com.luxoft.logeek.benchmark.sort;

import com.luxoft.logeek.benchmark.BaseBenchmark;
import com.luxoft.logeek.sort.ComparableTimSort;
import org.openjdk.jmh.annotations.*;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

@Fork(5)
@State(Scope.Benchmark)
@Warmup(iterations = 10)
@Measurement(iterations = 100)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
public class SortingBenchmark extends BaseBenchmark {

    private Long[] longs;

    @Setup
    public void setup() {
        super.init();
    }

    @Setup(Level.Invocation)
    public void createNewArray() {
        long positive = Math.abs(random.nextLong());
        long negative = random.nextLong();
        negative = negative < 0 ? negative : negative * -1;
        longs = new Long[]{positive, negative};
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
        ComparableTimSort.sort(longs, 0, longs.length, null, 0, 0);
        return longs;
    }

    @Benchmark
    public Long[] _measureEnhancedSortWithoutComparator() {
        ComparableTimSort._sort(longs, 0, longs.length, null, 0, 0);
        return longs;
    }

    @Benchmark
    public Long[] measureConventionalSortWithoutComparator() {
        ComparableTimSort.sortOriginal(longs, 0, longs.length, null, 0, 0);
        return longs;
    }
}

