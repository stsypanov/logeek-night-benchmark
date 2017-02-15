package com.luxoft.logeek.service;

import com.luxoft.logeek.entity.AuditTrailEntity;
import com.luxoft.logeek.repository.AuditTrailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Component
public class AuditSaveServiceImpl implements AuditSaveService {

    private final AuditTrailRepository auditTrailRepository;

    @Autowired
    public AuditSaveServiceImpl(AuditTrailRepository auditTrailRepository) {
        this.auditTrailRepository = auditTrailRepository;
    }


    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void save(Collection<AuditTrailEntity> auditTrails) {
        List<AuditTrailEntity> result = new ArrayList<>();
        auditTrails.forEach(auditTrail -> {
            result.add(auditTrail);
//			result.addAll(auditTrail.getChildren());
        });
        auditTrailRepository.save(result);
    }

}
