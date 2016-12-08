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

import com.cfsp.daoImplement.AccountImplement;
import com.cfsp.entity.Account;
import com.cfsp.utils.*;

public class AccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AccountServlet() {
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
				AccountImplement ci = new AccountImplement();
				Account bean = new Account();
				bean.setC_Number(jsonArray.getJSONObject(0).getString("C_Number"));
				bean.setA_Balance(jsonArray.getJSONObject(0).getString("A_Balance"));
				bean.setA_ID(jsonArray.getJSONObject(0).getString("A_ID"));
				

				ci.addAccount(bean);
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

					AccountImplement ci = new AccountImplement();
					
					ci.deleteAccountByID(jsonArray.getJSONObject(0).getString("A_ID"));
					
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
				AccountImplement ci = new AccountImplement();
				Account bean = new Account();
				bean.setC_Number(jsonArray.getJSONObject(0).getString("C_Number"));
				bean.setA_Balance(jsonArray.getJSONObject(0).getString("A_Balance"));
				bean.setA_ID(jsonArray.getJSONObject(0).getString("A_ID"));
				
				ci.updateAccount(bean);//�������ݿ�
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		} else if ("getData".equals(method)) {
		
			System.out.println("getData -- " + method);
			// ����JSON����
			PrintWriter out = response.getWriter();
			JSONArray jsonArray = new JSONArray();
			AccountImplement ci = new AccountImplement();
			List<Account> AccountList;

			try {
				AccountList = ci.searchAllAccount();
				for (Account bean : AccountList) {
					// JSONת�� �ṩ���ƶ��˵���
					JSONObject jsonObject = new JSONObject();
					jsonObject.put("C_Number", bean.getC_Number());
					jsonObject.put("A_Balance", bean.getA_Balance());
					jsonObject.put("A_ID", bean.getA_ID());

					
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
