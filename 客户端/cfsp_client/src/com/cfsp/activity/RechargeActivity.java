package com.cfsp.activity;

import java.math.BigDecimal;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.cfsp.entity.Account;
import com.cfsp.entity.Customer;
import com.cfsp.service.Accountservice;
import com.cfsp.service.CustomerService;
import com.cfsp_client.R;

public class RechargeActivity extends Activity {

	EditText editText_chargeMoney;
	ImageButton btn_ret;
	ImageButton bt_yinlianpay;
	ImageButton bt_alipay;
	ImageButton bt_weixinpay;

	private static Account myAccount;
	private static Customer myCustomer;

	public static void actionStart(Context context, Account myaccount,
			Customer customer) {
		Intent intent = new Intent(context, RechargeActivity.class);
		myAccount = myaccount;
		myCustomer = customer;
		context.startActivity(intent);
	}

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_balance_integeration);
		btn_ret = (ImageButton) findViewById(R.id.bt_rt);
		editText_chargeMoney = (EditText) findViewById(R.id.editText_chargeMoney);
		bt_yinlianpay = (ImageButton) findViewById(R.id.bt_yinlianpay);
		bt_alipay = (ImageButton) findViewById(R.id.bt_alipay);
		bt_weixinpay = (ImageButton) findViewById(R.id.bt_weixinpay);

		btn_ret.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(RechargeActivity.this,
						MyAccountActivity.class);
				startActivity(intent);

			}
		});

		bt_yinlianpay.setOnClickListener(new OnClickListener() {

			@SuppressLint("UseValueOf")
			@Override
			public void onClick(View arg0) {
				// 获得充值金额

				if ("".equals(editText_chargeMoney.getText().toString().trim())) {
					Toast.makeText(RechargeActivity.this, "金额未输入或格式不正确",
							Toast.LENGTH_SHORT).show();
				} else {
					// 将充值金额加上原来的金额组成新的金额
					double new_balance = Double.valueOf(myAccount.getA_Balance())
							+ Double.valueOf(editText_chargeMoney.getText()
									.toString());
					
					BigDecimal bg = new BigDecimal(new_balance);
					new_balance = bg.setScale(2, BigDecimal.ROUND_DOWN).doubleValue();
					
					// 将新的金额放入数据库中
					Double updateMoney = new Double(new_balance);
					excuteUpdateBalance(updateMoney);
				}	
			}
		}
		);

		bt_alipay.setOnClickListener(new OnClickListener() {

			@SuppressLint("UseValueOf")
			@Override
			public void onClick(View arg0) {
				/*Boolean can_notPay = true;
				String money = editText_chargeMoney.getText().toString().trim();
				for (int i = 0; i < money.length(); i++) {
					if (money.charAt(i) < '0' || money.charAt(i) > '9') {
						can_notPay = false;
						break;
					}
				}*/

				if ("".equals(editText_chargeMoney.getText().toString().trim())) {
					Toast.makeText(RechargeActivity.this, "金额未输入或格式不正确",
							Toast.LENGTH_SHORT).show();
				} else {
					// 将充值金额加上原来的金额组成新的金额
					double new_balance = Double.valueOf(myAccount.getA_Balance())
							+ Double.valueOf(editText_chargeMoney.getText()
									.toString());
					
					BigDecimal bg = new BigDecimal(new_balance);
					new_balance = bg.setScale(2, BigDecimal.ROUND_DOWN).doubleValue();
					
					// 将新的金额放入数据库中
					Double updateMoney = new Double(new_balance);
					excuteUpdateBalance(updateMoney);
				}	
			}
		});

		bt_weixinpay.setOnClickListener(new OnClickListener() {

			@SuppressLint("UseValueOf")
			@Override
			public void onClick(View arg0) {
				
				if ("".equals(editText_chargeMoney.getText().toString().trim())) {
					Toast.makeText(RechargeActivity.this, "金额未输入或格式不正确",
							Toast.LENGTH_SHORT).show();
				} else {
					// 将充值金额加上原来的金额组成新的金额
					double new_balance = Double.valueOf(myAccount.getA_Balance())
							+ Double.valueOf(editText_chargeMoney.getText()
									.toString());
					
					BigDecimal bg = new BigDecimal(new_balance);
					new_balance = bg.setScale(2, BigDecimal.ROUND_DOWN).doubleValue();
					
					// 将新的金额放入数据库中
					Double updateMoney = new Double(new_balance);
					excuteUpdateBalance(updateMoney);
				}	
			}
		});
	}

	/***************************************************************/
	private void excuteUpdateBalance(Double i) {
		new UpdateBalanceTask().execute(i);
	}


	private class UpdateBalanceTask extends AsyncTask<Double, Void, Double> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
		}

		@Override
		protected Double doInBackground(Double... arg0) {
			Double updateData = arg0[0];
			// 执行更新操作
			Account a = new Account();
			a = myAccount;
			a.setA_Balance(updateData.toString());
			// 更新
			Accountservice.updateAccount(a);
			// 同时更新积分信息
			Customer c = new Customer();
			c = myCustomer;
			
			double cut=Double.valueOf(editText_chargeMoney.getText().toString());
			c.setC_Integration(myCustomer.getC_Integration()+(int)cut);//截断
			c.setC_Level(myCustomer.getC_Level()+1);//增加积分
			CustomerService.updateCustomer(c);

			return updateData;
		}

		protected void onPostExecute(Double result) {
			super.onPostExecute(result);
			// 回调
			onUpdateBalanceComplete(result);
		}
	}

	private void onUpdateBalanceComplete(Double result) {

		BigDecimal bg = new BigDecimal(result);
		result = bg.setScale(2, BigDecimal.ROUND_DOWN).doubleValue();
		
		Toast.makeText(RechargeActivity.this,
				"已充值成功，现有余额：" + result.toString(), Toast.LENGTH_SHORT).show();

	}
}