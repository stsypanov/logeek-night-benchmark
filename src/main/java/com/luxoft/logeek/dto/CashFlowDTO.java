package com.luxoft.logeek.dto;

public class CashFlowDTO {
	private final int imoNumber;
	private final Long id;

	public CashFlowDTO(int imoNumber, long id) {
		this.imoNumber = imoNumber;
		this.id = id;
	}


	public int getImoNumber() {
		return imoNumber;
	}

	public Long getId() {
		return id;
	}
}
