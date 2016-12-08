package com.cfsp.service;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;






import com.cfsp.entity.Orders;
import com.cfsp.utils.GlobalVariable;
import com.cfsp.utils.HttpRequestImpl;
import com.cfsp.utils.WebUtil;

import android.annotation.SuppressLint;

@SuppressLint("NewApi")
public class OrdersService{

	// 服务端的接口URL地址
	// 获取数据
	private static String getUrl = "http://"+GlobalVariable.IP+":8080/CommunityFinancialServicesPlatformServer/OrdersServlet?method=getData";
	// 增加数据
	private static String postUrl = "http://"+GlobalVariable.IP+":8080/CommunityFinancialServicesPlatformServer/OrdersServlet?method=addData";
	// 修改数据
	private static String updUrl = "http://"+GlobalVariable.IP+":8080/CommunityFinancialServicesPlatformServer/OrdersServlet?method=updateData";
	// 删除数据
	private static String delUrl = "http://"+GlobalVariable.IP+":8080/CommunityFinancialServicesPlatformServer/OrdersServlet?method=deleteData";

	public static ArrayList<Orders> getAllOrder() {
		// 获取数据
		//String requestResult = WebUtil.getAllData(getUrl);
		String requestResult = HttpRequestImpl.doGet(getUrl);
		System.out.println("requestResult--" + requestResult);
		
		ArrayList<Orders> list = new ArrayList<Orders>();
		try {
			JSONArray jsonArray = new JSONArray(requestResult);
			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject jsonObj = jsonArray.getJSONObject(i);
				Orders bean = new Orders();
				bean.setC_Number(jsonObj.getString("C_Number"));
				bean.setO_Number(jsonObj.getString("O_Number"));
				bean.setO_Date(jsonObj.getString("O_Date"));
				bean.setO_Money(jsonObj.getDouble("O_Money"));
				bean.setO_ProductServe(jsonObj.getString("O_ProductServe"));

				bean.setO_IsPayOff(jsonObj.getString("O_IsPayOff"));
				
				bean.setO_Evaluate(jsonObj.getString("O_Evaluate"));
				
				list.add(bean);
			}
		} catch (JSONException e) {
			System.out.println("Json 转换错误!");
			e.printStackTrace();
		}
		return list;
	}

	public static void insertOrder(Orders bean) {
		// 增加数据
		try {
			// 定义一个JSON，用于向服务器提交数据
			JSONArray reqValue = new JSONArray();
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("C_Number", bean.getC_Number());
			jsonObject.put("O_Number", bean.getO_Number());
			jsonObject.put("O_Date", bean.getO_Date());

			jsonObject.put("O_Money", bean.getO_Money());
			jsonObject.put("O_ProductServe", bean.getO_ProductServe());
			jsonObject.put("O_IsPayOff", bean.getO_IsPayOff());
			jsonObject.put("O_Evaluate", bean.getO_Evaluate());
			
			reqValue.put(jsonObject);
			String postData = reqValue.toString();
			System.out.println("postData--" + postData);
			
			String postResult = WebUtil.setJsonData(postUrl, postData);
			
			System.out.println("postResult--" + postResult);
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	public static void updateOrder(Orders bean) {
		// 更新数据
		try {
			// 定义一个JSON，用于向服务器提交数据
			JSONArray reqValue = new JSONArray();
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("C_Number", bean.getC_Number());
			jsonObject.put("O_Number", bean.getO_Number());
			jsonObject.put("O_Date", bean.getO_Date());

			jsonObject.put("O_Money", bean.getO_Money());
			jsonObject.put("O_ProductServe", bean.getO_ProductServe());
			jsonObject.put("O_IsPayOff", bean.getO_IsPayOff());
			jsonObject.put("O_Evaluate", bean.getO_Evaluate());
			
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

	public static void deleteOrder(Orders bean) {
		// 删除数据
		try {
			// 定义一个JSON，用于向服务器提交数据
			JSONArray reqValue = new JSONArray();
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("C_Number", bean.getC_Number());
			jsonObject.put("O_Number", bean.getO_Number());
			jsonObject.put("O_Date", bean.getO_Date());

			jsonObject.put("O_Money", bean.getO_Money());
			jsonObject.put("O_ProductServe", bean.getO_ProductServe());
			jsonObject.put("O_IsPayOff", bean.getO_IsPayOff());
			jsonObject.put("O_Evaluate", bean.getO_Evaluate());
			
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
