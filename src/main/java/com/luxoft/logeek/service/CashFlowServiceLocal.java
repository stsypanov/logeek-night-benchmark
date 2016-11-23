package com.luxoft.logeek.service;

import com.luxoft.logeek.dto.LtavCalculationModelDTO;
import com.luxoft.logeek.dto.LtavCashFlowDetailsDTO;
import com.luxoft.logeek.entity.CashFlowEntity;

public interface CashFlowServiceLocal {
	Long createLtavCashFlow(LtavCashFlowDetailsDTO detailsDTO, LtavCalculationModelDTO ltavCalculationModelDTO);

	void afterCreateCashFlowProcessing(CashFlowEntity cashFlowEntity, boolean b);
}