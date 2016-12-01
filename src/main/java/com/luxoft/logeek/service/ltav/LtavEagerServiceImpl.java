package com.luxoft.logeek.service.ltav;

import com.luxoft.logeek.dto.LtavCalculationModelDTO;
import com.luxoft.logeek.dto.LtavCashFlowDetailsDTO;
import com.luxoft.logeek.exception.ServerCodeException;
import com.luxoft.logeek.repository.CashFlowRepository;
import com.luxoft.logeek.service.CashFlowServiceLocal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Component("eager")
public class LtavEagerServiceImpl implements LtavService {
	private final CashFlowServiceLocal cashFlowServiceLocal;

	@Autowired
	public LtavEagerServiceImpl(CashFlowServiceLocal cashFlowServiceLocal) {
		this.cashFlowServiceLocal = cashFlowServiceLocal;
	}

	@Override
	@Transactional
	public Long createLtavCashFlow(LtavCashFlowDetailsDTO detailsDTO, Optional<LtavCalculationModelDTO> calculationModel) {
		validate(detailsDTO);

		Long cashFlowId = cashFlowServiceLocal.createLtavCashFlow(detailsDTO, calculationModel.orElse(null));

		return cashFlowId;
	}

	private void validate(LtavCashFlowDetailsDTO detailsDTO) throws ServerCodeException {
		Integer imoNumber = detailsDTO.getImoNumber();

		boolean inValidIMO = validateIMO(imoNumber);
		if (!inValidIMO) {
			throw new ServerCodeException("IMO number is not valid");
		}

		int count = validateLtavCashFlowDetailsCount(imoNumber);
		if (count > 0) {
			throw new ServerCodeException("IMO number is not unique");
		}
	}

	private boolean validateIMO(Integer imoNumber) {
		return imoNumber % 2 == 0;
	}

	private int validateLtavCashFlowDetailsCount(Integer imoNumber) {
		return imoNumber - 3;
	}


}
