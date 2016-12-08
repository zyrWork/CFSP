package com.cfsp.entity;

public class Insurance {

	private String I_CompanyName; //保险开户公司名称
	private String I_Name;//保险名称
	private double I_Rate;//保险费率
	private String I_Explain; //保障说明
	private String I_AgencyNetwork;//代办网点
	private String I_ID;//主键
	
	//getter
	public String getI_CompanyName() {
		return I_CompanyName;
	}
	public String getI_Name() {
		return I_Name;
	}
	public double getI_Rate() {
		return I_Rate;
	}
	public String getI_Explain() {
		return I_Explain;
	}
	public String getI_AgencyNetwork() {
		return I_AgencyNetwork;
	}
	public String getI_ID() {
		return I_ID;
	}
	
	//setter
	public void setI_CompanyName(String I_CompanyName) {
		this.I_CompanyName=I_CompanyName;
	}
	public void setI_Name(String I_Name) {
		this.I_Name=I_Name;
	}
	public void setI_Rate(double I_Rate) {
		this.I_Rate=I_Rate;
	}
	public void setI_Explain(String I_Explain) {
		this.I_Explain=I_Explain;
	}
	public void setI_AgencyNetwork(String I_AgencyNetwork) {
		this.I_AgencyNetwork=I_AgencyNetwork;
	}
	public void setI_ID(String I_ID){
		this.I_ID=I_ID;
	}
}
