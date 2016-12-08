package com.cfsp.daoInterface;


import java.util.ArrayList;

import com.cfsp.entity.*;


public interface ProductInterface {
	public void addProduct(Product p) throws Exception;
	public	void deleteProductByID(String ID) throws Exception;
	public void updateProduct(Product p) throws Exception;
	public Product searchProductByID(String ID) throws Exception;
	public ArrayList<Product> searchAllProduct() throws Exception;
	
}