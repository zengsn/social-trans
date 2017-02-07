package com.qingtian.apps.user.action;

import com.qingtian.apps.user.entity.User;
import com.qingtian.apps.user.service.UserService;
import com.qingtian.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.workSpace.utils.CookieUtils;
import org.workSpace.utils.JsonUtils;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by machao on 2016/12/18.
 */
@RestController
@RequestMapping("User")
public class UserAction {

    //日志
    private Logger logger = LoggerFactory.getLogger("UserAction");

    //引入service
    @Resource(name = "userService")
    private UserService userService;

    /**
     * 查询全部用户
     * @param user
     * @return
     */
    @RequestMapping("selectAll.do")
    public String selectAll(User user){
        List<User> userList = userService.selectAll(user);
        return JsonUtils.genUpdateDataReturnJsonStr(true,"查询成功",userList);
    }

    /**
     * 根据id修改用户资料
     * @param user
     * @return
     */
    @RequestMapping("updateUser.do")
    public String updateUser(User user){

        //验证userId非空
        String userId = user.getUserId();
        if(userId == null || "".equals(userId)){
            logger.error("UserAction ------- updateUser : userId 为空");
            return  JsonUtils.genUpdateDataReturnJsonStr(false,"userId为空");

        }

        //验证帐号非空
        String nickName = user.getNickname();
        if(nickName == null || "".equals(nickName)){
            logger.error("UserAction ------- updateUser : nickName 为空");
            return  JsonUtils.genUpdateDataReturnJsonStr(false,"nickName为空");

        }

        //验证邮箱非空
        String email = user.getEmail();
        if(email == null || "".equals(email)){
            logger.error("UserAction ------- updateUser : email 为空");
            return  JsonUtils.genUpdateDataReturnJsonStr(false,"email为空");

        }

        //验证手机号码非空
        String phoneNumber = user.getPhoneNumber();
        if(phoneNumber == null || "".equals(phoneNumber)){
            logger.error("UserAction ------- updateUser : phoneNumber 为空");
            return  JsonUtils.genUpdateDataReturnJsonStr(false,"phoneNumber为空");

        }

        Boolean isSuccess = userService.updateUser(user);
        if(isSuccess){
            return JsonUtils.genUpdateDataReturnJsonStr(true,"修改用户资料成功");
        }else {
            return JsonUtils.genUpdateDataReturnJsonStr(false,"修改用户资料失败");
        }
    }

    /**
     *根据用户id删除
     * @param userId
     * @return
     */
    @RequestMapping("deleteUserById.do")
    public String deleteUserById(String userId){

        //验证userId非空
        if(userId == null || "".equals(userId)){
            logger.error("UserAction ------- updateUser : userId 为空");
            return  JsonUtils.genUpdateDataReturnJsonStr(false,"userId为空");

        }

        Boolean isSuccess = userService.deleteUserById(userId);
        if(isSuccess){
            return JsonUtils.genUpdateDataReturnJsonStr(true,"删除用户资料成功");
        }else {
            return JsonUtils.genUpdateDataReturnJsonStr(false,"删除用户资料失败");
        }
    }

    /**
     * 新增用户
     * @param user
     * @return
     */
    @RequestMapping("addUser.do")
    public String addUser(User user){

        //验证帐号非空
        String account = user.getAccount();
        if(account == null || "".equals(account)){
            logger.error("UserAction ------- addUser : account为空");
            return  JsonUtils.genUpdateDataReturnJsonStr(false,"account为空");
        }

        //验证密码非空
        String password = user.getPassword();
        if(password == null || "".equals(password)){
            logger.error("UserAction ------- updateUser : password 为空");
            return  JsonUtils.genUpdateDataReturnJsonStr(false,"password为空");
        }

        //验证角色非空
        String role = user.getRole();
        if(role == null || "".equals(role)){
            logger.error("UserAction ------- updateUser : role 为空");
            return  JsonUtils.genUpdateDataReturnJsonStr(false,"role为空");
        }

        //如果用户名为空，则让其为用户帐号
        String username = user.getUsername();
        if(username == null || "".equals(username)){
            user.setUsername(username);
        }

        Boolean isSuccess = userService.addUser(user);
        if(isSuccess){
            return JsonUtils.genUpdateDataReturnJsonStr(true,"新增用户成功");
        }else {
            return JsonUtils.genUpdateDataReturnJsonStr(false,"新增用户失败");
        }
    }

