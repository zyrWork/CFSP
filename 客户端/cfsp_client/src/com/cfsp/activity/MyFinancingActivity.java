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

	private ImageButton btnReturn = null; // 返回
	private ImageButton btnSearch = null; // 搜索

	private EditText etInput; // 输入理财产品
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
			executeSearchProductByName(); // 搜索所有订单放在allList对象数组中
			}
		});
	}

	private void executeSearchProductByName() {
	
		// 读取并返回查询的产品信息
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
					return bean; // 收集该用户所有订单
				}
			}
			return null; // 此时没有搜索到

		}

		@Override
		protected void onPostExecute(Product result) {
			super.onPostExecute(result);
			// 回调
			onSearchProductByNameComplete(result);
		}

		private void onSearchProductByNameComplete(Product result) {
			// TODO Auto-generated method stub

			if(result==null)
			{
				Toast.makeText(MyFinancingActivity.this, "暂无该商品！",
						Toast.LENGTH_SHORT).show();
				
			}
			else
			{
			  Toast.makeText(MyFinancingActivity.this, "搜索该商品成功",
					Toast.LENGTH_SHORT).show();

			  ProductActivity.actionStart(MyFinancingActivity.this, result);
			}

		}

	}

}