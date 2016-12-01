package com.luxoft.logeek.benchmark.transaction.runner;

import com.luxoft.logeek.benchmark.transaction.EagerTransactionBenchmark;
import com.luxoft.logeek.benchmark.transaction.LazyTransactionBenchmark;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

public class LazyTransactionBenchmarkRunner {

	public static void main(String[] args) throws RunnerException {
		Options opt = new OptionsBuilder()
				.include(LazyTransactionBenchmark.class.getSimpleName())
				.warmupIterations(5)
				.measurementIterations(20)
				.forks(0)//0 makes debugging possible
				.build();

		new Runner(opt).run();
	}
}
