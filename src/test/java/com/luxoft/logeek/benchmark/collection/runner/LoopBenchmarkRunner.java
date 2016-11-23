package com.luxoft.logeek.benchmark.collection.runner;

import com.luxoft.logeek.benchmark.collection.LoopBenchmark;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

public class LoopBenchmarkRunner {
	
	public static void main(String[] args) throws RunnerException {
		Options opt = new OptionsBuilder()
				.include(LoopBenchmark.class.getSimpleName())
				.warmupIterations(5)
				.measurementIterations(20)
				.forks(1)
				.build();

		new Runner(opt).run();

	}
}
