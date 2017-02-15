package com.luxoft.logeek.service;

import com.luxoft.logeek.entity.AuditTrailEntity;

import java.util.Collection;

public interface AuditSaveService {

    void save(Collection<AuditTrailEntity> auditTrails);
}
