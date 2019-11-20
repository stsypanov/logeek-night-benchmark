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
        SimpleEntity entity = simpleRepository.findById(id).orElseThrow(NullPointerException::new);
        entity.setName(newName);
        return entity;
    }

    @Override
    public SimpleEntity modifyCallingSave(Long id, String newName) {
        SimpleEntity entity = simpleRepository.findById(id).orElseThrow(NullPointerException::new);
        entity.setName(newName);
        return simpleRepository.save(entity);
    }

    @Override
    public SimpleEntity modifyWithoutCallingSaveFindByName(String name, String newName) {
        SimpleEntity entity = simpleRepository.findByName(name);
        entity.setName(newName);
        return entity;
    }
}
