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

import com.cfsp.daoImplement.OrdersImplement;
import com.cfsp.entity.Orders;
import com.cfsp.utils.*;

public class OrdersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public OrdersServlet() {
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
				OrdersImplement oi = new OrdersImplement();
				Orders bean = new Orders();
				bean.setC_Number(jsonArray.getJSONObject(0).getString("C_Number"));
				bean.setO_Number(jsonArray.getJSONObject(0).getString("O_Number"));
				bean.setO_Date(jsonArray.getJSONObject(0).getString("O_Date"));
				bean.setO_Money(Double.valueOf(jsonArray.getJSONObject(0).getString("O_Money")));
				bean.setO_ProductServe(jsonArray.getJSONObject(0).getString("O_ProductServe"));

				bean.setO_IsPayOff(jsonArray.getJSONObject(0).getString("O_IsPayOff"));
				bean.setO_Evaluate(jsonArray.getJSONObject(0).getString("O_Evaluate"));

				oi.addOrder(bean);
				
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

					OrdersImplement oi = new OrdersImplement();
					
					oi.deleteOrderID(jsonArray.getJSONObject(0).getString("O_Number"));
					
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
				OrdersImplement oi = new OrdersImplement();
				Orders bean = new Orders();
				
				bean.setC_Number(jsonArray.getJSONObject(0).getString("C_Number"));
				bean.setO_Number(jsonArray.getJSONObject(0).getString("O_Number"));
				bean.setO_Date(jsonArray.getJSONObject(0).getString("O_Date"));
				bean.setO_Money(Double.valueOf(jsonArray.getJSONObject(0).getString("O_Money")));
				bean.setO_ProductServe(jsonArray.getJSONObject(0).getString("O_ProductServe"));

				bean.setO_IsPayOff(jsonArray.getJSONObject(0).getString("O_IsPayOff"));
				bean.setO_Evaluate(jsonArray.getJSONObject(0).getString("O_Evaluate"));
				
				oi.updateOrder(bean);//更新数据库
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		} else if ("getData".equals(method)) {
	
			// 声明JSON对象
			PrintWriter out = response.getWriter();
			JSONArray jsonArray = new JSONArray();
	
			List<Orders> OrdersList;
			OrdersImplement oi = new OrdersImplement();
			
			try {
				OrdersList = oi.searchAllOrder();
				for (	Orders bean : OrdersList) {
					// JSON转换 提供给移动端调用
					JSONObject jsonObject = new JSONObject();
					jsonObject.put("C_Number", bean.getC_Number());
					jsonObject.put("O_Number", bean.getO_Number());
					jsonObject.put("O_Date", bean.getO_Date());

					jsonObject.put("O_ProductServe", bean.getO_ProductServe());
					jsonObject.put("O_IsPayOff", bean.getO_IsPayOff());
					jsonObject.put("O_Money", bean.getO_Money());
					jsonObject.put("O_Evaluate", bean.getO_Evaluate());
					
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
