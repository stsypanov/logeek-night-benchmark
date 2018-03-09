package com.luxoft.logeek.service.impl;

import com.luxoft.logeek.dto.CashFlowDto;
import com.luxoft.logeek.service.CashFlowServiceLocal;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

@Component
@Transactional(readOnly = true)
public class CashFlowServiceLocalImpl implements CashFlowServiceLocal {
	private final Random random;

	public CashFlowServiceLocalImpl() {
		random = ThreadLocalRandom.current();
	}

	@Override
	public long createCashFlow(CashFlowDto dto) {
		return random.nextLong();
	}

}
