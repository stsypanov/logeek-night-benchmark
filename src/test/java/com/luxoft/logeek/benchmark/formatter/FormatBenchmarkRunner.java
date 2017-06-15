package com.luxoft.logeek.benchmark.formatter;

import com.luxoft.logeek.benchmark.example.ExampleBenchmark;
import org.openjdk.jmh.profile.GCProfiler;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

public class FormatBenchmarkRunner {

    public static void main(String[] args) throws RunnerException {
		Options opt = new OptionsBuilder()
				.include(FormatterBenchmark.class.getSimpleName())
				.warmupIterations(10)
				.measurementIterations(100)
//				.addProfiler(GCProfiler.class)
				.forks(1)//0 makes debugging possible
				.build();

		new Runner(opt).run();
	}
}