package com.luxoft.logeek.benchmark.transaction;

import com.luxoft.logeek.dto.CashFlowDTO;
import com.luxoft.logeek.service.ltav.EagerLtavService;
import org.openjdk.jmh.annotations.*;

import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@State(Scope.Benchmark)
public class EagerTransactionBenchmark extends TransactionBenchmark {
	private CashFlowDTO dto;
	private EagerLtavService service;

	@Setup()
	public void init() {
		super.initContext();
		service = context.getBean(EagerLtavService.class);
	}

	@Setup(Level.Iteration)
	public void prepareFreshData() {
		dto = super.initDto();
	}

	@Benchmark
	public Long execute() {
		try {
			return service.createLtavCashFlow(dto);
		} catch (RuntimeException e) {
			return System.currentTimeMillis() << 2;
		}
	}

}
