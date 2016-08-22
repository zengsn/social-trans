package com.qingtian.test.dao.impl;

import java.io.Serializable;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.qingtian.test.dao.TestDao;
import com.qingtian.test.entity.Person;

public class TestDaoImpl extends HibernateDaoSupport implements TestDao {
	
	
	@Override
	public void save(Person person) {
		// TODO Auto-generated method stub
		getHibernateTemplate().save(person);
	}

	@Override
	public Person findPerson(Serializable id) {
		// TODO Auto-generated method stub
		return getHibernateTemplate().get(Person.class, id);
	}

}
