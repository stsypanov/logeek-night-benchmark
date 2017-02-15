package com.luxoft.logeek.service;

import java.util.List;

/**
 * Created by Сергей on 16.01.2017.
 */
public interface AuditAware {
	static boolean isAuditable(AuditAware auditAware) {
		return false;
	}

	List getModifiedColumns();

}
