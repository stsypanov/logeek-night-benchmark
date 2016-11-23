package com.luxoft.logeek.service;

import com.luxoft.logeek.dto.LtavCalculationModelDTO;
import com.luxoft.logeek.dto.LtavCashFlowDetailsDTO;
import com.luxoft.logeek.entity.CashFlowEntity;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class CashFlowServiceLocalImpl implements CashFlowServiceLocal {
	
	@Override
	public Long createLtavCashFlow(LtavCashFlowDetailsDTO detailsDTO, LtavCalculationModelDTO ltavCalculationModelDTO) {
		return null;
	}

	@Override
	public void afterCreateCashFlowProcessing(CashFlowEntity cashFlowEntity, boolean b) {
		
	}
}
