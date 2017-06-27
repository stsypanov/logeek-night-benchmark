package com.luxoft.logeek.benchmark.aspect;

import com.luxoft.logeek.benchmark.ContextAwareBenchmark;
import com.luxoft.logeek.service.AspectedService;
import org.openjdk.jmh.annotations.*;

import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@State(Scope.Benchmark)
public class AspectBenchmark extends ContextAwareBenchmark {
	private AspectedService aspectedService;

	@Setup()
	public void init() {
		super.init();
		aspectedService = getBean(AspectedService.class);
	}

	@Benchmark
	public Long execute() {
		return aspectedService.getRandomLong();
	}
}
