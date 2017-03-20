package com.luxoft.logeek.benchmark.sequence.runner;

import com.luxoft.logeek.benchmark.sequence.SequenceBenchmark;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

public class LazySequenceBenchmarkRunner {

	public static void main(String[] args) throws Exception{
		Options opt = new OptionsBuilder()
				.include(SequenceBenchmark.class.getSimpleName())
				.warmupIterations(5)
				.measurementIterations(20)
				.forks(1)//0 makes debugging possible
				.build();

		new Runner(opt).run();
	}
}
