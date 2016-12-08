package com.cfsp.service;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.cfsp.entity.Detail;
import com.cfsp.utils.GlobalVariable;
import com.cfsp.utils.HttpRequestImpl;
import com.cfsp.utils.WebUtil;

import android.annotation.SuppressLint;


@SuppressLint("NewApi")
public class Detailservice {
	// ����˵Ľӿ�URL��ַ
		// ��ȡ����
		private static String getUrl = "http://"+GlobalVariable.IP+":8080/CommunityFinancialServicesPlatformServer/DetailServlet?method=getData";
		// ��������
		private static String postUrl = "http://"+GlobalVariable.IP+":8080/CommunityFinancialServicesPlatformServer/DetailServlet?method=addData";
		// �޸�����
		private static String updUrl = "http://"+GlobalVariable.IP+":8080/CommunityFinancialServicesPlatformServer/DetailServlet?method=updateData";
		// ɾ������
		private static String delUrl = "http://"+GlobalVariable.IP+":8080/CommunityFinancialServicesPlatformServer/DetailServlet?method=deleteData";

		public static ArrayList<Detail> getAllDetail() {
			// ��ȡ����
	
			String requestResult = HttpRequestImpl.doGet(getUrl);
			System.out.println("requestResult--" + requestResult);
			
			ArrayList<Detail> list = new ArrayList<Detail>();
			try {
				JSONArray jsonArray = new JSONArray(requestResult);
				
				for (int i = 0; i < jsonArray.length(); i++) {
					JSONObject jsonObj = jsonArray.getJSONObject(i);
					Detail bean = new Detail();
					bean.setD_ID(jsonObj.getString("D_ID"));
					bean.setA_ID(jsonObj.getString("A_ID"));
					bean.setD_Type(jsonObj.getString("D_Type"));
					bean.setD_Money(jsonObj.getString("D_Money"));
					bean.setD_Datetime(jsonObj.getString("D_Datetime"));
					
					list.add(bean);
				}
			} catch (JSONException e) {
				System.out.println("Json ת������!");
				e.printStackTrace();
			}
			return list;
		}

		public static void insertDetail(Detail bean) {
			// ��������
			try {
				// ����һ��JSON��������������ύ����
				JSONArray reqValue = new JSONArray();
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("D_ID", bean.getD_ID());
				jsonObject.put("A_ID", bean.getA_ID());
				jsonObject.put("D_Money", bean.getD_Money());
				jsonObject.put("D_Type", bean.getD_Type());
				jsonObject.put("D_Datetime", bean.getD_Datetime());

				reqValue.put(jsonObject);
				String postData = reqValue.toString();
				System.out.println("postData--" + postData);
				
				String postResult = WebUtil.setJsonData(postUrl, postData);
				
				System.out.println("postResult--" + postResult);
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}

		public static void updateDetail(Detail bean) {
			// ��������
			try {
				// ����һ��JSON��������������ύ����
				JSONArray reqValue = new JSONArray();
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("D_IDr", bean.getD_ID());
				jsonObject.put("A_ID", bean.getA_ID());
				jsonObject.put("D_Money", bean.getD_Money());
				jsonObject.put("D_Type", bean.getD_Type());
				jsonObject.put("D_Datetime", bean.getD_Datetime());
	
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

		public static void deleteDetail(Detail bean) {
			// ɾ������
			try {
				// ����һ��JSON��������������ύ����
				JSONArray reqValue = new JSONArray();
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("D_IDr", bean.getD_ID());
				jsonObject.put("A_ID", bean.getA_ID());
				jsonObject.put("D_Money", bean.getD_Money());
				jsonObject.put("D_Type", bean.getD_Type());
				jsonObject.put("D_Datetime", bean.getD_Datetime());

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