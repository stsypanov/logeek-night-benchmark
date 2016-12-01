package com.luxoft.logeek.benchmark.transaction;

import com.luxoft.logeek.AppConfig;
import com.luxoft.logeek.dto.LtavCashFlowDetailsDTO;
import com.luxoft.logeek.service.ltav.LtavService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TransactionBenchmark {
	
	protected static final int DTO_COUNT = 100;
	protected LtavService ltavService;
	protected Random random;
	
	protected void initContext (){
		if (ltavService == null) {
			ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
			ltavService=context.getBean(LtavService.class);

			random =new Random(System.currentTimeMillis());
		}
	}
	
	protected List<LtavCashFlowDetailsDTO> initData() {
		List<LtavCashFlowDetailsDTO> dtos = new ArrayList<>(DTO_COUNT);
		for (int i = 0; i < DTO_COUNT; i++) {
			LtavCashFlowDetailsDTO dto = new LtavCashFlowDetailsDTO(i, random.nextLong());
			dtos.add(dto);
		}
		return dtos;
	}
	
	protected LtavCashFlowDetailsDTO initDto() {
		LtavCashFlowDetailsDTO dto;
		for (int i = random.nextInt(); ; i++) {
			if (i % 2 != 0) {
				dto = new LtavCashFlowDetailsDTO(i, random.nextLong());
				break;
			}
			continue;
		}
			
		return dto;
	}
}
