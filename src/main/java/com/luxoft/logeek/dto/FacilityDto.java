package com.luxoft.logeek.dto;

public class FacilityDto {
	
	private Long id;
	private boolean isMdrFacility;
	private Long cpyId;
	private boolean depositFacility;

	public Long getId() {
		return id;
	}

	public boolean isMdrFacility() {
		return isMdrFacility;
	}

	public Long getCpyId() {
		return cpyId;
	}

	public void setCpyId(Long cpyId) {
		this.cpyId = cpyId;
	}

	public boolean isDepositFacility() {
		return depositFacility;
	}

	public void setDepositFacility(boolean depositFacility) {
		this.depositFacility = depositFacility;
	}
}
