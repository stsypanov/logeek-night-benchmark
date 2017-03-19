package com.luxoft.logeek.service;

import com.luxoft.logeek.dto.AuditDto;
import com.luxoft.logeek.entity.AuditAction;
import com.luxoft.logeek.entity.AuditEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
@Transactional
public class AuditLocalServiceImpl implements AuditLocalService {
	private final Saver saver;

	@Autowired
	public AuditLocalServiceImpl(Saver saver) {
		this.saver = saver;
	}

	@Override
	public void auditChanges(Collection<AuditDto> inserts, Collection<AuditDto> updates, Collection<AuditDto> deletes) {
		inserts.forEach(insert -> audit(insert, AuditAction.INSERT));
		updates.forEach(update -> audit(update, AuditAction.UPDATE));
		deletes.forEach(delete -> audit(delete, AuditAction.DELETE));
	}

	@Override
	public void auditChangesEffectively(Collection<AuditDto> inserts, Collection<AuditDto> updates, Collection<AuditDto> deletes) {
		Stream<AuditDto> insertStream = inserts.stream();
		Stream<AuditDto> updatesStream = updates.stream();
		Stream<AuditDto> deleteStream = deletes.stream();

		List<AuditEntity> allItems = Stream.of(insertStream, updatesStream, deleteStream)
				.flatMap(Function.identity())
				.map(this::splitToAuditTrails)
				.flatMap(Collection::stream)
				.collect(Collectors.toList());

		saver.save(allItems);
	}

	private void audit(AuditDto auditAware, AuditAction action) {
		Collection<AuditEntity> auditTrails = splitToAuditTrails(auditAware);
		saver.save(auditTrails);
	}

	private Collection<AuditEntity> splitToAuditTrails(AuditDto auditDto) {
		return Collections.singletonList(new AuditEntity(auditDto.getId()));
	}
}
