package com.luxoft.logeek.service.ltav;

import com.luxoft.logeek.dto.CashFlowDTO;
import org.springframework.stereotype.Component;

@Component
public class ValidatorImpl implements Validator {
	
	@Override
	public void validate(CashFlowDTO detailsDTO) {
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
