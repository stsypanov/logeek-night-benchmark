package com.luxoft.logeek.benchmark.hashcode.calcapproach;

import com.luxoft.logeek.benchmark.BenchmarkBase;
import com.luxoft.logeek.hashcode.calcapproach.DataClass;
import com.luxoft.logeek.hashcode.calcapproach.DataClassWithChangedHashCalc;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.profile.GCProfiler;

/**
 * When running this benchmark make sure you use
 * {@link GCProfiler} to measure memory allocation
 */
public class HashCodeCalculationBenchmark extends BenchmarkBase {
	
	private DataClass data1;
	private DataClassWithChangedHashCalc data2;

	@Setup
	public void initTrial() {
		super.init();
	}

	@Setup(value = Level.Iteration)
	public void init() {
		long id = random.nextLong();
		String string = random.nextGaussian() + "";
		data1 = new DataClass(id, string);
		data2 = new DataClassWithChangedHashCalc(id, string);
	}

	@Benchmark
	public int measureConventionalHashCodeCalculation() {
		return data1.hashCode();
	}

	@Benchmark
	public int measureHashCodeCalculationWithObjectsHash() {
		return data2.hashCode();
	}
}
