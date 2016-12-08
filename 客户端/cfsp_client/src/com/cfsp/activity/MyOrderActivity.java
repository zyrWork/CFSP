package com.cfsp.activity;

import java.math.BigDecimal;
import java.util.ArrayList;

import com.cfsp.entity.Account;
import com.cfsp.entity.Orders;
import com.cfsp.service.Accountservice;
import com.cfsp.service.OrdersService;
import com.cfsp.utils.GlobalVariable;
import com.cfsp_client.R;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;

@SuppressLint("UseValueOf")
public class MyOrderActivity extends Activity {

	Button btn_paid;
	Button btn_wait_to_pay;
	Button btn_payMoney;
	TableLayout tableLayout;

	Boolean canNotPay = false;

	TextView txt_show_total_price;
	private final int WC = ViewGroup.LayoutParams.WRAP_CONTENT;
	private final int FP = ViewGroup.LayoutParams.FILL_PARENT;

	ArrayList<Orders> allList = new ArrayList<Orders>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.my_order);
		ImageButton btn_ret = (ImageButton) findViewById(R.id.btn_ret);
		// ��ʼ��

		allList = new ArrayList<Orders>();
		btn_paid = (Button) findViewById(R.id.btn_paid);
		btn_wait_to_pay = (Button) findViewById(R.id.btn_wait_to_pay);
		btn_payMoney = (Button) findViewById(R.id.btn_payMoney);

		txt_show_total_price = (TextView) findViewById(R.id.txt_show_total_price);

		tableLayout = (TableLayout) findViewById(R.id.TableLayout_myOrder);

		tableLayout.setStretchAllColumns(true);

		btn_ret.setOnClickListener(new OnClickListener() {
			// ����
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MyOrderActivity.this,
						MainPageActivity.class);
				startActivity(intent);
			}
		});

		btn_paid.setOnClickListener(new OnClickListener() {
			// ��ѯ�Ѹ����˵�
			@Override
			public void onClick(View v) {
				executeSearchHavePaidOrder();
			}
		});

		btn_wait_to_pay.setOnClickListener(new OnClickListener() {
			// ��ѯ�������˵�
			@Override
			public void onClick(View v) {
				executeSearchNeedToPayOrder();
			}
		});
		btn_payMoney.setOnClickListener(new OnClickListener() {
			// ����
			@Override
			public void onClick(View v) {

				executePayForAllOrder();

			}
		});
		executeSearchAllOrder(); // �������ж�������allList����������

	}

	private void executeSearchAllOrder() {
		// TODO Auto-generated method stub
		// ��ȡ�����ط������ϵĶ�����Ϣ
		new SearchAllOrderTask().execute();
	}

	private class SearchAllOrderTask extends
			AsyncTask<Void, Void, ArrayList<Orders>> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
		}

		@Override
		protected ArrayList<Orders> doInBackground(Void... params) {

			ArrayList<Orders> allOrders = new ArrayList<Orders>();
			ArrayList<Orders> result = new ArrayList<Orders>();
			allOrders = OrdersService.getAllOrder();
			// �����ж����й��˳��Լ��Ķ�����Ϣ

			for (Orders bean : allOrders) {
				if (GlobalVariable.USER_ID != null
						&& GlobalVariable.USER_ID.equals(bean.getC_Number())) {
					result.add(bean);// �ռ����û����ж���
				}
			}
			return result;
		}

		@Override
		protected void onPostExecute(ArrayList<Orders> result) {
			super.onPostExecute(result);
			// �ص�
			onSearchAllOrderComplete(result);
		}
	}

	private void onSearchAllOrderComplete(ArrayList<Orders> result) {

		allList = result; // ���ж���

		Toast.makeText(MyOrderActivity.this, "��ѯ���û����ж�����Ϣ�ɹ�",
				Toast.LENGTH_SHORT).show();
		flashOrderList(allList);
		double all_O_Money = 0.0;
		for (Orders bean : allList) {
			// ͬʱ����ö������ܼ۲������ø��ť
			all_O_Money += bean.getO_Money();
		}
		BigDecimal bg = new BigDecimal(all_O_Money);
	    all_O_Money = bg.setScale(2, BigDecimal.ROUND_DOWN).doubleValue();
		txt_show_total_price.setText(new Double(all_O_Money).toString());
		btn_payMoney.setVisibility(View.INVISIBLE);

	}

	// ˢ�¶�����
	private void flashOrderList(ArrayList<Orders> list) { 
		String[] title = { "   ������    ", "    ��������       ", "   ��      ��        ", 
				          "   ��Ʒ����      ", "        ��Ʒ����                      ", "       �Ƿ񸶿�         " };

		TableRow tableRowTitle = new TableRow(this);
		for (int col = 0; col < 6; col++) {
			TextView tv = new TextView(this); // ÿ�ζ�Ҫ�����¶���
			tv.setText(title[col]);
			tableRowTitle.addView(tv);
		}

		tableLayout
				.addView(tableRowTitle, new TableLayout.LayoutParams(FP, WC));

		for (int row = 0; row < list.size(); row++) // ȡ�����ݿ��еĶ���
		{
			TableRow tableRow = new TableRow(this);
			Orders elem = new Orders();
			elem = list.get(row);// ȡ�ö���

			TextView tv1 = new TextView(this);// �����ı���ͼ
			tv1.setText("  "+elem.getO_Number());
			tableRow.addView(tv1);

			TextView tv2 = new TextView(this);
			tv2.setText("  "+elem.getO_Date());
			tableRow.addView(tv2);

			TextView tv3 = new TextView(this);
			tv3.setText("  "+new Double(elem.getO_Money()).toString());
			tableRow.addView(tv3);

			TextView tv4 = new TextView(this);
			tv4.setText("  "+elem.getO_ProductServe());
			tableRow.addView(tv4);

			TextView tv5 = new TextView(this);
			tv5.setText("  "+elem.getO_Evaluate());
			tableRow.addView(tv5);

			TextView tv6 = new TextView(this);
			tv6.setText("  "+elem.getO_IsPayOff());
			tableRow.addView(tv6);

			tableLayout.addView(tableRow, new TableLayout.LayoutParams(FP, WC));
		}
	}

	private void executeSearchHavePaidOrder() {
		// ���������ʾ������Ϣ
		tableLayout.removeAllViewsInLayout();

		ArrayList<Orders> HavePaidOrderList = new ArrayList<Orders>();

		for (Orders bean : allList) {
			if ("true".equals(bean.getO_IsPayOff())) {
				// ��ѯ���Ѹ�����Ϣ
				HavePaidOrderList.add(bean);
			}
		}
		flashOrderList(HavePaidOrderList);

		double all_O_Money = 0.0;
		for (Orders bean : HavePaidOrderList) {
			// ͬʱ����ö������ܼ۲��ҽ�ֹ���ť
			all_O_Money += bean.getO_Money();
		}
		BigDecimal bg = new BigDecimal(all_O_Money);
	    all_O_Money = bg.setScale(2, BigDecimal.ROUND_DOWN).doubleValue();
		txt_show_total_price.setText(new Double(all_O_Money).toString());
		btn_payMoney.setVisibility(View.INVISIBLE);
	}

	private void executeSearchNeedToPayOrder() {
		// ���������ʾ������Ϣ
		tableLayout.removeAllViewsInLayout();

		ArrayList<Orders> NeedToPayOrderList = new ArrayList<Orders>();
		for (Orders bean : allList) {
			if ("false".equals(bean.getO_IsPayOff())) {
				NeedToPayOrderList.add(bean);
			}
		}
		flashOrderList(NeedToPayOrderList);

		double all_O_Money = 0.0;
		for (Orders bean : NeedToPayOrderList) {
			// ͬʱ����ö������ܼ۲������ø��ť
			all_O_Money += bean.getO_Money();
		}
		BigDecimal bg = new BigDecimal(all_O_Money);
	    all_O_Money = bg.setScale(2, BigDecimal.ROUND_DOWN).doubleValue();
		txt_show_total_price.setText(new Double(all_O_Money).toString());
		btn_payMoney.setVisibility(View.VISIBLE);
	}

	/**************************************************/

	private void executePayForAllOrder() {
		// TODO Auto-generated method stub
		// ��ȡ�����ط������ϵĶ�����Ϣ
		new PayForAllOrderTask().execute();
	}

	private class PayForAllOrderTask extends AsyncTask<Void, Void, Void> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
		}

		@Override
		protected Void doInBackground(Void... params) {

			ArrayList<Orders> payOrders = new ArrayList<Orders>();

			double all_O_Money = 0.0;

			for (Orders bean : allList) {
				if ("false".equals(bean.getO_IsPayOff())) {
					bean.setO_IsPayOff("true");
					payOrders.add(bean);
					all_O_Money += bean.getO_Money();// ��¼�����ܶ�
				}
			}

			// Ȼ���û����ʽ������Ӧ�Ľ��
			// �������˻�
			String id = GlobalVariable.USER_ID; // ����ID,������Ϣ
			Account account = new Account();

			ArrayList<Account> list = new ArrayList<Account>();
			list = Accountservice.getAllAccount();
			for (int i = 0; i < list.size(); i++) {
				Account bean = new Account();
				bean = list.get(i);

				if (id.equals(bean.getC_Number())) {// ��IDƥ������ҵ��û�
					account = bean;
					break;
				}
			}
			if (Double.parseDouble(account.getA_Balance()) >= all_O_Money) {// ��������֧��
				for (Orders bean : payOrders) {
					OrdersService.updateOrder(bean);// �޸����ݿ�
				}
				account.setA_Balance(new Double(Double.parseDouble(account
						.getA_Balance()) - all_O_Money).toString());
				Accountservice.updateAccount(account);
			} else {
				canNotPay = true;
			}
			return null;
		}

		@Override
		protected void onPostExecute(Void v) {
			super.onPostExecute(v);
			// �ص�
			onPayForAllOrderComplete();
		}
	}

	private void onPayForAllOrderComplete() {

		if (canNotPay == false) {
			Toast.makeText(MyOrderActivity.this, "���ж���֧���ɹ�������",
					Toast.LENGTH_LONG).show();
			executeSearchNeedToPayOrder();// ˢ�½���
		} else {
			Toast.makeText(MyOrderActivity.this, "�������㣬���ֵ������",
					Toast.LENGTH_LONG).show();
		}
	}

}