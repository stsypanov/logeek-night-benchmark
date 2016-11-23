package com.luxoft.logeek.service;

import com.luxoft.logeek.dto.LtavCalculationModelDTO;
import com.luxoft.logeek.dto.LtavCashFlowDetailsDTO;

import java.rmi.ServerException;
import java.util.Optional;

public interface LtavService {

	Long createLtavCashFlow(LtavCashFlowDetailsDTO detailsDTO, Optional<LtavCalculationModelDTO> calculationModel) throws ServerException;
}
