package com.luxoft.logeek.benchmark.bulk;

import com.luxoft.logeek.benchmark.BenchmarkBase;
import org.openjdk.jmh.annotations.Benchmark;

import java.util.EnumSet;
import java.util.List;
import java.util.stream.Stream;

import static com.luxoft.logeek.benchmark.bulk.EnumSetVsStreamOfBenchmark.MyEnum.*;
import static java.util.stream.Collectors.toList;

public class EnumSetVsStreamOfBenchmark extends BenchmarkBase {

	@Benchmark
	public List<String> measureEnumSetStream() {
		return EnumSet.of(A, B, C, D, E, F, G, H, I).stream().map(MyEnum::name).collect(toList());
	}

	@Benchmark
	public List<String> measureStreamOf() {
		return Stream.of(A, B, C, D, E, F, G, H, I).map(MyEnum::name).collect(toList());
	}

	enum MyEnum {
		A,
		B,
		C,
		D,
		E,
		F,
		G,
		H,
		I
	}
}
