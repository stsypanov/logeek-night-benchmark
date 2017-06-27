package com.luxoft.logeek.entity;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

@Getter
@Entity
@Table(name = "CONTRACT")
public class ContractEntity {
	@Id
	private long id;

	@Column
	long facility;

	@Column
	BigDecimal compensation;

	@Column
	BigDecimal deposit;

	@Column
	BigDecimal outstanding;

	@Column
	boolean property;

	protected ContractEntity() {
	}

	public ContractEntity(long id, long facility, double compensation, double deposit, double outstanding) {
		this.id = id;
		this.facility = facility;
		this.compensation = new BigDecimal(compensation);
		this.deposit = new BigDecimal(deposit);
		this.outstanding = new BigDecimal(outstanding);
	}
}
