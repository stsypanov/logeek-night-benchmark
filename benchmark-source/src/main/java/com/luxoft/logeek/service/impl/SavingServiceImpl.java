package com.luxoft.logeek.service.impl;

import com.luxoft.logeek.entity.SimpleEntity;
import com.luxoft.logeek.repository.SimpleRepository;
import com.luxoft.logeek.service.SavingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
@RequiredArgsConstructor
public class SavingServiceImpl implements SavingService {
    private final SimpleRepository simpleRepository;

    @Override
    public SimpleEntity modifyWithoutCallingSave(Long id, String newName) {
        SimpleEntity entity = simpleRepository.findOne(id);
        entity.setName(newName);
        return entity;
    }

    @Override
    public SimpleEntity modifyCallingSave(Long id, String newName) {
        SimpleEntity entity = simpleRepository.findOne(id);
        entity.setName(newName);
        return simpleRepository.save(entity);
    }
}