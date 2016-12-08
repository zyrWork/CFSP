package com.cfsp.service;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.cfsp.entity.Product_Order;
import com.cfsp.utils.GlobalVariable;
import com.cfsp.utils.HttpRequestImpl;
import com.cfsp.utils.WebUtil;

public class Product_OrderService {

	// 服务端的接口URL地址
			// 获取数据
			private static String getUrl = "http://"+GlobalVariable.IP+":8080/CommunityFinancialServicesPlatformServer/Product_OrderServlet?method=getData";
			// 增加数据
			private static String postUrl = "http://"+GlobalVariable.IP+":8080/CommunityFinancialServicesPlatformServer/Product_OrderServlet?method=addData";
			// 修改数据
			private static String updUrl = "http://"+GlobalVariable.IP+":8080/CommunityFinancialServicesPlatformServer/Product_OrderServlet?method=updateData";
			// 删除数据
			private static String delUrl = "http://"+GlobalVariable.IP+":8080/CommunityFinancialServicesPlatformServer/Product_OrderServlet?method=deleteData";

			public static ArrayList<Product_Order> getAllNews() {
				// 获取数据
			//	String requestResult = WebUtil.getAllData(getUrl);
				
				String requestResult = HttpRequestImpl.doGet(getUrl);
				System.out.println("requestResult--" + requestResult);
			
				ArrayList<Product_Order> list = new ArrayList<Product_Order>();
				try {
					JSONArray jsonArray = new JSONArray(requestResult);
					for (int i = 0; i < jsonArray.length(); i++) {
						JSONObject jsonObj = jsonArray.getJSONObject(i);
						Product_Order po = new Product_Order();
						po.setO_Number(jsonObj.getString("O_Number"));
						po.setP_Number(jsonObj.getString("P_Number"));
						po.setPO_Count(jsonObj.getInt("PO_Count"));
						
						list.add(po);
					}
				} catch (JSONException e) {
					System.out.println("Json 转换错误!");
					e.printStackTrace();
				}
				return list;
			}

			public static void insertProduct_Order(Product_Order bean) {
				// 增加数据
				try {
					// 定义一个JSON，用于向服务器提交数据
					JSONArray reqValue = new JSONArray();
					JSONObject jsonObject = new JSONObject();
					jsonObject.put("O_Number", bean.getO_Number());
					jsonObject.put("P_Number", bean.getP_Number());
					jsonObject.put("PO_Count", bean.getPO_Count());
					reqValue.put(jsonObject);
					String postData = reqValue.toString();
					System.out.println("postData--" + postData);				
					String postResult = WebUtil.setJsonData(postUrl, postData);
					
					System.out.println("postResult--" + postResult);
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}

			public static void updateProduct_Order(Product_Order bean) {
				// 更新数据
				try {
					// 定义一个JSON，用于向服务器提交数据
					JSONArray reqValue = new JSONArray();
					JSONObject jsonObject = new JSONObject();
					jsonObject.put("O_Number", bean.getO_Number());
					jsonObject.put("P_Number", bean.getP_Number());
					jsonObject.put("PO_Count", bean.getPO_Count());
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

			public static void deleteProduct_Order(Product_Order bean) {
				// 删除数据
				try {
					// 定义一个JSON，用于向服务器提交数据
					JSONArray reqValue = new JSONArray();
					JSONObject jsonObject = new JSONObject();
					jsonObject.put("O_Number", bean.getO_Number());
					jsonObject.put("P_Number", bean.getP_Number());
					jsonObject.put("PO_Count", bean.getPO_Count());
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
