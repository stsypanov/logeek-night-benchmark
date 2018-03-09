package com.luxoft.logeek.service.ltav;

import com.luxoft.logeek.dto.CashFlowDto;
import com.luxoft.logeek.service.CashFlowServiceLocal;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class EagerLtavServiceImpl implements EagerLtavService {
	private final CashFlowServiceLocal cashFlowServiceLocal;

	@Override
	@Transactional(readOnly = true)
	public long createCashFlow(CashFlowDto dto) {
		boolean isInvalid = dto.getNumber() % 2 != 0;
		if (isInvalid) {
			return -1L;
		}

		return cashFlowServiceLocal.createCashFlow(dto);
	}

}
