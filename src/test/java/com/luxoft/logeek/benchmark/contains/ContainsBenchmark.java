package com.luxoft.logeek.benchmark.contains;

import org.openjdk.jmh.annotations.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

/**
 * Created by Сергей on 10.02.2017.
 */
@BenchmarkMode({Mode.AverageTime})
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@State(Scope.Benchmark)
public class ContainsBenchmark {
	private ThreadLocalRandom random = ThreadLocalRandom.current();

	@Param({"10", "100", "1000", "10000", "100000", "1000000", "10000000"})
	private int count;

	private List<Long> items;
	private Long itemToBeFound;

	@Setup(Level.Iteration)
	public void setup() {
		items = random.longs(count, 0, count).map(Long::valueOf).collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
		itemToBeFound = random.nextLong(count);
	}

	@Benchmark
	public boolean contains() {
		return new HashSet<>(items).contains(itemToBeFound);
	}

}