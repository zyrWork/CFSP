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

import com.cfsp.daoImplement.DetailImplement;
import com.cfsp.entity.Detail;
import com.cfsp.utils.*;

public class DetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public DetailServlet() {
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
				DetailImplement ci = new DetailImplement();
				Detail bean = new Detail();
				bean.setD_ID(jsonArray.getJSONObject(0).getString("D_ID"));
				bean.setA_ID(jsonArray.getJSONObject(0).getString("A_ID"));
				bean.setD_Type(jsonArray.getJSONObject(0).getString("D_Type"));
				bean.setD_Money(jsonArray.getJSONObject(0).getString("D_Money"));
				bean.setD_Datetime(jsonArray.getJSONObject(0).getString("D_Datetime"));

				

				ci.addDetail(bean);
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

					DetailImplement ci = new DetailImplement();
					
					ci.deleteDetailByID(jsonArray.getJSONObject(0).getString("D_ID"));
					
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
				DetailImplement ci = new DetailImplement();
				Detail bean = new Detail();
				bean.setD_ID(jsonArray.getJSONObject(0).getString("D_ID"));
				bean.setA_ID(jsonArray.getJSONObject(0).getString("A_ID"));
				bean.setD_Type(jsonArray.getJSONObject(0).getString("D_Type"));
				bean.setD_Money(jsonArray.getJSONObject(0).getString("D_Money"));
				bean.setD_Datetime(jsonArray.getJSONObject(0).getString("D_Datetime"));

				ci.updateDetail(bean);//�������ݿ�
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		} else if ("getData".equals(method)) {
		
			// ����JSON����
			PrintWriter out = response.getWriter();
			JSONArray jsonArray = new JSONArray();
			DetailImplement ci = new DetailImplement();
			List<Detail> DetailList;

			try {
				DetailList = ci.searchAllDetail();
				for (Detail bean : DetailList) {
					// JSONת�� �ṩ���ƶ��˵���
					JSONObject jsonObject = new JSONObject();
					jsonObject.put("D_ID", bean.getD_ID());
					jsonObject.put("A_ID", bean.getA_ID());
					jsonObject.put("D_Type", bean.getD_Type());

					jsonObject.put("D_Money", bean.getD_Money());
					jsonObject.put("D_Datetime", bean.getD_Datetime());
					
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
