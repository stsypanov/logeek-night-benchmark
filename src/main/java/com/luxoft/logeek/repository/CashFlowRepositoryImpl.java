package com.luxoft.logeek.repository;

import com.luxoft.logeek.entity.ContractEntity;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class CashFlowRepositoryImpl extends BaseDao implements CashFlowRepository {

	@Override
	public List<ContractEntity> findEffectively(Collection<Long> checkedIds) {

		if (checkedIds.isEmpty()) return Collections.emptyList();

		String query = "select e from SomeEntity e " +
					   " where e.property = true " +
					   "   and e.id in :checkedIds";

		return em.createQuery(query, ContractEntity.class)
				.setParameter("checkedIds", checkedIds)
				.getResultList();
	}

	@Override
	public List<ContractEntity> findIneffectively(Collection<Long> checkedIds) {
		String query = "select e from SomeEntity e " +
					   " where e.property = true ";

		List<ContractEntity> entities = em
				.createQuery(query, ContractEntity.class)
				.getResultList();

		return entities.stream()
				.filter(entity -> checkedIds.contains(entity.getId()))
				.collect(Collectors.toList());
	}
}
