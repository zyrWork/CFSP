package com.cfsp.daoImplement;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
//import java.text.SimpleDateFormat;
//import java.text.DateFormat;

import com.cfsp.connectDB.DBConnection;
import com.cfsp.daoInterface.*;
import com.cfsp.entity.Vegetable;


public class VegetableImplement implements  VegetableInterface  {

	@Override
	public void addVegetable(Vegetable v) throws Exception {
		Connection conn = DBConnection.getConnection();
		PreparedStatement ps = conn.prepareStatement("insert into Vegetable(V_ID,V_Kind,V_Explain,V_BornPlace,V_Price,V_SendTime) values(?,?,?,?,?,?)");
		ps.setString(1, v.getV_ID());
		ps.setString(2, v.getV_Kind());
		ps.setString(3, v.getV_Explain());
		ps.setString(4, v.getV_BornPlace());
		ps.setDouble(5, v.getV_Price());
		
		ps.setDate(6, Date.valueOf(v.getV_SendTime()));
		
		ps.executeUpdate();
		System.out.println("插入成功");
		DBConnection.close(ps, conn);
	}

	@Override
	public void deleteVegetableID(String ID)  throws Exception {
		Connection conn = DBConnection.getConnection();
		PreparedStatement ps = conn.prepareStatement("delete from Vegetable where V_ID=?");
		ps.setString(1,ID);
		ps.executeUpdate();
		System.out.println("删除成功"); 
		DBConnection.close(ps, conn);
	}

	@Override
	public void updateVegetable(Vegetable v) throws Exception {
		Connection conn = DBConnection.getConnection();
		PreparedStatement ps = conn.prepareStatement("update Vegetable set V_Kind=?,V_Explain=?,V_BornPlace=?,V_Price=?,V_SendTime=? where V_ID=?");
		ps.setString(1, v.getV_Kind());
		ps.setString(2, v.getV_Explain());
		ps.setString(3, v.getV_BornPlace());
		ps.setDouble(4, v.getV_Price());
		//DateFormat df=new SimpleDateFormat("YYYY-MM-HH");
		ps.setDate(5, java.sql.Date.valueOf(v.getV_SendTime()));
		ps.setString(6, v.getV_ID());

		ps.executeUpdate();
		System.out.println("修改成功");
		DBConnection.close(ps, conn);
	}

	@Override
	public Vegetable searchVegetableByID(String ID) throws Exception {
		Vegetable bean = null;
		Connection conn = DBConnection.getConnection();
		PreparedStatement ps = conn.prepareStatement("select * from Vegetable where V_ID=?");
		ps.setString(1, ID);
		ResultSet rs = ps.executeQuery();
		
		while(rs.next()) {
	      bean = new Vegetable();
		  bean.setV_BornPlace(rs.getString("V_BornPlace"));
		  bean.setV_Explain( rs.getString("V_Explain"));
		  bean.setV_ID( rs.getString("V_ID"));
		  bean.setV_Kind( rs.getString("V_Kind"));
		  bean.setV_Price( rs.getDouble("V_Price"));
		  bean.setV_SendTime( rs.getDate("V_SendTime").toString());
		}
		System.out.println("查找成功");
		System.out.println(bean.getV_SendTime());
		DBConnection.close(rs, ps, conn);
		return bean;
	}

	@Override
	public ArrayList<Vegetable> searchAllVegetable()throws Exception {
		ArrayList<Vegetable> list = new ArrayList<Vegetable>();
		Connection conn = DBConnection.getConnection();
		PreparedStatement ps = conn.prepareStatement("select * from Vegetable");
		ResultSet rs = ps.executeQuery();
		
		while(rs.next()) {
			Vegetable bean = new Vegetable();
			 bean.setV_BornPlace(rs.getString("V_BornPlace"));
		     bean.setV_Explain( rs.getString("V_Explain"));
		     bean.setV_ID( rs.getString("V_ID"));
		     bean.setV_Kind( rs.getString("V_Kind"));
		     bean.setV_Price( rs.getDouble("V_Price"));
		     bean.setV_SendTime( rs.getString("V_SendTime").toString());
			list.add(bean);
		}
		System.out.println("查找成功");
		DBConnection.close(rs, ps, conn);
		return list;
	}

/*
	public  static void main(String argc[])
	{
		Vegetable vegetable=new Vegetable();
		vegetable.setV_ID("000001");
		vegetable.setV_Explain("tomato");
		vegetable.setV_BornPlace("hubei");
		vegetable.setV_Price(1.50);
		vegetable.setV_Kind("hubei");
		vegetable.setV_SendTime("1973-01-02");
		
		VegetableImplement v=new VegetableImplement();
		
	    try {
			 v.addVegetable(vegetable);
			 v.deleteVegetableID(vegetable.getV_ID());
			 v.addVegetable(vegetable);
			 v.searchVegetableByID(vegetable.getV_ID());
		
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

*/
}
