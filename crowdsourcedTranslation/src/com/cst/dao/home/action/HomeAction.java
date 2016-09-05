package com.cst.dao.home.action;

import com.opensymphony.xwork2.ActionSupport;

public class HomeAction extends ActionSupport {
	
	
	public String frame(){
		return "frame";
	}
	
	//跳转到顶部
	public String top(){
		return "top";
	}
	//跳转到左边菜单
	public String left(){
		return "left";
	}
}
