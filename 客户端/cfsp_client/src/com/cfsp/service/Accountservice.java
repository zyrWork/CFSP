package com.cfsp.service;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.cfsp.entity.Account;
import com.cfsp.utils.GlobalVariable;
import com.cfsp.utils.HttpRequestImpl;
import com.cfsp.utils.WebUtil;

import android.annotation.SuppressLint;


@SuppressLint("NewApi")
public class Accountservice {
	// 服务端的接口URL地址
		// 获取数据
		private static String getUrl = "http://"+GlobalVariable.IP+":8080/CommunityFinancialServicesPlatformServer/AccountServlet?method=getData";
		// 增加数据
		private static String postUrl = "http://"+GlobalVariable.IP+":8080/CommunityFinancialServicesPlatformServer/AccountServlet?method=addData";
		// 修改数据
		private static String updUrl = "http://"+GlobalVariable.IP+":8080/CommunityFinancialServicesPlatformServer/AccountServlet?method=updateData";
		// 删除数据
		private static String delUrl = "http://"+GlobalVariable.IP+":8080/CommunityFinancialServicesPlatformServer/AccountServlet?method=deleteData";

		public static ArrayList<Account> getAllAccount() {
			// 获取数据
			//String requestResult = WebUtil.getAllData(getUrl);
			
			
			String requestResult = HttpRequestImpl.doGet(getUrl);
			System.out.println("requestResult--" + requestResult);
		
			ArrayList<Account> list = new ArrayList<Account>();
			try {
				JSONArray jsonArray = new JSONArray(requestResult);
				for (int i = 0; i < jsonArray.length(); i++) {
					JSONObject jsonObj = jsonArray.getJSONObject(i);
					Account bean = new Account();
					bean.setC_Number(jsonObj.getString("C_Number"));
					bean.setA_Balance(jsonObj.getString("A_Balance"));
					bean.setA_ID(jsonObj.getString("A_ID"));
					
					list.add(bean);
				}
			} catch (JSONException e) {
				System.out.println("Json 转换错误!");
				e.printStackTrace();
			}
			return list;
		}

		public static void insertAccount(Account bean) {
			// 增加数据
			try {
				// 定义一个JSON，用于向服务器提交数据
				JSONArray reqValue = new JSONArray();
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("C_Number", bean.getC_Number());
				jsonObject.put("A_Balance", bean.getA_Balance());
				jsonObject.put("A_ID", bean.getA_ID());

				reqValue.put(jsonObject);
				String postData = reqValue.toString();
				System.out.println("postData--" + postData);
				
				String postResult = WebUtil.setJsonData(postUrl, postData);
				System.out.println("postResult--" + postResult);
				
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}

		public static void updateAccount(Account bean) {
			// 更新数据
			try {
				// 定义一个JSON，用于向服务器提交数据
				JSONArray reqValue = new JSONArray();
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("C_Number", bean.getC_Number());
				jsonObject.put("A_Balance", bean.getA_Balance());
				jsonObject.put("A_ID", bean.getA_ID());

				
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

		public static void deleteAccount(Account bean) {
			// 删除数据
			try {
				// 定义一个JSON，用于向服务器提交数据
				JSONArray reqValue = new JSONArray();
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("C_Number", bean.getC_Number());
				jsonObject.put("A_Balance", bean.getA_Balance());
				jsonObject.put("A_ID", bean.getA_ID());

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