package com.cfsp.daoInterface;

import java.util.ArrayList;

import com.cfsp.entity.Health;


public interface HealthInterface {
	public void addHealth(Health h) throws Exception;
	public void deleteHealthByID(String ID) throws Exception;
	public void updateHealth(Health h) throws Exception;
	public Health searchHealthByID(String ID) throws Exception;
	public ArrayList<Health> searchAllHealth() throws Exception;
}