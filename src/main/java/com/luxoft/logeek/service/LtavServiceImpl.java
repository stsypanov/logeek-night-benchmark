package com.luxoft.logeek.service;

import com.luxoft.logeek.dto.LtavCalculationModelDTO;
import com.luxoft.logeek.dto.LtavCashFlowDetailsDTO;
import com.luxoft.logeek.entity.CashFlowEntity;
import com.luxoft.logeek.exception.ServerCodeException;
import com.luxoft.logeek.repository.CashFlowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Component
public class LtavServiceImpl implements LtavService {
	private final CashFlowServiceLocal cashFlowServiceLocal;
	private final CashFlowRepository cashFlowRepository;

	@Autowired
	public LtavServiceImpl(CashFlowServiceLocal cashFlowServiceLocal, CashFlowRepository cashFlowRepository) {
		this.cashFlowServiceLocal = cashFlowServiceLocal;
		this.cashFlowRepository = cashFlowRepository;
	}

	@Override
	@Transactional
	public Long createLtavCashFlow(LtavCashFlowDetailsDTO detailsDTO, Optional<LtavCalculationModelDTO> calculationModel) throws ServerCodeException {
		validate(detailsDTO);

		Long cashFlowId = cashFlowServiceLocal.createLtavCashFlow(detailsDTO, calculationModel.orElse(null));
		CashFlowEntity cashFlowEntity = cashFlowRepository.findOne(cashFlowId);
		cashFlowServiceLocal.afterCreateCashFlowProcessing(cashFlowEntity, detailsDTO.getId() != null);

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
