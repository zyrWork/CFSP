package com.cfsp.activity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;




import com.cfsp.utils.GlobalVariable;
import com.cfsp.utils.WebUtil;
import com.cfsp_client.R;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class CustomerLoginActivity extends Activity {
	// 登录URL
	String loginUrl = "http://"+GlobalVariable.IP+":8080/CommunityFinancialServicesPlatformServer/CustomerServlet?method=login";
  
	ImageButton loginBtn;
	TextView signBtn;

	EditText account;
	EditText password;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.customer_login);
		signBtn = (TextView) findViewById(R.id.register);
		loginBtn = (ImageButton) findViewById(R.id.login);
		account = (EditText) findViewById(R.id.account);
		password = (EditText) findViewById(R.id.pwd);
		// 按钮初始化
		signBtn.setOnClickListener(new OnClickListener() {
			// 注册按钮点击事件
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(CustomerLoginActivity.this,
						RegisterActivity.class);
				startActivity(intent);
			}
		});

		loginBtn.setOnClickListener(new View.OnClickListener() {
			// 登录按钮点击事件
			@Override
			public void onClick(View v) {

	            String account_number = CustomerLoginActivity.this.account
						.getText().toString().trim();
				String password = CustomerLoginActivity.this.password.getText()
						.toString().trim();
				if ("".equals(account_number)) {
					Toast.makeText(CustomerLoginActivity.this, "请填写账号",
							Toast.LENGTH_SHORT).show();
					return;
				}
				if ("".equals(password)) {
					Toast.makeText(CustomerLoginActivity.this, "请填写密码",
							Toast.LENGTH_SHORT).show();
					return;
				}
				// 如果已经填写了用户名和密码，执行登录操作
				executeLogin(account_number, password);
			}
		});
	}


	private void executeLogin(String account_number, String password) {
		new LoginTask().execute(account_number, password);
	}

	private class LoginTask extends AsyncTask<String, Void, String> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
		}

		@Override
		protected String doInBackground(String... params) {
			String result = null;
			JSONArray reqValue;
			JSONObject jsonObject;

			try {
				// 将账号名和密码封装到JSONArray中，进行HTTP通信
				reqValue = new JSONArray();
				jsonObject = new JSONObject();

				jsonObject.put("account_number", params[0]);
				jsonObject.put("password", params[1]);
				reqValue.put(jsonObject);

				JSONArray rec = WebUtil.getDataByPara(loginUrl, reqValue);
				System.out.print(reqValue);

				if (rec != null) {// 如果成功获取用户编号
					result = rec.getJSONObject(0).getString("C_Number");
					System.out.println("成功联网");
	
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
			return result;
		}

		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);
			// 回调
			onLoginComplete(result);
		}
	}

	private void onLoginComplete(String C_Number) {
		if (C_Number == null) {// 如果没有获取到用户ID，说明登录失败
			Toast.makeText(CustomerLoginActivity.this, "账户名或密码错误或联网失败",
					Toast.LENGTH_SHORT).show();
			return;
		} else {
			// 如果成功获取到返回的用户ID，说明登录成功
			Toast.makeText(CustomerLoginActivity.this, "登录成功",
					Toast.LENGTH_SHORT).show();
			Toast.makeText(CustomerLoginActivity.this, "您的ID:"+C_Number,
					Toast.LENGTH_SHORT).show();
			
			GlobalVariable.USER_ID=C_Number;//登记当前用户 ID
			MainPageActivity.actionStart(CustomerLoginActivity.this, C_Number);//传递用户ID,用于搜索有关用户信息
			
		}
	}

}
