package com.luxoft.logeek.benchmark.transaction;

import com.luxoft.logeek.AppConfig;
import com.luxoft.logeek.dto.LtavCashFlowDetailsDTO;
import com.luxoft.logeek.service.ltav.LtavService;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.rmi.ServerException;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@State(Scope.Benchmark)
public class EagerTransactionBenchmark extends TransactionBenchmark {
	private LtavCashFlowDetailsDTO dto;

	@Setup()
	public void init() {
		if (ltavService == null) {
			ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
			ltavService=context.getBean("eager", LtavService.class);

			random =new Random(System.currentTimeMillis());
		}
	}
	
	@Setup(Level.Iteration)
	public void prepareFreshData() {
		dto = super.initDto();
	}
	
	@Benchmark
	public void execute(Blackhole bh) {
		try {
			Long ltavCashFlow = ltavService.createLtavCashFlow(dto, Optional.empty());
			bh.consume(ltavCashFlow);
		} catch (RuntimeException e) {
			bh.consume(e);
		}
	}
	
}
