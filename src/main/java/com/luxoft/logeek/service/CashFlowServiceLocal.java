package com.luxoft.logeek.service;

import com.luxoft.logeek.dto.CashFlowDTO;
import com.luxoft.logeek.entity.CashFlowEntity;

public interface CashFlowServiceLocal {
	Long createCashFlow(CashFlowDTO detailsDTO);

	void afterCreateCashFlowProcessing(CashFlowEntity cashFlowEntity, boolean b);
}