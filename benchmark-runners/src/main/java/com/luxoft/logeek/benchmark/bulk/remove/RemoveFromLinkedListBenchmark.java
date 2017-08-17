package com.luxoft.logeek.benchmark.bulk.remove;

import org.openjdk.jmh.annotations.*;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.LongStream;

import static java.util.stream.Collectors.toCollection;

@State(Scope.Thread)
@Warmup(batchSize = 1000, iterations = 50)
@Measurement(batchSize = 1000, iterations = 50)
@BenchmarkMode(Mode.SingleShotTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
public class RemoveFromLinkedListBenchmark {

	@Param({"100", "1000", "10000"})
	private int itemsCount; 			//items count

	@Param({"5", "10", "25", "50"})
	private int percentOfRemovedItems;  //count of items removed from list

	private List<Long> linkedList;
	private int from;
	private int to;

	@Setup(Level.Iteration)
	public void initIteration() {
		from = itemsCount / 2; //remove from the second half of the list
		to = from + (itemsCount / 100 * percentOfRemovedItems);
	}

	@Setup(Level.Invocation)
	public void initInvocation() {
		linkedList = LongStream.range(0, itemsCount).boxed().collect(toCollection(LinkedList::new));
	}

	@Benchmark
	public List<Long> measureRemoveFromLinkedListOneByOne_reverseOrder() {
		for (int i = to - 1; i >= from; i--) {
			linkedList.remove(i);
		}
		return linkedList;
	}

	@Benchmark
	public List<Long> measureRemoveFromLinkedListOneByOne_directOrder() {
		for (int i = from; i < to; i++) {
			linkedList.remove(from);
		}
		return linkedList;
	}

	@Benchmark
	public List<Long> measureRemoveFromLinkedListUsingSubList() {
		linkedList.subList(from, to).clear();
		return linkedList;
	}

}