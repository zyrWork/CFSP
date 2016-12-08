package com.cfsp.daoImplement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import com.cfsp.connectDB.DBConnection;
import com.cfsp.daoInterface.*;
import com.cfsp.entity.Product_Order;

public class Product_OrderImplement implements Product_OrderInterface{

	@Override
	public void addProduct_Order(Product_Order po) throws Exception {
		Connection conn = DBConnection.getConnection();
		PreparedStatement ps = conn.prepareStatement("insert into Manager(O_Number,P_Number,PO_Count) values(?,?,?)");
		ps.setString(1, po.getO_Number());
		ps.setString(2, po.getP_Number());
		ps.setInt(3, po.getPO_Count());
		ps.executeUpdate();
		System.out.println("≤Â»Î≥…π¶");
		DBConnection.close(ps, conn);
		
	}

	@Override
	public void deleteProduct_OrderID(String ID) throws Exception {
		Connection conn = DBConnection.getConnection();
		PreparedStatement ps = conn.prepareStatement("delete from Product_Order where O_Number=?");
		ps.setString(1,ID);
		ps.executeUpdate();
		DBConnection.close(ps, conn);
		
	}

	@Override
	public void updateProduct_Order(Product_Order po) throws Exception {
		Connection conn = DBConnection.getConnection();
		PreparedStatement ps = conn.prepareStatement("update Product_Order set O_Number=?,P_Number=? where PO_Count=?");
		ps.setString(1,po.getO_Number());
		ps.setString(2,po.getP_Number());
		ps.setInt(3,po.getPO_Count());
		ps.executeUpdate();
		DBConnection.close(ps, conn);
		
	}

	@Override
	public Product_Order searchProduct_OrderByPO(String pID, String oID) throws Exception {
		Product_Order bean = null;
		Connection conn = DBConnection.getConnection();
		PreparedStatement ps = conn.prepareStatement("select * from Product_Order where pID=? and oID=?");
		ps.setString(1, pID);
		ps.setString(2, oID);
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			bean = new Product_Order();
			bean.setO_Number(rs.getString("O_Number"));
			bean.setP_Number(rs.getString("P_Number"));
			bean.setPO_Count(rs.getInt("PO_Count"));
		}
		DBConnection.close(rs, ps, conn);
		return bean;
	}

	@Override
	public ArrayList<Product_Order> searchAllProduct_Order() throws Exception {
		ArrayList<Product_Order> list = new ArrayList<Product_Order>();
		Connection conn = DBConnection.getConnection();
		PreparedStatement ps = conn.prepareStatement("select * from Product_Order");
		ResultSet rs = ps.executeQuery();
		
		while(rs.next()) {
			Product_Order po = new Product_Order();
			po.setO_Number(rs.getString("O_Number"));
			po.setP_Number(rs.getString("P_Number"));
			po.setPO_Count(rs.getInt("PO_Count"));
			list.add(po);
		}
		DBConnection.close(rs, ps, conn);
		return list;
	}

}