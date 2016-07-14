package com.qingtian.helptranslation;

import cn.bmob.v3.Bmob;

import com.qingtian.helptranslation.base.BaseActivity;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends BaseActivity {
	
	private TextView btn_register;
	private Button btn_login;
	
	@Override
	public void initView() {
		// TODO Auto-generated method stub
		Bmob.initialize(this, "cedc31a80814444195731ecf60f87627");
		setContentView(R.layout.activity_main);
		btn_register=(TextView) findViewById(R.id.btn_register);
		btn_login = (Button) findViewById(R.id.btn_login);
	}

	@Override
	public void initListener() {
		// TODO Auto-generated method stub
		btn_login.setOnClickListener(this);
		btn_register.setOnClickListener(this);
	}

	@Override
	public void initData() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void processClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btn_register:
			Intent intent=new Intent(this,RegisterActivity.class);
			startActivity(intent);
			
			break;
		case R.id.btn_login:
			Toast.makeText(getApplicationContext(), "login success", 0).show();
		default:
			break;
		}
	}

  

    
    
}
