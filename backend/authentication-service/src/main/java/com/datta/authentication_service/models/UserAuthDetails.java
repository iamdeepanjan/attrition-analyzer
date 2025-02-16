package com.datta.authentication_service.models;

public class UserAuthDetails {

	private Long userId;
	private String username;
	private String password;
	private String name;

	public UserAuthDetails() {
	}

	public UserAuthDetails(Long userId, String username, String password, String name) {
		super();
		this.userId = userId;
		this.username = username;
		this.password = password;
		this.name = name;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}