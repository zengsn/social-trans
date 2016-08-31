package com.cst.dao.user.dao;

import java.util.List;

import com.cst.dao.core.dao.BaseDao;
import com.cst.dao.user.entity.User;
import com.cst.dao.user.entity.UserRole;

public interface UserDao extends BaseDao<User> {

	List<User> findUserByAccountAndId(String account, String id);
	
	//保存用户角色
	public void saveUserRole(UserRole userRole);
	
	//根据用户id删除用户所有的角色
	public void deleteUserRoleByUserId(String id);

	public List<UserRole> getUserRolesByUserId(String id);

}
