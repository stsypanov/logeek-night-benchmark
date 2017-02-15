package com.luxoft.logeek.service;

import com.luxoft.logeek.entity.AuditAction;
import com.luxoft.logeek.entity.AuditTrailEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@Component
@Transactional
public class AuditLocalServiceImpl implements AuditLocalService {
	private final AtomicLong sequence;
	private final AuditSaveService auditSaveService;

	@Autowired
	public AuditLocalServiceImpl(AuditSaveService auditSaveService) {
		this.auditSaveService = auditSaveService;
		sequence = new AtomicLong(1L);
	}

	@Override
	public void auditChanges(Set<AuditAware> inserts, Set<AuditAware> updates, Set<AuditAware> deletes) throws RuntimeException{
		inserts.forEach(insert -> audit(insert, AuditAction.INSERT));
		updates.forEach(update -> audit(update, AuditAction.UPDATE));
		deletes.forEach(delete -> audit(delete, AuditAction.DELETE));
	}

	@Override
	public void audit(AuditAware auditAware, AuditAction action) {
		Collection<AuditTrailEntity> auditTrails = splitToAuditTrails(auditAware);
		auditSaveService.save(auditTrails);
	}


	private Collection<AuditTrailEntity> splitToAuditTrails(AuditAware auditAware) {
		return Collections.singletonList(new AuditTrailEntity(sequence.incrementAndGet()));
	}
}
