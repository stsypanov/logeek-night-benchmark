package com.luxoft.logeek.benchmark.sort;

import com.luxoft.logeek.benchmark.BaseBenchmark;
import com.luxoft.logeek.sort.ArraysStub;
import org.openjdk.jmh.annotations.*;

import java.util.Arrays;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;

/**
 * We need this benchmark to check whether performance is not degradated for 2 cases
 * 1) array.length=2
 * 1) array.length>2
 */
@State(Scope.Thread)
@BenchmarkMode(Mode.Throughput)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
public class RegressionSortingBenchmark extends BaseBenchmark {

	@Param({"10", "100", "1000"})
	private int arrayLength;

	private Long[] sortedArray;
	private Long[] notSortedInitialArray;

	@Setup
	public void setup() {
		super.init();
	}

	@Setup(Level.Invocation)
	public void createNewArray() {
		sortedArray = random.longs(Long.MIN_VALUE, Long.MAX_VALUE).limit(100).boxed().toArray(value -> new Long[100]);
		notSortedInitialArray = sortedArray.clone();
	}

	@TearDown(Level.Invocation)
	public void afterEachInvocation() {
		Iterator<Long> iterator = Arrays.stream(sortedArray).iterator();
		while (iterator.hasNext()) {
			Long current = iterator.next();
			if (iterator.hasNext()) {
				Long next = iterator.next();
				if (current.compareTo(next) > 0) {
					//log arrays if something goes wrong
					System.out.println("Initial array: " + Arrays.toString(notSortedInitialArray));
					System.out.println("Sorted array: " + Arrays.toString(sortedArray));
					throw new RuntimeException("Array must be sorted but current is greater than next: " + current + ", " + next);
				}
			}
		}
	}

	@Benchmark
	public Long[] measureEnhancedSort_arrayLengthGreaterThanTwo() {
		ArraysStub.sort(sortedArray);
		return sortedArray;
	}

	@Benchmark
	public Long[] measureConventionalSort_arrayLengthGreaterThanTwo() {
		Arrays.sort(sortedArray);
		return sortedArray;
	}

}
