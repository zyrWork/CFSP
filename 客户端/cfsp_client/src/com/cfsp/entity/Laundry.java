package com.cfsp.entity;

public class Laundry {

	private String L_ServerName; //服务名称
	private String L_ServerKind;//服务类别
	private String L_Explain;//说明
	private double L_Price; //价格
	private String L_SortNumber;//排序号
	private String L_Tele;//联系方式
	private String L_ID;//主键
	
	//getter
	public String getL_ServerName() {
		return L_ServerName;
	}
	public String getL_ServerKind() {
		return L_ServerKind;
	}
	public String getL_Explain() {
		return L_Explain;
	}
	public double getL_Price() {
		return L_Price;
	}
	public String getL_SortNumber() {
		return L_SortNumber;
	}
	public String getL_Tele() {
		return L_Tele;
	}
	public String getL_ID() {
		return L_ID;
	}
	
	//setter
	public void setL_ServerName(String L_ServerName) {
		this.L_ServerName=L_ServerName;
	}
	public void setL_ServerKind(String L_ServerKind) {
		this.L_ServerKind=L_ServerKind;
	}
	public void setL_Price(double L_Price) {
		this.L_Price=L_Price;
	}
	public void setL_Explain(String L_Explain) {
		this.L_Explain=L_Explain;
	}
	public void setL_SortNumber(String L_SortNumber) {
		this.L_SortNumber=L_SortNumber;
	}
	public void setL_ID(String L_ID){
		this.L_ID=L_ID;
	}
	public void setL_Tele(String L_Tele){
		this.L_Tele=L_Tele;
	}
}
