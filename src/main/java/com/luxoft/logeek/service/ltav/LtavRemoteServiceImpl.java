package com.luxoft.logeek.service.ltav;

import com.luxoft.logeek.dto.LtavCalculationModelDTO;
import com.luxoft.logeek.dto.LtavCashFlowDetailsDTO;
import com.luxoft.logeek.exception.ServerCodeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component("remote")
public class LtavRemoteServiceImpl implements LtavRemoteService {
	private final LtavService ltavLazyService;
	private final Validator validator;

	@Autowired
	public LtavRemoteServiceImpl(@Qualifier("lazy") LtavService ltavLazyService,
								 Validator validator) {
		this.ltavLazyService = ltavLazyService;
		this.validator = validator;
	}

	@Override
	public Long createLtavCashFlow(LtavCashFlowDetailsDTO detailsDTO, Optional<LtavCalculationModelDTO> calculationModel) {
		validator.validate(detailsDTO);
		return ltavLazyService.createLtavCashFlow(detailsDTO, calculationModel);
	}


}
