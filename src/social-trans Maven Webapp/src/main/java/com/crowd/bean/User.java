package com.crowd.bean;

import org.apache.ibatis.type.Alias;
@Alias("User")
public class User {
		
		   @Override
	public String toString() {
		return "User [roleId=" + roleId + ", headImage=" + headImage
				+ ", account=" + account + ", userId=" + userId + ", password="
				+ password + ", username=" + username + ", email=" + email
				+ ", phoneNumber=" + phoneNumber + ", role=" + role
				+ ", hobby=" + hobby + "]";
	}

		   private String roleId;//角色ID
		   
		    private String headImage;//头像

		    private String account;//账号

		    private String userId;//用户ID

		    /**
		     *用户密码
		     */
		    private String password;//用户密码

		    /**
		     *用户名
		     */
		    private String username;//用户名

		    /**
		     *用户邮箱
		     */
		    private String email;
			public String getEmail() {
				return email;
			}

			public void setEmail(String email) {
				this.email = email;
			}

			/**
		     *用户手机号
		     */
		    private String phoneNumber;//用户手机号码

		    /**
		     *角色
		     */
		    private String role;//角色

		    /**
		     *兴趣
		     */
		    private String hobby;
		    /**
		     * 获取属性 
		     * @param userId
		     */
			public String getRoleId() {
				return roleId;
			}

			public void setRoleId(String roleId) {
				this.roleId = roleId;
			}

			public String getHeadImage() {
				return headImage;
			}

			public void setHeadImage(String headImage) {
				this.headImage = headImage;
			}

			public String getAccount() {
				return account;
			}

			public void setAccount(String account) {
				this.account = account;
			}

			public String getUserId() {
				return userId;
			}

			public void setUserId(String userId) {
				this.userId = userId;
			}

			public String getPassword() {
				return password;
			}

			public void setPassword(String password) {
				this.password = password;
			}

			

			public String getPhoneNumber() {
				return phoneNumber;
			}

			public void setPhoneNumber(String phoneNumber) {
				this.phoneNumber = phoneNumber;
			}

			public String getRole() {
				return role;
			}

			public void setRole(String role) {
				this.role = role;
			}

			
			  public String getHobby() {
				return hobby;
			}

			public void setHobby(String hobby) {
				this.hobby = hobby;
			}

			public String getUsername() {
					return username;
				}

				public void setUsername(String username) {
					this.username = username;
				}

			public User() {
				super();
				// TODO Auto-generated constructor stub
			}

			public User(String username,String password) {
				super();
				// TODO Auto-generated constructor stub
			}
		  
}
