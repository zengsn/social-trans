package com.qingtian.test.action;

import javax.annotation.Resource;

import com.opensymphony.xwork2.ActionSupport;
import com.qingtian.test.service.TestService;



public class TestAciton extends ActionSupport{
		
	@Resource
	private TestService testService;
	
	@Override
	public String execute() throws Exception {
		testService.say();
		return SUCCESS;
	}
}
