package com.luxoft.logeek.utils;

public enum  DatasetStatus {
	Draft("DRAFT"),
	Pending("PENDING"),
	Approved("APPROVED"),
	Stored("STORED");
	
	private String code;

	DatasetStatus(String code) {
		this.code = code;
	}

	public static DatasetStatus find(String code) {
		if (code == null) return null;
		
		for (DatasetStatus status : values()) {
			if (status.code.equals(code)) {
				return status;
			}
		}
		return null;
	}
}
