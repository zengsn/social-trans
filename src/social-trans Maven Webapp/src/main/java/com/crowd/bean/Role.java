package com.crowd.bean;

public class Role {
	/*
	*角色id
	*/
	private String roleId;

	/**
	*角色名称
	*/
	private String rolename;

	/**
	*说明
	*/
	private String description;

	/**
	*角色等级
	*/
	private int rolelevel;

	public void setRoleId(String roleId){
	this.roleId=roleId;
	}
	public String getRoleId(){
		return roleId;
	}
	public void setRolename(String rolename){
	this.rolename=rolename;
	}
	public String getRolename(){
		return rolename;
	}
	public void setDescription(String description){
	this.description=description;
	}
	public String getDescription(){
		return description;
	}
	public int getRolelevel() {
		return rolelevel;
	}
	public void setRolelevel(int rolelevel) {
		this.rolelevel = rolelevel;
	}
	
}
