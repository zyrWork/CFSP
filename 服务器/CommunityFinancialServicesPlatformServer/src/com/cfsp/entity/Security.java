package com.cfsp.entity;

public class Security {

	private String S_CompanyName; //֤ȯ������˾
	private String S_Explain;//˵��
	private double S_Rate;//����
	private String S_AfterSale; //�ۺ���
	private String S_AgencyNetwork;//��������
	private String S_ID;//����
	
	//getter
	public String getS_CompanyName() {
		return S_CompanyName;
	}
	public String getS_Explain() {
		return S_Explain;
	}
	public double getS_Rate() {
		return S_Rate;
	}
	public String getS_AfterSale() {
		return S_AfterSale;
	}
	public String getS_AgencyNetwork() {
		return S_AgencyNetwork;
	}
	public String getS_ID() {
		return S_ID;
	}
	
	//setter
	public void setS_CompanyName(String S_CompanyName) {
		this.S_CompanyName=S_CompanyName;
	}
	public void setS_Explain(String S_Explain) {
		this.S_Explain=S_Explain;
	}
	public void setS_Rate(double S_Rate) {
		this.S_Rate=S_Rate;
	}
	public void setS_AfterSale(String S_AfterSale) {
		this.S_AfterSale=S_AfterSale;
	}
	public void setS_AgencyNetwork(String S_AgencyNetwork) {
		this.S_AgencyNetwork=S_AgencyNetwork;
	}
	public void setS_ID(String S_ID){
		this.S_ID=S_ID;
	}

}
