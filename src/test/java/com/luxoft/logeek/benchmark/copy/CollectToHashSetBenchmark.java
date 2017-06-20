package com.luxoft.logeek.benchmark.copy;

import com.luxoft.logeek.benchmark.BenchmarkBase;
import org.openjdk.jmh.annotations.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@State(Scope.Benchmark)
public class CollectToHashSetBenchmark extends BenchmarkBase {

	private List<Integer> items;

	@Param({"10", "100", "1000", "10000", "100000"})
	private int size;

	@Setup(value = Level.Iteration)
	public void init() {
		super.init();
		items = random.ints(size).boxed().collect(toList());
	}


	@Benchmark
	public Set<String> measureDirectCopyFromStream() {
		Set<String> set = items.stream()
				.map(Object::toString)
				.collect(toSet());
		return set;
	}

	@Benchmark
	public Set<String> measureCopyFromCollectedList() {
		List<String> list = items.stream()
				.map(Object::toString)
				.collect(toList());
		return new HashSet<>(list);
	}
}
