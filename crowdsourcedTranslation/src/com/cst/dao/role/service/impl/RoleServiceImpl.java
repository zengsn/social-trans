package com.cst.dao.role.service.impl;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cst.dao.role.dao.RoleDao;
import com.cst.dao.role.entity.Role;
import com.cst.dao.role.service.RoleService;

@Service("roleService")
public class RoleServiceImpl implements RoleService {

	@Resource
	private RoleDao roleDao;

	@Override
	public void save(Role role) {
		// TODO Auto-generated method stub
		roleDao.save(role);
	}

	@Override
	public void update(Role role) {
		// TODO Auto-generated method stub
		// 1、删除该角色对于的所有权限
		roleDao.deleteRolePrivilegeByRoleId(role.getRoleId());
		// 2、更新角色及权限
		roleDao.update(role);
	}

	@Override
	public void delete(Serializable id) {
		// TODO Auto-generated method stub
		roleDao.delete(id);
	}

	@Override
	public Role findObjectById(Serializable id) {
		// TODO Auto-generated method stub
		return roleDao.findObjectById(id);
	}

	@Override
	public List<Role> findObjects() {
		// TODO Auto-generated method stub
		return roleDao.findObjects();
	}

}
