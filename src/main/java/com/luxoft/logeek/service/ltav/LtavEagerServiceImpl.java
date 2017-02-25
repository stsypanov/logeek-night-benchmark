package com.luxoft.logeek.service.ltav;

import com.luxoft.logeek.dto.CashFlowDTO;
import com.luxoft.logeek.service.CashFlowServiceLocal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component("eager")
public class LtavEagerServiceImpl implements LtavService {
	private final CashFlowServiceLocal cashFlowServiceLocal;

	@Autowired
	public LtavEagerServiceImpl(CashFlowServiceLocal cashFlowServiceLocal) {
		this.cashFlowServiceLocal = cashFlowServiceLocal;
	}

	@Override
	@Transactional
	public Long createLtavCashFlow(CashFlowDTO detailsDTO) {
		validate(detailsDTO);

		Long cashFlowId = cashFlowServiceLocal.createCashFlow(detailsDTO);

		return cashFlowId;
	}

	private void validate(CashFlowDTO detailsDTO) throws IllegalArgumentException {
		Integer imoNumber = detailsDTO.getImoNumber();

		boolean inValidIMO = validateIMO(imoNumber);
		if (!inValidIMO) {
			throw new IllegalArgumentException("IMO number is not valid");
		}

		int count = validateLtavCashFlowDetailsCount(imoNumber);
		if (count > 0) {
			throw new IllegalArgumentException("IMO number is not unique");
		}
	}

	private boolean validateIMO(Integer imoNumber) {
		return imoNumber % 2 == 0;
	}

	private int validateLtavCashFlowDetailsCount(Integer imoNumber) {
		return imoNumber - 3;
	}


}
