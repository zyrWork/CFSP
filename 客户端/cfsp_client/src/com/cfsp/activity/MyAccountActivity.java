package com.cfsp.activity;

import java.math.BigDecimal;
import java.util.ArrayList;

import com.cfsp.entity.Account;
import com.cfsp.entity.Customer;
import com.cfsp.service.Accountservice;
import com.cfsp.service.CustomerService;
import com.cfsp.utils.GlobalVariable;
import com.cfsp_client.R;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;

public class MyAccountActivity extends Activity {

	TextView txtView_showUserLevel;
	TextView txtView_showBalance;
	TextView txtView_showUsername;
	Account myAccount;
	Customer customer;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);	
		setContentView(R.layout.my_account);   
		
		//��ʼ����Դ
		Button btnBill = (Button) findViewById(R.id.btnBill);
		Button btn_recharge = (Button) findViewById(R.id.btn_recharge);
		ImageButton btn_return = (ImageButton) findViewById(R.id.btn_return);
		
		 txtView_showUserLevel=(TextView) findViewById(R.id.txtView_showUserLevel);
		 txtView_showBalance=(TextView) findViewById(R.id.txtView_showBalance);
		 txtView_showUsername=(TextView) findViewById(R.id.txtView_showUsername);

		 executeShowAccount( ) ;	
		 
		 btnBill.setOnClickListener(new OnClickListener() {
				// �˵���Ϣ
				@Override
				public void onClick(View v) {
					Intent intent = new Intent(MyAccountActivity.this,
							MyBillActivity.class);
					startActivity(intent);
					
					MyBillActivity.actionStart(MyAccountActivity.this,  myAccount);
				}
			
		 });
		 
		 btn_recharge.setOnClickListener(new OnClickListener() {
				// ��ֵ
				@Override
				public void onClick(View v) {
					
					RechargeActivity.actionStart(MyAccountActivity.this,  myAccount,customer);
				}
			
		 });
		 
		 btn_return.setOnClickListener(new OnClickListener() {
				// ����
				@Override
				public void onClick(View v) {
					Intent intent = new Intent(MyAccountActivity.this,
							MainPageActivity.class);
					startActivity(intent);
				}
		 });
	}
	
	private void executeShowAccount( ) {
		new ShowCustomerTask().execute();//��ʾ�û���Ϣ
		new ShowAccountTask().execute();//��ʾ�˵���Ϣ
	}

	private class ShowCustomerTask extends AsyncTask<Void, Void, Customer> {
     //��ʾ�˵���ҵ��
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
		}

		@Override
		protected Customer doInBackground(Void... arg0) {
			//�����ݿ��е�����Ϣ����ʾ
			
			String id=GlobalVariable.USER_ID;  //����ID,������Ϣ
			Customer cu = new Customer();
			cu=null;
			
			ArrayList<Customer> list = new ArrayList<Customer>();
			list=CustomerService.getAllCustomer();
			
			for( int i=0;i<list.size();i++)
			{
				Customer bean = new Customer();
				bean=list.get(i);
				
				if(id.equals(bean.getC_Number()))
				{//��IDƥ������ҵ��û�
					cu=bean;
					break;
				}
			}	
			return cu;
		}
		@Override
		protected void onPostExecute(Customer result) {
			super.onPostExecute(result);
			// �ص�
			onGetCustomerComplete(result);
		}
	}
	@SuppressLint("UseValueOf") private void onGetCustomerComplete(Customer result) {
		//���´�ʱ�û����û��ȼ����û���
		if(result==null)
		{
			
		}
		else
		{
			customer=result;
			txtView_showUserLevel.setText(new Integer(result.getC_Level()).toString());
			txtView_showUsername.setText(result.getC_Username());

		}
	}
	/***************************************************************/
	//��ʾ�˵���Ϣ
	private class ShowAccountTask extends AsyncTask<Void, Void, Account> {
	     //��ʾ�˵���ҵ��
			@Override
			protected void onPreExecute() {
				super.onPreExecute();
			}

			@Override
			protected Account doInBackground(Void... arg0) {
				//�����ݿ��е�����Ϣ����ʾ
				
				String id=GlobalVariable.USER_ID;  //����ID,������Ϣ
				Account account = new Account();
				
				ArrayList<Account> list = new ArrayList<Account>();
				list=Accountservice.getAllAccount();
				for( int i=0;i<list.size();i++)
				{
					Account bean = new Account();
					bean=list.get(i);
					
					if(id.equals(bean.getC_Number()))
					{//��IDƥ������ҵ��û�
						account=bean;
						break;
					}
				}	
				return account;
			}
			@Override
			protected void onPostExecute(Account result) {
				super.onPostExecute(result);
				// �ص�
				onGetAccountComplete(result);
			}
		}
		@SuppressLint("UseValueOf") private void onGetAccountComplete(Account result) {
			//���´�ʱ���˻����
			if(result==null)
			{
				
			}
			else
			{
				double my_balance=Double.parseDouble(result.getA_Balance());
				BigDecimal bg = new BigDecimal(my_balance);
				my_balance = bg.setScale(2, BigDecimal.ROUND_DOWN).doubleValue();
				txtView_showBalance.setText(new Double(my_balance).toString());
				myAccount=result;//�����˵�
				Toast.makeText(MyAccountActivity.this, "��ǰ�ʻ����Ϊ��"+new Double(my_balance).toString(),
						Toast.LENGTH_SHORT).show();
				
			}
		
		}

}