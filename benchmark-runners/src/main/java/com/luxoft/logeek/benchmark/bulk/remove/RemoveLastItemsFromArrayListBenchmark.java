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
public class RemoveLastItemsFromArrayListBenchmark {

	@Param({"100", "1000", "10000"})
	private int itemsCount; 			//items count

	@Param({"5", "10", "25", "50"})
	private int percentOfRemovedItems;  //count of items removed from list

	private List<Long> arrayList;
	private int from;

	@Setup(Level.Iteration)
	public void initIteration() {
		from = itemsCount - (itemsCount / 100 * percentOfRemovedItems);
	}

	@Setup(Level.Invocation)
	public void initInvocation() {
		arrayList = LongStream.range(0, itemsCount).boxed().collect(toCollection(ArrayList::new));
	}

	/**
     * Always remove the last element
     */
	@Benchmark
	public List<Long> measureRemoveFromArrayListOneByOne() {
		int removedItemsCount = arrayList.size() - 1;
		for (int i = from; i <= removedItemsCount; i++) {
			arrayList.remove(arrayList.size() - 1);
		}
		return arrayList;
	}

	@Benchmark
	public List<Long> measureRemoveFromArrayListUsingSubList() {
		arrayList.subList(from, arrayList.size()).clear();
		return arrayList;
	}

}