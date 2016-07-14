package com.qingtian.helptranslation.base;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

public abstract class BaseActivity extends Activity implements View.OnClickListener{
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		initView();
		initListener();
		initData();
	}
	
	public abstract void initView();
	public abstract void initListener();
	public abstract void initData();
	public abstract void processClick(View v);
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		processClick(v);
	}
}
