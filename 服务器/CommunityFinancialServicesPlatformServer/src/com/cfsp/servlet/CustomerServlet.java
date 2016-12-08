package com.cfsp.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import com.cfsp.daoImplement.CustomerImplement;
import com.cfsp.entity.Customer;
import com.cfsp.utils.*;

public class CustomerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public CustomerServlet() {
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
				CustomerImplement ci = new CustomerImplement();
				Customer bean = new Customer();
				bean.setC_Account_number( new String((jsonArray.getJSONObject(0).getString("C_Account_number").getBytes("gbk")),"UTF-8"));
				bean.setC_Community( new String((jsonArray.getJSONObject(0).getString("C_Community")).getBytes("gbk"),"UTF-8"));
				bean.setC_Email(new String((jsonArray.getJSONObject(0).getString("C_Email")).getBytes("gbk"),"UTF-8"));
				bean.setC_HealthTimes(Integer.valueOf(jsonArray.getJSONObject(0).getString("C_HealthTimes")));
				bean.setC_ID(new String((jsonArray.getJSONObject(0).getString("C_ID")).getBytes("gbk"),"UTF-8"));

				bean.setC_Integration(Integer.valueOf(jsonArray.getJSONObject(0).getString("C_Integration")));
				bean.setC_Level(Integer.valueOf(jsonArray.getJSONObject(0).getString("C_Level")));
				bean.setC_Nickname( new String((jsonArray.getJSONObject(0).getString("C_Nickname")).getBytes("gbk"),"UTF-8"));
		//		bean.setC_Number(new String((jsonArray.getJSONObject(0).getString("C_Number")).getBytes("gbk"),"UTF-8"));
				bean.setC_Password( new String((jsonArray.getJSONObject(0).getString("C_Password")).getBytes("gbk"),"UTF-8"));

				bean.setC_Username(new String((jsonArray.getJSONObject(0).getString("C_Username")).getBytes("gbk"),"UTF-8"));
				bean.setC_Sex(new String((jsonArray.getJSONObject(0).getString("C_Sex")).getBytes("gbk"),"UTF-8"));
				bean.setC_Tele(new String((jsonArray.getJSONObject(0).getString("C_Tele")).getBytes("gbk"),"UTF-8"));

				ci.addCustomer(bean);
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

					CustomerImplement ci = new CustomerImplement();
					
					ci.deleteCustomerByID(jsonArray.getJSONObject(0).getString("C_Number"));
					
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
				CustomerImplement ci = new CustomerImplement();
				Customer bean = new Customer();
				bean.setC_Account_number( new String((jsonArray.getJSONObject(0).getString("C_Account_number").getBytes("gbk")),"UTF-8"));
				bean.setC_Community( new String((jsonArray.getJSONObject(0).getString("C_Community")).getBytes("gbk"),"UTF-8"));
				bean.setC_Email(new String((jsonArray.getJSONObject(0).getString("C_Email")).getBytes("gbk"),"UTF-8"));
				bean.setC_HealthTimes(Integer.valueOf(jsonArray.getJSONObject(0).getString("C_HealthTimes")));
				bean.setC_ID(new String((jsonArray.getJSONObject(0).getString("C_ID")).getBytes("gbk"),"UTF-8"));

				bean.setC_Integration(Integer.valueOf(jsonArray.getJSONObject(0).getString("C_Integration")));
				bean.setC_Level(Integer.valueOf(jsonArray.getJSONObject(0).getString("C_Level")));
				bean.setC_Nickname( new String((jsonArray.getJSONObject(0).getString("C_Nickname")).getBytes("gbk"),"UTF-8"));
				bean.setC_Number(new String((jsonArray.getJSONObject(0).getString("C_Number")).getBytes("gbk"),"UTF-8"));
				bean.setC_Password( new String((jsonArray.getJSONObject(0).getString("C_Password")).getBytes("gbk"),"UTF-8"));

				bean.setC_Username(new String((jsonArray.getJSONObject(0).getString("C_Username")).getBytes("gbk"),"UTF-8"));
				bean.setC_Sex(new String((jsonArray.getJSONObject(0).getString("C_Sex")).getBytes("gbk"),"UTF-8"));
				bean.setC_Tele(new String((jsonArray.getJSONObject(0).getString("C_Tele")).getBytes("gbk"),"UTF-8"));

