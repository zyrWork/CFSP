package com.cfsp.daoInterface;

import java.util.ArrayList;

import com.cfsp.entity.Insurance;

public interface InsuranceInterface {
	public void addInsurance(Insurance insurance) throws Exception;
	public void deleteInsuranceByID(String ID)  throws Exception;
	public void updateInsurance(Insurance insurance)  throws Exception;
	public Insurance searchInsuranceByID(String ID)  throws Exception;
	public ArrayList<Insurance> searchAllInsurance()  throws Exception;
}
