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

import com.cfsp.daoImplement.NewsImplement;
import com.cfsp.entity.News;
import com.cfsp.utils.HttpRequestImpl;

public class NewsServlet  extends HttpServlet  {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1706841416146827451L;

	public NewsServlet() {
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
				NewsImplement ni = new NewsImplement();
				News bean = new News();
				bean.setN_ID(jsonArray.getJSONObject(0).getString("N_ID"));
				bean.setN_Code(jsonArray.getJSONObject(0).getString("N_Code"));
				bean.setN_Title(jsonArray.getJSONObject(0).getString("N_Title"));
				bean.setN_Info(jsonArray.getJSONObject(0).getString("N_Info"));
				bean.setN_Publisher(jsonArray.getJSONObject(0).getString("N_Publisher"));

				bean.setN_PubTime(jsonArray.getJSONObject(0).getString("N_PubTime"));
				bean.setN_BuyLink(jsonArray.getJSONObject(0).getString("N_BuyLink"));
				bean.setN_OrderLink(jsonArray.getJSONObject(0).getString("N_OrderLink"));
				bean.setN_Picture(jsonArray.getJSONObject(0).getString("N_Picture"));
				

				ni.addNews(bean);
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

					NewsImplement ni = new NewsImplement();
					
					ni.deleteNewsID(jsonArray.getJSONObject(0).getString("N_ID"));
					
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
				NewsImplement ni = new NewsImplement();
				News bean = new News();
				bean.setN_ID(jsonArray.getJSONObject(0).getString("N_ID"));
				bean.setN_Code(jsonArray.getJSONObject(0).getString("N_Code"));
				bean.setN_Title(jsonArray.getJSONObject(0).getString("N_Title"));
				bean.setN_Info(jsonArray.getJSONObject(0).getString("N_Info"));
				bean.setN_Publisher(jsonArray.getJSONObject(0).getString("N_Publisher"));

				bean.setN_PubTime(jsonArray.getJSONObject(0).getString("N_PubTime"));
				bean.setN_BuyLink(jsonArray.getJSONObject(0).getString("N_BuyLink"));
				bean.setN_OrderLink(jsonArray.getJSONObject(0).getString("N_OrderLink"));
				bean.setN_Picture(jsonArray.getJSONObject(0).getString("N_Picture"));

				ni.updateNews(bean);//�������ݿ�
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		} else if ("getData".equals(method)) {
			
			// ����JSON����
			PrintWriter out = response.getWriter();
			JSONArray jsonArray = new JSONArray();
			NewsImplement ni = new NewsImplement();
			List<News> NewsList;

			try {
				NewsList = ni.searchAllNews();
				for (News bean : NewsList) {
					// JSONת�� �ṩ���ƶ��˵���
					JSONObject jsonObject = new JSONObject();
					jsonObject.put("N_ID", bean.getN_ID());
					jsonObject.put("N_Code", bean.getN_Code());
					jsonObject.put("N_Title", bean.getN_Title());

					jsonObject.put("N_Info", bean.getN_Info());
					jsonObject.put("N_Publisher", bean.getN_Publisher());
					jsonObject.put("N_PubTime", bean.getN_PubTime());
					jsonObject.put("N_BuyLink", bean.getN_BuyLink());
					jsonObject.put("N_OrderLink", bean.getN_OrderLink());

					jsonObject.put("N_Picture", bean.getN_Picture());

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