    /**
     * 验证用户帐号唯一性
     * @param username
     * @return
     */
    @RequestMapping("verifyAccount.do")
    public String verifyAccount(String username){

        //验证帐号非空
        if(username == null || "".equals(username)){
            logger.error("UserAction ------- updateUser : username 为空");
            return  JsonUtils.genUpdateDataReturnJsonStr(false,"username为空");
        }


        Boolean Vresult = userService.verifyAccount(username);
        if(Vresult){
            //帐号已存在
            return JsonUtils.genUpdateDataReturnJsonStr(true,"帐号唯一");
        }else {
            //帐号不存在
            return JsonUtils.genUpdateDataReturnJsonStr(false,"帐号不存在");
        }
    }

    @RequestMapping("login.do")
    public String login(String username, String password, HttpServletRequest request, HttpServletResponse response){

        //验证用户名是否为空
        if(StringUtils.isEmpty(username)){
            logger.error("UserAction ------- login : username 为空");
            return  JsonUtils.genUpdateDataReturnJsonStr(false,"username为空");
        }
        //验证密码是否为空
        if(StringUtils.isEmpty(password)){
            logger.error("UserAction ------- login : password 为空");
            return  JsonUtils.genUpdateDataReturnJsonStr(false,"password为空");
        }

        User user = userService.login(username,password);
        if(user!= null){
            CookieUtils.addCookie(response,"userId",user.getUserId());
            CookieUtils.addCookie(response,"nickname",user.getNickname());
            //该用户存在
            return JsonUtils.genUpdateDataReturnJsonStr(true,"登录成功");
        }else {
            //用户不存在
            return JsonUtils.genUpdateDataReturnJsonStr(false,"登录失败");
        }

    }

    @RequestMapping("/register.do")
    public String register(String account,String password){

        //验证帐号是否为空
        if(StringUtils.isEmpty(account)){
            logger.error("UserAction ------- register : account 为空");
            return  JsonUtils.genUpdateDataReturnJsonStr(false,"account为空");
        }

        //验证密码是否为空
        if(StringUtils.isEmpty(password)){
            logger.error("UserAction ------- register : password 为空");
            return  JsonUtils.genUpdateDataReturnJsonStr(false,"password为空");
        }

        Boolean isSuccess = userService.register(account,password);
        if(isSuccess){
            return  JsonUtils.genUpdateDataReturnJsonStr(true,"注册成功");
        }else{
            return  JsonUtils.genUpdateDataReturnJsonStr(false,"注册失败");
        }
    }

    /**
     * 验证用户帐号唯一性
     * @param account
     * @return
     */
    @RequestMapping("verifyAccount1.do")
    public String verifyAccount1(String account){

        //验证帐号非空
        if(account == null || "".equals(account)){
            logger.error("UserAction ------- updateUser : account 为空");
            return  JsonUtils.genUpdateDataReturnJsonStr(false,"account为空");
        }


        Boolean Vresult = userService.verifyAccount1(account);
        if(Vresult){
            //帐号已存在
            return JsonUtils.genUpdateDataReturnJsonStr(true,"帐号唯一");
        }else {
            //帐号不存在
            return JsonUtils.genUpdateDataReturnJsonStr(false,"帐号不存在");
        }
    }

    @RequestMapping("login1.do")
    public String login1(String account, String password, HttpServletRequest request, HttpServletResponse response){

        //验证账号是否为空
        if(StringUtils.isEmpty(account)){
            logger.error("UserAction ------- login : account 为空");
            return  JsonUtils.genUpdateDataReturnJsonStr(false,"account为空");
        }
        //验证密码是否为空
        if(StringUtils.isEmpty(password)){
            logger.error("UserAction ------- login : password 为空");
            return  JsonUtils.genUpdateDataReturnJsonStr(false,"password为空");
        }

        User user = userService.login1(account,password);
        if(user!= null){

            //该用户存在
            return JsonUtils.genUpdateDataReturnJsonStr(true,"登录成功");
        }else {
            //用户不存在
            return JsonUtils.genUpdateDataReturnJsonStr(false,"登录失败");
        }

    }
}
