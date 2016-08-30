package com.cst.dao.user.entity;

import java.io.Serializable;

import com.cst.dao.role.entity.Role;



public class UserRoleId implements Serializable {
		
	private Role role;
	private String userId;
	
	
	
	
	public UserRoleId() {
		
	}
	public UserRoleId(Role role, String userId) {
		this.role = role;
		this.userId = userId;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	
}	
