package com.luxoft.logeek.benchmark.sequence;

import com.luxoft.logeek.benchmark.ContextAwareBenchmarkBase;
import com.luxoft.logeek.entity.ContractEntity;
import com.luxoft.logeek.repository.ContractRepository;
import com.luxoft.logeek.service.ContractService;
import org.openjdk.jmh.annotations.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@OutputTimeUnit(TimeUnit.MILLISECONDS)
public class SequenceBenchmark extends ContextAwareBenchmarkBase {
	private static final int facilityBound = 3;

	private ContractService service;
	private ContractRepository contractRepository;

	private long facility;

	@Setup
	public void init() {
		super.init();
		service = context.getBean(ContractService.class);
		contractRepository = context.getBean(ContractRepository.class);
		populateTable();
	}

	@Setup(Level.Iteration)
	public void setUp() {
		facility = random.nextInt(facilityBound);
	}

	@Benchmark
	public long doCalculationLazily() {
		return service.doCalculationLazily(facility);
	}

	@Benchmark
	public long doCalculationEagerly() {
		return service.doCalculationEagerly(facility);
	}

	private void populateTable() {
		int count = 100000;//number of rows in table
		List<ContractEntity> items = new ArrayList<>(count);
		for (long i = 0; i < count; i++) {
			int sign = i % 2 == 0 ? 1 : -1;//should value be positive / negative
			ContractEntity entity = new ContractEntity(
					i,
					random.nextInt(facilityBound),// facility [0...3] used in 'where'-clause
					random.nextInt(10000)*sign,
					random.nextInt(10000)*sign,
					random.nextInt(10000)*sign
			);
			items.add(entity);
		}
		contractRepository.saveAll(items);
	}
}
