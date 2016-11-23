package com.luxoft.logeek.benchmark.sequence.runner;

import com.luxoft.logeek.benchmark.sequence.EagerSequenceBenchmark;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

public class EagerSequenceBenchmarkRunner {

	public static void main(String[] args) throws RunnerException {
		Options opt = new OptionsBuilder()
				.include(EagerSequenceBenchmark.class.getSimpleName())
				.warmupIterations(5)
				.measurementIterations(20)
				.forks(1)//0 makes debugging possible
				.build();

		new Runner(opt).run();
	}
}
