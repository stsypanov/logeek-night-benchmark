package com.luxoft.logeek.benchmark.transaction;

import com.luxoft.logeek.dto.CashFlowDTO;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@State(Scope.Benchmark)
public class EagerTransactionBenchmark extends TransactionBenchmark {
	private CashFlowDTO dto;

	@Setup()
	public void init() {
		super.initContext();
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
