package com.cfsp.daoImplement;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Date;
import java.util.ArrayList;

import com.cfsp.connectDB.DBConnection;
import com.cfsp.daoInterface.*;
import com.cfsp.entity.Product;

public class ProductImplement implements ProductInterface {

	@Override
	public void addProduct(Product p) throws Exception {
		Connection conn = DBConnection.getConnection();
		PreparedStatement ps = conn.prepareStatement("insert into Product(P_Number,S_Number,P_Name,P_Price,P_Explain,"
													+ "P_BelongTo,P_Category,P_InvestKind,P_InvestStyle,P_InvestEvaluate,P_Code,"
													+ "P_Deadline,P_AnnualizedReturn,P_SubscriptionPoint,P_ExpectedRate,"
													+ "P_DateBegin,P_DateEnd,P_BrowseCount,P_BuyCount) "
													+ "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
		ps.setString(1, p.getP_Number());
		ps.setString(2, p.getS_Number());
		ps.setString(3, p.getP_Name());
		ps.setDouble(4, p.getP_Price());
		ps.setString(5, p.getP_Explain());
		ps.setString(6, p.getP_BelongTo());
		ps.setString(7, p.getP_Category());
		ps.setString(8, p.getP_InvestKind());
		ps.setString(9, p.getP_InvestStyle());
		ps.setString(10, p.getP_InvestEvaluate());
		ps.setString(11, p.getP_Code());
		ps.setString(12, p.getP_Deadline());
		ps.setDouble(13, p.getP_AnnualizedReturn());
		ps.setString(14, p.getP_SubscriptionPoint());
		ps.setDouble(15, p.getP_ExpectedRate());
		ps.setString(16, p.getP_DateBegin());
		ps.setString(17, p.getP_DateEnd());
		ps.setInt(18, p.getP_BrowseCount());
		ps.setInt(19, p.getP_BuyCount());
		
		ps.executeUpdate();
		System.out.println("≤Â»Î≥…π¶");
		DBConnection.close(ps, conn);
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteProductByID(String ID) throws Exception {
		// TODO Auto-generated method stub
		Connection conn = DBConnection.getConnection();
		PreparedStatement ps = conn.prepareStatement("delete from Product where P_Number=?");
		ps.setString(1,ID);
		ps.executeUpdate();
		DBConnection.close(ps, conn);
	}

	@Override
	public void updateProduct(Product p) throws Exception {
		// TODO Auto-generated method stub
		Connection conn = DBConnection.getConnection();
		PreparedStatement ps = conn.prepareStatement("update Product set S_Number=?,P_Name=?,"
													+ "P_Price=?,P_Explain=?,P_BelongTo=?,P_Category=?,P_InvestKind=?,"
													+ "P_InvestStyle=?,P_InvestEvaluate=?,P_Code=?,P_Deadline=?,"
													+ "P_AnnualizedReturn=?,P_SubscriptionPoint=?,P_ExpectedRate=?,"
													+ "P_DateBegin=?,P_DateEnd=?, P_BrowseCount=?, P_BuyCount=?, "
													+ "where P_Number=?");
		
		ps.setString(1, p.getS_Number());
		ps.setString(2, p.getP_Name());
		ps.setDouble(3, p.getP_Price());
		ps.setString(4, p.getP_Explain());
		ps.setString(5, p.getP_BelongTo());
		ps.setString(6, p.getP_Category());
		ps.setString(7, p.getP_InvestKind());
		ps.setString(8, p.getP_InvestStyle());
		ps.setString(9, p.getP_InvestEvaluate());
		ps.setString(10, p.getP_Code());
		ps.setString(11, p.getP_Deadline());
		ps.setDouble(12, p.getP_AnnualizedReturn());
		ps.setString(13, p.getP_SubscriptionPoint());
		ps.setDouble(14, p.getP_ExpectedRate());
		ps.setString(15,   p.getP_DateBegin());
		ps.setString(16, p.getP_DateEnd());
		ps.setInt(17, p.getP_BrowseCount());
		ps.setInt(18, p.getP_BuyCount());
		ps.setString(19, p.getP_Number());
		
		ps.executeUpdate();
		DBConnection.close(ps, conn);
	}

	@Override
	public Product searchProductByID(String ID) throws Exception {
		// TODO Auto-generated method stub
		Product bean = null;
		Connection conn = DBConnection.getConnection();
		PreparedStatement ps = conn.prepareStatement("select * from Product where P_Number=?");
		ps.setString(1, ID);
		ResultSet rs = ps.executeQuery();
	
		while(rs.next()) {
			bean = new Product();
			bean.setS_Number(rs.getString("S_Number"));
			bean.setP_Number(rs.getString("P_Number"));
			bean.setP_Name(rs.getString("P_Name"));
			bean.setP_Price(rs.getDouble("P_Price"));
			bean.setP_Explain(rs.getString("P_Explain"));
			bean.setP_BelongTo(rs.getString("P_BelongTo"));
			bean.setP_Category(rs.getString("P_Category"));
			bean.setP_InvestKind(rs.getString("P_InvestKind"));
			bean.setP_InvestStyle(rs.getString("P_InvestStyle"));
			bean.setP_InvestEvaluate(rs.getString("P_InvestEvaluate"));
			bean.setP_Code(rs.getString("P_Code"));
			bean.setP_Deadline(rs.getString("P_Deadline"));
			bean.setP_AnnualizedReturn(rs.getDouble("P_AnnualizedReturn"));
			bean.setP_SubscriptionPoint(rs.getString("P_SubscriptionPoint"));
			bean.setP_ExpectedRate(rs.getDouble("P_ExpectedRate"));
			bean.setP_DateBegin(rs.getString("P_DateBegin"));
			bean.setP_DateEnd(rs.getString("P_DateEnd"));
			bean.setP_BrowseCount(rs.getInt("P_BrowseCount"));
			bean.setP_BuyCount(rs.getInt("P_BuyCount"));
		}
		DBConnection.close(rs, ps, conn);
		return bean;
	}

	@Override
	public ArrayList<Product> searchAllProduct() throws Exception {
		// TODO Auto-generated method stub
		ArrayList<Product> list = new ArrayList<Product>();
		Connection conn = DBConnection.getConnection();
		PreparedStatement ps = conn.prepareStatement("select * from Product");
		ResultSet rs = ps.executeQuery();
		
		while(rs.next()) {
			Product bean = new Product();
			bean.setS_Number(rs.getString("S_Number"));
			bean.setP_Number(rs.getString("P_Number"));
			bean.setP_Name(rs.getString("P_Name"));
			bean.setP_Price(rs.getDouble("P_Price"));
			bean.setP_Explain(rs.getString("P_Explain"));
			bean.setP_BelongTo(rs.getString("P_BelongTo"));
			bean.setP_Category(rs.getString("P_Category"));
			bean.setP_InvestKind(rs.getString("P_InvestKind"));
			bean.setP_InvestStyle(rs.getString("P_InvestStyle"));
			bean.setP_InvestEvaluate(rs.getString("P_InvestEvaluate"));
			bean.setP_Code(rs.getString("P_Code"));
			bean.setP_Deadline(rs.getString("P_Deadline"));
			bean.setP_AnnualizedReturn(rs.getDouble("P_AnnualizedReturn"));
			bean.setP_SubscriptionPoint(rs.getString("P_SubscriptionPoint"));
			bean.setP_ExpectedRate(rs.getDouble("P_ExpectedRate"));
			bean.setP_DateBegin(rs.getString("P_DateBegin"));
			bean.setP_DateEnd(rs.getString("P_DateEnd"));
			bean.setP_BrowseCount(rs.getInt("P_BrowseCount"));
			bean.setP_BuyCount(rs.getInt("P_BuyCount"));
			list.add(bean);
			
		}
		DBConnection.close(rs, ps, conn);
		return list;
	}

}
