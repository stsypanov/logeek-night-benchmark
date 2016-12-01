package com.luxoft.logeek.service.ltav;

import com.luxoft.logeek.dto.LtavCashFlowDetailsDTO;
import com.luxoft.logeek.exception.ServerCodeException;

public interface Validator {
	void validate(LtavCashFlowDetailsDTO detailsDTO);
}
