package com.cfsp.daoInterface;

import java.util.ArrayList;

import com.cfsp.entity.Account;

public interface AccountInterface {
	public void addAccount(Account account) throws Exception;
	public void deleteAccountByID(String ID) throws Exception;
	public void updateAccount(Account account) throws Exception;
	public Account searchAccountByID(String ID) throws Exception;
	public ArrayList<Account> searchAllAccount() throws Exception;
}
