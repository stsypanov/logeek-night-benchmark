package com.luxoft.logeek.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CPY")
public class CpyEntity {
	
	@Id
	private Long id;
	
	@Column(name="pd_rating")
	private boolean pdRating;

	public boolean hasBadPdRating() {
		return pdRating;
	}
}
