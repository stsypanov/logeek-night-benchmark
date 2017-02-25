package com.luxoft.logeek.benchmark.contains.runner;

import com.luxoft.logeek.benchmark.contains.ContainsBenchmark;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

public class ContainsBenchmarkRunner {

	public static void main(String[] args) throws RunnerException {
		Options opt = new OptionsBuilder()
				.include(ContainsBenchmark.class.getSimpleName())
				.warmupIterations(10)
				.measurementIterations(10)
				.forks(1)//0 makes debugging possible
				.build();

		new Runner(opt).run();
	}
}
