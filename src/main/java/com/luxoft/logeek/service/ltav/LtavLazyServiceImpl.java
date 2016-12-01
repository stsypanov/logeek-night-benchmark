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

@Component("lazy")
public class LtavLazyServiceImpl implements LtavService {
	private final CashFlowServiceLocal cashFlowServiceLocal;

	@Autowired
	public LtavLazyServiceImpl(CashFlowServiceLocal cashFlowServiceLocal) {
		this.cashFlowServiceLocal = cashFlowServiceLocal;
	}
	
	@Override
	@Transactional
	public Long createLtavCashFlow(LtavCashFlowDetailsDTO detailsDTO, Optional<LtavCalculationModelDTO> calculationModel) {
		Long cashFlowId = cashFlowServiceLocal.createLtavCashFlow(detailsDTO, calculationModel.orElse(null));
//		CashFlowEntity cashFlowEntity = cashFlowRepository.findOne(cashFlowId);
//		cashFlowServiceLocal.afterCreateCashFlowProcessing(cashFlowEntity, detailsDTO.getId() != null);

		return cashFlowId;
	}
}
