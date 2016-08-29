package com.cst.dao.role.entity;

import java.io.Serializable;
import java.util.Set;



public class Role implements Serializable {
	
		private String roleId;//用户
		private String name;//角色名称
		private String state;//状态
		private Set<RolePrivilege> rolePrivileges;//中间表的主键
		
		//角色状态
		public static String ROLE_STATE_VALID = "1";//有效
		public static String ROLE_STATE_INVALID = "0";//无效
		
		public Role() {
		}
		public Role(String roleId, String name, String state,
				Set<com.cst.dao.role.entity.RolePrivilege> rolePrivileges) {
			this.roleId = roleId;
			this.name = name;
			this.state = state;
			this.rolePrivileges = rolePrivileges;
		}
		public String getRoleId() {
			return roleId;
		}
		public void setRoleId(String roleId) {
			this.roleId = roleId;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getState() {
			return state;
		}
		public void setState(String state) {
			this.state = state;
		}
		public Set<RolePrivilege> getRolePrivileges() {
			return rolePrivileges;
		}
		public void setRolePrivileges(Set<RolePrivilege> rolePrivileges) {
			this.rolePrivileges = rolePrivileges;
		}
		
		
}
