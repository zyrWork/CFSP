package com.cfsp.daoImplement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Date;
import java.util.ArrayList;

import com.cfsp.connectDB.DBConnection;
import com.cfsp.daoInterface.*;
import com.cfsp.entity.Health;

public class HealthImplement implements HealthInterface {

	@Override
	public void addHealth(Health h) throws Exception {
		// TODO Auto-generated method stub
		Connection conn = DBConnection.getConnection();
		PreparedStatement ps = conn.prepareStatement("insert into Health(H_ID,C_Number,H_Datetime,"
													+ "H_Index,H_Result,H_Height,H_Weight,H_BloodPressure,H_BloodSugar,"
													+ "H_Cardiogram,H_BloodFat)"
															+ " values(?,?,?,?,?,?,?,?,?,?,?)");
		ps.setString(1, h.getH_ID());
		ps.setString(2, h.getC_Number());
		ps.setDate(3, Date.valueOf(h.getH_Datetime()));
		ps.setInt(4, h.getH_Index());
		ps.setString(5, h.getH_Result());
		ps.setInt(6, h.getH_Height());
		ps.setInt(7, h.getH_Weight());
		ps.setString(8, h.getH_BloodPressure());
		ps.setString(9, h.getH_BloodSugar());
		ps.setString(10, h.getH_Cardiogram());
		ps.setString(11, h.getH_BloodFat());
		
		ps.executeUpdate();
		System.out.println("≤Â»Î≥…π¶");
		DBConnection.close(ps, conn);
	}

	@Override
	public void deleteHealthByID(String ID) throws Exception {
		// TODO Auto-generated method stub
		Connection conn = DBConnection.getConnection();
		PreparedStatement ps = conn.prepareStatement("delete from Health where H_ID=?");
		ps.setString(1,ID);
		ps.executeUpdate();
		DBConnection.close(ps, conn);
	}

	@Override
	public void updateHealth(Health h) throws Exception {
		// TODO Auto-generated method stub
		Connection conn = DBConnection.getConnection();
		PreparedStatement ps = conn.prepareStatement("update Health set C_Number=?,H_Datetime=?,H_Index=?,"
													+ "H_Result=?,H_Height=?,H_Weight=?,H_BloodPressure=?,H_BloodSugar=?,H_Cardiogram=?,"
													+ "H_BloodFat=? where H_ID=?");
		ps.setString(1, h.getC_Number());
		ps.setDate(2, Date.valueOf(h.getH_Datetime()));
		ps.setInt(3, h.getH_Index());
		ps.setString(4, h.getH_Result());
		ps.setInt(5, h.getH_Height());
		ps.setInt(6, h.getH_Weight());
		ps.setString(7, h.getH_BloodPressure());
		ps.setString(8, h.getH_BloodSugar());
		ps.setString(9, h.getH_Cardiogram());
		ps.setString(10, h.getH_BloodFat());
		ps.setString(11, h.getH_ID());
		
		ps.executeUpdate();
		DBConnection.close(ps, conn);
	}

	@Override
	public Health searchHealthByID(String ID) throws Exception {
		// TODO Auto-generated method stub
		Health bean = null;
		Connection conn = DBConnection.getConnection();
		PreparedStatement ps = conn.prepareStatement("select * from Health where H_ID=?");
		ps.setString(1, ID);
		ResultSet rs = ps.executeQuery();
		
		while(rs.next()) {
			bean= new Health();
			bean.setC_Number(rs.getString("C_Number"));
			bean.setH_Datetime(rs.getString("H_Datetime").toString());
			bean.setH_Index(rs.getInt("H_Index"));
			bean.setH_Result(rs.getString("H_Result"));
			bean.setH_Height(rs.getInt("H_Height"));
			bean.setH_Weight(rs.getInt("H_Weight"));
			bean.setH_BloodPressure(rs.getString("H_BloodPressure"));
			bean.setH_BloodSugar(rs.getString("H_BloodSugar"));
			bean.setH_Cardiogram(rs.getString("H_Cardiogram"));
			bean.setH_BloodFat(rs.getString("H_BloodFat"));
			bean.setH_ID(rs.getString("H_ID"));
		}
		DBConnection.close(rs, ps, conn);
		return bean;
	}

	@Override
	public ArrayList<Health> searchAllHealth() throws Exception {
		// TODO Auto-generated method stub
		ArrayList<Health> list = new ArrayList<Health>();
		Connection conn = DBConnection.getConnection();
		PreparedStatement ps = conn.prepareStatement("select * from Health");
		ResultSet rs = ps.executeQuery();
		
		while(rs.next()) {
			Health bean = new Health();
			bean.setC_Number(rs.getString("C_Number"));
			bean.setH_Datetime(rs.getString("H_Datetime").toString());
			bean.setH_Index(rs.getInt("H_Index"));
			bean.setH_Result(rs.getString("H_Result"));
			bean.setH_Height(rs.getInt("H_Height"));
			bean.setH_Weight(rs.getInt("H_Weight"));
			bean.setH_BloodPressure(rs.getString("H_BloodPressure"));
			bean.setH_BloodSugar(rs.getString("H_BloodSugar"));
			bean.setH_Cardiogram(rs.getString("H_Cardiogram"));
			bean.setH_BloodFat(rs.getString("H_BloodFat"));
			bean.setH_ID(rs.getString("H_ID"));
			list.add(bean);
		}
		DBConnection.close(rs, ps, conn);
		return list;
	}
/*
	public static void main(String args[])
	{
		Health bean;
		HealthImplement hi=new HealthImplement();
		bean = new Health();
		bean.setC_Number("001");
		bean.setH_Datetime("H_Datetime");
		bean.setH_Index(12);
		bean.setH_Result("H_Result");
		bean.setH_Height(12);
		bean.setH_Weight(23);
		bean.setH_BloodPressure("H_BloodPressure");
		bean.setH_BloodSugar("H_BloodSugar");
		bean.setH_Cardiogram("H_Cardiogram");
		bean.setH_BloodFat("H_BloodFat");
		bean.setH_ID("H_ID");
		try {
		//	ai.addCustomer(bean);
		//	bean.setC_Sex("nan");
		//	di.addDetail(bean);
			//di.deleteDetailByID(bean.getD_ID());
			//hi.addHealth(bean);
			//hi.deleteHealthByID(bean.getH_ID());
		//	bean.setH_BloodFat("1223");
			//hi.updateHealth(bean);
			hi.searchAllHealth();
			hi.searchHealthByID(bean.getH_ID());
		
        //   bean=ai.searchCustomerByID(bean.getC_Number());
			ArrayList<Customer> list = new ArrayList<Customer>();
			list=ai.searchAllCustomer();
			//ai.deleteCustomerByID(bean.getC_Number());
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
*/
}

