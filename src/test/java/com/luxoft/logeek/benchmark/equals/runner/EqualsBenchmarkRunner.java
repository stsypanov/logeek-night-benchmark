package com.luxoft.logeek.benchmark.equals.runner;

import com.luxoft.logeek.benchmark.equals.EqualsBenchmark;
import com.luxoft.logeek.benchmark.sequence.EagerSequenceBenchmark;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

public class EqualsBenchmarkRunner {

	public static void main(String[] args) throws RunnerException {
		Options opt = new OptionsBuilder()
				.include(EqualsBenchmark.class.getSimpleName())
				.warmupIterations(10)
				.measurementIterations(100)
				.forks(1)//0 makes debugging possible
				.build();

		new Runner(opt).run();
	}
}
