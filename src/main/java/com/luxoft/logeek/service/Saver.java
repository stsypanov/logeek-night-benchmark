package com.luxoft.logeek.service;

import com.luxoft.logeek.entity.AuditEntity;

import java.util.Collection;

public interface Saver {

    void save(Collection<AuditEntity> auditTrails);
}
