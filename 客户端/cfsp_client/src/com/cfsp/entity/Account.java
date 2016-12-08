package com.cfsp.entity;

public class Account {
	private String C_Number;
	private String A_Balance;
	private String A_ID;

	//getter
	public String getC_Number() {
		return C_Number;
	}
	public String getA_Balance() {
		return A_Balance;
	}
	public String getA_ID() {
		return A_ID;
	}
	//setter
	public void setC_Number(String C_Number) {
		 this.C_Number=C_Number;
	}
	public void setA_Balance(String A_Balance) {
		 this.A_Balance=A_Balance;
	}
	public void setA_ID(String A_ID) {
		 this.A_ID=A_ID;
	}
	@Override
	public String toString( ) {
		return A_Balance+A_ID+C_Number;
	}
	
}
