package com.cfsp.daoImplement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Date;
import java.util.ArrayList;

import com.cfsp.connectDB.DBConnection;
import com.cfsp.daoInterface.*;
import com.cfsp.entity.Detail;

public class DetailImplement implements DetailInterface {

	@Override
	public void addDetail(Detail d) throws Exception {
		// TODO Auto-generated method stub
		Connection conn = DBConnection.getConnection();
		PreparedStatement ps = conn.prepareStatement("insert into Detail(D_ID,A_ID,D_Type,D_Money,D_Datetime) values(?,?,?,?,?)");
		ps.setString(1, d.getD_ID());
		ps.setString(2, d.getA_ID());
		ps.setString(3, d.getD_Type());
		ps.setString(4, d.getD_Money());
		ps.setString(5, d.getD_Datetime());
		ps.executeUpdate();
		System.out.println("≤Â»Î≥…π¶");
		DBConnection.close(ps, conn);
		
	}

	@Override
	public void deleteDetailByID(String ID) throws Exception {
		// TODO Auto-generated method stub
		Connection conn = DBConnection.getConnection();
		PreparedStatement ps = conn.prepareStatement("delete from Detail where D_ID=?");
		ps.setString(1,ID);
		ps.executeUpdate();
		DBConnection.close(ps, conn);
		
	}

	@Override
	public void updateDetail(Detail d) throws Exception {
		// TODO Auto-generated method stub
		Connection conn = DBConnection.getConnection();
		PreparedStatement ps = conn.prepareStatement("update Detail set A_ID=?,D_Type=?,D_Money=?,D_Datetime=? where D_ID=?");
		ps.setString(1, d.getA_ID());
		ps.setString(2, d.getD_Type());
		ps.setString(3, d.getD_Money());
		ps.setString(4, d.getD_Datetime());
		ps.setString(5, d.getD_ID());
		ps.executeUpdate();
		DBConnection.close(ps, conn);
	}

	@Override
	public Detail searchDetailByID(String ID) throws Exception {
		// TODO Auto-generated method stub
		Detail bean = null;
		Connection conn = DBConnection.getConnection();
		PreparedStatement ps = conn.prepareStatement("select * from Detail where D_ID=?");
		ps.setString(1, ID);
		ResultSet rs = ps.executeQuery();
		
		while(rs.next()) {
			bean = new Detail();
			bean.setD_ID(rs.getString("D_ID"));
			bean.setA_ID(rs.getString("A_ID"));
			bean.setD_Type(rs.getString("D_Type"));
			bean.setD_Money(rs.getString("D_Money"));
			bean.setD_Datetime(rs.getString("D_Datetime"));
		}
		DBConnection.close(rs, ps, conn);
		return bean;
	}

	@Override
	public ArrayList<Detail> searchAllDetail() throws Exception {
		// TODO Auto-generated method stub
		ArrayList<Detail> list = new ArrayList<Detail>();
		Connection conn = DBConnection.getConnection();
		PreparedStatement ps = conn.prepareStatement("select * from Detail");
		ResultSet rs = ps.executeQuery();
		
		while(rs.next()) {
			Detail a = new Detail();
			a.setD_ID(rs.getString("D_ID"));
			a.setA_ID(rs.getString("A_ID"));
			a.setD_Type(rs.getString("D_Type"));
			a.setD_Money(rs.getString("D_Money"));
			a.setD_Datetime(rs.getString("D_Datetime"));
			list.add(a);
		}
		DBConnection.close(rs, ps, conn);
		return list;
	}
		
	
}

