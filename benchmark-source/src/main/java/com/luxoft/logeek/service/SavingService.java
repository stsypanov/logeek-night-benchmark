package com.luxoft.logeek.service;

import com.luxoft.logeek.entity.SimpleEntity;

public interface SavingService {

    SimpleEntity modifyWithoutCallingSave(Long id, String newName);

    SimpleEntity modifyCallingSave(Long id, String newName);
}
