package com.cfsp.daoImplement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.cfsp.connectDB.DBConnection;
import com.cfsp.daoInterface.*;
import com.cfsp.entity.Security;

public class SecurityImplement implements SecurityInterface {

	@Override
	public void addSecurity(Security s) throws Exception {
		// TODO Auto-generated method stub
		Connection conn = DBConnection.getConnection();
		PreparedStatement ps = conn.prepareStatement("insert into Security(S_ID,S_CompanyName,S_Explain,S_Rate,"
													+ "S_AfterSale,S_AgencyNetwork) values(?,?,?,?,?,?)");
		ps.setString(1, s.getS_ID());
		ps.setString(2, s.getS_CompanyName());
		ps.setString(3, s.getS_Explain());
		ps.setDouble(4, s.getS_Rate());
		ps.setString(5, s.getS_AfterSale());
		ps.setString(6, s.getS_AgencyNetwork());
		ps.executeUpdate();
		System.out.println("≤Â»Î≥…π¶");
		DBConnection.close(ps, conn);
		
	}

	@Override
	public void deleteSecurityID(String ID) throws Exception {
		// TODO Auto-generated method stub
		Connection conn = DBConnection.getConnection();
		PreparedStatement ps = conn.prepareStatement("delete from Security where S_ID=?");
		ps.setString(1,ID);
		ps.executeUpdate();
		DBConnection.close(ps, conn);
	}

	@Override
	public void updateSecurity(Security s) throws Exception {
		// TODO Auto-generated method stub
		Connection conn = DBConnection.getConnection();
		PreparedStatement ps = conn.prepareStatement("update Security set S_CompanyName=?,S_Explain=?,S_Rate=?,"
													+ "S_AfterSale=? ,S_AgencyNetwork=? where S_ID=?");
		ps.setString(1, s.getS_CompanyName());
		ps.setString(2, s.getS_Explain());
		ps.setDouble(3, s.getS_Rate());
		ps.setString(4, s.getS_AfterSale());
		ps.setString(5, s.getS_AgencyNetwork());
		ps.setString(6, s.getS_ID());
		ps.executeUpdate();
		DBConnection.close(ps, conn);
	}

	@Override
	public Security searchSecurityByID(String ID) throws Exception {
		// TODO Auto-generated method stub
		Security bean = null;
		Connection conn = DBConnection.getConnection();
		PreparedStatement ps = conn.prepareStatement("select * from Security where S_ID=?");
		ps.setString(1, ID);
		ResultSet rs = ps.executeQuery();
		
		while(rs.next()) {
			bean = new Security();
			bean.setS_CompanyName(rs.getString("S_CompanyName"));
			bean.setS_Explain(rs.getString("S_Explain"));
			bean.setS_Rate(rs.getDouble("S_Rate"));
			bean.setS_AfterSale(rs.getString("S_AfterSale"));
			bean.setS_AgencyNetwork(rs.getString("S_AgencyNetwork"));
			bean.setS_ID(rs.getString("S_ID"));
		}
		DBConnection.close(rs, ps, conn);
		return bean;
	}

	@Override
	public ArrayList<Security> searchAllSecurity() throws Exception {
		// TODO Auto-generated method stub
		ArrayList<Security> list = new ArrayList<Security>();
		Connection conn = DBConnection.getConnection();
		PreparedStatement ps = conn.prepareStatement("select * from Security");
		ResultSet rs = ps.executeQuery();
		
		while(rs.next()) {
			Security a = new Security();
			a.setS_CompanyName(rs.getString("S_CompanyName"));
			a.setS_Explain(rs.getString("S_Explain"));
			a.setS_Rate(rs.getDouble("S_Rate"));
			a.setS_AfterSale(rs.getString("S_AfterSale"));
			a.setS_AgencyNetwork(rs.getString("S_AgencyNetwork"));
			a.setS_ID(rs.getString("S_ID"));
			list.add(a);
		}
		DBConnection.close(rs, ps, conn);
		return list;
	}
	
}