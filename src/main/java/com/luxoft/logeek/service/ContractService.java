package com.luxoft.logeek.service;

public interface ContractService {
	long doCalculationEagerly(Long facility);

	long doCalculationLazily(Long facility);
}
