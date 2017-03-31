package com.luxoft.logeek.benchmark.example.runner;

import com.luxoft.logeek.benchmark.example.PatchedArrayListBenchmark;
import com.luxoft.logeek.benchmark.example.RegularArrayListBenchmark;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

public class PatchedArrayListBenchmarkRunner {
	
	public static void main(String[] args) throws RunnerException {
		Options opt = new OptionsBuilder()
				.include(PatchedArrayListBenchmark.class.getSimpleName())
				.include(RegularArrayListBenchmark.class.getSimpleName())
				.warmupIterations(10)
				.measurementIterations(20)
				.forks(1)//0 makes debugging possible
				.build();

		new Runner(opt).run();
	}
}
