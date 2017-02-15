package com.luxoft.logeek.entity;

import com.luxoft.logeek.service.AuditAware;

import java.util.Collections;
import java.util.List;

/**
 * Created by Сергей on 16.01.2017.
 */
public class AuditEntity implements AuditAware {
	@Override
	public List getModifiedColumns() {
		return Collections.emptyList();
	}
}
