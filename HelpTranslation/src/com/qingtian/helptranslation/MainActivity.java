package com.qingtian.helptranslation;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.listener.SaveListener;

import com.qingtian.helptranslation.base.BaseActivity;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.text.TextUtils;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends BaseActivity {
	
	private TextView btn_register;
	private Button btn_login;
	private EditText et_username;
	private EditText et_password;
	
	@Override
	public void initView() {
		// TODO Auto-generated method stub
		Bmob.initialize(this, "cedc31a80814444195731ecf60f87627");
		setContentView(R.layout.activity_main);
		btn_register=(TextView) findViewById(R.id.btn_register);
		btn_login = (Button) findViewById(R.id.btn_login);
		et_username = (EditText) findViewById(R.id.et_username);
		et_password = (EditText) findViewById(R.id.et_password);
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
			//Toast.makeText(getApplicationContext(), "login success", 0).show();
			login();
		default:
			break;
		}
	}

  
	public void login(){
		BmobUser user=new BmobUser();
		String user_name = et_username.getText().toString().trim();
		String user_password = et_password.getText().toString().trim();
		if (TextUtils.isEmpty(user_name) || TextUtils.isEmpty(user_password)
				 ) {
			Toast.makeText(getApplicationContext(), "用户名或密码不能为空", 0).show();
		} else{
			user.setUsername(user_name);
			user.setPassword(user_password);
			user.login(getApplicationContext(), new SaveListener() {
				
				@Override
				public void onSuccess() {
					// TODO Auto-generated method stub
					Toast.makeText(getApplicationContext(), "login success", 0).show();
				}
				
				@Override
				public void onFailure(int arg0, String arg1) {
					// TODO Auto-generated method stub
					Toast.makeText(getApplicationContext(), "login fail", 0).show();
				}
			});
		}
	}
    
    
}
