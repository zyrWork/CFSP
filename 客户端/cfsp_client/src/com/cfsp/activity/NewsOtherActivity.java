package com.cfsp.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cfsp.entity.News;
import com.cfsp.service.NewsService;
import com.cfsp_client.R;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
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
import android.widget.AbsListView.OnScrollListener;
import android.widget.SimpleAdapter;
import android.widget.Toast;

public class NewsOtherActivity extends Activity implements OnItemClickListener,
		OnScrollListener {

	ArrayList<News> otherNewslist;

	private ListView listview;
	private SimpleAdapter simp_adapter;
	private List<Map<String, Object>> datalist;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.news_other);

		listview = (ListView) findViewById(R.id.listview_news_other);

		datalist = new ArrayList<Map<String, Object>>();

		otherNewslist = new ArrayList<News>();

		ImageButton other_ret = (ImageButton) findViewById(R.id.other_ret);
		other_ret.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(NewsOtherActivity.this,
						MainPageActivity.class);
				startActivity(intent);

			}
		});
		executeSearchAllNews();

	}

	// �������Դ����
	private List<Map<String, Object>> getData() {

		for (News news : otherNewslist) {
			Map<String, Object> map0 = new HashMap<String, Object>();

			map0.put("N_Title", "    " + news.getN_Title() + "   ");
			map0.put("N_ID", news.getN_ID());

			datalist.add(map0);
		}
		return datalist;
	}

	private void executeSearchAllNews() {
		new SearchAllNewsTask().execute();
	}

	private class SearchAllNewsTask extends
			AsyncTask<Void, Void, ArrayList<News>> {
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
		}

		@Override
		protected ArrayList<News> doInBackground(Void... params) {

			ArrayList<News> list = new ArrayList<News>();
			ArrayList<News> result = new ArrayList<News>();
			list = NewsService.getAllNews();
			// ɸѡ������������
			for (News news : list) {
				if (news.getN_Code().equals("other")) {
					result.add(news);
				}
			}

			return result;
		}

		@Override
		protected void onPostExecute(ArrayList<News> result) {

			super.onPostExecute(result);
			// �ص�
			onSearchAllNewsComplete(result);
		}
	}

	private void onSearchAllNewsComplete(ArrayList<News> result) {
		if (result == null) {
			Toast.makeText(NewsOtherActivity.this, "���޸������ţ�",
					Toast.LENGTH_SHORT).show();
		} else {
			otherNewslist = result;
			Toast.makeText(NewsOtherActivity.this, "���Ų�ѯ�ɹ���",
					Toast.LENGTH_SHORT).show();
			//��ʱ��ʼ��������
			simp_adapter = new SimpleAdapter(this, getData(),
					R.layout.news_item, new String[] { "N_Title", "N_ID" },
					new int[] { R.id.N_Title, R.id.N_ID });

			listview.setAdapter(simp_adapter);
			listview.setOnItemClickListener(this);
			listview.setOnScrollListener(this);
		}
	}

	public void onScrollStateChange(NumberPicker arg0, int arg1) {

	}

	// �б�����
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub
		ListView listview = (ListView) parent;
		@SuppressWarnings("unchecked")
		HashMap<String, Object> datalist = (HashMap<String, Object>) listview
				.getItemAtPosition(position);
		String N_ID = datalist.get("N_ID").toString();
		Toast.makeText(getApplicationContext(), N_ID, Toast.LENGTH_SHORT)
				.show();
		//��ת������ʵ��ҳ��,����ʵ�ʲ���
		NewsBodyActivity.actionStart(NewsOtherActivity.this, N_ID,otherNewslist);

	}

	@Override
	public void onScroll(AbsListView arg0, int arg1, int arg2, int arg3) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onScrollStateChanged(AbsListView arg0, int arg1) {
		// TODO Auto-generated method stub
		switch (arg1) {
		case SCROLL_STATE_FLING:
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("N_Title", otherNewslist.get(0).getN_Title());
			map.put("N_ID", otherNewslist.get(0).getN_ID());
			datalist.add(map);
			simp_adapter.notifyDataSetChanged();
			break;

		case SCROLL_STATE_IDLE:

			break;

		case SCROLL_STATE_TOUCH_SCROLL:

			break;

		}
	}

	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
