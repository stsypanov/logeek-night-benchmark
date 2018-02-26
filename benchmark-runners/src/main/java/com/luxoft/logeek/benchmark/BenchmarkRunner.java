package com.luxoft.logeek.benchmark;

import com.luxoft.logeek.benchmark.stream.AllMatchVsContainsAllBenchmark;
import org.openjdk.jmh.profile.GCProfiler;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

public class BenchmarkRunner {

	public static void main(String[] args) throws RunnerException {
		Options opt = new OptionsBuilder()
				.include(AllMatchVsContainsAllBenchmark.class.getSimpleName())
				.warmupIterations(10)
				.measurementIterations(10)
				.forks(5) //0 makes debugging possible
				.shouldFailOnError(true)
//				.jvmArgsAppend(
//						"-XX:+UnlockDiagnosticVMOptions",
//						"-XX:+PrintCompilation",
//						"-XX:+PrintInlining",
//						"-XX:+LogCompilation"
//				)
//				.addProfiler(GCProfiler.class)// memory and GC profiler
				.build();

		new Runner(opt).run();
	}
}