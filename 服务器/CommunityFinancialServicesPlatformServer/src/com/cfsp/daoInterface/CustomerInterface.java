package com.cfsp.daoInterface;

import java.util.ArrayList;

import com.cfsp.entity.Customer;


public interface CustomerInterface {
	public void addCustomer(Customer c) throws Exception;
	public void deleteCustomerByID(String ID) throws Exception;
	public void updateCustomer(Customer c) throws Exception;
	public Customer searchCustomerByID(String ID) throws Exception;
	public ArrayList<Customer> searchAllCustomer() throws Exception;
}
