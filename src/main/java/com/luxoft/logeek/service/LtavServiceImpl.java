package com.luxoft.logeek.service;

import com.luxoft.logeek.dto.CashFlowDTO;
import com.luxoft.logeek.entity.AuditEntity;
import com.luxoft.logeek.repository.CashFlowRepository;
import com.luxoft.logeek.repository.LoanContractRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.luxoft.logeek.service.LtavCalculatorValidator.isValidDto;

@SuppressWarnings("ALL")
@Component
public class LtavServiceImpl implements LtavService {
	private final CashFlowServiceLocal localService;
	private final CashFlowRepository cashFlowRepository;
	private final LoanContractRepository jpaRepository;
	private SaveService saveService;

	@Autowired
	public LtavServiceImpl(CashFlowServiceLocal localService, CashFlowRepository cashFlowRepository, LoanContractRepository jpaRepository) {
		this.localService = localService;
		this.cashFlowRepository = cashFlowRepository;
		this.jpaRepository = jpaRepository;
	}

	@Override
	public void createCashFlow(CashFlowDTO dto) {
		validate(dto);

		localService.createCashFlow(dto);
	}

	private void validate(CashFlowDTO dto) {
		boolean inValidIMO = isValidDto(dto);

		if (!inValidIMO) {
			throw new IllegalArgumentException("IMO number is not valid");
		}
	}

	private boolean validateIMO(Integer imoNumber) {
		return imoNumber % 2 == 0;
	}

	private int validateLtavCashFlowDetailsCount(Integer imoNumber) {
		return imoNumber - 3;
	}

	public void saveChanges(Set<AuditAware> items) {
		items.stream().filter(AuditAware::isAuditable).forEach(this::audit);
	}

	//todo must be in separate benchmark
	public void audit(AuditAware entity) {
		List<AuditEntity> entities = splitToAuditTrails(entity);

		saveService.save(entities); //DB accessed here

		entity.getModifiedColumns().clear();
	}

	private List<AuditEntity> splitToAuditTrails(AuditAware entity) {
		return null;
	}

	public void saveChangesEffectively(Set<AuditAware> inserts) {
		List<AuditEntity> entities = inserts.stream()
				.filter(AuditAware::isAuditable)
				.map(this::convertIntoAuditEntities)
				.flatMap(List::stream)
				.collect(Collectors.toList());

		saveService.save(entities);
	}

	public List<AuditEntity> convertIntoAuditEntities(AuditAware entity) {
		List<AuditEntity> entities = splitToAuditTrails(entity);

		entity.getModifiedColumns().clear();

		return entities;
	}


}
