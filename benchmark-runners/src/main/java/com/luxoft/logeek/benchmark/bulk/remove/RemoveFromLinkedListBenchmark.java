package com.luxoft.logeek.benchmark.bulk.remove;

import com.luxoft.logeek.benchmark.BenchmarkBase;
import org.openjdk.jmh.annotations.*;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static java.util.stream.Collectors.toCollection;

@Warmup(batchSize = 1000)
@Measurement(batchSize = 1000)
@BenchmarkMode(Mode.SingleShotTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
public class RemoveFromLinkedListBenchmark extends BenchmarkBase {

	@Param({"100", "1000", "10000"})
	private int itemsCount; 			//items count

	@Param({"5", "10", "25"})
	private int percentOfRemovedItems;  //count of items removed from list

	private List<Long> linkedList;
	private int from;
	private int to;

	@Setup
	public void init() {
		super.init();
	}

	@Setup(Level.Iteration)
	public void initIteration() {
		from = itemsCount / 2; //remove from the second half of the list
		to = from + (itemsCount / 100 * percentOfRemovedItems);
//		System.out.println(String.format("Items count = %d, removing items from = %d to = %d", itemsCount, from, to));
	}

	@Setup(Level.Invocation)
	public void initInvocation() {
		linkedList = random.longs(itemsCount).boxed().collect(toCollection(LinkedList::new));
	}

	@Benchmark
	public List<Long> measureRemoveFromLinkedListOneByOne() {
		for (int i = from; i < to; i++) {
			linkedList.remove(i);
		}
		return linkedList;
	}

	@Benchmark
	public List<Long> measureRemoveFromLinkedListUsingSubList() {
		linkedList.subList(from, to).clear();
		return linkedList;
	}

}