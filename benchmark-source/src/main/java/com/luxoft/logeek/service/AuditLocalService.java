package com.luxoft.logeek.service;

import com.luxoft.logeek.dto.AuditDto;

import java.util.Collection;

public interface AuditLocalService {
	void auditChanges(Collection<AuditDto> inserts);

	void auditChangesEffectively(Collection<AuditDto> inserts);
}
