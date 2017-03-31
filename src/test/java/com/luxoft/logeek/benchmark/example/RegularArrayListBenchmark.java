package com.luxoft.logeek.benchmark.example;

import com.luxoft.logeek.benchmark.BenchmarkBase;
import org.openjdk.jmh.annotations.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@BenchmarkMode({Mode.AverageTime})
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@State(Scope.Benchmark)
public class RegularArrayListBenchmark extends BenchmarkBase {

	private List<Long> regularArrayList1;
	private List<Long> regularArrayList2;

	private List<Long> regularArrayList3;
	private List<Long> regularArrayList4;

	@Setup()
	public void init() {
		super.init();
	}

	@Setup(value = Level.Iteration)
	public void prepareDate() {
		int size = random.nextInt(500);

		regularArrayList1 = getRegularArrayList(size);
		regularArrayList2 = getRegularArrayList(size);

		regularArrayList3 = getRegularArrayList(1);
		regularArrayList4 = getRegularArrayList(2);
	}

	private ArrayList<Long> getRegularArrayList(int size) {
		return random.longs(size, 0, 1)
				.boxed()
				.collect(Collectors.toCollection(ArrayList::new));
	}

	@Benchmark
	public boolean measureEquals_regularArrayLists_sameSize() {
		return regularArrayList2.equals(regularArrayList1);
	}

	@Benchmark
	public boolean measureEquals_regularArrayLists_differentSize() {
		return regularArrayList3.equals(regularArrayList4);
	}


}
