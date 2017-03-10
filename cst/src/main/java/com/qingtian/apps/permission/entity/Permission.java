package com.qingtian.apps.permission.entity;
import java.io.Serializable;

   /**
    * permission 实体类
    * Thu Mar 09 09:47:52 CST 2017 
    */ 


public class Permission implements Serializable {
	/**
	*权限id
	*/
	private String permissionId;

	/**
	*权限名称
	*/
	private String permissionName;

	/**
	*permissionName别名
	*/
	private String sname;

	/**
	*说明
	*/
	private String description;

	/**
	*聚合标识
	*/
	private String tag;

	/**
	*P_menu对应的menuCode
	*/
	private String menuCode;

	/**
	*'禁用，1启用 0禁用',
	*/
	private String disabled;

	public void setPermissionId(String permissionId){
	this.permissionId=permissionId;
	}
	public String getPermissionId(){
		return permissionId;
	}
	public void setPermissionName(String permissionName){
	this.permissionName=permissionName;
	}
	public String getPermissionName(){
		return permissionName;
	}
	public void setSname(String sname){
	this.sname=sname;
	}
	public String getSname(){
		return sname;
	}
	public void setDescription(String description){
	this.description=description;
	}
	public String getDescription(){
		return description;
	}
	public void setTag(String tag){
	this.tag=tag;
	}
	public String getTag(){
		return tag;
	}
	public void setMenuCode(String menuCode){
	this.menuCode=menuCode;
	}
	public String getMenuCode(){
		return menuCode;
	}
	public void setDisabled(String disabled){
	this.disabled=disabled;
	}
	public String getDisabled(){
		return disabled;
	}
}

