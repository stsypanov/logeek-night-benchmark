package com.luxoft.logeek.benchmark.sort;

import com.luxoft.logeek.benchmark.BaseBenchmark;
import com.luxoft.logeek.sort.ArraysStub;
import org.openjdk.jmh.annotations.*;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

/**
 * We need this benchmark to check whether performance is not degradated for 2 cases
 * 1) array.length=2
 * 1) array.length>2
 */
@State(Scope.Thread)
@BenchmarkMode(Mode.Throughput)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
public class RegressionSortingSingleItemArrayBenchmark extends BaseBenchmark {

    private Long[] singleItemArray;

    @Setup
    public void setup() {
        super.init();
    }

    @Setup(Level.Invocation)
    public void createNewArray() {
		singleItemArray = new Long[]{random.nextLong()};
    }

	@Benchmark
	public Long[] measureEnhancedSort_singleItemArray() {
		ArraysStub.sort(singleItemArray);
		return singleItemArray;
	}

	@Benchmark
	public Long[] measureConventionalSort_singleItemArray() {
		Arrays.sort(singleItemArray);
		return singleItemArray;
	}

}
