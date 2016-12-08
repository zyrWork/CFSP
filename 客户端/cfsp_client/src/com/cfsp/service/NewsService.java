package com.cfsp.service;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.cfsp.entity.News;
import com.cfsp.utils.GlobalVariable;
import com.cfsp.utils.HttpRequestImpl;
import com.cfsp.utils.WebUtil;



public class NewsService {
	// 服务端的接口URL地址
		// 获取数据
		private static String getUrl = "http://"+GlobalVariable.IP+":8080/CommunityFinancialServicesPlatformServer/NewsServlet?method=getData";
		// 增加数据
		private static String postUrl = "http://"+GlobalVariable.IP+":8080/CommunityFinancialServicesPlatformServer/NewsServlet?method=addData";
		// 修改数据
		private static String updUrl = "http://"+GlobalVariable.IP+":8080/CommunityFinancialServicesPlatformServer/NewsServlet?method=updateData";
		// 删除数据
		private static String delUrl = "http://"+GlobalVariable.IP+":8080/CommunityFinancialServicesPlatformServer/NewsServlet?method=deleteData";

		public static ArrayList<News> getAllNews() {
			// 获取数据
			//String requestResult = WebUtil.getAllData(getUrl);
			
			String requestResult = HttpRequestImpl.doGet(getUrl);
			System.out.println("requestResult--" + requestResult);
			
			ArrayList<News> list = new ArrayList<News>();
			try {
				JSONArray jsonArray = new JSONArray(requestResult);
				for (int i = 0; i < jsonArray.length(); i++) {
					JSONObject jsonObj = jsonArray.getJSONObject(i);
					News ne = new News();
					ne.setN_ID(jsonObj.getString("N_ID"));
					ne.setN_Code(jsonObj.getString("N_Code"));
					ne.setN_Title(jsonObj.getString("N_Title"));
					ne.setN_Info(jsonObj.getString("N_Info"));
					ne.setN_Publisher(jsonObj.getString("N_Publisher"));

					ne.setN_PubTime(jsonObj.getString("N_PubTime"));
					ne.setN_BuyLink(jsonObj.getString("N_BuyLink"));
					ne.setN_OrderLink(jsonObj.getString("N_OrderLink"));
					ne.setN_Picture(jsonObj.getString("N_Picture"));
					list.add(ne);
				}
			} catch (JSONException e) {
				System.out.println("Json 转换错误!");
				e.printStackTrace();
			}
			return list;
		}

		public static void insertNews(News bean) {
			// 增加数据
			try {
				// 定义一个JSON，用于向服务器提交数据
				JSONArray reqValue = new JSONArray();
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("N_ID", bean.getN_ID());
				jsonObject.put("N_Code", bean.getN_Code());
				jsonObject.put("N_Title", bean.getN_Title());
				jsonObject.put("N_Info", bean.getN_Info());
				jsonObject.put("N_Publisher", bean.getN_Publisher());
				jsonObject.put("N_PubTime", bean.getN_PubTime());
				jsonObject.put("N_BuyLink", bean.getN_BuyLink());
				jsonObject.put("N_OrderLink", bean.getN_OrderLink());
				jsonObject.put("N_Picture", bean.getN_Picture());
				reqValue.put(jsonObject);
				String postData = reqValue.toString();
				System.out.println("postData--" + postData);				
				String postResult = WebUtil.setJsonData(postUrl, postData);
				
				System.out.println("postResult--" + postResult);
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}

		public static void updateNews(News bean) {
			// 更新数据
			try {
				// 定义一个JSON，用于向服务器提交数据
				JSONArray reqValue = new JSONArray();
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("N_ID", bean.getN_ID());
				jsonObject.put("N_Code", bean.getN_Code());
				jsonObject.put("N_Title", bean.getN_Title());
				jsonObject.put("N_Info", bean.getN_Info());
				jsonObject.put("N_Publisher", bean.getN_Publisher());
				jsonObject.put("N_PubTime", bean.getN_PubTime());
				jsonObject.put("N_BuyLink", bean.getN_BuyLink());
				jsonObject.put("N_OrderLink", bean.getN_OrderLink());
				jsonObject.put("N_Picture", bean.getN_Picture());
				reqValue.put(jsonObject);
				String postData = reqValue.toString();
				System.out.println("postData--" + postData);
				//重要一步
				String postResult = WebUtil.setJsonData(updUrl, postData);

				System.out.println("postResult--" + postResult);
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}

		public static void deleteNews(News bean) {
			// 删除数据
			try {
				// 定义一个JSON，用于向服务器提交数据
				JSONArray reqValue = new JSONArray();
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("N_ID", bean.getN_ID());
				jsonObject.put("N_Code", bean.getN_Code());
				jsonObject.put("N_Title", bean.getN_Title());
				jsonObject.put("N_Info", bean.getN_Info());
				jsonObject.put("N_Publisher", bean.getN_Publisher());
				jsonObject.put("N_PubTime", bean.getN_PubTime());
				jsonObject.put("N_BuyLink", bean.getN_BuyLink());
				jsonObject.put("N_OrderLink", bean.getN_OrderLink());
				jsonObject.put("N_Picture", bean.getN_Picture());
				reqValue.put(jsonObject);
				String postData = reqValue.toString();
				System.out.println("postData--" + postData);
				//重要一步
				String postResult = WebUtil.setJsonData(delUrl, postData);

				System.out.println("postResult--" + postResult);
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}

}
