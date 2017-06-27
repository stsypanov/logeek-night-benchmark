package com.luxoft.logeek.service.impl;

import com.luxoft.logeek.dto.AuditDto;
import com.luxoft.logeek.entity.AuditAction;
import com.luxoft.logeek.entity.AuditEntity;
import com.luxoft.logeek.service.AuditLocalService;
import com.luxoft.logeek.service.Saver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toList;

@Component
@Transactional
public class AuditLocalServiceImpl implements AuditLocalService {
	private final Saver saver;

	@Autowired
	public AuditLocalServiceImpl(Saver saver) {
		this.saver = saver;
	}

	@Override
	@Transactional
	public void auditChanges(Collection<AuditDto> inserts, Collection<AuditDto> updates, Collection<AuditDto> deletes) {
		inserts.forEach(insert -> audit(insert, AuditAction.INSERT));
		updates.forEach(update -> audit(update, AuditAction.UPDATE));
		deletes.forEach(delete -> audit(delete, AuditAction.DELETE));
	}

	@Override
	public void auditChangesEffectively(Collection<AuditDto> inserts, Collection<AuditDto> updates, Collection<AuditDto> deletes) {
		Stream<AuditEntity> insertStream = inserts.stream()
				.map(auditDto -> toAuditEntity(auditDto, AuditAction.INSERT))
				.flatMap(Collection::stream);

		Stream<AuditEntity> updatesStream = updates.stream()
				.map(auditDto -> toAuditEntity(auditDto, AuditAction.UPDATE))
				.flatMap(Collection::stream);

		Stream<AuditEntity> deleteStream = deletes.stream()
				.map(auditDto -> toAuditEntity(auditDto, AuditAction.DELETE))
				.flatMap(Collection::stream);

		List<AuditEntity> allItems = Stream.of(insertStream, updatesStream, deleteStream).flatMap(identity()).collect(toList());

		saver.save(allItems);
	}

	private void audit(AuditDto auditAware, AuditAction action) {
		Collection<AuditEntity> auditTrails = toAuditEntity(auditAware, action);
		saver.save(auditTrails);
	}

	private Collection<AuditEntity> toAuditEntity(AuditDto auditDto, AuditAction action) {
		return Collections.singletonList(new AuditEntity(auditDto.getId()));
	}
}
