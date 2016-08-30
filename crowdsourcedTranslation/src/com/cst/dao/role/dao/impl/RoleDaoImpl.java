package com.cst.dao.role.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;

import com.cst.dao.core.dao.impl.BaseDaoImpl;
import com.cst.dao.role.dao.RoleDao;
import com.cst.dao.role.entity.Role;

public class RoleDaoImpl extends BaseDaoImpl<Role> implements RoleDao {
	
	//删除该角色对于的所有权限
	@Override
	public void deleteRolePrivilegeByRoleId(String roleId) {
		//删除角色权限表单中对应id的角色的角色id
		Query query = getSession().createQuery("DELETE FROM RolePrivilege WHERE id.role.roleId=?");
		//设置roleId参数
		query.setParameter(0, roleId);
		//执行更新操作
		query.executeUpdate();
	}

	

}
