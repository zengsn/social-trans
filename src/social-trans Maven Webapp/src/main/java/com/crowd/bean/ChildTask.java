package com.crowd.bean;

public class ChildTask {
	private String parentTaskId;
	
	private String childTaskId;
	
	private int part;

	public String getParentTaskId() {
		return parentTaskId;
	}

	public void setParentTaskId(String parentTaskId) {
		this.parentTaskId = parentTaskId;
	}

	public String getChildTaskId() {
		return childTaskId;
	}

	public void setChildTaskId(String childTaskId) {
		this.childTaskId = childTaskId;
	}

	public int getPart() {
		return part;
	}

	public void setPart(int part) {
		this.part = part;
	}
	
}
