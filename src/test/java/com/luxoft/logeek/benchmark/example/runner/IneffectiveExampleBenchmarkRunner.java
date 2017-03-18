package com.luxoft.logeek.benchmark.example.runner;

import com.luxoft.logeek.benchmark.example.IneffectiveExampleBenchmark;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

public class IneffectiveExampleBenchmarkRunner {
	
	public static void main(String[] args) throws RunnerException {
		Options opt = new OptionsBuilder()
				.include(IneffectiveExampleBenchmark.class.getSimpleName())
				.warmupIterations(10)
				.measurementIterations(20)
				.forks(1)//0 makes debugging possible
				.build();

		new Runner(opt).run();
	}

}
