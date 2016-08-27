package com.cst.dao.user.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Query;

import com.cst.dao.core.dao.impl.BaseDaoImpl;
import com.cst.dao.user.dao.UserDao;
import com.cst.dao.user.entity.User;

public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<User> findUserByAccountAndId(String account, String id) {
		String sql = "FROM user WHERE account = ?";
		//判断id是否存在。若是增加页面跳转，则不存在id
		if (StringUtils.isNotBlank(id)) {
			sql += " AND id !=?";
		}
		Query query = getSession().createQuery(sql);
		query.setParameter(0, account);
		if (StringUtils.isNotBlank(id)) {
			query.setParameter(1, id);
		}
		
		return query.list();
	}

}
