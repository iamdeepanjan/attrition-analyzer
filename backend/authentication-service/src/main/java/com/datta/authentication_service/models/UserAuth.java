package com.datta.authentication_service.models;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="authdetails")
public class UserAuth {

	@Id
	private String username;
    private String password;
    private String name;
    private Long userId;
    
    public UserAuth() {}

	public UserAuth(String username, String password, String name, Long userId) {
		super();
		this.username = username;
		this.password = password;
		this.name = name;
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

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}
   
}
