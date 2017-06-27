package com.luxoft.logeek.benchmark.transaction;

import com.luxoft.logeek.benchmark.BenchmarkWithAppContext;
import com.luxoft.logeek.dto.CashFlowDto;
import com.luxoft.logeek.service.ltav.EagerLtavService;
import com.luxoft.logeek.service.ltav.LazyLtavService;
import org.openjdk.jmh.annotations.*;

import java.util.concurrent.TimeUnit;

@BenchmarkMode({Mode.AverageTime, Mode.Throughput})
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@State(Scope.Benchmark)
public class LazyTransactionBenchmark extends BenchmarkWithAppContext {
	private CashFlowDto dto;
	private LazyLtavService lazyService;
	private EagerLtavService eagerService;

	@Setup()
	public void init() {
		super.init();
		lazyService = context.getBean(LazyLtavService.class);
		eagerService = context.getBean(EagerLtavService.class);
	}

	@Setup(Level.Iteration)
	public void prepareFreshData() {
		dto = initDto();
	}

	@Benchmark
	public Long measureLazyTransaction() {
		try {
			return lazyService.createCashFlow(dto);
		} catch (RuntimeException e) {
			return System.currentTimeMillis() >>> 3;
		}
	}

	@Benchmark
	public Long measureEagerTransaction() {
		try {
			return eagerService.createCashFlow(dto);
		} catch (RuntimeException e) {
			return System.currentTimeMillis() << 2;
		}
	}

	private CashFlowDto initDto() {
		CashFlowDto dto;
		for (int i = random.nextInt(); ; i++) {
			if (i % 2 != 0) {
				dto = new CashFlowDto(i);
				break;
			}
		}

		return dto;
	}
}
