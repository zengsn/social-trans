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
     * 根据id删除用户
     * @param userId
     * @return
     */
    public Boolean deleteUserById(String userId){
        User user = new User();
        user.setUserId(userId);
        int result = sqlSession.delete("User.deleteUserById",user);
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


}
