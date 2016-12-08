package com.cfsp.daoImplement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.cfsp.connectDB.DBConnection;
import com.cfsp.daoInterface.*;
import com.cfsp.entity.Laundry;

public class LaundryImplement implements LaundryInterface  {

	@Override
	public void addLaundry(Laundry laundry) throws Exception {
		Connection conn = DBConnection.getConnection();
		PreparedStatement ps = conn.prepareStatement("insert into Laundry(L_ID,L_ServerName,L_ServerKind,L_Explain,L_Price,L_SortNumber,L_Tele) values(?,?,?,?,?,?,?)");
		ps.setString(1, laundry.getL_ID());
		ps.setString(2, laundry.getL_ServerName());
		ps.setString(3, laundry.getL_ServerKind());
		ps.setString(4, laundry.getL_Explain());
		ps.setDouble(5, laundry.getL_Price());
		ps.setString(6, laundry.getL_SortNumber());
		ps.setString(7, laundry.getL_Tele());
		
		ps.executeUpdate();
		System.out.println("≤Â»Î≥…π¶");
		DBConnection.close(ps, conn);
		
	}

	@Override
	public void deleteLaundryID(String ID) throws Exception {
		Connection conn = DBConnection.getConnection();
		PreparedStatement ps = conn.prepareStatement("delete from Laundry where L_ID=?");
		ps.setString(1,ID);
		ps.executeUpdate();
		DBConnection.close(ps, conn);		
	}

	@Override
	public void updateLaundry(Laundry laundry) throws Exception {
		Connection conn = DBConnection.getConnection();
		PreparedStatement ps = conn.prepareStatement("update Laundry set L_ServerName=?,L_ServerKind=?,L_Explain=?,L_Price=?,L_SortNumber=?,L_Tele=? where L_ID=?");
		ps.setString(1, laundry.getL_ServerName());
		ps.setString(2, laundry.getL_ServerKind());
		ps.setString(3, laundry.getL_Explain());
		ps.setDouble(4, laundry.getL_Price());
		ps.setString(5, laundry.getL_SortNumber());
		ps.setString(6, laundry.getL_Tele());
		ps.setString(7, laundry.getL_ID());
		
		ps.executeUpdate();
		DBConnection.close(ps, conn);		
	}

	@Override
	public Laundry searchLaundryByID(String ID) throws Exception {
		Laundry bean = null;
		Connection conn = DBConnection.getConnection();
		PreparedStatement ps = conn.prepareStatement("select * from Laundry where L_ID=?");
		ps.setString(1, ID);
		ResultSet rs = ps.executeQuery();
		
		while(rs.next()) {
			bean = new Laundry();
			bean.setL_ID(rs.getString("L_ID"));
			bean.setL_ServerName(rs.getString("L_ServerName"));
			bean.setL_ServerKind(rs.getString("L_ServerKind"));
			bean.setL_Explain(rs.getString("L_Explain"));
			bean.setL_Price(rs.getDouble("L_Price"));
			bean.setL_SortNumber(rs.getString("L_SortNumber"));
			bean.setL_Tele(rs.getString("L_Tele"));
		}
		DBConnection.close(rs, ps, conn);
		return bean;
	}

	@Override
	public ArrayList<Laundry> searchAllLaundry() throws Exception {
		ArrayList<Laundry> list = new ArrayList<Laundry>();
		Connection conn = DBConnection.getConnection();
		PreparedStatement ps = conn.prepareStatement("select * from Laundry");
		ResultSet rs = ps.executeQuery();
		
		while(rs.next()) {
			Laundry bean = new Laundry();
			bean.setL_ID(rs.getString("L_ID"));
			bean.setL_ServerName(rs.getString("L_ServerName"));
			bean.setL_ServerKind(rs.getString("L_ServerKind"));
			bean.setL_Explain(rs.getString("L_Explain"));
			bean.setL_Price(rs.getDouble("L_Price"));
			bean.setL_SortNumber(rs.getString("L_SortNumber"));
			bean.setL_Tele(rs.getString("L_Tele"));
			list.add(bean);
		}
		DBConnection.close(rs, ps, conn);
		return list;
	}

	
  
}
