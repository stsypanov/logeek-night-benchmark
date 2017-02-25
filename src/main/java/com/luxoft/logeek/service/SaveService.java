package com.luxoft.logeek.service;

import com.luxoft.logeek.entity.AuditEntity;

import java.util.List;

/**
 * Created by Сергей on 16.01.2017.
 */
public interface SaveService {
	void save(List<AuditEntity> entities);
}
