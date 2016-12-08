package com.cfsp.daoImplement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import com.cfsp.connectDB.DBConnection;
import com.cfsp.daoInterface.*;
import com.cfsp.entity.News;

public class NewsImplement implements NewsInterface  {

	@Override
	public void addNews(News news) throws Exception {
		Connection conn = DBConnection.getConnection();
		PreparedStatement ps = conn.prepareStatement("insert into News(N_ID,N_Code,N_Title,N_Info,N_Publisher,N_PubTime,N_BuyLink,N_OrderLink,N_Picture) values(?,?,?,?,?,?,?,?,?)");
		ps.setString(1, news.getN_ID());
		ps.setString(2, news.getN_Code());
		ps.setString(3,news.getN_Title());
		ps.setString(4, news.getN_Info());
		ps.setString(5, news.getN_Publisher());
		ps.setString(6, news.getN_PubTime());
		ps.setString(7, news.getN_BuyLink());
		ps.setString(8, news.getN_OrderLink());
		ps.setString(9, news.getN_Picture());
		ps.executeUpdate();
		System.out.println("插入成功");
		DBConnection.close(ps, conn);
		
	}

	@Override
	public void deleteNewsID(String ID) throws Exception {
		Connection conn = DBConnection.getConnection();
		PreparedStatement ps = conn.prepareStatement("delete from News where N_ID=?");
		ps.setString(1,ID);
		ps.executeUpdate();
		System.out.println("删除成功");
		DBConnection.close(ps, conn);
		
	}

	@Override
	public void updateNews(News news) throws Exception {
		Connection conn = DBConnection.getConnection();
		PreparedStatement ps = conn.prepareStatement("update News set N_Code=?,N_Title=?,N_Info=?,N_Publisher=?,N_PubTime=?,N_BuyLink=?,N_OrderLink=?,N_Picture=? where N_ID=?");

		ps.setString(1, news.getN_Code());
		ps.setString(2, news.getN_Title());
		ps.setString(3, news.getN_Info());
		ps.setString(4, news.getN_Publisher());
		ps.setString(5, news.getN_PubTime());
		ps.setString(6, news.getN_BuyLink());
		ps.setString(7, news.getN_OrderLink());
		ps.setString(8, news.getN_Picture());
		ps.setString(9, news.getN_ID());
		
		ps.executeUpdate();
		System.out.println("修改成功");
		DBConnection.close(ps, conn);
		
	}

	@Override
	public News searchNewsByID(String ID) throws Exception {
		 News bean = null;
		Connection conn = DBConnection.getConnection();
		PreparedStatement ps = conn.prepareStatement("select * from  News where N_ID=?");
		ps.setString(1, ID);
		ResultSet rs = ps.executeQuery();
		
		while(rs.next()) {
			bean = new News();
			bean.setN_ID(rs.getString("N_ID"));
			bean.setN_Code(rs.getString("N_Code"));
			bean.setN_Title(rs.getString("N_Title"));
			bean.setN_Info(rs.getString("N_Info"));
			bean.setN_Publisher(rs.getString("N_Publisher"));
			bean.setN_PubTime(rs.getString("N_PubTime"));
			bean.setN_BuyLink(rs.getString("N_BuyLink"));
			bean.setN_OrderLink(rs.getString("N_OrderLink"));
			bean.setN_Picture(rs.getString("N_Picture"));
			
		}
		System.out.println("查阅成功");
		DBConnection.close(rs, ps, conn);
		return bean;
	}

	@Override
	public ArrayList<News> searchAllNews() throws Exception {
		ArrayList<News> list = new ArrayList<News>();
		Connection conn = DBConnection.getConnection();
		PreparedStatement ps = conn.prepareStatement("select * from News");
		ResultSet rs = ps.executeQuery();
		
		while(rs.next()) {
			News n = new News();
			n.setN_ID(rs.getString("N_ID"));
			n.setN_Code(rs.getString("N_Code"));
			n.setN_Title(rs.getString("N_Title"));
			n.setN_Info(rs.getString("N_Info"));
		    n.setN_Publisher(rs.getString("N_Publisher"));
			n.setN_PubTime(rs.getString("N_PubTime"));
			n.setN_BuyLink(rs.getString("N_BuyLink"));
			n.setN_OrderLink(rs.getString("N_OrderLink"));
			n.setN_Picture(rs.getString("N_Picture"));
			
			list.add(n);
		}
		System.out.println("查阅成功");
		DBConnection.close(rs, ps, conn);
		return list;
	}
	/*
	public  static void main(String argc[])
	{
		News news=new News();
		news.setN_ID("000001");
		news.setN_Code("000001");
		news.setN_Title("100");
		news.setN_Info("sd");
		news.setN_Publisher("yes");
		news.setN_PubTime("2016-01-02");
		news.setN_BuyLinkr("www");
		news.setN_OrderLink("www"); 
		news.setN_Picture("d:/picture/1.jpg");
		
		NewsImplement n=new NewsImplement();
		
	    try {
			 n.addNews(news);
			 n.deleteNewsID(news.getN_ID());
			 n.addNews(news);
			 n.searchNewsByID(news.getN_ID());
		
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }*/
	
	
}