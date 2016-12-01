package com.luxoft.logeek.service.ltav;

import com.luxoft.logeek.dto.LtavCalculationModelDTO;
import com.luxoft.logeek.dto.LtavCashFlowDetailsDTO;
import com.luxoft.logeek.exception.ServerCodeException;

import java.util.Optional;

public interface LtavService {

	Long createLtavCashFlow(LtavCashFlowDetailsDTO detailsDTO, Optional<LtavCalculationModelDTO> calculationModel);
}
