package com.nixmash.shiro.models;

import java.io.Serializable;

public class User implements Serializable {

	private static final long serialVersionUID = -4746333924452133573L;

	private Long user_id;
	private String username;
	private String email;
	private String password;

	public User() {
	}

	public User(String username, String email, String password) {
		this.username = username;
		this.email = email;
		this.password = password;
	}

	public Long getUserId() {
		return user_id;
	}

	public void setUserId(Long user_id) {
		this.user_id = user_id;
	}

	public String getEmail() {
		return email;
	}


	public String getPassword() {
		return password;
	}

	public String getUsername() {
		return username;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setUsername(String username) {
		this.username = username;
	}


	@Override
	public String toString() {
		return "User{" +
				"user_id=" + user_id +
				", username='" + username + '\'' +
				", email='" + email + '\'' +
				", password='" + password + '\'' +
				'}';
	}
}
