package com.qingtian.helptranslation.Adapter;

import java.util.ArrayList;
import java.util.List;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class MainPagerAdapter extends FragmentPagerAdapter{
	
	//用来存储fragment
	private List<Fragment> fragments;
	public MainPagerAdapter(FragmentManager fm,List<Fragment> fragments) {
		super(fm);
		this.fragments=fragments;
		// TODO Auto-generated constructor stub
	}
	
	//返回某个fragment
	@Override
	public Fragment getItem(int location) {
		// TODO Auto-generated method stub
		return fragments.get(location);
	}
	
	//返回fragment数目
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return fragments.size();
	}

}
