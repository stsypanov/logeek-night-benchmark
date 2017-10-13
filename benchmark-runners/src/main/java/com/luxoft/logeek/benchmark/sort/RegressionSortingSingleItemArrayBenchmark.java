package com.luxoft.logeek.benchmark.sort;

import com.luxoft.logeek.benchmark.BaseBenchmark;
import com.luxoft.logeek.sort.ComparableTimSort;
import org.openjdk.jmh.annotations.*;

import java.util.concurrent.TimeUnit;

/**
 * We need this benchmark to check whether performance is not degradated for 2 cases
 * 1) array.length=2
 * 1) array.length>2
 */
@Fork(5)
@State(Scope.Benchmark)
@Warmup(iterations = 10)
@Measurement(iterations = 100)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
public class RegressionSortingSingleItemArrayBenchmark extends BaseBenchmark {

    private Long[] singletonArray;

    @Setup
    public void setup() {
        super.init();
    }

    @Setup(Level.Invocation)
    public void createNewArray() {
        singletonArray = new Long[]{random.nextLong()};
    }

    @Benchmark
    public Long[] measureEnhancedSortWithoutComparator() {
        ComparableTimSort.sort(singletonArray, 0, singletonArray.length, null, 0, 0);
        return singletonArray;
    }

    @Benchmark
    public Long[] _measureEnhancedSortWithoutComparator() {
        ComparableTimSort._sort(singletonArray, 0, singletonArray.length, null, 0, 0);
        return singletonArray;
    }

    @Benchmark
    public Long[] measureConventionalSortWithoutComparator() {
        ComparableTimSort.sortOriginal(singletonArray, 0, singletonArray.length, null, 0, 0);
        return singletonArray;
    }

}
