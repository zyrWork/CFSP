package com.cfsp.daoImplement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import com.cfsp.connectDB.DBConnection;
import com.cfsp.daoInterface.*;
import com.cfsp.entity.Account;



public class AccountImplement implements AccountInterface {

	@Override
	public void addAccount(Account account) throws Exception {
		Connection conn = DBConnection.getConnection();
		PreparedStatement ps = conn.prepareStatement("insert into Account(A_ID,C_Number,A_Balance) values(?,?,?)");
		ps.setString(1, account.getA_ID());
		ps.setString(2, account.getC_Number());
		ps.setString(3,account.getA_Balance());
		ps.executeUpdate();
		System.out.println("≤Â»Î≥…π¶");
		DBConnection.close(ps, conn);
	}

	@Override
	public void deleteAccountByID(String ID) throws Exception {
		Connection conn = DBConnection.getConnection();
		PreparedStatement ps = conn.prepareStatement("delete from Account where A_ID=?");
		ps.setString(1,ID);
		ps.executeUpdate();
		DBConnection.close(ps, conn);
	}

	@Override
	public void updateAccount(Account account) throws Exception {
		Connection conn = DBConnection.getConnection();
		PreparedStatement ps = conn.prepareStatement("update Account set C_Number=?,A_Balance=? where A_ID=?");
		ps.setString(1, account.getC_Number());
		ps.setString(2,account.getA_Balance());
		ps.setString(3,account.getA_ID());
		ps.executeUpdate();
		DBConnection.close(ps, conn);
	}

	@Override
	public Account searchAccountByID(String ID) throws Exception {
		Account bean = null;
		Connection conn = DBConnection.getConnection();
		PreparedStatement ps = conn.prepareStatement("select * from Account where A_ID=?");
		ps.setString(1, ID);
		ResultSet rs = ps.executeQuery();
		
		while(rs.next()) {
			bean = new Account();
			bean.setA_ID(rs.getString("A_ID"));
			bean.setC_Number(rs.getString("C_Number"));
			bean.setA_Balance(rs.getString("A_Balance"));
		}
		DBConnection.close(rs, ps, conn);
		return bean;
	}

	@Override
	public ArrayList<Account> searchAllAccount() throws Exception {
		ArrayList<Account> list = new ArrayList<Account>();
		Connection conn = DBConnection.getConnection();
		PreparedStatement ps = conn.prepareStatement("select * from Account");
		ResultSet rs = ps.executeQuery();
		
		while(rs.next()) {
			Account a = new Account();
			a.setA_ID(rs.getString("A_ID"));
			a.setC_Number(rs.getString("C_Number"));
			a.setA_Balance(rs.getString("A_Balance"));
			list.add(a);
		}
		DBConnection.close(rs, ps, conn);
		return list;
	}
	/*
	public static void main(String args[])
	{
		Account a=new Account();
		AccountImplement ai=new AccountImplement();
		a.setA_ID("A_ID1");
		a.setC_Number("001");
		a.setA_Balance("A_Balance1");
		try {
			//a.setA_ID("A_ID2");
			//ai.addAccount(a);
			//a.setA_ID("A_ID3");
			ai.addAccount(a);
			a.setA_Balance("123");
			ai.updateAccount(a);
			//ai.deleteAccountByID(a.getA_ID());
			a=ai.searchAccountByID(a.getA_ID());
			ArrayList<Account> list = new ArrayList<Account>();
			list=ai.searchAllAccount();
			for( Account a1 :list )
			System.out.print(a1.toString());
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}*/
	
}

