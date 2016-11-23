package com.luxoft.logeek.entity;

import lombok.Getter;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "USERS")
public class User {

	@Id
	private long id;

	@Column
	private String name;

	@Column
	private String password;

	@Column
	private String email;

	@Column
	private String role;

	User() {
	}

	public User(long id, String name, String password, String email, String role) {
		this.id = id;
		this.name = name;
		this.password = password;
		this.email = email;
		this.role = role;
	}
}
