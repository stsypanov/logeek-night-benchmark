package com.luxoft.logeek.benchmark.hashcode.calcapproach;

import com.luxoft.logeek.benchmark.BenchmarkBase;
import com.luxoft.logeek.hashcode.calcapproach.DataClass;
import com.luxoft.logeek.hashcode.calcapproach.DataClassWithChangedHashCalc;
import org.openjdk.jmh.annotations.*;

import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toMap;

@OutputTimeUnit(TimeUnit.MILLISECONDS)
public class HashCodeCalculationMapBenchmark extends BenchmarkBase {
	private static final Function<Long, DataClass> LONG_DATA_CLASS_FUNCTION =
			aLong -> new DataClass(
					aLong,
					aLong.toString()
			);
	private static final Function<Long, DataClassWithChangedHashCalc> LONG_DATA_CLASS_WITH_CHANGED_HASH_CALC_FUNCTION =
			aLong -> new DataClassWithChangedHashCalc(
					aLong,
					aLong.toString()
			);
	
	private Stream<Long> stream1;
	private Stream<Long> stream2;

	@Param({"10", "100", "1000"})
	private int size;

	@Setup
	public void initTrial() {
		super.init();
	}

	@Setup(value = Level.Invocation)
	public void init() {
		stream1 = random.longs(size).boxed();
		stream2 = random.longs(size).boxed();
	}

	@Benchmark
	public Map<DataClass, Long> measureConventionalHashCodeCalculation() {
		return stream1
				.collect(toMap(
						LONG_DATA_CLASS_FUNCTION,
						Function.identity()
				));
	}

	@Benchmark
	public Map<DataClassWithChangedHashCalc, Long> measureHashCodeCalculationWithObjectsHash() {
		return stream2
				.collect(toMap(
						LONG_DATA_CLASS_WITH_CHANGED_HASH_CALC_FUNCTION,
						Function.identity()
				));
	}
}
