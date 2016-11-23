package com.luxoft.logeek.repository;

import com.luxoft.logeek.entity.CashFlowEntity;
import org.springframework.stereotype.Component;

@Component
public class CashFlowRepositoryImpl implements CashFlowRepository{
	
	@Override
	public CashFlowEntity findOne(Long cashFlowId) {
		return null;
	}
}
