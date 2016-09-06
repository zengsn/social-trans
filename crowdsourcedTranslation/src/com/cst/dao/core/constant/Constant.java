package com.cst.dao.core.constant;

import java.util.HashMap;
import java.util.Map;

public class Constant {
	
	//系统中用户在session中的标识符
	public static String USER = "SYS_USER";
		
	/*----------------------系统权限集合--------------------------*/
	public static String PRIVILEGE_YHGL = "yhgl"; //用户管理
	public static String PRIVILEGE_JSGL= "jsgl"; //角色管理
	public static String PRIVILEGE_RWGL = "rwgl"; //任务管理
	

	public static Map<String, String> PRIVILEGE_MAP;
	static {
		PRIVILEGE_MAP = new HashMap<String, String>();
		PRIVILEGE_MAP.put(PRIVILEGE_YHGL, "用户管理");
		PRIVILEGE_MAP.put(PRIVILEGE_JSGL, "角色管理");
		PRIVILEGE_MAP.put(PRIVILEGE_RWGL, "任务管理");
		
	}
}
