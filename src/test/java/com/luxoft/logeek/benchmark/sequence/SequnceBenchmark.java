package com.luxoft.logeek.benchmark.sequence;

import com.luxoft.logeek.benchmark.BenchmarkBase;
import com.luxoft.logeek.entity.LoanContractEntity;
import com.luxoft.logeek.repository.LoanContractRepository;
import com.luxoft.logeek.service.ContractService;

import java.util.ArrayList;
import java.util.List;

public class SequnceBenchmark extends BenchmarkBase {

	protected static final int facilityBound = 3;

	protected ContractService service;
	protected LoanContractRepository loanContractRepository;

	/**
	 * Here we populate table with randomized data 
	 */
	protected void init() {
		super.initContext();

		service = context.getBean(ContractService.class);
		loanContractRepository = context.getBean(LoanContractRepository.class);

		populateTable();
	}


	protected void populateTable() {
		int count = 100000;//number of rows in table
		List<LoanContractEntity> items = new ArrayList<>(count);
		for (long i = 0; i < count; i++) {
			int sign = i % 2 == 0 ? 1 : -1;//should value be positive / negative
			LoanContractEntity entity = new LoanContractEntity(
					i,
					random.nextInt(facilityBound),// facility [0...3] used in 'where'-clause
					random.nextInt(10000)*sign,
					random.nextInt(10000)*sign,
					random.nextInt(10000)*sign
			);
			items.add(entity);
		}
		loanContractRepository.save(items);
	}
}
