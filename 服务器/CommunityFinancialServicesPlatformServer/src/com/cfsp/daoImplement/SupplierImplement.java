package com.cfsp.daoImplement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.cfsp.connectDB.DBConnection;
import com.cfsp.daoInterface.*;
import com.cfsp.entity.Supplier;

public class SupplierImplement implements SupplierInterface {

	@Override
	public void addSupplier(Supplier s) throws Exception {
		Connection conn = DBConnection.getConnection();
		PreparedStatement ps = conn.prepareStatement("insert into Supplier(S_Number,S_Account_number,S_Password,S_Suppliername,S_Address,S_Tele) values(?,?,?,?,?,?)");
		ps.setString(1, s.getS_Number());
		ps.setString(2, s.getS_Account_number());
		ps.setString(3, s.getS_Password());
		ps.setString(4, s.getS_Suppliername());
		ps.setString(5, s.getS_Address());
		ps.setString(6, s.getS_Tele());
		
		ps.executeUpdate();
		System.out.println("≤Â»Î≥…π¶");
		DBConnection.close(ps, conn);
	}

	@Override
	public void deleteSupplierID(String ID) throws Exception {
		Connection conn = DBConnection.getConnection();
		PreparedStatement ps = conn.prepareStatement("delete from Supplier where S_Number=?");
		ps.setString(1,ID);
		ps.executeUpdate();
		DBConnection.close(ps, conn);
	}

	@Override
	public void updateSupplier(Supplier s) throws Exception {
		Connection conn = DBConnection.getConnection();
		PreparedStatement ps = conn.prepareStatement("update Supplier set S_Account_number=?,S_Password=?,S_Suppliername=?,S_Address=?,S_Tele=? where S_Number=?");

		ps.setString(1, s.getS_Account_number());
		ps.setString(2, s.getS_Password());
		ps.setString(3, s.getS_Suppliername());
		ps.setString(4, s.getS_Address());
		ps.setString(5, s.getS_Tele());
		ps.setString(6, s.getS_Number());
		
		ps.executeUpdate();
		DBConnection.close(ps, conn);
		
	}

	@Override
	public Supplier searchSupplierByID(String ID) throws Exception {
		Supplier bean = null;
		Connection conn = DBConnection.getConnection();
		PreparedStatement ps = conn.prepareStatement("select * from Supplier where S_Number=?");
		ps.setString(1, ID);
		ResultSet rs = ps.executeQuery();
		
		while(rs.next()) {
			bean = new Supplier();
			
			bean.setS_Account_number(  rs.getString("S_Account_number"));
			bean.setS_Address( rs.getString("S_Password"));
			bean.setS_Number( rs.getString("S_Number")) ;
			bean.setS_Password( rs.getString("S_Password"));
			bean.setS_Suppliername( rs.getString("S_Suppliername"));
			bean.setS_Tele( rs.getString("S_Tele"));
		}
		DBConnection.close(rs, ps, conn);
		return bean;
	}

	@Override
	public ArrayList<Supplier> searchAllSupplier() throws Exception {
		ArrayList<Supplier> list = new ArrayList<Supplier>();
		Connection conn = DBConnection.getConnection();
		PreparedStatement ps = conn.prepareStatement("select * from Supplier");
		ResultSet rs = ps.executeQuery();
		
		while(rs.next()) {
			Supplier bean = new Supplier();
			
			bean.setS_Account_number(  rs.getString("S_Account_number"));
			bean.setS_Address( rs.getString("S_Password"));
			bean.setS_Number( rs.getString("S_Number")) ;
			bean.setS_Password( rs.getString("S_Password"));
			bean.setS_Suppliername( rs.getString("S_Suppliername"));
			bean.setS_Tele( rs.getString("S_Tele"));
			
			list.add(bean);
		}
		DBConnection.close(rs, ps, conn);
		return list;
	}
	
}

