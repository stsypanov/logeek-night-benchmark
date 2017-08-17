package com.luxoft.logeek.benchmark.bulk.remove;

import org.openjdk.jmh.annotations.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.LongStream;

import static java.util.stream.Collectors.toCollection;

@State(Scope.Thread)
@Warmup(batchSize = 1000, iterations = 50)
@Measurement(batchSize = 1000, iterations = 50)
@BenchmarkMode(Mode.SingleShotTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
public class RemoveFromArrayListBenchmark {

	@Param({"100", "1000", "10000"})
	private int itemsCount; 			//items count

	@Param({"5", "10", "25", "50"})
	private int percentOfRemovedItems;  //count of items removed from list

	private List<Long> arrayList;
	private int from;
	private int to;

	@Setup(Level.Iteration)
	public void initIteration() {
		from = itemsCount / 2; //remove from the second half of the list
		to = from + (itemsCount / 100 * percentOfRemovedItems);
	}

	@Setup(Level.Invocation)
	public void initInvocation() {
		arrayList = LongStream.range(0, itemsCount).boxed().collect(toCollection(ArrayList::new));
	}

	@Benchmark
	public List<Long> measureRemoveFromArrayListOneByOne_reverseOrder() {
		for (int i = to - 1; i >= from; i--) {
			arrayList.remove(i);
		}
		return arrayList;
	}

	@Benchmark
	public List<Long> measureRemoveFromArrayListOneByOne_directOrder() {
		for (int i = from; i < to; i++) {
			arrayList.remove(from);
		}
		return arrayList;
	}

	@Benchmark
	public List<Long> measureRemoveFromArrayListUsingSubList() {
		arrayList.subList(from, to).clear();
		return arrayList;
	}

}