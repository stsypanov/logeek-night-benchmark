package com.luxoft.logeek.service.impl;

import com.luxoft.logeek.entity.AuditEntity;
import com.luxoft.logeek.repository.AuditRepository;
import com.luxoft.logeek.service.Saver;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Component
@RequiredArgsConstructor
public class SaverImpl implements Saver {
    private final AuditRepository repository;

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void save(Collection<AuditEntity> auditTrails) {
        List<AuditEntity> result = new ArrayList<>();
        auditTrails.forEach(auditTrail -> {
            result.add(auditTrail);
			result.addAll(auditTrail.getChildren());
        });
        repository.saveAll(result);
    }

}
