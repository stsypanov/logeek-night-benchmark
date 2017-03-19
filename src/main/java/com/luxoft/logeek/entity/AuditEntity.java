package com.luxoft.logeek.entity;

import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

@Getter
@Entity
@Table(name = "ATE")
public class AuditEntity {

	@Id
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PARENT_ID")
	private AuditEntity parent;

	@OneToMany(mappedBy = "parent")
	private Collection<AuditEntity> children = new ArrayList<>();

	protected AuditEntity() {
	}

	public AuditEntity(Long id) {
		this.id = id;
	}

	public Collection<? extends AuditEntity> getChildren() {
		return Collections.emptyList();
	}
}
