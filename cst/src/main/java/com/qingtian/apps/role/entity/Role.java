package com.qingtian.apps.role.entity;
import java.io.Serializable;

   /**
    * role 实体类
    * Wed Mar 08 16:45:14 CST 2017 
    */ 


public class Role implements Serializable {
	/**
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
	*数据的有效性，'1'有效，'0'无效 用于逻辑删除
	*/
	private String disabled;

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
	public void setDisabled(String disabled){
	this.disabled=disabled;
	}
	public String getDisabled(){
		return disabled;
	}
}

