package com.cfsp.daoInterface;

import java.util.ArrayList;

import com.cfsp.entity.Laundry;


public interface LaundryInterface {
	public void addLaundry(Laundry laundry) throws Exception;
	public void deleteLaundryID(String ID) throws Exception;
	public void updateLaundry(Laundry laundry) throws Exception;
	public Laundry searchLaundryByID(String ID) throws Exception;
	public ArrayList<Laundry> searchAllLaundry() throws Exception;
}
