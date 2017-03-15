package com.luxoft.logeek.benchmark.aspect.runner;

import com.luxoft.logeek.benchmark.aspect.AspectBenchmark;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

public class AspectBenchmarkRunner {
	
	public static void main(String[] args) throws RunnerException {
		Options opt = new OptionsBuilder()
				.include(AspectBenchmark.class.getSimpleName())
				.warmupIterations(5)
				.measurementIterations(10)
				.forks(1)//0 makes debugging possible
				.build();

		new Runner(opt).run();
	}
}
