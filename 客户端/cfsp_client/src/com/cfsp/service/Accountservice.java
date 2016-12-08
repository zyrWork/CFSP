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
	// ����˵Ľӿ�URL��ַ
		// ��ȡ����
		private static String getUrl = "http://"+GlobalVariable.IP+":8080/CommunityFinancialServicesPlatformServer/AccountServlet?method=getData";
		// ��������
		private static String postUrl = "http://"+GlobalVariable.IP+":8080/CommunityFinancialServicesPlatformServer/AccountServlet?method=addData";
		// �޸�����
		private static String updUrl = "http://"+GlobalVariable.IP+":8080/CommunityFinancialServicesPlatformServer/AccountServlet?method=updateData";
		// ɾ������
		private static String delUrl = "http://"+GlobalVariable.IP+":8080/CommunityFinancialServicesPlatformServer/AccountServlet?method=deleteData";

		public static ArrayList<Account> getAllAccount() {
			// ��ȡ����
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
				System.out.println("Json ת������!");
				e.printStackTrace();
			}
			return list;
		}

		public static void insertAccount(Account bean) {
			// ��������
			try {
				// ����һ��JSON��������������ύ����
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
			// ��������
			try {
				// ����һ��JSON��������������ύ����
				JSONArray reqValue = new JSONArray();
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("C_Number", bean.getC_Number());
				jsonObject.put("A_Balance", bean.getA_Balance());
				jsonObject.put("A_ID", bean.getA_ID());

				
				reqValue.put(jsonObject);
				String postData = reqValue.toString();
				System.out.println("postData--" + postData);
				//��Ҫһ��
				String postResult = WebUtil.setJsonData(updUrl, postData);

				System.out.println("postResult--" + postResult);
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}

		public static void deleteAccount(Account bean) {
			// ɾ������
			try {
				// ����һ��JSON��������������ύ����
				JSONArray reqValue = new JSONArray();
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("C_Number", bean.getC_Number());
				jsonObject.put("A_Balance", bean.getA_Balance());
				jsonObject.put("A_ID", bean.getA_ID());

				reqValue.put(jsonObject);
				String postData = reqValue.toString();
				System.out.println("postData--" + postData);
				//��Ҫһ��
				String postResult = WebUtil.setJsonData(delUrl, postData);

				System.out.println("postResult--" + postResult);
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}

	}