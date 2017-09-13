package com.crowd.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.crowd.bean.User;
@Repository("UserDao")
public interface UserDao {	
		//添加用户
		int insertUser(User user);
		//更改用户数据disabled
		//int updateUserDisabledByUserId(String userId);
		//根据account获取用户userId
		String getUserIdByAccount(String account);
		//删除用户
		int deleteUserByUserId(String userId);
		//更改用户资料
		int UpdateUserByUserId(User user);
		//查找所有用户
		List<User> selectAllUser();
		//用户登录
		User Userlogin(@Param("account")String account,@Param("password")String password);
		//检测用户的唯一性
		List<User> verifyAccount(String account);
		 //根据userId来查询用户信息  
	    User selectUserById(String userId);	 
}
