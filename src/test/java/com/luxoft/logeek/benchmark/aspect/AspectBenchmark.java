package com.luxoft.logeek.benchmark.aspect;

import com.luxoft.logeek.benchmark.BenchmarkBase;
import com.luxoft.logeek.service.AspectedService;
import org.openjdk.jmh.annotations.*;

import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@State(Scope.Benchmark)
public class AspectBenchmark extends BenchmarkBase {
	private AspectedService aspectedService;

	@Setup()
	public void init() {
		super.initContext();
		aspectedService = context.getBean(AspectedService.class);
	}

	@Benchmark
	public Long execute() {
		return aspectedService.getRandomLong();
	}
}
