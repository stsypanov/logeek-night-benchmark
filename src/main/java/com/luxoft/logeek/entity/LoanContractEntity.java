package com.luxoft.logeek.entity;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "LOAN_CONTRACT")
@Getter
public class LoanContractEntity {
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

	LoanContractEntity() {
	}

	public LoanContractEntity(long id, long facility, double compensation, double deposit, double outstanding) {
		this.id = id;
		this.facility = facility;
		this.compensation = new BigDecimal(compensation);
		this.deposit = new BigDecimal(deposit);
		this.outstanding = new BigDecimal(outstanding);
	}
}
