package com.luxoft.logeek.service.impl;

import com.luxoft.logeek.dto.CashFlowDto;
import com.luxoft.logeek.service.CashFlowServiceLocal;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Random;

@Service
@Transactional
public class CashFlowServiceLocalImpl implements CashFlowServiceLocal {
	private final Random random;

	public CashFlowServiceLocalImpl() {
		random = new Random(System.currentTimeMillis());
	}

	@Override
	public Long createCashFlow(CashFlowDto dto) {
		return random.nextLong();
	}

}
