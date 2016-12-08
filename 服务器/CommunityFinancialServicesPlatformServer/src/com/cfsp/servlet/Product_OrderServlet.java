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


import com.cfsp.daoImplement.Product_OrderImplement;

import com.cfsp.entity.Product_Order;
import com.cfsp.utils.HttpRequestImpl;

public class Product_OrderServlet  extends HttpServlet  {

	private static final long serialVersionUID = 1L;

	public Product_OrderServlet() {
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
				Product_OrderImplement poi = new Product_OrderImplement();
				Product_Order bean = new Product_Order();
				bean.setO_Number(jsonArray.getJSONObject(0).getString("O_Number"));
				bean.setP_Number(jsonArray.getJSONObject(0).getString("P_Number"));
				bean.setPO_Count(Integer.valueOf(jsonArray.getJSONObject(0).getString("PO_Count")));
				poi.addProduct_Order(bean);
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

					Product_OrderImplement poi = new Product_OrderImplement();
					
					poi.deleteProduct_OrderID(jsonArray.getJSONObject(0).getString("O_Number"));
					
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
				Product_OrderImplement poi = new Product_OrderImplement();
				Product_Order bean = new Product_Order();
				bean.setO_Number(jsonArray.getJSONObject(0).getString("O_Number"));
				bean.setP_Number(jsonArray.getJSONObject(0).getString("P_Number"));
				bean.setPO_Count(Integer.valueOf(jsonArray.getJSONObject(0).getString("PO_Count")));

				poi.updateProduct_Order(bean);//更新数据库
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		} else if ("getData".equals(method)) {
			// --------------------------------------------------------------
			// 获取方法和参数的URL
		/*	String paramName = null;
			Enumeration<?> en = request.getParameterNames();// 获取URL参数名
			while (en.hasMoreElements()) {
				paramName = (String) en.nextElement();
				nameValue = request.getParameter(paramName);// 根据参数名获取值
				System.out.println("get name -- " + nameValue);
			}*/
			// --------------------------------------------------------------------------

			// 声明JSON对象
			PrintWriter out = response.getWriter();
			JSONArray jsonArray = new JSONArray();
			Product_OrderImplement poi = new Product_OrderImplement();
			List<Product_Order> Product_OrderList;

			try {
				Product_OrderList = poi.searchAllProduct_Order();
				for (Product_Order bean : Product_OrderList) {
					// JSON转换 提供给移动端调用
					JSONObject jsonObject = new JSONObject();
					jsonObject.put("O_Number", bean.getO_Number());
					jsonObject.put("P_Number", bean.getP_Number());
					jsonObject.put("PO_Count", bean.getPO_Count());

					

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
