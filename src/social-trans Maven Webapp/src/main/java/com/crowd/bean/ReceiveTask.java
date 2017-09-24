package com.crowd.bean;

import org.apache.ibatis.type.Alias;

/*
 * 任务
 */
@Alias("receiveTask")
public class ReceiveTask {

	//任务唯一标识ID
	 private String taskId;
	 //任务名
	 private String taskName;
	 //描述
	 private String description;
	 //发布任务者 与UserName 匹配
	 private String publisher;
	 //与userId关联
	 private String publishId;
	 //任务开始时间
	 private String startTime;
	 //任务完成最后限期
	 private String finishTime;
	 //是否被接收 0为未接受 1为接收
	 private int isReceive;
	 //任务悬赏
	 private int taskMoney;
	 //任务需求人数
	 private int receiveNum;
	 
	 private String fileId;
	 
	 private String fileCode;
	 
	 private int state;
	 
	 private int totalNum;
	 
	 private int isChild;
	 
	 private String taskText;
	 
	 private String parentId;

	public String getTaskText() {
		return taskText;
	}

	public void setTaskText(String taskText) {
		this.taskText = taskText;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public int getIsChild() {
		return isChild;
	}

	public void setIsChild(int isChild) {
		this.isChild = isChild;
	}

	public int getTotalNum() {
		return totalNum;
	}

	public void setTotalNum(int totalNum) {
		this.totalNum = totalNum;
	}

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getPublishId() {
		return publishId;
	}

	public void setPublishId(String publishId) {
		this.publishId = publishId;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getFinishTime() {
		return finishTime;
	}

	public void setFinishTime(String finishTime) {
		this.finishTime = finishTime;
	}

	public int getIsReceive() {
		return isReceive;
	}

	public void setIsReceive(int isReceive) {
		this.isReceive = isReceive;
	}

	public int getTaskMoney() {
		return taskMoney;
	}

	public void setTaskMoney(int taskMoney) {
		this.taskMoney = taskMoney;
	}

	public int getReceiveNum() {
		return receiveNum;
	}

	public void setReceiveNum(int receiveNum) {
		this.receiveNum = receiveNum;
	}

	public String getFileId() {
		return fileId;
	}

	public void setFileId(String fileId) {
		this.fileId = fileId;
	}

	public String getFileCode() {
		return fileCode;
	}

	public void setFileCode(String fileCode) {
		this.fileCode = fileCode;
	}

	 public int getState() {
			return state;
		}

		public void setState(int state) {
			this.state = state;
		}

	
	
}
