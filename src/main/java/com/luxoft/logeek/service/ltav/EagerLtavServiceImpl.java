package com.luxoft.logeek.service.ltav;

import com.luxoft.logeek.dto.CashFlowDTO;
import com.luxoft.logeek.service.CashFlowServiceLocal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EagerLtavServiceImpl implements EagerLtavService {
	private final CashFlowServiceLocal cashFlowServiceLocal;
	private final Validator validator;

	@Autowired
	public EagerLtavServiceImpl(CashFlowServiceLocal cashFlowServiceLocal, Validator validator) {
		this.cashFlowServiceLocal = cashFlowServiceLocal;
		this.validator = validator;
	}

	@Override
	@Transactional
	public Long createLtavCashFlow(CashFlowDTO detailsDTO) {
		validator.validate(detailsDTO);

		return cashFlowServiceLocal.createCashFlow(detailsDTO);
	}

}
