package com.crowd.bean;

public class Message {
	private String userId;
	private String taskId;
	private String message;
	private int state;
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getTaskId() {
		return taskId;
	}
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	@Override
	public String toString() {
		return "Message [userId=" + userId + ", taskId=" + taskId
				+ ", message=" + message + ", state=" + state + "]";
	}
	
	
}
