package com.luxoft.logeek.benchmark.sequence;

import com.luxoft.logeek.AppConfig;
import com.luxoft.logeek.JpaConfig;
import com.luxoft.logeek.entity.LoanContractEntity;
import com.luxoft.logeek.repository.LoanContractRepository;
import com.luxoft.logeek.service.ContractService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SequnceBenchmark {

	protected static final int facilityBound = 3;

	protected ContractService service;
	protected LoanContractRepository loanContractRepository;
	protected Random random;

	/**
	 * Here we populate table with randomized data 
	 */
	protected void init() {
		initContext();

		populateTable();
	}

	protected void initContext() {
		if (service == null && loanContractRepository == null) {
			AnnotationConfigApplicationContext appContext = new AnnotationConfigApplicationContext(JpaConfig.class, AppConfig.class);

			service = appContext.getBean(ContractService.class);
			loanContractRepository = appContext.getBean(LoanContractRepository.class);

			random = new Random(System.currentTimeMillis());
		}
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
