package com.cfsp.activity;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.cfsp.entity.Orders;
import com.cfsp.entity.Product;
import com.cfsp.service.OrdersService;
import com.cfsp.utils.GlobalVariable;
import com.cfsp.utils.WebUtil;
import com.cfsp.service.ProductService;
import com.cfsp_client.R;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class MyFinancingActivity extends Activity {

	private ImageButton btnReturn = null; // ����
	private ImageButton btnSearch = null; // ����

	private EditText etInput; // ������Ʋ�Ʒ
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.my_financing);
		btnReturn = (ImageButton) findViewById(R.id.btnReturn);
		btnSearch = (ImageButton) findViewById(R.id.bt_search);
	
		etInput = (EditText) this.findViewById(R.id.etInput);

		btnReturn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(MyFinancingActivity.this,MainPageActivity.class);
				startActivity(intent);
				
			}
		});

		btnSearch.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
			executeSearchProductByName(); // �������ж�������allList����������
			}
		});
	}

	private void executeSearchProductByName() {
	
		// ��ȡ�����ز�ѯ�Ĳ�Ʒ��Ϣ
		 new SearchProductByNameTask().execute();
	}

	private class SearchProductByNameTask extends
			AsyncTask<Void, Void, Product> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
		}

		@Override
		protected Product doInBackground(Void... params) {

			ArrayList<Product> allProduct = new ArrayList<Product>();

			allProduct = ProductService.getAllProduct();

			for (Product bean : allProduct) {
				if (etInput.getText().toString().equals(bean.getP_Name())) {
					return bean; // �ռ����û����ж���
				}
			}
			return null; // ��ʱû��������

		}

		@Override
		protected void onPostExecute(Product result) {
			super.onPostExecute(result);
			// �ص�
			onSearchProductByNameComplete(result);
		}

		private void onSearchProductByNameComplete(Product result) {
			// TODO Auto-generated method stub

			if(result==null)
			{
				Toast.makeText(MyFinancingActivity.this, "���޸���Ʒ��",
						Toast.LENGTH_SHORT).show();
				
			}
			else
			{
			  Toast.makeText(MyFinancingActivity.this, "��������Ʒ�ɹ�",
					Toast.LENGTH_SHORT).show();

			  ProductActivity.actionStart(MyFinancingActivity.this, result);
			}

		}

	}

}