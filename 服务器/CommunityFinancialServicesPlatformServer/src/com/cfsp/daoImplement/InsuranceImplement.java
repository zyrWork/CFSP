package com.cfsp.daoImplement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.cfsp.connectDB.DBConnection;
import com.cfsp.daoInterface.*;
import com.cfsp.entity.Insurance;

public class InsuranceImplement implements InsuranceInterface {

	@Override
	public void addInsurance(Insurance insurance) throws Exception {
		Connection conn = DBConnection.getConnection();
		PreparedStatement ps = conn.prepareStatement("insert into Insurance(I_ID,I_CompanyName,I_Name,I_Rate,I_Explain,I_AgencyNetwork) values(?,?,?,?,?,?)");
		ps.setString(1, insurance.getI_ID());
		ps.setString(2, insurance.getI_Name());
		ps.setString(3, insurance.getI_CompanyName());
		ps.setDouble(4, insurance.getI_Rate());
		ps.setString(5, insurance.getI_Explain());
		ps.setString(6, insurance.getI_AgencyNetwork());
		
		ps.executeUpdate();
		System.out.println("≤Â»Î≥…π¶");
		DBConnection.close(ps, conn);
		
	}

	@Override
	public void deleteInsuranceByID(String ID) throws Exception {
		Connection conn = DBConnection.getConnection();
		PreparedStatement ps = conn.prepareStatement("delete from Insurance where I_ID=?");
		ps.setString(1,ID);
		ps.executeUpdate();
		DBConnection.close(ps, conn);		
	}

	@Override
	public void updateInsurance(Insurance insurance) throws Exception {
		Connection conn = DBConnection.getConnection();
		PreparedStatement ps = conn.prepareStatement("update Insurance set I_CompanyName=?,I_Name=?,I_Rate=?,I_Explain=?,I_AgencyNetwork=? where I_ID=?");
		ps.setString(1, insurance.getI_Name());
		ps.setString(2, insurance.getI_CompanyName());
		ps.setDouble(3, insurance.getI_Rate());
		ps.setString(4, insurance.getI_Explain());
		ps.setString(5, insurance.getI_AgencyNetwork());
		ps.setString(6, insurance.getI_ID());
		
		ps.executeUpdate();
		DBConnection.close(ps, conn);		
	}

	@Override
	public Insurance searchInsuranceByID(String ID) throws Exception {
		Insurance bean = null;
		Connection conn = DBConnection.getConnection();
		PreparedStatement ps = conn.prepareStatement("select * from Insurance where I_ID=?");
		ps.setString(1, ID);
		ResultSet rs = ps.executeQuery();
		
		while(rs.next()) {
			bean = new Insurance();
			bean.setI_ID(rs.getString("I_ID"));
			bean.setI_CompanyName(rs.getString("I_CompanyName"));
			bean.setI_Name(rs.getString("I_Name"));
			bean.setI_Rate(rs.getDouble("I_Rate"));
			bean.setI_Explain(rs.getString("I_Explain"));
			bean.setI_AgencyNetwork(rs.getString("I_AgencyNetwork"));
		}
		DBConnection.close(rs, ps, conn);
		return bean;
	}

	@Override
	public ArrayList<Insurance> searchAllInsurance() throws Exception {
		ArrayList<Insurance> list = new ArrayList<Insurance>();
		Connection conn = DBConnection.getConnection();
		PreparedStatement ps = conn.prepareStatement("select * from Insurance");
		ResultSet rs = ps.executeQuery();
		
		while(rs.next()) {
			Insurance bean = new Insurance();
			bean.setI_ID(rs.getString("I_ID"));
			bean.setI_CompanyName(rs.getString("I_CompanyName"));
			bean.setI_Name(rs.getString("I_Name"));
			bean.setI_Rate(rs.getDouble("I_Rate"));
			bean.setI_Explain(rs.getString("I_Explain"));
			bean.setI_AgencyNetwork(rs.getString("I_AgencyNetwork"));
			list.add(bean);
			
		}
		DBConnection.close(rs, ps, conn);
		return list;
	}


}
