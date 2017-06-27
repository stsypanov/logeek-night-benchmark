package com.luxoft.logeek.benchmark.aspect;

import com.luxoft.logeek.benchmark.ContextAwareBenchmarkBase;
import com.luxoft.logeek.service.AspectedService;
import org.openjdk.jmh.annotations.*;

public class AspectBenchmark extends ContextAwareBenchmarkBase {
	private AspectedService aspectedService;

	@Setup
	public void init() {
		super.init();
		aspectedService = getBean(AspectedService.class);
	}

	@Benchmark
	public Long execute() {
		return aspectedService.getRandomLong();
	}
}
