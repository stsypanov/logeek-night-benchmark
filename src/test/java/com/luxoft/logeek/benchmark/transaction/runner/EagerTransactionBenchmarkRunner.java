package com.luxoft.logeek.benchmark.transaction.runner;

import com.luxoft.logeek.benchmark.sequence.EagerSequenceBenchmark;
import com.luxoft.logeek.benchmark.transaction.EagerTransactionBenchmark;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

public class EagerTransactionBenchmarkRunner {

	public static void main(String[] args) throws RunnerException {
		Options opt = new OptionsBuilder()
				.include(EagerTransactionBenchmark.class.getSimpleName())
				.warmupIterations(5)
				.measurementIterations(20)
				.forks(1)//0 makes debugging possible
				.build();

		new Runner(opt).run();
	}
}
