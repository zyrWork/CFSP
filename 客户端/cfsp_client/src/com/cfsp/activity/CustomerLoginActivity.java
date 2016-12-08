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
	// ��¼URL
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
		// ��ť��ʼ��
		signBtn.setOnClickListener(new OnClickListener() {
			// ע�ᰴť����¼�
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(CustomerLoginActivity.this,
						RegisterActivity.class);
				startActivity(intent);
			}
		});

		loginBtn.setOnClickListener(new View.OnClickListener() {
			// ��¼��ť����¼�
			@Override
			public void onClick(View v) {

	            String account_number = CustomerLoginActivity.this.account
						.getText().toString().trim();
				String password = CustomerLoginActivity.this.password.getText()
						.toString().trim();
				if ("".equals(account_number)) {
					Toast.makeText(CustomerLoginActivity.this, "����д�˺�",
							Toast.LENGTH_SHORT).show();
					return;
				}
				if ("".equals(password)) {
					Toast.makeText(CustomerLoginActivity.this, "����д����",
							Toast.LENGTH_SHORT).show();
					return;
				}
				// ����Ѿ���д���û��������룬ִ�е�¼����
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
				// ���˺����������װ��JSONArray�У�����HTTPͨ��
				reqValue = new JSONArray();
				jsonObject = new JSONObject();

				jsonObject.put("account_number", params[0]);
				jsonObject.put("password", params[1]);
				reqValue.put(jsonObject);

				JSONArray rec = WebUtil.getDataByPara(loginUrl, reqValue);
				System.out.print(reqValue);

				if (rec != null) {// ����ɹ���ȡ�û����
					result = rec.getJSONObject(0).getString("C_Number");
					System.out.println("�ɹ�����");
	
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
			return result;
		}

		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);
			// �ص�
			onLoginComplete(result);
		}
	}

	private void onLoginComplete(String C_Number) {
		if (C_Number == null) {// ���û�л�ȡ���û�ID��˵����¼ʧ��
			Toast.makeText(CustomerLoginActivity.this, "�˻�����������������ʧ��",
					Toast.LENGTH_SHORT).show();
			return;
		} else {
			// ����ɹ���ȡ�����ص��û�ID��˵����¼�ɹ�
			Toast.makeText(CustomerLoginActivity.this, "��¼�ɹ�",
					Toast.LENGTH_SHORT).show();
			Toast.makeText(CustomerLoginActivity.this, "����ID:"+C_Number,
					Toast.LENGTH_SHORT).show();
			
			GlobalVariable.USER_ID=C_Number;//�Ǽǵ�ǰ�û� ID
			MainPageActivity.actionStart(CustomerLoginActivity.this, C_Number);//�����û�ID,���������й��û���Ϣ
			
		}
	}

}
