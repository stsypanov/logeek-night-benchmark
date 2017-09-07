package com.luxoft.logeek.benchmark.set;

import com.luxoft.logeek.benchmark.BenchmarkBase;
import com.luxoft.logeek.utils.ContainerUtils;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Setup;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.IntStream;

public class SetAddAllBenchmark extends BenchmarkBase {
	@Param({"10", "1000", "10000"})
	private int count;

	private Integer[] integers;
	private Set<Integer> integerSet1;
	private Set<Integer> integerSet2;
	private Set<Integer> integerSet3;

	@Setup
	public void setup() {
		integers = IntStream.range(0, count).boxed().toArray(value -> new Integer[count]);
	}

	@Setup(Level.Invocation)
	public void prepareFreshSet() {
		integerSet1 = new HashSet<>();
		integerSet2 = new HashSet<>();
		integerSet3 = new HashSet<>();
	}

	@Benchmark
	public boolean measureAddAllViaMethod() {
		return integerSet1.addAll(Arrays.asList(integers));
	}

	@Benchmark
	public boolean measureAddAllViaCollectionsAddAll() {
		return Collections.addAll(integerSet2, integers);
	}

	@Benchmark
	public Set<Integer> measureAddAllViaUtilsAddAll() {
		return ContainerUtils.addAll(integerSet3, integers);
	}
}
