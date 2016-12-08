package com.cfsp.daoImplement;

import java.util.ArrayList;

import com.cfsp.daoInterface.*;
import com.cfsp.entity.Orders;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Date;
import com.cfsp.connectDB.DBConnection;

public class OrdersImplement implements OrdersInterface {

	@Override
	public void addOrder(Orders o) throws Exception {
		Connection conn = DBConnection.getConnection();
		PreparedStatement ps = conn.prepareStatement("insert into Orders(O_Number,C_Number,O_Date,O_Money,O_ProductServe,O_IsPayOff,O_Evaluate) values(?,?,?,?,?,?,?)");
		ps.setString(1, o.getO_Number());
		ps.setString(2, o.getC_Number());
		ps.setString(3, o.getO_Date());
		ps.setDouble(4, o.getO_Money());
		ps.setString(5, o.getO_ProductServe());
		ps.setString(6, o.getO_IsPayOff());
		ps.setString(7, o.getO_Evaluate());
		ps.executeUpdate();
		System.out.println("插入成功");
		DBConnection.close(ps, conn);		
	}

	@Override
	public void deleteOrderID(String ID) throws Exception {
		Connection conn = DBConnection.getConnection();
		PreparedStatement ps = conn.prepareStatement("delete from Orders where O_Number=?");
		ps.setString(1,ID);
		ps.executeUpdate();
		System.out.println("删除成功");
		DBConnection.close(ps, conn);
		
	}

	@Override
	public void updateOrder(Orders o) throws Exception {
		Connection conn = DBConnection.getConnection();
		PreparedStatement ps = conn.prepareStatement("update Orders set C_Number=?,O_Date=?,O_Money=?,O_ProductServe=?,O_IsPayOff=?,O_Evaluate=? where O_Number=?");
		ps.setString(1, o.getC_Number());
		ps.setString(2, o.getO_Date());
		ps.setDouble(3, o.getO_Money());
		ps.setString(4, o.getO_ProductServe());
		ps.setString(5, o.getO_IsPayOff());
		ps.setString(6, o.getO_Evaluate());
		ps.setString(7, o.getO_Number());
		
		ps.executeUpdate();
		System.out.println("修改成功");
		DBConnection.close(ps, conn);
		
	}

	@Override
	public Orders searchOrderByID(String O_Number) throws Exception {
		Orders bean = null;
		Connection conn = DBConnection.getConnection();
		PreparedStatement ps = conn.prepareStatement("select * from Orders where O_Number=?");
		ps.setString(1, O_Number);
		ResultSet rs = ps.executeQuery();
		
		while(rs.next()) {
			bean = new Orders();
			bean.setO_Number(rs.getString("O_Number"));
			bean.setC_Number(rs.getString("C_Number"));
			bean.setO_Date(rs.getString("O_Date"));
			bean.setO_Money(rs.getDouble("O_Money"));
			bean.setO_ProductServe(rs.getString("O_ProductServe"));
			bean.setO_IsPayOff(rs.getString("O_IsPayOff"));
			bean.setO_Evaluate(rs.getString("O_Evaluate"));
		}
		DBConnection.close(rs, ps, conn);
		System.out.println("查找成功");
		return bean;
	}

	@Override
	public ArrayList<Orders> searchAllOrder() throws Exception {
		ArrayList<Orders> list = new ArrayList<Orders>();
		Connection conn = DBConnection.getConnection();
		PreparedStatement ps = conn.prepareStatement("select * from Orders");
		ResultSet rs = ps.executeQuery();
		
		while(rs.next()) {
			Orders bean = new Orders();
			bean.setO_Number(rs.getString("O_Number"));
			bean.setC_Number(rs.getString("C_Number"));
			bean.setO_Date(rs.getString("O_Date"));
			bean.setO_Money(rs.getDouble("O_Money"));
			bean.setO_ProductServe(rs.getString("O_ProductServe"));
			bean.setO_IsPayOff(rs.getString("O_IsPayOff"));
			bean.setO_Evaluate(rs.getString("O_Evaluate"));
			list.add(bean);
		}
		System.out.println("查找成功");
		DBConnection.close(rs, ps, conn);
		return list;
	}
/*
	public  static void main(String argc[])
	{
		Orders orders=new Orders();
		orders.setO_Number("000001");
		orders.setC_Number("000001");
		orders.setO_Money(100);
		orders.setO_ProductServe("sd");
		orders.setO_IsPayOff("yes");
		orders.setO_Evaluate("very good");
		orders.setO_Date("2016-07-17");
		
		OrdersImplement o=new OrdersImplement();
		
	    try {
			 o.addOrder(orders);
			 o.deleteOrderID(orders.getO_Number());
			 o.addOrder(orders);
			 o.searchOrderByID(orders.getO_Number());
		
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }*/

}