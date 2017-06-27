package com.luxoft.logeek.benchmark.hashcode;

import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

public class HashCodeCachingBenchmarkRunner {

    public static void main(String[] args) throws RunnerException {
		Options opt = new OptionsBuilder()
				.include(HashCodeCachingBenchmark.class.getSimpleName())
				.warmupIterations(10)
				.measurementIterations(100)
//				.addProfiler(GCProfiler.class)
				.forks(1)//0 makes debugging possible
				.build();

		new Runner(opt).run();
	}
}