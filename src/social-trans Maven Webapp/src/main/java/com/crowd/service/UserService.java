package com.crowd.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crowd.bean.User;
import com.crowd.dao.UserDao;

@Service("userService") 
public class UserService {
 
	   @Autowired
	    private UserDao userDao;  
	 //添加用户
	    public int insertUser(User user){
	    	return this.userDao.insertUser(user);
	    }
	  //根据account获取用户userId
	    public String getUserIdByAccount(String account){
	    	return userDao.getUserIdByAccount(account);
	    }
	  //删除用户
	    public int deleteUserByUserId(String userId){
	    	return userDao.deleteUserByUserId(userId);
	    }
	  //更改用户资料
	    public int UpdateUserByUserId(User user){
	    	return userDao.UpdateUserByUserId(user);
	    }
	  //用户登录
	    public boolean Userlogin(String account,String password){
	    	User user = userDao.Userlogin(account, password);
		        if(user == null){
		            //帐号已存在
		            return false;
		        }else {
		            //帐号不存在
		            return true;
		        }
	    }
	  //检测用户的唯一性
	   public boolean verifyAccount(String account){
	        List<User> list = userDao.verifyAccount(account);
	        if(list!= null && list.size()>0){
	            //帐号已存在
	            return false;
	        }else {
	            //帐号不存在
	            return true;
	        }
	   }  
	   //根据userId来查询用户信息 
	   public User selectUserById(String userId){
		   return userDao.selectUserById(userId);
	   }
	   public List<User> selectAllUser(){
		   return userDao.selectAllUser();
	   }
}

