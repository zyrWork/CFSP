package com.cfsp.activity;

import java.io.InputStream;
import java.util.ArrayList;

import com.cfsp.entity.News;
import com.cfsp.utils.WebUtil;
import com.cfsp_client.R;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class NewsBodyActivity extends Activity {

	private static ArrayList<News> someNewslist;// 该新闻可以是经济类、民生类。。。
	private static String newsID;
	ImageView N_Picture_Show;
	TextView txtView_show_news_info;

	News currentNews;
	// 从新闻容器中获取新闻
	public static void actionStart(Context context, String N_ID,
			ArrayList<News> news) {
		Intent intent = new Intent(context, NewsBodyActivity.class);
		someNewslist = news;
		newsID = N_ID;
		context.startActivity(intent);
	}

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.news_body);
		
		ImageButton ibtn_return = (ImageButton) findViewById(R.id.ibtn_return);

		N_Picture_Show = (ImageView) this.findViewById(R.id.N_Picture_Show);
		txtView_show_news_info= (TextView)this.findViewById(R.id.txtView_show_news_info);
		
		currentNews = new News();
		
		ibtn_return.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				NewsBodyActivity.this.finish();
			}
		});

		executeShowNews();
	}

	@SuppressWarnings("unchecked")
	private void executeShowNews() {

		new ShowNewsTask().execute();
	}

	private class ShowNewsTask extends AsyncTask<Void, Void, News> {

		@SuppressWarnings("null")
		protected News doInBackground(Void... urls) {

			News result = null;

			for (News news : someNewslist) {
				if (news.getN_ID().equals(newsID)) {
					result = news;
					break;
				}
			}
			return result;// 获得该新闻
		}

		protected void onPostExecute(News result) {
			super.onPostExecute(result);

			onShowNewsComplete(result);
		}
	}

	private void onShowNewsComplete(News result) {
		if (result == null) {
			Toast.makeText(NewsBodyActivity.this, "查找新闻失败", Toast.LENGTH_SHORT)
					.show();
			return;
		} else {
			Toast.makeText(NewsBodyActivity.this, "查找新闻成功", Toast.LENGTH_SHORT)
					.show();

			new LoadNewsTask().execute(result);
			//记录下此时的新闻
			currentNews=result;
			Toast.makeText(NewsBodyActivity.this, "正在加载图片", Toast.LENGTH_SHORT)
			.show();
		}
	}

	private class LoadNewsTask extends AsyncTask<News, Void, Bitmap> {

		@SuppressWarnings("null")
		protected Bitmap doInBackground(News... urls) {

			News n = urls[0];

			InputStream in = null;
			Bitmap bitmap = null;

			try {

				in = WebUtil.getImageViewInputStream(n.getN_Picture());

				bitmap = BitmapFactory.decodeStream(in);

			} catch (Exception e) {
				e.printStackTrace();
			}
			return bitmap;
		}

		protected void onPostExecute(Bitmap result) {
			super.onPostExecute(result);

			onLoadNewsComplete(result);
		}
	}

	private void onLoadNewsComplete(Bitmap result) {
		if (result == null) {
			Toast.makeText(NewsBodyActivity.this, "图片加载失败", Toast.LENGTH_SHORT)
					.show();
			return;
		} else {
			Toast.makeText(NewsBodyActivity.this, "图片加载成功", Toast.LENGTH_SHORT)
					.show();
			N_Picture_Show.setImageBitmap(result);
			txtView_show_news_info.setText(currentNews.getN_Info());
		}
	}

}
