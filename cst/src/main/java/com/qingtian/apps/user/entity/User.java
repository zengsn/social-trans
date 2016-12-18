package com.qingtian.apps.user.entity;
import java.io.Serializable;
import org.apache.ibatis.type.Alias;

@Alias("User")
public class User implements Serializable{
    private static final long serialVersionUID = 1L;

    /**
     *
     */
    private String userId;

    /**
     *用户帐号
     */
    private String username;

    /**
     *用户密码
     */
    private String password;

    /**
     *用户名
     */
    private String nickname;

    /**
     *用户邮箱
     */
    private String email;

    /**
     *用户手机号
     */
    private String phoneNumber;

    /**
     *角色
     */
    private String role;

    /**
     * 获取属性 
     * @param userId
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }
    /**
     * 设置属性 
     * @return userId
     */
    public String getUserId() {
        return userId;
    }

    /**
     * 获取属性 用户帐号
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }
    /**
     * 设置属性 用户帐号
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /**
     * 获取属性 用户密码
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }
    /**
     * 设置属性 用户密码
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * 获取属性 用户名
     * @param nickname
     */
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
    /**
     * 设置属性 用户名
     * @return nickname
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * 获取属性 用户邮箱
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }
    /**
     * 设置属性 用户邮箱
     * @return email
     */
    public String getEmail() {
        return email;
    }

    /**
     * 获取属性 用户手机号
     * @param phoneNumber
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    /**
     * 设置属性 用户手机号
     * @return phoneNumber
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * 获取属性 角色
     * @param role
     */
    public void setRole(String role) {
        this.role = role;
    }
    /**
     * 设置属性 角色
     * @return role
     */
    public String getRole() {
        return role;
    }

}
