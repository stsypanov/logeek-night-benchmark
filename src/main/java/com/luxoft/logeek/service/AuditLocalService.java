package com.luxoft.logeek.service;

import com.luxoft.logeek.entity.AuditAction;

import java.util.Set;

/**
 * Created by Сергей on 12.02.2017.
 */
public interface AuditLocalService {

	void auditChanges(Set<AuditAware> inserts, Set<AuditAware> updates, Set<AuditAware> deletes);

	void audit(AuditAware auditAware, AuditAction action);

}
