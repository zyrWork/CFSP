package com.cfsp.daoInterface;

import java.util.ArrayList;

import com.cfsp.entity.*;

public interface VegetableInterface {
	public void addVegetable(Vegetable v) throws Exception;
	public void deleteVegetableID(String ID)  throws Exception;
	public void updateVegetable(Vegetable v)  throws Exception;
	public Vegetable searchVegetableByID(String ID)  throws Exception;
	public ArrayList<Vegetable> searchAllVegetable()  throws Exception;
}