package com.luxoft.logeek.service;

import com.luxoft.logeek.dto.LtavCalculationModelDTO;
import com.luxoft.logeek.dto.LtavCashFlowDetailsDTO;
import com.luxoft.logeek.entity.CashFlowEntity;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Random;

@Component
public class CashFlowServiceLocalImpl implements CashFlowServiceLocal {
	private final Random random;

	public CashFlowServiceLocalImpl() {
		random=new Random(System.currentTimeMillis());
	}

	@Override
	public Long createLtavCashFlow(LtavCashFlowDetailsDTO detailsDTO, LtavCalculationModelDTO ltavCalculationModelDTO) {
		return random.nextLong();
	}

	@Override
	public void afterCreateCashFlowProcessing(CashFlowEntity cashFlowEntity, boolean b) {
		
	}
}
