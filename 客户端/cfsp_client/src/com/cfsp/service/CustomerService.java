package com.cfsp.service;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;







import com.cfsp.entity.Customer;
import com.cfsp.utils.GlobalVariable;
import com.cfsp.utils.HttpRequestImpl;
import com.cfsp.utils.WebUtil;

import android.annotation.SuppressLint;



@SuppressLint("NewApi")
public class CustomerService {

	// ����˵Ľӿ�URL��ַ
	// ��ȡ����
	private static String getUrl = "http://"+GlobalVariable.IP+":8080/CommunityFinancialServicesPlatformServer/CustomerServlet?method=getData";
	// ��������
	private static String postUrl = "http://"+GlobalVariable.IP+":8080/CommunityFinancialServicesPlatformServer/CustomerServlet?method=addData";
	// �޸�����
	private static String updUrl = "http://"+GlobalVariable.IP+":8080/CommunityFinancialServicesPlatformServer/CustomerServlet?method=updateData";
	// ɾ������
	private static String delUrl = "http://"+GlobalVariable.IP+":8080/CommunityFinancialServicesPlatformServer/CustomerServlet?method=deleteData";
  

	
	public static ArrayList<Customer> getAllCustomer( ) {
		// ��ȡ����
		//sString requestResult = WebUtil.getAllData(getUrl);
		String requestResult = HttpRequestImpl.doGet(getUrl);
		System.out.println("requestResult--" + requestResult);
		
		ArrayList<Customer> list = new ArrayList<Customer>();
		if(requestResult==null)
		{
			
		}else 
		{
			try {
				JSONArray jsonArray = new JSONArray(requestResult);
				for (int i = 0; i < jsonArray.length(); i++) {
					JSONObject jsonObj = jsonArray.getJSONObject(i);
					Customer cu = new Customer();//���ܶ���������
					cu.setC_Number(jsonObj.getString("C_Number"));
					cu.setC_Account_number(jsonObj.getString("C_Account_number"));
					cu.setC_Password(jsonObj.getString("C_Password"));
					cu.setC_Username(jsonObj.getString("C_Username"));
					cu.setC_Sex(jsonObj.getString("C_Sex"));

					cu.setC_ID(jsonObj.getString("C_ID"));
					cu.setC_Tele(jsonObj.getString("C_Tele"));
					cu.setC_Email(jsonObj.getString("C_Email"));
					cu.setC_Nickname(jsonObj.getString("C_Nickname"));
					cu.setC_Community(jsonObj.getString("C_Community"));

					cu.setC_Integration(jsonObj.getInt("C_Integration"));
					cu.setC_Level(jsonObj.getInt("C_Level"));
					cu.setC_HealthTimes(jsonObj.getInt("C_HealthTimes"));
					
					list.add(cu);
				}
			} catch (JSONException e) {
				System.out.println("Json ת������!");
				e.printStackTrace();
			}
		}
		
		return list;
	}
	

	public static void insertCustomer(Customer bean) {
		// ��������
		try {
			// ����һ��JSON��������������ύ����
			JSONArray reqValue = new JSONArray();
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
			reqValue.put(jsonObject);
			String postData = reqValue.toString();
			System.out.println("postData--" + postData);
			
			String postResult = WebUtil.setJsonData(postUrl, postData);
			
			System.out.println("postResult--" + postResult);
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	public static void updateCustomer(Customer bean) {
		// ��������
		try {
			// ����һ��JSON��������������ύ����
			JSONArray reqValue = new JSONArray();
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
			reqValue.put(jsonObject);
			String postData = reqValue.toString();
			System.out.println("postData--" + postData);
			//��Ҫһ��
			String postResult = WebUtil.setJsonData(updUrl, postData);

			System.out.println("postResult--" + postResult);
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	public static void deleteCustomer(Customer bean) {
		// ɾ������
		try {
			// ����һ��JSON��������������ύ����
			JSONArray reqValue = new JSONArray();
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
			reqValue.put(jsonObject);
			String postData = reqValue.toString();
			System.out.println("postData--" + postData);
			//��Ҫһ��
			String postResult = WebUtil.setJsonData(delUrl, postData);

			System.out.println("postResult--" + postResult);
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

}
