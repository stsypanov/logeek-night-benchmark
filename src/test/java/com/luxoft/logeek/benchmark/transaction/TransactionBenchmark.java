package com.luxoft.logeek.benchmark.transaction;

import com.luxoft.logeek.benchmark.BenchmarkBase;
import com.luxoft.logeek.dto.CashFlowDTO;
import com.luxoft.logeek.service.ltav.LtavService;

import java.util.ArrayList;
import java.util.List;

public class TransactionBenchmark extends BenchmarkBase {

	protected static final int DTO_COUNT = 100;
	protected LtavService ltavService;

	protected void initContext() {
		super.initContext();
		if (ltavService == null) {
			ltavService = context.getBean(LtavService.class);
		}
	}

	protected List<CashFlowDTO> initData() {
		List<CashFlowDTO> dtos = new ArrayList<>(DTO_COUNT);
		for (int i = 0; i < DTO_COUNT; i++) {
			CashFlowDTO dto = new CashFlowDTO(i, random.nextLong());
			dtos.add(dto);
		}
		return dtos;
	}

	protected CashFlowDTO initDto() {
		CashFlowDTO dto;
		for (int i = random.nextInt(); ; i++) {
			if (i % 2 != 0) {
				dto = new CashFlowDTO(i, random.nextLong());
				break;
			}
			continue;
		}

		return dto;
	}
}
