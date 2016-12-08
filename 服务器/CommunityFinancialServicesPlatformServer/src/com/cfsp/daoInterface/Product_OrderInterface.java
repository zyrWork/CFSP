package com.cfsp.daoInterface;


import java.util.ArrayList;

import com.cfsp.entity.*;


public interface Product_OrderInterface {
	public void addProduct_Order(Product_Order po) throws Exception;
	public void deleteProduct_OrderID(String ID) throws Exception;
	public void updateProduct_Order(Product_Order po) throws Exception;
	public Product_Order searchProduct_OrderByPO(String pID,String oID) throws Exception;
	public ArrayList<Product_Order> searchAllProduct_Order()throws Exception;
}