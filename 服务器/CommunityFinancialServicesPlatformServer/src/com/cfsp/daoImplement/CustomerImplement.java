package com.cfsp.daoImplement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.cfsp.connectDB.DBConnection;
import com.cfsp.daoInterface.*;
import com.cfsp.entity.Customer;

public class CustomerImplement implements CustomerInterface {

	@Override
	public void addCustomer(Customer c) throws Exception {
		Connection conn = DBConnection.getConnection();
		PreparedStatement ps = conn.prepareStatement("insert into Customer(C_Account_number,C_Password,C_Username,C_Sex,C_ID,C_Tele,C_Email,C_Nickname,C_Community,C_Integration,C_Level,C_HealthTimes) values(?,?,?,?,?,?,?,?,?,?,?,?)");
	//	ps.setInt(1, Integer.valueOf(c.getC_Number()));
		ps.setString(1, c.getC_Account_number());
		ps.setString(2, c.getC_Password());
		ps.setString(3, c.getC_Username());
		ps.setString(4, c.getC_Sex());
		ps.setString(5, c.getC_ID());
		ps.setString(6, c.getC_Tele());
		ps.setString(7, c.getC_Email());
		ps.setString(8, c.getC_Nickname());
		ps.setString(9, c.getC_Community());
		ps.setInt(10, c.getC_Integration());
		ps.setInt(11, c.getC_Level());
		ps.setInt(12, c.getC_HealthTimes());
		
		ps.executeUpdate();
		System.out.println("插入成功");
		DBConnection.close(ps, conn);
	}


	@Override
	public void deleteCustomerByID(String ID) throws Exception {
		Connection conn = DBConnection.getConnection();
		PreparedStatement ps = conn.prepareStatement("delete from Customer where C_Number=?");
		ps.setInt(1,Integer.valueOf(ID));
		ps.executeUpdate();
		DBConnection.close(ps, conn);
	}

	@Override
	public void updateCustomer(Customer c) throws Exception {
		Connection conn = DBConnection.getConnection();
		PreparedStatement ps = conn.prepareStatement("update Customer set C_Account_number=?,C_Password=?,C_Username=?,C_Sex=?,C_ID=?,C_Tele=?,C_Email=?,C_Nickname=?,C_Community=?,C_Integration=?,C_Level=?,C_HealthTimes=? where C_Number=?");
		ps.setString(1, c.getC_Account_number());
		ps.setString(2, c.getC_Password());
		ps.setString(3, c.getC_Username());
		ps.setString(4, c.getC_Sex());
		ps.setString(5, c.getC_ID());
		ps.setString(6, c.getC_Tele());
		ps.setString(7, c.getC_Email());
		ps.setString(8, c.getC_Nickname());
		ps.setString(9, c.getC_Community());
		ps.setInt(10, c.getC_Integration());
		ps.setInt(11, c.getC_Level());
		ps.setInt(12, c.getC_HealthTimes());
		ps.setInt(13, Integer.valueOf(c.getC_Number()));
		
		ps.executeUpdate();
		DBConnection.close(ps, conn);
	}

	@Override
	public Customer searchCustomerByID(String ID)throws Exception {
		Customer bean = null;
		Connection conn = DBConnection.getConnection();
		//此处修改为按账户号登录
		PreparedStatement ps = conn.prepareStatement("select * from Customer where C_Account_number=?");
		ps.setString(1, ID);
		ResultSet rs = ps.executeQuery();
		
		while(rs.next()) {
            bean=new Customer();
			bean.setC_Account_number(rs.getString("C_Account_number"));
			bean.setC_Community(rs.getString("C_Community"));
			bean.setC_Email(rs.getString("C_Email"));
			bean.setC_HealthTimes(rs.getInt("C_HealthTimes"));
			bean.setC_ID(rs.getString("C_ID"));
			bean.setC_Integration(rs.getInt("C_Integration"));
			bean.setC_Level(rs.getInt("C_Level"));
			bean.setC_Nickname(rs.getString("C_Nickname"));
			bean.setC_Number(new Integer(rs.getInt("C_Number")).toString());
			bean.setC_Password(rs.getString("C_Password"));
			bean.setC_Username(rs.getString("C_Username"));
			bean.setC_Sex(rs.getString("C_Sex"));
			bean.setC_Tele(rs.getString("C_Tele"));
		}
		DBConnection.close(rs, ps, conn);
		System.out.println("登录成功");
		return bean;
	}

	@Override
	public ArrayList<Customer> searchAllCustomer() throws Exception {
		ArrayList<Customer> list = new ArrayList<Customer>();
		Connection conn = DBConnection.getConnection();
		PreparedStatement ps = conn.prepareStatement("select * from Customer");
		ResultSet rs = ps.executeQuery();
		
		while(rs.next()) {
			Customer bean = new Customer();
			bean.setC_Account_number(rs.getString("C_Account_number"));
			bean.setC_Community(rs.getString("C_Community"));
			bean.setC_Email(rs.getString("C_Email"));
			bean.setC_HealthTimes(rs.getInt("C_HealthTimes"));
			bean.setC_ID(rs.getString("C_ID"));
			bean.setC_Integration(rs.getInt("C_Integration"));
			bean.setC_Level(rs.getInt("C_Level"));
			bean.setC_Nickname(rs.getString("C_Nickname"));
			bean.setC_Number(new Integer(rs.getInt("C_Number")).toString());
			bean.setC_Password(rs.getString("C_Password"));
			bean.setC_Username(rs.getString("C_Username"));
			bean.setC_Sex(rs.getString("C_Sex"));
			bean.setC_Tele(rs.getString("C_Tele"));
			list.add(bean);
		}
		DBConnection.close(rs, ps, conn);
		System.out.println("成功");
		return list;
	}
/*	public static void main(String args[])
	{
		Customer bean=new Customer();
		CustomerImplement ai=new CustomerImplement();
		bean.setC_Account_number("C_Account_number");
		bean.setC_Community("C_Community");
		bean.setC_Email("C_Email");
		bean.setC_HealthTimes(12);
		bean.setC_ID("C_ID");
		bean.setC_Integration(11);
		bean.setC_Level(14);
		bean.setC_Nickname("C_Nickname");
		bean.setC_Number("001");
		bean.setC_Password("C_Password");
		bean.setC_Username("C_Username");
		bean.setC_Sex("C_Sex");
		bean.setC_Tele("C_Tele");
		try {
		//	ai.addCustomer(bean);
			bean.setC_Sex("nan");
			ai.updateCustomer(bean);
			bean=ai.searchCustomerByID(bean.getC_Number());
			ArrayList<Customer> list = new ArrayList<Customer>();
			list=ai.searchAllCustomer();
			//ai.deleteCustomerByID(bean.getC_Number());
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}*/
}

