package com.luxoft.logeek.entity;

import javax.persistence.*;

@Entity
@Table(name = "FACILITY")
public class FacilityEntity {
	@Id
	private Long id;

	@JoinColumn(name = "cpy")
	@ManyToOne(fetch = FetchType.LAZY)
	private CpyEntity cpy;

	public CpyEntity getCpy() {
		return cpy;
	}

	
}
