package com.cfsp.entity;

public class Vegetable {

	private String V_Kind; //�߲����
	private String V_Explain;//˵��
	private String V_BornPlace;//����
	private double V_Price; //�۸�
	private String V_SendTime;//����ʱ��
	private String V_ID;//����ʱ��
	
	//getter
	public String getV_Kind() {
		return V_Kind;
	}
	public String getV_Explain() {
		return V_Explain;
	}
	public String getV_BornPlace() {
		return V_BornPlace;
	}
	public double getV_Price() {
		return V_Price;
	}
	public String getV_SendTime() {
		return V_SendTime;
	}
	public String getV_ID() {
		return V_ID;
	}
	
	//setter
	public void setV_Kind(String V_Kind) {
		this.V_Kind=V_Kind;
	}
	public void setV_Explain(String V_Explain) {
		this.V_Explain=V_Explain;
	}
	public void setV_BornPlace(String V_BornPlace) {
		this.V_BornPlace=V_BornPlace;
	}
	public void setV_Price(double V_Price) {
		this.V_Price=V_Price;
	}
	public void setV_SendTime(String V_SendTime) {
		this.V_SendTime=V_SendTime;
	}
	public void setV_ID(String V_ID) {
		this.V_ID=V_ID;
	}
}
