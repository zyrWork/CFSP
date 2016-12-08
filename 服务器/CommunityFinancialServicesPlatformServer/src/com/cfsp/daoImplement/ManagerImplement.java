package com.cfsp.daoImplement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import com.cfsp.connectDB.DBConnection;
import com.cfsp.daoInterface.*;
import com.cfsp.entity.Manager;

public class ManagerImplement implements ManagerInterface {

	@Override
	public void addManager(Manager m) throws Exception {
		Connection conn = DBConnection.getConnection();
		PreparedStatement ps = conn.prepareStatement("insert into Manager(M_Number,M_Username,M_Password) values(?,?,?)");
		ps.setString(1, m.getM_Number());
		ps.setString(2, m.getM_Username());
		ps.setString(3,m.getM_Password());
		ps.executeUpdate();
		System.out.println("≤Â»Î≥…π¶");
		DBConnection.close(ps, conn);
		
	}

	@Override
	public void deleteManagerID(String ID) throws Exception {
		Connection conn = DBConnection.getConnection();
		PreparedStatement ps = conn.prepareStatement("delete from Manager where M_Number=?");
		ps.setString(1,ID);
		ps.executeUpdate();
		DBConnection.close(ps, conn);
		
	}

	@Override
	public void updateManager(Manager m) throws Exception {
		Connection conn = DBConnection.getConnection();
		PreparedStatement ps = conn.prepareStatement("update Manager set M_Username=?,M_Password=? where M_Number=?");
		ps.setString(1,m.getM_Username());
		ps.setString(2,m.getM_Password());
		ps.setString(3,m.getM_Number());
		ps.executeUpdate();
		DBConnection.close(ps, conn);
		
	}

	@Override
	public Manager searchManagerByID(String ID) throws Exception {
		Manager bean = null;
		Connection conn = DBConnection.getConnection();
		PreparedStatement ps = conn.prepareStatement("select * from Manager where M_Number=?");
		ps.setString(1, ID);
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			bean = new Manager();
			bean.setM_Number(rs.getString("M_Number"));
			bean.setM_Username(rs.getString("M_Username"));
			bean.setM_Password(rs.getString("M_Password"));
		}
		DBConnection.close(rs, ps, conn);
		return bean;
		
	}

	@Override
	public ArrayList<Manager> searchAllManager() throws Exception {
		ArrayList<Manager> list = new ArrayList<Manager>();
		Connection conn = DBConnection.getConnection();
		PreparedStatement ps = conn.prepareStatement("select * from Manager");
		ResultSet rs = ps.executeQuery();
		
		while(rs.next()) {
			Manager m = new Manager();
			m.setM_Number(rs.getString("M_Number"));
			m.setM_Username(rs.getString("M_Username"));
			m.setM_Password(rs.getString("M_Password"));
			list.add(m);
		}
		DBConnection.close(rs, ps, conn);
		return list;
	}


}

