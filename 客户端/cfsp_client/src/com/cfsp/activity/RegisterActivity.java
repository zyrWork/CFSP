package com.cfsp.activity;


import com.cfsp.entity.Customer;
import com.cfsp.service.CustomerService;
import com.cfsp_client.R;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends Activity {
	
	EditText editText_phone;
	EditText editText_account;
	EditText editText_pwd;
	EditText editText_nickname;
	EditText editText_email;
	EditText editText_community_name;
	EditText editText_name;
	EditText editText_sex;
	EditText editText_identify_number;

	Customer bean ;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.register);
		// 按钮初始化
		Button returnBtn = (Button) findViewById(R.id.bt_return);
		Button registerBtn = (Button) findViewById(R.id.bt_register);
		//文本框初始化
		editText_phone = (EditText) findViewById(R.id.editText_phone);
		editText_account = (EditText) findViewById(R.id.editText_account);
		editText_pwd = (EditText) findViewById(R.id.editText_pwd);
		editText_nickname = (EditText) findViewById(R.id.editText_nickname);
		editText_email = (EditText) findViewById(R.id.editText_email);
		editText_community_name = (EditText) findViewById(R.id.editText_community_name);
		editText_name=(EditText) findViewById(R.id.editText_name);
		editText_sex=(EditText) findViewById(R.id.editText_sex);
		editText_identify_number=(EditText) findViewById(R.id.editText_identify_number);
	
		bean = new Customer();
		
		returnBtn.setOnClickListener(new OnClickListener() {
			// 返回按钮点击事件
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(RegisterActivity.this,
						CustomerLoginActivity.class);
				startActivity(intent);
			}
		});

		registerBtn.setOnClickListener(new OnClickListener() {
			// 注册按钮点击事件
			@Override
			public void onClick(View v) {
				if(RegisterActivity.this.editText_account.getText().toString().trim()==""
						||RegisterActivity.this.editText_pwd.getText().toString().trim()=="")
				{
					Toast.makeText(RegisterActivity.this, "必须填写账户和密码！！！",
							Toast.LENGTH_SHORT).show();
				}
				else
				{
					bean.setC_Account_number(RegisterActivity.this.editText_account.getText().toString().trim());
					bean.setC_Community(RegisterActivity.this.editText_community_name.getText().toString().trim());
					bean.setC_Email(RegisterActivity.this.editText_email.getText().toString().trim());
					bean.setC_HealthTimes(0);
					bean.setC_ID(RegisterActivity.this.editText_identify_number.getText().toString().trim());
					bean.setC_Integration(10);
					bean.setC_Level(0);
					bean.setC_Nickname(RegisterActivity.this.editText_nickname.getText().toString().trim());

					bean.setC_Password(RegisterActivity.this.editText_pwd.getText().toString().trim());
					bean.setC_Username(RegisterActivity.this.editText_name.getText().toString().trim());
					bean.setC_Sex(RegisterActivity.this.editText_sex.getText().toString().trim());
					bean.setC_Tele(RegisterActivity.this.editText_phone.getText().toString().trim());
						
				}
			
				
				executeRegister();
			
			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	private void executeRegister( ) {
		new RegisterTask().execute();
	}

	private class RegisterTask extends AsyncTask<Void, Void, Void> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
		}

		@Override
		protected Void doInBackground(Void... arg0) {
			// TODO Auto-generated method stub

              CustomerService.insertCustomer(bean);

			return null;
		}
		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);
			// 回调
			onRegisterComplete();
		}
	}

	private void onRegisterComplete() {
		Toast.makeText(RegisterActivity.this, "注册成功",
				Toast.LENGTH_SHORT).show();
		
		Intent intent = new Intent(RegisterActivity.this,
				CustomerLoginActivity.class);
		startActivity(intent);
	}
}
