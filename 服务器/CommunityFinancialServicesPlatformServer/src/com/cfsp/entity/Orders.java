package com.cfsp.entity;

public class Orders {
	private String C_Number; 
	private String O_Number;
	private String O_Date;
	private double O_Money;
	private String O_ProductServe;
	private String O_IsPayOff;
	private String O_Evaluate;

	//getter
	public String getC_Number() {
		return C_Number;
	}
	public String getO_Number() {
		return O_Number;
	}
	public String getO_Date() {
		return O_Date;
	}
	public double getO_Money() {
		return O_Money;
	}
	public String getO_ProductServe() {
		return O_ProductServe;
	}
	public String getO_IsPayOff() {
		return O_IsPayOff;
	}
	public String getO_Evaluate() {
		return O_Evaluate;
	}
	
	//setter
    public void setC_Number(String C_Number) {
     	this.C_Number=C_Number;
	}
	public void setO_Number(String O_Number) {
		this.O_Number=O_Number;
	}
	public void setO_Date(String O_Date) {
		this.O_Date=O_Date;
	}
	public void setO_Money(double O_Money) {
		this.O_Money=O_Money;
	}
	public void setO_ProductServe(String O_ProductServe) {
		this.O_ProductServe=O_ProductServe;
	}
	public void setO_IsPayOff(String O_IsPayOff){
		this.O_IsPayOff=O_IsPayOff;
	}
	public void setO_Evaluate(String O_Evaluate){
		this.O_Evaluate=O_Evaluate;
	}
	
	
}
