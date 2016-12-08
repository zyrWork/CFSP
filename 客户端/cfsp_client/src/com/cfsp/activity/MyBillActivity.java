package com.cfsp.activity;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.cfsp.entity.Account;
import com.cfsp.entity.Detail;
import com.cfsp.service.Detailservice;
import com.cfsp_client.R;

public class MyBillActivity extends Activity {

	TableLayout tableLayout;
	private static Account myAccount;
	private final int WC = ViewGroup.LayoutParams.WRAP_CONTENT;
	private final int FP = ViewGroup.LayoutParams.FILL_PARENT;

	public static void actionStart(Context context, Account myaccount) {
		Intent intent = new Intent(context, MyBillActivity.class);
		myAccount = myaccount;
		context.startActivity(intent);
	}

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.my_bill);

		tableLayout = (TableLayout) findViewById(R.id.TableLayout_myBill);
		tableLayout.setStretchAllColumns(true);

		ImageButton returnBtn = (ImageButton) findViewById(R.id.ret);

		returnBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MyBillActivity.this,
						MyAccountActivity.class);
				startActivity(intent);
			}
		});

		executeSearchAllDetail();
	}

	private void executeSearchAllDetail() {
		// TODO Auto-generated method stub
		// 读取并返回服务器上的帐单信息
		new SearchAllDetailTask().execute();
	}

	private class SearchAllDetailTask extends
			AsyncTask<Void, Void, ArrayList<Detail>> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
		}

		@Override
		protected ArrayList<Detail> doInBackground(Void... params) {

			ArrayList<Detail> detailList = new ArrayList<Detail>();
			ArrayList<Detail> allDetailList = new ArrayList<Detail>();

			// 获取所有帐单
			allDetailList = Detailservice.getAllDetail();

			// 通过账户号挑选该用户的所有账单

			for (Detail bean : allDetailList) {
				if (myAccount.getA_ID().equals(bean.getA_ID())) {
					detailList.add(bean);
				}
			}

			return detailList;
		}

		@Override
		protected void onPostExecute(ArrayList<Detail> result) {
			super.onPostExecute(result);
			// 回调
			onSearchAllDetailComplete(result);
		}
	}

	private void onSearchAllDetailComplete(ArrayList<Detail> result) {

		Toast.makeText(MyBillActivity.this, "您的所有账户信息成功", Toast.LENGTH_SHORT)
				.show();
		flashDetailList(result);

	}

	// 刷新账单表
	private void flashDetailList(ArrayList<Detail> list) {

		String[] title = { "    交易ID    ", "     交易金额         ", "     交易时间        ", "     交易类型       "  };

		TableRow tableRowTitle = new TableRow(this);
		for (int col = 0; col < 4; col++) {
			TextView tv = new TextView(this); // 每次都要创建新对象
			tv.setText(title[col]);
			tableRowTitle.addView(tv);
		}

		tableLayout
				.addView(tableRowTitle, new TableLayout.LayoutParams(FP, WC));

		for (int row = 0; row < list.size(); row++) // 取得数据库中的账单
		{
			TableRow tableRow = new TableRow(this);
			Detail elem = new Detail();
			elem = list.get(row);// 取得对象

			TextView tv1 = new TextView(this);// 定义文本视图
			tv1.setText("   "+elem.getD_ID());
			tableRow.addView(tv1);

			TextView tv2 = new TextView(this);
			tv2.setText("   "+elem.getD_Money());
			tableRow.addView(tv2);

			TextView tv3 = new TextView(this);
			tv3.setText("   "+elem.getD_Datetime());
			tableRow.addView(tv3);

			TextView tv4 = new TextView(this);
			tv4.setText("   "+elem.getD_Type());
			tableRow.addView(tv4);

			tableLayout.addView(tableRow, new TableLayout.LayoutParams(FP, WC));
		}
	}

}