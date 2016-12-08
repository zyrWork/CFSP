package com.cfsp.daoInterface;

import java.util.ArrayList;

import com.cfsp.entity.*;


public interface SupplierInterface {
	public void addSupplier(Supplier s) throws Exception;
	public void deleteSupplierID(String ID) throws Exception;
	public void updateSupplier(Supplier s) throws Exception;
	public Supplier searchSupplierByID(String ID) throws Exception;
	public ArrayList<Supplier> searchAllSupplier() throws Exception;
}