package com.qingtian.helptranslation.Bean;

import cn.bmob.v3.BmobUser;

public class RegisterBean extends BmobUser{
	private String user_name;
	private String password;
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
