package com.luxoft.logeek.benchmark.sequence;

import org.openjdk.jmh.annotations.*;

import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@State(Scope.Benchmark)
public class EagerSequenceBenchmark extends SequnceBenchmark {

	@Setup()
	public void init() {
		super.init();
	}

	@Benchmark
	public void execute() {
		long facility = random.nextInt(facilityBound);
		service.doCalculation(facility);
	}
}
