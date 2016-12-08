package com.cfsp.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import com.cfsp.daoImplement.ProductImplement;
import com.cfsp.entity.Product;
import com.cfsp.utils.*;

public class ProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ProductServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 设置请求字符集,以便从请求读取中文
		request.setCharacterEncoding("UTF-8");
		// 设置响应字符集,以输出中文到浏览器正常显示
		response.setContentType("text/json;charset=UTF-8");

		String method = request.getParameter("method");

		if ("addData".equals(method)) {
			// 插入数据
			System.out.println("add -- " + method);
			// 声明JSON对象
			JSONArray jsonArray = null;
			String reqMessage;

			// 插入数据
			try {
				// 传递网络请求对象，获取移动端数据
				reqMessage = HttpRequestImpl.doGet(request);
				jsonArray = new JSONArray(reqMessage);

				System.out.println("结果--" + jsonArray.toString());

				// 存数据库
				ProductImplement ci = new ProductImplement();
				Product bean = new Product();
 
				bean.setS_Number(jsonArray.getJSONObject(0).getString("S_Number"));
				bean.setP_Number(jsonArray.getJSONObject(0).getString("P_Number"));
				bean.setP_Name(jsonArray.getJSONObject(0).getString("P_Name"));
				bean.setP_Explain(jsonArray.getJSONObject(0).getString("P_Explain"));
				bean.setP_BelongTo(jsonArray.getJSONObject(0).getString("P_BelongTo"));

				bean.setP_Category(jsonArray.getJSONObject(0).getString("P_Category"));
				bean.setP_InvestKind(jsonArray.getJSONObject(0).getString("P_InvestKind"));
				bean.setP_InvestStyle(jsonArray.getJSONObject(0).getString("P_InvestStyle"));
				bean.setP_InvestEvaluate(jsonArray.getJSONObject(0).getString("P_InvestEvaluate"));
				bean.setP_Code(jsonArray.getJSONObject(0).getString("P_Code"));
				bean.setP_Deadline(jsonArray.getJSONObject(0).getString("P_Deadline"));
				bean.setP_SubscriptionPoint(jsonArray.getJSONObject(0).getString("P_SubscriptionPoint"));
				bean.setP_DateBegin(jsonArray.getJSONObject(0).getString("P_DateBegin"));
				bean.setP_DateEnd(jsonArray.getJSONObject(0).getString("P_DateEnd"));

				bean.setP_BrowseCount(jsonArray.getJSONObject(0).getInt("P_BrowseCount"));
				bean.setP_BuyCount(jsonArray.getJSONObject(0).getInt("P_BuyCount"));
				bean.setP_Price(jsonArray.getJSONObject(0).getDouble("P_Price"));
				bean.setP_AnnualizedReturn(jsonArray.getJSONObject(0).getDouble("P_AnnualizedReturn"));
				bean.setP_ExpectedRate(jsonArray.getJSONObject(0).getDouble("P_ExpectedRate"));

				ci.addProduct(bean);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if ("deleteData".equals(method)) {
				// 删除
				System.out.println("delete -- " + method);
				// 声明JSON对象
				JSONArray jsonArray = null;
				String reqMessage;
				
				try {
					// 传递网络请求对象，获取移动端数据
					reqMessage = HttpRequestImpl.doGet(request);
					jsonArray = new JSONArray(reqMessage);

					System.out.println("结果--" + jsonArray.toString());

					ProductImplement ci = new ProductImplement();
					
					ci.deleteProductByID(jsonArray.getJSONObject(0).getString("P_Number"));
					
				} catch (Exception e) {
					e.printStackTrace();
				}
		}else if ("updateData".equals(method)) {
			System.out.println("update -- " + method);
			// 声明JSON对象
			JSONArray jsonArray = null;
			String reqMessage;

			// 更新数据
			try {
				// 传递网络请求对象，获取移动端数据
				reqMessage = HttpRequestImpl.doGet(request);
				jsonArray = new JSONArray(reqMessage);

				System.out.println("结果--" + jsonArray.toString());

				//更新表
				ProductImplement ci = new ProductImplement();
				Product bean = new Product();
				bean.setS_Number(jsonArray.getJSONObject(0).getString("S_Number"));
				bean.setP_Number(jsonArray.getJSONObject(0).getString("P_Number"));
				bean.setP_Name(jsonArray.getJSONObject(0).getString("P_Name"));
				bean.setP_Explain(jsonArray.getJSONObject(0).getString("P_Explain"));
				bean.setP_BelongTo(jsonArray.getJSONObject(0).getString("P_BelongTo"));

				bean.setP_Category(jsonArray.getJSONObject(0).getString("P_Category"));
				bean.setP_InvestKind(jsonArray.getJSONObject(0).getString("P_InvestKind"));
				bean.setP_InvestStyle(jsonArray.getJSONObject(0).getString("P_InvestStyle"));
				bean.setP_InvestEvaluate(jsonArray.getJSONObject(0).getString("P_InvestEvaluate"));
				bean.setP_Code(jsonArray.getJSONObject(0).getString("P_Code"));
				bean.setP_Deadline(jsonArray.getJSONObject(0).getString("P_Deadline"));
				bean.setP_SubscriptionPoint(jsonArray.getJSONObject(0).getString("P_SubscriptionPoint"));
				bean.setP_DateBegin(jsonArray.getJSONObject(0).getString("P_DateBegin"));
				bean.setP_DateEnd(jsonArray.getJSONObject(0).getString("P_DateEnd"));

				bean.setP_BrowseCount(jsonArray.getJSONObject(0).getInt("P_BrowseCount"));
				bean.setP_BuyCount(jsonArray.getJSONObject(0).getInt("P_BuyCount"));
				bean.setP_Price(jsonArray.getJSONObject(0).getDouble("P_Price"));
				bean.setP_AnnualizedReturn(jsonArray.getJSONObject(0).getDouble("P_AnnualizedReturn"));
				bean.setP_ExpectedRate(jsonArray.getJSONObject(0).getDouble("P_ExpectedRate"));

				ci.updateProduct(bean);//更新数据库
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		} else if ("getData".equals(method)) {
	
			// 声明JSON对象
			PrintWriter out = response.getWriter();
			JSONArray jsonArray = new JSONArray();
			ProductImplement ci = new ProductImplement();
			List<Product> ProductList;

			try {
				ProductList = ci.searchAllProduct();
				for (Product bean : ProductList) {
					// JSON转换 提供给移动端调用
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

					jsonArray.put(jsonObject);

				}
				out.write(jsonArray.toString());
				out.flush();
				System.out.println("结果--" + jsonArray.toString());
			} catch (Exception e) {
				e.printStackTrace();
			}
		} 
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doGet(request, response);
	}
}

