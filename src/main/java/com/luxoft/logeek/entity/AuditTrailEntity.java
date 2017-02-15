package com.luxoft.logeek.entity;

import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Сергей on 12.02.2017.
 */
@Getter
@Entity
@Table(name = "ATE")
public class AuditTrailEntity {

	@Id
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PARENT_ID")
	private AuditTrailEntity parent;

	@OneToMany(mappedBy = "parent")
	private Collection<AuditTrailEntity> children = new ArrayList<>();

	protected AuditTrailEntity() {
	}

	public AuditTrailEntity(Long id) {
		this.id = id;
	}
}
