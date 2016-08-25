package com.cst.dao.core.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.Query;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.cst.dao.core.dao.BaseDao;

public abstract class BaseDaoImpl<T> extends HibernateDaoSupport implements
		BaseDao<T> {
	
	Class<T> clazz;
	
	
	public BaseDaoImpl() {
		//利用反射获取BaseDaoImpl<User>
		ParameterizedType pt =  (ParameterizedType)this.getClass().getGenericSuperclass();
		clazz = (Class<T>)pt.getActualTypeArguments()[0];
	}
	
	@Override
	public void save(T entity) {
		// TODO Auto-generated method stub
		getHibernateTemplate().save(entity);
	}

	@Override
	public void update(T entity) {
		// TODO Auto-generated method stub 
		getHibernateTemplate().update(entity);
	}

	@Override
	public void delete(Serializable id) {
		// TODO Auto-generated method stub
		getHibernateTemplate().delete(findObjectById(id));
	}

	@Override
	public T findObjectById(Serializable id) {
		// TODO Auto-generated method stub
		return getHibernateTemplate().get(clazz, id);
	}

	@Override
	public List<T> findObjects() {
		// TODO Auto-generated method stub
		//从表名获取query
		Query query = getSession().createQuery("FROM " + clazz.getSimpleName());
		return query.list();
	}

}
