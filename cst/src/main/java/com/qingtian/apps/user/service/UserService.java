package com.qingtian.apps.user.service;


import com.qingtian.apps.user.entity.User;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.workSpace.utils.RandomGUID;

import java.util.List;

/**
 * Created by Administrator on 2016/12/18.
 */
@Service
public class UserService {

    //日志
    private Logger logger = LoggerFactory.getLogger("UserServcie.class");

    //引入数据库操作sql
    @Autowired
    private SqlSessionTemplate sqlSession;

    /**
     * 查询出全部用户
     * @param user
     * @return
     */
    public List<User> selectAll(User user){
        //只查找存在的用户
        user.setDisabled("1");
        List<User> userList = null;
        userList = sqlSession.selectList("User.selectAll",user);
        return userList;
    }

    /**
     * 修改用户资料
     * @param user
     * @return
     */
    public Boolean updateUser(User user){

        int result = sqlSession.update("User.updateById",user);
        return result>0? true:false;
    }

    /**
     * 根据id删除用户（逻辑删除）
     * @param userId
     * @return
     */
    public Boolean deleteUserById(String userId){
        User user = new User();
        user.setUserId(userId);
        int result = sqlSession.update("User.deleteUserById",user);
        return result>0? true:false;
    }

    /**
     * 新增用户
     * @param user
     * @return
     */
    public Boolean addUser(User user){
        //随机产生一个GUID
        String userId = new RandomGUID().toString();
        user.setUserId(userId);
        int result = sqlSession.insert("User.addUser",user);
        return result>0? true:false;
    }

    /**
     * 校验用户帐号唯一性
     * @param username
     * @return
     */
    public Boolean verifyAccount(String username){
        User user = new User();
        user.setUsername(username);
        List<User> list = sqlSession.selectList("User.verifyAccount",user);
        if(list!= null && list.size()>0){
            //帐号不唯一
            return true;
        }else {
            //帐号唯一
            return false;
        }
    }


}
