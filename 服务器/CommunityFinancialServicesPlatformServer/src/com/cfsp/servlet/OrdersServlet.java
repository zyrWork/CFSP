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

					OrdersImplement oi = new OrdersImplement();
					
					oi.deleteOrderID(jsonArray.getJSONObject(0).getString("O_Number"));
					
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
				OrdersImplement oi = new OrdersImplement();
				Orders bean = new Orders();
				
				bean.setC_Number(jsonArray.getJSONObject(0).getString("C_Number"));
				bean.setO_Number(jsonArray.getJSONObject(0).getString("O_Number"));
				bean.setO_Date(jsonArray.getJSONObject(0).getString("O_Date"));
				bean.setO_Money(Double.valueOf(jsonArray.getJSONObject(0).getString("O_Money")));
				bean.setO_ProductServe(jsonArray.getJSONObject(0).getString("O_ProductServe"));

				bean.setO_IsPayOff(jsonArray.getJSONObject(0).getString("O_IsPayOff"));
				bean.setO_Evaluate(jsonArray.getJSONObject(0).getString("O_Evaluate"));
				
				oi.updateOrder(bean);//�������ݿ�
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		} else if ("getData".equals(method)) {
	
			// ����JSON����
			PrintWriter out = response.getWriter();
			JSONArray jsonArray = new JSONArray();
	
			List<Orders> OrdersList;
			OrdersImplement oi = new OrdersImplement();
			
			try {
				OrdersList = oi.searchAllOrder();
				for (	Orders bean : OrdersList) {
					// JSONת�� �ṩ���ƶ��˵���
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
