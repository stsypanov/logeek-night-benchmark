package com.luxoft.logeek.benchmark.audit.runner;

import com.luxoft.logeek.benchmark.audit.AuditBenchmark;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

public class AuditBenchmarkRunner {

	public static void main(String[] args) throws RunnerException {
		Options opt = new OptionsBuilder()
				.include(AuditBenchmark.class.getSimpleName())
				.warmupIterations(2)
				.measurementIterations(2)
				.forks(1)//0 makes debugging possible
				.build();

		new Runner(opt).run();
	}
}
