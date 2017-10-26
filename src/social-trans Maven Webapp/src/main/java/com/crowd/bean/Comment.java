package com.crowd.bean;

public class Comment {
	private String userId;
	private String username;
	private String comment;
	private String num;
	private String acceptId;
	public String getUserId() {
		return userId;
	}
	public String getAcceptId() {
		return acceptId;
	}
	public void setAcceptId(String acceptId) {
		this.acceptId = acceptId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
	}
	
	@Override
	public String toString() {
		return "Comment [userId=" + userId + ", username=" + username
				+ ", comment=" + comment + ", num=" + num + "]";
	}
	
}
