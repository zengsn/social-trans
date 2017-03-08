package com.qingtian.apps.permission.entity;
import org.apache.ibatis.type.Alias;

import java.io.Serializable;

   /**
    * permission 实体类
    * Wed Mar 08 17:34:27 CST 2017 
    */ 

@Alias("Permission")
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

