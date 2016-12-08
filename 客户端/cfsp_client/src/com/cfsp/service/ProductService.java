package com.cfsp.service;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;






import com.cfsp.entity.Product;
import com.cfsp.utils.GlobalVariable;
import com.cfsp.utils.HttpRequestImpl;
import com.cfsp.utils.WebUtil;

import android.annotation.SuppressLint;

@SuppressLint("NewApi")
public class ProductService{

	// 服务端的接口URL地址
	// 获取数据
	private static String getUrl = "http://"+GlobalVariable.IP+":8080/CommunityFinancialServicesPlatformServer/ProductServlet?method=getData";
	// 增加数据
	private static String postUrl = "http://"+GlobalVariable.IP+":8080/CommunityFinancialServicesPlatformServer/ProductServlet?method=addData";
	// 修改数据
	private static String updUrl = "http://"+GlobalVariable.IP+":8080/CommunityFinancialServicesPlatformServer/ProductServlet?method=updateData";
	// 删除数据
	private static String delUrl = "http://"+GlobalVariable.IP+":8080/CommunityFinancialServicesPlatformServer/ProductServlet?method=deleteData";

	public static ArrayList<Product> getAllProduct() {
		// 获取数据
		//String requestResult = WebUtil.getAllData(getUrl);
		
		String requestResult = HttpRequestImpl.doGet(getUrl);
		System.out.println("requestResult--" + requestResult);
		
		ArrayList<Product> list = new ArrayList<Product>();
		try {
			JSONArray jsonArray = new JSONArray(requestResult);
			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject jsonObj = jsonArray.getJSONObject(i);
				Product bean = new Product();//不能定义在外面！！！
				
				
				bean.setS_Number(jsonObj.getString("S_Number"));
				bean.setP_Number(jsonObj.getString("P_Number"));
				bean.setP_Name(jsonObj.getString("P_Name"));
				bean.setP_Explain(jsonObj.getString("P_Explain"));
				bean.setP_BelongTo(jsonObj.getString("P_BelongTo"));

				bean.setP_Category(jsonObj.getString("P_Category"));
				bean.setP_InvestKind(jsonObj.getString("P_InvestKind"));
				bean.setP_InvestStyle(jsonObj.getString("P_InvestStyle"));
				bean.setP_InvestEvaluate(jsonObj.getString("P_InvestEvaluate"));
				bean.setP_Code(jsonObj.getString("P_Code"));
				bean.setP_Deadline(jsonObj.getString("P_Deadline"));
				bean.setP_SubscriptionPoint(jsonObj.getString("P_SubscriptionPoint"));
				bean.setP_DateBegin(jsonObj.getString("P_DateBegin"));
				bean.setP_DateEnd(jsonObj.getString("P_DateEnd"));

				bean.setP_BrowseCount(jsonObj.getInt("P_BrowseCount"));
				bean.setP_BuyCount(jsonObj.getInt("P_BuyCount"));
				bean.setP_Price(jsonObj.getDouble("P_Price"));
				bean.setP_AnnualizedReturn(jsonObj.getDouble("P_AnnualizedReturn"));
				bean.setP_ExpectedRate(jsonObj.getDouble("P_ExpectedRate"));
				list.add(bean);
			}
		} catch (JSONException e) {
			System.out.println("Json 转换错误!");
			e.printStackTrace();
		}
		return list;
	}

	public static void insertProduct(Product bean) {
		// 增加数据
		try {
			// 定义一个JSON，用于向服务器提交数据
			JSONArray reqValue = new JSONArray();
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("S_Number", bean.getS_Number());
			jsonObject.put("P_Number", bean.getP_Number());
			jsonObject.put("P_Name", bean.getP_Name());

			jsonObject.put("P_Price", bean.getP_Price());
			jsonObject.put("P_Explain", bean.getP_Explain());
			jsonObject.put("P_BelongTo", bean.getP_BelongTo());
			jsonObject.put("P_Category", bean.getP_Category());
			jsonObject.put("P_InvestKind", bean.getP_InvestKind());

			jsonObject.put("P_InvestStyle", bean.getP_InvestStyle());
			jsonObject.put("P_InvestEvaluate", bean.getP_InvestEvaluate());
			jsonObject.put("P_Code", bean.getP_Code());
			jsonObject.put("P_Deadline", bean.getP_Deadline());
			jsonObject.put("P_AnnualizedReturn", bean.getP_AnnualizedReturn());
			jsonObject.put("P_SubscriptionPoint", bean.getP_SubscriptionPoint());
			jsonObject.put("P_ExpectedRate", bean.getP_ExpectedRate());
			jsonObject.put("P_DateBegin", bean.getP_DateBegin());
			jsonObject.put("P_DateEnd", bean.getP_DateEnd());
			jsonObject.put("P_BrowseCount", bean.getP_BrowseCount());
			jsonObject.put("P_BuyCount", bean.getP_BuyCount());
			reqValue.put(jsonObject);
			String postData = reqValue.toString();
			System.out.println("postData--" + postData);
			
			String postResult = WebUtil.setJsonData(postUrl, postData);
			
			System.out.println("postResult--" + postResult);
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	public static void updateProduct(Product bean) {
		// 更新数据
		try {
			// 定义一个JSON，用于向服务器提交数据
			JSONArray reqValue = new JSONArray();
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("S_Number", bean.getS_Number());
			jsonObject.put("P_Number", bean.getP_Number());
			jsonObject.put("P_Name", bean.getP_Name());

			jsonObject.put("P_Price", bean.getP_Price());
			jsonObject.put("P_Explain", bean.getP_Explain());
			jsonObject.put("P_BelongTo", bean.getP_BelongTo());
			jsonObject.put("P_Category", bean.getP_Category());
			jsonObject.put("P_InvestKind", bean.getP_InvestKind());

			jsonObject.put("P_InvestStyle", bean.getP_InvestStyle());
			jsonObject.put("P_InvestEvaluate", bean.getP_InvestEvaluate());
			jsonObject.put("P_Code", bean.getP_Code());
			jsonObject.put("P_Deadline", bean.getP_Deadline());
			jsonObject.put("P_AnnualizedReturn", bean.getP_AnnualizedReturn());
			jsonObject.put("P_SubscriptionPoint", bean.getP_SubscriptionPoint());
			jsonObject.put("P_ExpectedRate", bean.getP_ExpectedRate());
			jsonObject.put("P_DateBegin", bean.getP_DateBegin());
			jsonObject.put("P_DateEnd", bean.getP_DateEnd());
			jsonObject.put("P_BrowseCount", bean.getP_BrowseCount());
			jsonObject.put("P_BuyCount", bean.getP_BuyCount());
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

	public static void deleteProduct(Product bean) {
		// 删除数据
		try {
			// 定义一个JSON，用于向服务器提交数据
			JSONArray reqValue = new JSONArray();
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("S_Number", bean.getS_Number());
			jsonObject.put("P_Number", bean.getP_Number());
			jsonObject.put("P_Name", bean.getP_Name());

			jsonObject.put("P_Price", bean.getP_Price());
			jsonObject.put("P_Explain", bean.getP_Explain());
			jsonObject.put("P_BelongTo", bean.getP_BelongTo());
			jsonObject.put("P_Category", bean.getP_Category());
			jsonObject.put("P_InvestKind", bean.getP_InvestKind());

			jsonObject.put("P_InvestStyle", bean.getP_InvestStyle());
			jsonObject.put("P_InvestEvaluate", bean.getP_InvestEvaluate());
			jsonObject.put("P_Code", bean.getP_Code());
			jsonObject.put("P_Deadline", bean.getP_Deadline());
			jsonObject.put("P_AnnualizedReturn", bean.getP_AnnualizedReturn());
			jsonObject.put("P_SubscriptionPoint", bean.getP_SubscriptionPoint());
			jsonObject.put("P_ExpectedRate", bean.getP_ExpectedRate());
			jsonObject.put("P_DateBegin", bean.getP_DateBegin());
			jsonObject.put("P_DateEnd", bean.getP_DateEnd());
			jsonObject.put("P_BrowseCount", bean.getP_BrowseCount());
			jsonObject.put("P_BuyCount", bean.getP_BuyCount());
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


