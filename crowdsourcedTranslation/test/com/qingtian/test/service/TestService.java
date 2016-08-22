package com.qingtian.test.service;

import java.io.Serializable;

import com.qingtian.test.entity.Person;



public interface TestService {
		
	public void say();
	
	//保存人员
	public void save(Person person);
	
	//根据id查询人员
	public Person findPerson(Serializable id);
}
