package com.cfsp.daoInterface;

import java.util.ArrayList;

import com.cfsp.entity.Detail;


public interface DetailInterface {
	public void addDetail(Detail d) throws Exception;
	public void deleteDetailByID(String ID) throws Exception;
	public void updateDetail(Detail d) throws Exception;
	public Detail searchDetailByID(String ID) throws Exception;
	public ArrayList<Detail> searchAllDetail() throws Exception;
}

