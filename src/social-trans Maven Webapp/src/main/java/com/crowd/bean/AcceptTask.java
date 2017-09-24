package com.crowd.bean;

public class AcceptTask {
	private String taskId;
	private String userId;
	private String acceptId;
	private String accepter;
	private int isSubmit;
	private String submitText;
	
	private int goods;

	public int getGoods() {
		return goods;
	}

	public void setGoods(int goods) {
		this.goods = goods;
	}
	public AcceptTask() {
		super();
	}
	public AcceptTask(String taskId, String userId, String acceptId,
			String accepter) {
		super();
		this.taskId = taskId;
		this.userId = userId;
		this.acceptId = acceptId;
		this.accepter = accepter;
	}
	public String getTaskId() {
		return taskId;
	}
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getAcceptId() {
		return acceptId;
	}
	public void setAcceptId(String acceptId) {
		this.acceptId = acceptId;
	}
	public String getAccepter() {
		return accepter;
	}
	public void setAccepter(String accepter) {
		this.accepter = accepter;
	}
	public int getIsSubmit() {
		return isSubmit;
	}
	public void setIsSubmit(int isSubmit) {
		this.isSubmit = isSubmit;
	}

	public String getSubmitText() {
		return submitText;
	}

	public void setSubmitText(String submitText) {
		this.submitText = submitText;
	}
	
}
