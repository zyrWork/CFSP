package com.cfsp.daoInterface;

import java.util.ArrayList;

import com.cfsp.entity.*;


public interface OrdersInterface {
	public void addOrder(Orders o) throws Exception;
	public void deleteOrderID(String ID) throws Exception;
	public void updateOrder(Orders o) throws Exception;
	public Orders searchOrderByID(String ID) throws Exception;
	public ArrayList<Orders> searchAllOrder() throws Exception;
}