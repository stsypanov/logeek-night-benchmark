package com.luxoft.logeek.service.ltav;

import com.luxoft.logeek.dto.CashFlowDTO;
import com.luxoft.logeek.service.CashFlowServiceLocal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component("lazy")
public class LtavLazyServiceImpl implements LtavService {
	private final CashFlowServiceLocal cashFlowServiceLocal;

	@Autowired
	public LtavLazyServiceImpl(CashFlowServiceLocal cashFlowServiceLocal) {
		this.cashFlowServiceLocal = cashFlowServiceLocal;
	}
	
	@Override
	@Transactional
	public Long createLtavCashFlow(CashFlowDTO detailsDTO) {
		return cashFlowServiceLocal.createCashFlow(detailsDTO);
	}
}
