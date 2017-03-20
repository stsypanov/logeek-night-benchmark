package com.luxoft.logeek.benchmark.example.runner;

import com.luxoft.logeek.benchmark.example.ExampleBenchmark;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

public class ExampleBenchmarkRunner {
	
	public static void main(String[] args) throws RunnerException {
		Options opt = new OptionsBuilder()
				.include(ExampleBenchmark.class.getSimpleName())
				.warmupIterations(10)
				.measurementIterations(20)
				.forks(1)//0 makes debugging possible
				.build();

		new Runner(opt).run();
	}
}
