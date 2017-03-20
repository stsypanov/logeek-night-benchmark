package com.luxoft.logeek.service.ltav;

import com.luxoft.logeek.dto.CashFlowDto;
import com.luxoft.logeek.service.CashFlowServiceLocal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LazyLtavServiceImpl implements LazyLtavService {
	private final CashFlowServiceLocal cashFlowServiceLocal;
	private final Validator validator;

	@Autowired
	public LazyLtavServiceImpl(CashFlowServiceLocal cashFlowServiceLocal, Validator validator) {
		this.cashFlowServiceLocal = cashFlowServiceLocal;
		this.validator = validator;
	}

	@Override
	public Long createCashFlow(CashFlowDto dto) {
		validator.validate(dto);

		return cashFlowServiceLocal.createCashFlow(dto);
	}
}
