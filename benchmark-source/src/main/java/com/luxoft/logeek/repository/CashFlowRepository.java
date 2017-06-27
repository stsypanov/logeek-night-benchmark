package com.luxoft.logeek.repository;

import com.luxoft.logeek.entity.ContractEntity;

import java.util.Collection;
import java.util.List;

public interface CashFlowRepository {

	List<ContractEntity> findEffectively(Collection<Long> checkedIds);

	List<ContractEntity> findIneffectively(Collection<Long> checkedIds);
}
