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
		// ���������ַ���,�Ա�������ȡ����
		request.setCharacterEncoding("UTF-8");
		// ������Ӧ�ַ���,��������ĵ������������ʾ
		response.setContentType("text/json;charset=UTF-8");

		String method = request.getParameter("method");

		if ("addData".equals(method)) {
			// ��������
			System.out.println("add -- " + method);
			// ����JSON����
			JSONArray jsonArray = null;
			String reqMessage;

			// ��������
			try {
				// ��������������󣬻�ȡ�ƶ�������
				reqMessage = HttpRequestImpl.doGet(request);
				jsonArray = new JSONArray(reqMessage);

				System.out.println("���--" + jsonArray.toString());

				// �����ݿ�
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
				// ɾ��
				System.out.println("delete -- " + method);
				// ����JSON����
				JSONArray jsonArray = null;
				String reqMessage;
				
				try {
					// ��������������󣬻�ȡ�ƶ�������
					reqMessage = HttpRequestImpl.doGet(request);
					jsonArray = new JSONArray(reqMessage);

					System.out.println("���--" + jsonArray.toString());

					Product_OrderImplement poi = new Product_OrderImplement();
					
					poi.deleteProduct_OrderID(jsonArray.getJSONObject(0).getString("O_Number"));
					
				} catch (Exception e) {
					e.printStackTrace();
				}
		}else if ("updateData".equals(method)) {
			System.out.println("update -- " + method);
			// ����JSON����
			JSONArray jsonArray = null;
			String reqMessage;

			// ��������
			try {
				// ��������������󣬻�ȡ�ƶ�������
				reqMessage = HttpRequestImpl.doGet(request);
				jsonArray = new JSONArray(reqMessage);

				System.out.println("���--" + jsonArray.toString());

				//���±�
				Product_OrderImplement poi = new Product_OrderImplement();
				Product_Order bean = new Product_Order();
				bean.setO_Number(jsonArray.getJSONObject(0).getString("O_Number"));
				bean.setP_Number(jsonArray.getJSONObject(0).getString("P_Number"));
				bean.setPO_Count(Integer.valueOf(jsonArray.getJSONObject(0).getString("PO_Count")));

				poi.updateProduct_Order(bean);//�������ݿ�
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		} else if ("getData".equals(method)) {
			// --------------------------------------------------------------
			// ��ȡ�����Ͳ�����URL
		/*	String paramName = null;
			Enumeration<?> en = request.getParameterNames();// ��ȡURL������
			while (en.hasMoreElements()) {
				paramName = (String) en.nextElement();
				nameValue = request.getParameter(paramName);// ���ݲ�������ȡֵ
				System.out.println("get name -- " + nameValue);
			}*/
			// --------------------------------------------------------------------------

			// ����JSON����
			PrintWriter out = response.getWriter();
			JSONArray jsonArray = new JSONArray();
			Product_OrderImplement poi = new Product_OrderImplement();
			List<Product_Order> Product_OrderList;

			try {
				Product_OrderList = poi.searchAllProduct_Order();
				for (Product_Order bean : Product_OrderList) {
					// JSONת�� �ṩ���ƶ��˵���
					JSONObject jsonObject = new JSONObject();
					jsonObject.put("O_Number", bean.getO_Number());
					jsonObject.put("P_Number", bean.getP_Number());
					jsonObject.put("PO_Count", bean.getPO_Count());

					

					jsonArray.put(jsonObject);

				}
				out.write(jsonArray.toString());
				out.flush();
				System.out.println("���--" + jsonArray.toString());
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
