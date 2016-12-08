package com.cfsp.daoInterface;

import java.util.ArrayList;

import com.cfsp.entity.*;


public interface SecurityInterface {
	public void addSecurity(Security s) throws Exception;
	public void deleteSecurityID(String ID) throws Exception;
	public void updateSecurity(Security s) throws Exception;
	public Security searchSecurityByID(String ID) throws Exception;
	public ArrayList<Security> searchAllSecurity() throws Exception;
}