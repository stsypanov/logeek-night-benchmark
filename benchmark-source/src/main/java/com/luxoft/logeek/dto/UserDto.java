package com.luxoft.logeek.dto;

public class UserDto {
	private Long userId;

	public UserDto(Long userId) {
		this.userId = userId;
	}

	public Long getUserId() {
		return userId;
	}
}