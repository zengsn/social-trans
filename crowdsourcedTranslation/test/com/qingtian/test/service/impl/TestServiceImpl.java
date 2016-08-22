package com.qingtian.test.service.impl;

import java.io.Serializable;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.qingtian.test.dao.TestDao;
import com.qingtian.test.entity.Person;
import com.qingtian.test.service.TestService;

@Service("testService")
public class TestServiceImpl implements TestService {

	@Override
	public void say() {
		// TODO Auto-generated method stub
		System.out.println("this is the TestService");
	}
	
	@Resource
	private TestDao testDao;
	
	public void save(Person person){
		testDao.save(person);
	}

	@Override
	public Person findPerson(Serializable id) {
		//testDao.save(new Person("¬Ì»ÿ"));
		return testDao.findPerson(id);
	}
}
