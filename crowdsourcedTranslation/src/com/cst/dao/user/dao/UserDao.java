package com.cst.dao.user.dao;

import java.util.List;

import com.cst.dao.core.dao.BaseDao;
import com.cst.dao.user.entity.User;

public interface UserDao extends BaseDao<User> {

	List<User> findUserByAccountAndId(String account, String id);

}
