package com.cst.dao.core.action;

import com.opensymphony.xwork2.ActionSupport;

public abstract class BaseAction extends ActionSupport {
		
	protected String[] selectedRow;//É¾³ý¶àÑ¡
	public String[] getSelectedRow() {
		return selectedRow;
	}
	public void setSelectedRow(String[] selectedRow) {
		this.selectedRow = selectedRow;
	}
}	
