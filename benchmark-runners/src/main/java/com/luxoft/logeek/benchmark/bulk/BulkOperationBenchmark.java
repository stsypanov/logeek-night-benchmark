package com.luxoft.logeek.benchmark.bulk;

import com.luxoft.logeek.benchmark.BenchmarkBase;
import org.openjdk.jmh.annotations.*;

import java.util.*;
import java.util.concurrent.TimeUnit;

import static java.util.stream.Collectors.toList;

@Warmup(batchSize = 100, iterations = 100)
@Measurement(batchSize = 100, iterations = 100)
@BenchmarkMode(Mode.SingleShotTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
public class BulkOperationBenchmark extends BenchmarkBase {
	
	@Param({"10", "100", "1000", "10000"})
	private int itemsCount;
	
	private List<Long> initialItems;
	private Long[] initialItemsArray;

	@Setup
	public void init() {
		super.init();
	}

	@Setup(Level.Iteration)
	public void initIteration() {
		initialItems = random.longs(itemsCount).boxed().collect(toList());
		initialItemsArray = initialItems.toArray(new Long[initialItems.size()]);
	}

	@Benchmark
	public List<Long> measureAddOneByOne_ArrayList() {
		List<Long> newList = new ArrayList<>();
		for (Long item : initialItems) {
			newList.add(item);
		}
		return newList;
	}

	@Benchmark
	public List<Long> measureAddAll_ArrayList() {
		List<Long> newList = new ArrayList<>();
		newList.addAll(initialItems);
		return newList;
	}

	@Benchmark
	public List<Long> measureAddAllViaConstructorArg_ArrayList() {
		List<Long> newList = new ArrayList<>(initialItems);
		return newList;
	}

	@Benchmark
	public Set<Long> measureAddOneByOne_HashSet() {
		Set<Long> newSet = new HashSet<>();
		for (Long item : initialItems) {
			newSet.add(item);
		}
		return newSet;
	}

	@Benchmark
	public Set<Long> measureAddAll_HashSet() {
		Set<Long> newSet = new HashSet<>();
		newSet.addAll(initialItems);
		return newSet;
	}

	@Benchmark
	public Set<Long> measureAddAllViaConstructorArg_HashSet() {
		Set<Long> newSet = new HashSet<>(initialItems);
		return newSet;
	}

	@Benchmark
	public Set<Long> measureAddAllVarArgViaAsList_HashSet() {
		Set<Long> set = new HashSet<>(Arrays.asList(initialItemsArray));
		return set;
	}

	@Benchmark
	public Set<Long> measureAddAllVarArgViaCollectionsAddAll_HashSet() {
		Set<Long> set = new HashSet<>(initialItemsArray.length);
		Collections.addAll(set, initialItemsArray);
		return set;
	}

	@Benchmark
	public List<Long> measureAddAllVarArgViaAsList_ArrayList() {
		List<Long> set = new ArrayList<>(Arrays.asList(initialItemsArray));
		return set;
	}

	@Benchmark
	public List<Long> measureAddAllVarArgViaCollectionsAddAll_ArrayList() {
		List<Long> set = new ArrayList<>(initialItemsArray.length);
		Collections.addAll(set, initialItemsArray);
		return set;
	}
}
