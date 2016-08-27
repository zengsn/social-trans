package com.cst.dao.user.service;

import java.io.Serializable;
import java.rmi.ServerException;
import java.util.List;

import com.cst.dao.user.entity.User;

public interface UserService {
	
	//新增
	public void save(User user);
	//更新
	public void update(User user);
	//根据id删除
	public void delete(Serializable id);
	//根据id查找
	public User findObjectById(Serializable id);
	//查找列表
	public List<User> findObjects() throws ServerException;
	
	/**
	 * 根据用户的帐号和id来查询用户
	 * @param account 帐号
	 * @param id id
	 * @return 用户列表
	 */
	public List<User> findUserByAccountAndId(String account, String id);
}