				ci.updateCustomer(bean);//更新数据库
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		} else if ("getData".equals(method)) {
			
			System.out.println("getData -- " + method);
			// 声明JSON对象
			PrintWriter out = response.getWriter();
			JSONArray jsonArray = new JSONArray();
			CustomerImplement ci = new CustomerImplement();
			
			ArrayList<Customer> CustomerList = new ArrayList<Customer>();

			try {
				CustomerList = ci.searchAllCustomer();
				for (Customer bean : CustomerList) {
					// JSON转换 提供给移动端调用
					JSONObject jsonObject = new JSONObject();
					jsonObject.put("C_Number", bean.getC_Number());
					jsonObject.put("C_Username", bean.getC_Username());
					jsonObject.put("C_HealthTimes", bean.getC_HealthTimes());

					jsonObject.put("C_Integration", bean.getC_Integration());
					jsonObject.put("C_Level", bean.getC_Level());
					jsonObject.put("C_Account_number", bean.getC_Account_number());
					jsonObject.put("C_Community", bean.getC_Community());
					jsonObject.put("C_Email", bean.getC_Email());

					jsonObject.put("C_ID", bean.getC_ID());
					jsonObject.put("C_Nickname", bean.getC_Nickname());
					jsonObject.put("C_Password", bean.getC_Password());
					jsonObject.put("C_Sex", bean.getC_Sex());
					jsonObject.put("C_Tele", bean.getC_Tele());

					jsonArray.put(jsonObject);

				}
				out.write(jsonArray.toString());
				out.flush();
				System.out.println("结果--" + jsonArray.toString());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if ("login".equals(method)) {
			System.out.println("login -- " + method);

			String reqMessage, respMessage;
			JSONArray reqObject = null;
			JSONArray respObject = null;

			try {
				BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream(), "UTF-8"));
				StringBuffer sb = new StringBuffer("");
				String temp;
				while ((temp = br.readLine()) != null) {
					sb.append(temp);
				}
				br.close();

				reqMessage = sb.toString();
				System.out.println("请求报文:" + reqMessage);

				reqObject = new JSONArray(reqMessage);
				CustomerImplement ci = new CustomerImplement();

				// 获取账号和密码
				String account_number = reqObject.getJSONObject(0).getString("account_number");
				String password = reqObject.getJSONObject(0).getString("password");
 
				System.out.println("p--"+password);
				System.out.println("n--"+account_number);
				Customer cu = ci.searchCustomerByID(account_number);

				if(cu==null)
				{//避免cu为空，引起空指针错误
					respObject=null;
				}
				else if (cu.getC_Password() != null && cu.getC_Password().equals(password)) {
					respObject = new JSONArray().put(new JSONObject().put("C_Number", cu.getC_Number()));
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				respMessage = respObject == null ? "" : respObject.toString();
				System.out.println("返回报文:" + respMessage);
				PrintWriter pw = response.getWriter();
				pw.write(respMessage);
				pw.flush();
				pw.close();
			}
		}else if ("register".equals(method)) {
			System.out.println("register -- " + method);
			// 声明JSON对象
			JSONArray jsonArray = null;
			String reqMessage;

			//注册的本质就是插入数据
			// 插入数据
			try {
				// 传递网络请求对象，获取移动端数据
				reqMessage = HttpRequestImpl.doGet(request);
				jsonArray = new JSONArray(reqMessage);

				System.out.println("结果--" + jsonArray.toString());

				// 存数据库
				CustomerImplement ci = new CustomerImplement();
				Customer bean = new Customer();
				bean.setC_Account_number( new String((jsonArray.getJSONObject(0).getString("C_Account_number").getBytes("gbk")),"UTF-8"));
				bean.setC_Community( new String((jsonArray.getJSONObject(0).getString("C_Community")).getBytes("gbk"),"UTF-8"));
				bean.setC_Email(new String((jsonArray.getJSONObject(0).getString("C_Email")).getBytes("gbk"),"UTF-8"));
				bean.setC_HealthTimes(Integer.valueOf(jsonArray.getJSONObject(0).getString("C_HealthTimes")));
				bean.setC_ID(new String((jsonArray.getJSONObject(0).getString("C_ID")).getBytes("gbk"),"UTF-8"));

				bean.setC_Integration(Integer.valueOf(jsonArray.getJSONObject(0).getString("C_Integration")));
				bean.setC_Level(Integer.valueOf(jsonArray.getJSONObject(0).getString("C_Level")));
				bean.setC_Nickname( new String((jsonArray.getJSONObject(0).getString("C_Nickname")).getBytes("gbk"),"UTF-8"));
			//	bean.setC_Number(new String((jsonArray.getJSONObject(0).getString("C_Number")).getBytes("gbk"),"UTF-8"));
				bean.setC_Password( new String((jsonArray.getJSONObject(0).getString("C_Password")).getBytes("gbk"),"UTF-8"));

				bean.setC_Username(new String((jsonArray.getJSONObject(0).getString("C_Username")).getBytes("gbk"),"UTF-8"));
				bean.setC_Sex(new String((jsonArray.getJSONObject(0).getString("C_Sex")).getBytes("gbk"),"UTF-8"));
				bean.setC_Tele(new String((jsonArray.getJSONObject(0).getString("C_Tele")).getBytes("gbk"),"UTF-8"));


				ci.addCustomer(bean);
				
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
