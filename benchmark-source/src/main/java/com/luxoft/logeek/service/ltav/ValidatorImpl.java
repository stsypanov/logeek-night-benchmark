package com.luxoft.logeek.service.ltav;

import com.luxoft.logeek.dto.CashFlowDto;
import org.springframework.stereotype.Component;

@Component
public class ValidatorImpl implements Validator {
	
	@Override
	public void validate(CashFlowDto dto) {
		boolean isInvalid = dto.getNumber() % 2 != 0;
		if (isInvalid) {
			throw new IllegalArgumentException("Number is not valid");
		}
	}
}
