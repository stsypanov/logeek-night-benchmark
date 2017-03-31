package com.luxoft.logeek.benchmark.aspect;

import com.luxoft.logeek.benchmark.BenchmarkWithAppContext;
import com.luxoft.logeek.service.AspectedService;
import org.openjdk.jmh.annotations.*;

import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@State(Scope.Benchmark)
public class AspectBenchmark extends BenchmarkWithAppContext {
	private AspectedService aspectedService;

	@Setup()
	public void init() {
		super.init();
		aspectedService = context.getBean(AspectedService.class);
	}

	@Benchmark
	public Long execute() {
		return aspectedService.getRandomLong();
	}
}
