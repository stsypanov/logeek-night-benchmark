package com.luxoft.logeek.service;

import com.luxoft.logeek.repository.LoanContractRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.function.Predicate;

@Component
public class ContractServiceImpl implements ContractService {

	private LoanContractRepository jpaRepository;

	@Autowired
	public ContractServiceImpl(LoanContractRepository jpaRepository) {
		this.jpaRepository = jpaRepository;
	}

	@Override
	public long doCalculation(Long facility) {
		BigDecimal compensation = jpaRepository.findCompensationByFacility(facility);
		BigDecimal deposit = jpaRepository.findDepositByFacilityId(facility);
		BigDecimal outstanding = jpaRepository.findOutstandingByFacility(facility);

		boolean hasCompensation = compensation.signum() > 0;
		boolean hasDeposit = deposit.signum() > 0;
		boolean hasOutstanding = outstanding.signum() > 0;

		if (hasDeposit || hasCompensation) {
			return calculateWihtCompensation();
		} else if (hasOutstanding) {
			return calculateWihtOutstanding();
		} else {
			return calculateDefault();
		}
	}

	private final Predicate<Long> hasCompensation = facility -> {
		BigDecimal compensation = jpaRepository.findCompensationByFacility(facility);
		return compensation.signum() > 0;
	};

	private final Predicate<Long> hasDeposit = facility -> {
		BigDecimal deposit = jpaRepository.findDepositByFacilityId(facility);
		return deposit.signum() > 0;
	};

	private final Predicate<Long> hasOutstanding = facility -> {
		BigDecimal outstanding = jpaRepository.findOutstandingByFacility(facility);
		return outstanding.signum() > 0;
	};

	@Override
	public long doCalculationLazily(Long facility) {
		if (hasDeposit.or(hasCompensation).test(facility)) {
			return calculateWihtCompensation();
		} else if (hasOutstanding.test(facility)) {
			return calculateWihtOutstanding();
		} else {
			return calculateDefault();
		}
	}

	private long calculateWihtCompensation() {
		return System.currentTimeMillis() << 2;
	}

	private long calculateWihtOutstanding() {
		return System.currentTimeMillis() >> 6;
	}

	private long calculateDefault() {
		return System.currentTimeMillis() >>> 3;
	}
}
