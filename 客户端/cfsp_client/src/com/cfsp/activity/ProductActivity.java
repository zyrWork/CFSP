package com.cfsp.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cfsp.entity.Product;
import com.cfsp.utils.GlobalVariable;
import com.cfsp_client.R;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.NumberPicker;
import android.widget.AbsListView;
import android.widget.Toast;
import android.widget.AbsListView.OnScrollListener;
import android.widget.SimpleAdapter;

public class ProductActivity extends Activity implements OnItemClickListener, OnScrollListener {

	public static void actionStart(Context context, Product product) {
		Intent intent = new Intent(context, ProductActivity.class);
	
		GlobalVariable.p = product;
		context.startActivity(intent);
	}

	private Button invest_now;
	private ListView listview;
	
	private SimpleAdapter simp_adapter;
	private List<Map<String, Object>> datalist;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.products);
		listview = (ListView) findViewById(R.id.listview_products);
		
		invest_now=(Button) findViewById(R.id.invest_now);
		ImageButton product_ret=(ImageButton) findViewById(R.id.product_ret);
		product_ret.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(ProductActivity.this,
						MyFinancingActivity.class);
				startActivity(intent);
			}
		
	 });
		datalist = new ArrayList<Map<String, Object>>();

		simp_adapter = new SimpleAdapter(this, getData(), R.layout.items, new String[] { "pic1", "text1" },
				new int[] { R.id.textView_part1, R.id.textView_part2 });

		listview.setAdapter(simp_adapter);
		listview.setOnItemClickListener(this);
		listview.setOnScrollListener(this);
		
		invest_now.setOnClickListener(new OnClickListener() {
			// 注册按钮点击事件
			@Override
			public void onClick(View v) {
				Toast.makeText(ProductActivity.this, "已投资成功",
						Toast.LENGTH_SHORT).show();
			}
		});
	}

	// 添加数据源函数
	private List<Map<String, Object>> getData() {

		Map<String, Object> map1 = new HashMap<String, Object>();
		map1.put("pic1", "产品编号         ");
		map1.put("text1", GlobalVariable.p.getP_Number());
		datalist.add(map1);

		Map<String, Object> map2 = new HashMap<String, Object>();
		map2.put("pic1", "产品说明      ");
		map2.put("text1", GlobalVariable.p.getP_Explain());
		datalist.add(map2);

		Map<String, Object> map3 = new HashMap<String, Object>();
		map3.put("pic1", "所属机构      ");
		map3.put("text1", GlobalVariable.p.getP_BelongTo());
		datalist.add(map3);

		Map<String, Object> map4 = new HashMap<String, Object>();
		map4.put("pic1", "产品代码     ");
		map4.put("text1", GlobalVariable.p.getP_Code());
		datalist.add(map4);
		
		Map<String, Object> map5 = new HashMap<String, Object>();
		map5.put("pic1", "产品期限     ");
		map5.put("text1", GlobalVariable.p.getP_Deadline());
		datalist.add(map5);
		
		Map<String, Object> map6 = new HashMap<String, Object>();
		map6.put("pic1", "年化收益率     ");
		map6.put("text1", GlobalVariable.p.getP_AnnualizedReturn());
		datalist.add(map6);
		
		Map<String, Object> map7 = new HashMap<String, Object>();
		map7.put("pic1", "认购起点     ");
		map7.put("text1", GlobalVariable.p.getP_SubscriptionPoint());
		datalist.add(map7);
		
		Map<String, Object> map8 = new HashMap<String, Object>();
		map8.put("pic1", "预期收益值     ");
		map8.put("text1", GlobalVariable.p.getP_ExpectedRate());
		datalist.add(map8);

		Map<String, Object> map9 = new HashMap<String, Object>();
		map9.put("pic1", "产品类别         ");
		map9.put("text1", GlobalVariable.p.getP_Category());
		datalist.add(map9);

		Map<String, Object> map10 = new HashMap<String, Object>();
		map10.put("pic1", "投资类别        ");
		map10.put("text1", GlobalVariable.p.getP_InvestKind());
		datalist.add(map10);

		Map<String, Object> map11 = new HashMap<String, Object>();
		map11.put("pic1", "投资风格         ");
		map11.put("text1", GlobalVariable.p.getP_InvestStyle());
		datalist.add(map11);

		Map<String, Object> map12 = new HashMap<String, Object>();
		map12.put("pic1", "发行开始日期         ");
		map12.put("text1", GlobalVariable.p.getP_DateBegin());
		datalist.add(map12);

		Map<String, Object> map13 = new HashMap<String, Object>();
		map13.put("pic1", "发行结束日期         ");
		map13.put("text1", GlobalVariable.p.getP_DateEnd());
		datalist.add(map13);

		Map<String, Object> map14 = new HashMap<String, Object>();
		map14.put("pic1", "风险评估         ");
		map14.put("text1", GlobalVariable.p.getP_InvestEvaluate());
		datalist.add(map14);

		return datalist;
	}

	public void onScrollStateChange(NumberPicker arg0, int arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onScroll(AbsListView arg0, int arg1, int arg2, int arg3) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onScrollStateChanged(AbsListView arg0, int arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		// TODO Auto-generated method stub

	}

}
