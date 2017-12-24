package com.luxoft.logeek.service.impl;

import com.luxoft.logeek.dto.AuditDto;
import com.luxoft.logeek.entity.AuditEntity;
import com.luxoft.logeek.service.AuditLocalService;
import com.luxoft.logeek.service.Saver;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Component
@Transactional
@RequiredArgsConstructor
public class AuditLocalServiceImpl implements AuditLocalService {
    private final Saver saver;

    @Override
    public void auditChanges(Collection<AuditDto> inserts) {
        inserts.forEach(insert -> {
            Collection<AuditEntity> auditTrails = toAuditEntity(insert);
            saver.save(auditTrails);
        });
    }

    @Override
    public void auditChangesEffectively(Collection<AuditDto> inserts) {
        List<AuditEntity> allItems = inserts.stream()
                .map(this::toAuditEntity)
                .flatMap(Collection::stream)
                .collect(toList());

        saver.save(allItems);
    }

    private Collection<AuditEntity> toAuditEntity(AuditDto auditDto) {
        return Collections.singletonList(new AuditEntity(auditDto.getId()));
    }
}
