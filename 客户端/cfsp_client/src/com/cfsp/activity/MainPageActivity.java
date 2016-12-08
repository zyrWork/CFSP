package com.cfsp.activity;

import com.cfsp_client.R;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;

@SuppressLint("NewApi")
public class MainPageActivity extends FragmentActivity {

	public static void actionStart(Context context, String C_Number) {
		Intent intent = new Intent(context, MainPageActivity.class);
		intent.putExtra("C_Number", C_Number);

		context.startActivity(intent);
	}

	private FragmentTabHost mTabHost;

	private LayoutInflater layoutInflater;

	@SuppressWarnings("rawtypes")
	private Class fragmentArray[] = { HomePageActivity.class, MyGoldenWelHomeActivity.class,
			TwoDimensionalCodeActivity.class };

	private int mImageViewArray[] = { R.drawable.home, R.drawable.jhj, R.drawable.ewm, };

	private String mTextviewArray[] = { "新闻", "我的金惠家", "二维码", };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_page);
		initView();
	}

	private void initView() {

		layoutInflater = LayoutInflater.from(this);

		mTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
		mTabHost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);

		int count = fragmentArray.length;

		for (int i = 0; i < count; i++) {

			TabSpec tabSpec = mTabHost.newTabSpec(mTextviewArray[i]).setIndicator(getTabItemView(i));

			mTabHost.addTab(tabSpec, fragmentArray[i], null);

			mTabHost.getTabWidget().getChildAt(i).setBackgroundResource(R.drawable.main_tab_item_bg);
		}
	}

	private View getTabItemView(int index) {
		View view = layoutInflater.inflate(R.layout.tab_item_view, null);

		ImageView imageView = (ImageView) view.findViewById(R.id.imageview);
		imageView.setImageResource(mImageViewArray[index]);

		TextView textView = (TextView) view.findViewById(R.id.textview);
		textView.setText(mTextviewArray[index]);

		return view;
	}
}
