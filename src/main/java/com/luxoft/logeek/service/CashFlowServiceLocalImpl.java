package com.luxoft.logeek.service;

import com.luxoft.logeek.dto.CashFlowDTO;
import com.luxoft.logeek.entity.CashFlowEntity;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class CashFlowServiceLocalImpl implements CashFlowServiceLocal {
	private final Random random;

	public CashFlowServiceLocalImpl() {
		random=new Random(System.currentTimeMillis());
	}

	@Override
	public Long createCashFlow(CashFlowDTO detailsDTO) {
		return random.nextLong();
	}

	@Override
	public void afterCreateCashFlowProcessing(CashFlowEntity cashFlowEntity, boolean b) {
		
	}
}
