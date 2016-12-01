package com.luxoft.logeek.service;

import com.luxoft.logeek.repository.LoanContractRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.function.Predicate;

@Component
public class ContractServiceImpl implements ContractService {

	private LoanContractRepository loanContractRepository;

	@Autowired
	public ContractServiceImpl(LoanContractRepository loanContractRepository) {
		this.loanContractRepository = loanContractRepository;
	}

	@Override
	public long doCalculation(Long facility) {
		BigDecimal compensation = loanContractRepository.findCompensationByFacility(facility);
		BigDecimal deposit = loanContractRepository.findDepositByFacilityId(facility);
		BigDecimal outstanding = loanContractRepository.findOutstandingByFacility(facility);

		boolean hasCompensation = compensation.signum() > 0;
		boolean hasDeposit = deposit.signum() > 0;
		boolean hasOutstanding = outstanding.signum() > 0;

		if (hasDeposit || hasCompensation) {
			return System.currentTimeMillis();
		} else if (hasOutstanding) {
			return System.currentTimeMillis();
		} else {
			return System.currentTimeMillis();
		}
	}


	private final Predicate<Long> hasCompensation = facility -> {
		BigDecimal compensation = loanContractRepository.findCompensationByFacility(facility);
		return compensation.signum() > 0;
	};

	private final Predicate<Long> hasDeposit = facility -> {
		BigDecimal deposit = loanContractRepository.findDepositByFacilityId(facility);
		return deposit.signum() > 0;
	};

	private final Predicate<Long> hasOutstanding = facility -> {
		BigDecimal outstanding = loanContractRepository.findOutstandingByFacility(facility);
		return outstanding.signum() > 0;
	};

	@Override
	public long doCalculationLazily(Long facility) {
		if (hasDeposit.or(hasCompensation).test(facility)) {
			return System.currentTimeMillis() * 2 - 5;
		} else if (hasOutstanding.test(facility)) {
			return System.currentTimeMillis() * 3 - 6;
		} else {
			return System.currentTimeMillis() * 4 - 7;
		}
	}

}
