package com.cfsp.daoInterface;

import java.util.ArrayList;

import com.cfsp.entity.Manager;


public interface ManagerInterface {
	public void addManager(Manager m) throws Exception;
	public void deleteManagerID(String ID)throws Exception;
	public void updateManager(Manager m)throws Exception;
	public Manager searchManagerByID(String ID)throws Exception;
	public ArrayList<Manager> searchAllManager()throws Exception;
}
