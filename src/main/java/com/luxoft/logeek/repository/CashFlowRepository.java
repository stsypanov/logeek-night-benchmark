package com.luxoft.logeek.repository;

import com.luxoft.logeek.entity.CashFlowEntity;

public interface CashFlowRepository {
	CashFlowEntity findOne(Long cashFlowId);
}
