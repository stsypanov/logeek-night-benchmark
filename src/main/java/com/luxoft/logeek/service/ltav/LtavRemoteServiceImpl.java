package com.luxoft.logeek.service.ltav;

import com.luxoft.logeek.dto.CashFlowDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

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
	public Long createLtavCashFlow(CashFlowDTO detailsDTO) {
		validator.validate(detailsDTO);
		return ltavLazyService.createLtavCashFlow(detailsDTO);
	}


}
