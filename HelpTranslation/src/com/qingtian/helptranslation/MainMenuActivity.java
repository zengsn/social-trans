package com.qingtian.helptranslation;

import java.util.ArrayList;
import java.util.List;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.qingtian.helptranslation.Adapter.MainPagerAdapter;
import com.qingtian.helptranslation.base.BaseActivity;
import com.qingtian.helptranslation.ui.fragment.ResultFragment;
import com.qingtian.helptranslation.ui.fragment.TaskFragment;
import com.qingtian.helptranslation.ui.fragment.TranslateFragment;


public class MainMenuActivity extends BaseActivity{

	private ViewPager viewPager;
	private List<Fragment> fragments;
	private MainPagerAdapter mainPageradapter;

	@Override
	public void initView() {
		setContentView(R.layout.menu);
		viewPager = (ViewPager) findViewById(R.id.viewPager);
	}

	@Override
	public void initListener() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void initData() {
		// TODO Auto-generated method stub
		//创建fragment，并添加到集合中
		fragments=new ArrayList<Fragment>();
		TaskFragment taskFragment=new TaskFragment();
		TranslateFragment translateFragment=new TranslateFragment();
		ResultFragment resultFragment=new ResultFragment();
		fragments.add(taskFragment);
		fragments.add(translateFragment);
		fragments.add(resultFragment);
		mainPageradapter=new MainPagerAdapter(getSupportFragmentManager(), fragments);
		viewPager.setAdapter(mainPageradapter);
	}

	@Override
	public void processClick(View v) {
		// TODO Auto-generated method stub
		
	}

}
