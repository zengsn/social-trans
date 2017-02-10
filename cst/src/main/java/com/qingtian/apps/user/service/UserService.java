package com.qingtian.apps.user.service;


import com.qingtian.apps.user.entity.User;
import com.qingtian.utils.Constant;
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

    private String validString = Constant.VALID;
    private String inValidString = Constant.IN_VALID;


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

//    /**
//     * 校验用户帐号唯一性
//     * @param username
//     * @return
//     */
//    public Boolean verifyAccount(String username){
//        User user = new User();
//        user.setUsername(username);
//        List<User> list = sqlSession.selectList("User.verifyAccount",user);
//        if(list!= null && list.size()>0){
//            //帐号不唯一
//            return true;
//        }else {
//            //帐号唯一
//            return false;
//        }
//    }

    /**
     * 登录验证
     * @param username
     * @param password
     * @return
     */
//    public User login(String username,String password){
//        User reUser = new User();
//        try{
//            User user = new User();
//            user.setUsername(username);
//            user.setPassword(password);
//            reUser = sqlSession.selectOne("User.login",user);
//            return reUser;
//        }catch (Exception e){
//            return null;
//        }
//
//
//
//    }

    /**
     * 注册用户
     */
    public Boolean register(String account,String password){
        User user = new User();
        user.setUserId(new RandomGUID().toString());
        user.setAccount(account);
        user.setPassword(password);
        int result = 0;
        try{
            result = sqlSession.insert("User.register",user);
            return result>0? true:false;
        }catch (Exception e){
            logger.error("UserService ------- register : 操作由于异常而失败"+e.getStackTrace());
            return false;
        }
    }

    /**
     * 校验用户帐号唯一性
     * @param account
     * @return
     */
    public Boolean verifyAccount(String account){
        User user = new User();
        user.setAccount(account);
        List<User> list = sqlSession.selectList("User.verifyAccount",user);
        if(list!= null && list.size()>0){
            //帐号不唯一
            return true;
        }else {
            //帐号唯一
            return false;
        }
    }

    /**
     * 登录验证
     * @param account
     * @param password
     * @return
     */
    public User login(String account,String password){
        User reUser = new User();
        try{
            User user = new User();
            user.setPassword(password);
            user.setAccount(account);
            //数据有效性
            user.setDisabled(validString);
            reUser = sqlSession.selectOne("User.login",user);
            return reUser;
        }catch (Exception e){
            return null;
        }

    }

    /**
     * 根据userId来查询用户信息
     * @param userId
     * @return
     */
    public User selectById(String userId)throws Exception{
        User user = new User();
        user.setUserId(userId);
        user.setDisabled(validString);
        User reUser = new User();
        reUser = sqlSession.selectOne("User.selectById",user);
        return reUser;
    }
}
