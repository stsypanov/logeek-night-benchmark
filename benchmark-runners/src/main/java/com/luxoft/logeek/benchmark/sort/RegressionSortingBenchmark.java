package com.luxoft.logeek.benchmark.sort;

import com.luxoft.logeek.benchmark.BaseBenchmark;
import com.luxoft.logeek.sort.ComparableTimSort;
import org.openjdk.jmh.annotations.*;

import java.util.Comparator;
import java.util.concurrent.TimeUnit;
import java.util.stream.LongStream;

/**
 * We need this benchmark to check whether performance is not degradated for 2 cases
 * 1) array.length=2
 * 1) array.length>2
 */
@Fork(5)
@State(Scope.Benchmark)
@Warmup(iterations = 10)
@Measurement(iterations = 10)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
public class RegressionSortingBenchmark extends BaseBenchmark {

    @Param({"5", "10", "100"})
    private int arrayLength;

    private Long[] array1;
    private Long[] array2;
    private Long[] array3;

    @Setup
    public void setup() {
        super.init();
    }

    @Setup(Level.Invocation)
    public void createNewArray() {
        Long[] reversedArray = LongStream.range(0, arrayLength)
                .boxed()
                .sorted(Comparator.reverseOrder())
                .toArray(value -> new Long[arrayLength]);
        array1 = reversedArray.clone();
        array2 = reversedArray.clone();
        array3 = reversedArray.clone();
    }

    @Benchmark
    public Long[] measureEnhancedSort() {
        ComparableTimSort.sort(array1, 0, array1.length, null, 0, 0);
        return array1;
    }

    @Benchmark
    public Long[] _measureEnhancedSort() {
        ComparableTimSort._sort(array2, 0, array2.length, null, 0, 0);
        return array2;
    }

    @Benchmark
    public Long[] measureConventionalSort() {
        ComparableTimSort.sort(array3, 0, array3.length, null, 0, 0);
        return array3;
    }

}
