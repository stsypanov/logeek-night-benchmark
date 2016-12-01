package com.luxoft.logeek.dto;

public class LtavCashFlowDetailsDTO {
	private final int imoNumber;
	private final Long id;

	public LtavCashFlowDetailsDTO(int imoNumber, long id) {
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
