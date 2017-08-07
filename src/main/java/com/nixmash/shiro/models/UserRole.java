package com.nixmash.shiro.models;

import java.io.Serializable;

public class UserRole implements Serializable {
	/**
	 *
	 */
	private static final long serialVersionUID = -5401510492152424643L;

	private Integer id;
	private String roleName;
	private String username;

	public UserRole() {
	}

	public String getUsername() {
		return username;
	}

	public Integer getId() {
		return id;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setUsername(String email) {
		this.username = username;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
}
