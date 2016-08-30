package com.cst.dao.role.dao;

import com.cst.dao.core.dao.BaseDao;
import com.cst.dao.role.entity.Role;

public interface RoleDao extends BaseDao<Role> {
	
	//删除该角色对于的所有权限
	public void deleteRolePrivilegeByRoleId(String roleId);
	
}
