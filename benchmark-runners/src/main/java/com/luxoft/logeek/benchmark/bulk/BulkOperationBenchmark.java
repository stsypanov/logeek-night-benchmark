package com.luxoft.logeek.benchmark.bulk;

import com.luxoft.logeek.benchmark.BenchmarkBase;
import org.openjdk.jmh.annotations.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import static java.util.stream.Collectors.toList;

@OutputTimeUnit(TimeUnit.MICROSECONDS)
public class BulkOperationBenchmark extends BenchmarkBase {
	
	@Param({"10", "100", "1000", "10000"})
	private int itemsCount;
	
	private List<Long> initialItems;
	
	@Setup
	public void init() {
		super.init();
	}

	@Setup(Level.Iteration)
	public void initIteration() {
		initialItems = random.longs(itemsCount).boxed().collect(toList());
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
		Set<Long> newList = new HashSet<>();
		for (Long item : initialItems) {
			newList.add(item);
		}
		return newList;
	}

	@Benchmark
	public Set<Long> measureAddAll_HashSet() {
		Set<Long> newList = new HashSet<>();
		newList.addAll(initialItems);
		return newList;
	}

	@Benchmark
	public Set<Long> measureAddAllViaConstructorArg_HashSet() {
		Set<Long> newList = new HashSet<>(initialItems);
		return newList;
	}
}
