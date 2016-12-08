package com.cfsp.activity;

import java.util.ArrayList;

import com.cfsp.entity.Customer;
import com.cfsp.service.CustomerService;
import com.cfsp.utils.GlobalVariable;
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


public class MyScoreActivity extends Activity {

	TextView textView_show;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.my_score);
		ImageButton scoreBtn = (ImageButton) findViewById(R.id.bt_getscore);
		scoreBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MyScoreActivity.this,
						                   GetScoreActivity.class);
				startActivity(intent);
			}
		});

		ImageButton returnBtn = (ImageButton) findViewById(R.id.bt_returnjhj);
		returnBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MyScoreActivity.this,
						MainPageActivity.class);
				startActivity(intent);
			}
		});
		
		 textView_show= (TextView) findViewById(R.id.textView_show);
		 executeAddIntegration( ) ;	
		
	}
	private void executeAddIntegration( ) {
		new AddIntegrationTask().execute();
	}

	private class AddIntegrationTask extends AsyncTask<Void, Void, Customer> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
		}

		@Override
		protected Customer doInBackground(Void... arg0) {
			// TODO Auto-generated method stub
			Customer cu = new Customer();
			Customer bean = new Customer();
			ArrayList<Customer> list = new ArrayList<Customer>();
			list=CustomerService.getAllCustomer();
			
			for( int i=0;i<list.size();i++)
			{
				bean=list.get(i);
				
				if(GlobalVariable.USER_ID.equals(bean.getC_Number()))
				{
					cu=bean;
					break;
				}
			}
			return cu;
		}
		@Override
		protected void onPostExecute(Customer result) {
			super.onPostExecute(result);
			// 回调
			onAddIntegrationComplete(result);
		}
	}
	private void onAddIntegrationComplete(Customer result) {
		  
		 textView_show.setTextColor(android.graphics.Color.RED);
		 textView_show.setText("尊敬的"+result.getC_Username()+"客户:"+"\n"+
				               "   您当前的积分总数为:  "+result.getC_Integration()+"  !");
	}
}