package com.luxoft.logeek.service;

import com.luxoft.logeek.entity.AuditEntity;
import com.luxoft.logeek.repository.AuditRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Component
public class SaverImpl implements Saver {

    private final AuditRepository repository;

    @Autowired
    public SaverImpl(AuditRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void save(Collection<AuditEntity> auditTrails) {
        List<AuditEntity> result = new ArrayList<>();
        auditTrails.forEach(auditTrail -> {
            result.add(auditTrail);
			result.addAll(auditTrail.getChildren());
        });
        repository.save(result);
    }

}
