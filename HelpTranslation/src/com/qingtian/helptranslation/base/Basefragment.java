package com.qingtian.helptranslation.base;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public abstract class Basefragment extends Fragment implements View.OnClickListener{
	
	//fragment创建后调用
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		initData();
		initListener();
	}
	
	//返回view对象，作为fragment显示内容
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		return initView(inflater, container, savedInstanceState);
	}
	
	public abstract View initView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState);
	public abstract void initData();
	public abstract void initListener();
	public abstract void processClick(View view);
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		processClick(v);
	}
}
