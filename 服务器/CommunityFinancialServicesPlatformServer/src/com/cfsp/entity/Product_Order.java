package com.cfsp.entity;

public class Product_Order {
		private String O_Number; //�����ţ����
		private String P_Number;//��Ʒ����
		private int PO_Count;//������Ʒ����
		
		//getter
		public String getO_Number() {
			return O_Number;
		}
		public String getP_Number() {
			return P_Number;
		}
		public int getPO_Count() {
			return PO_Count;
		}
		
		//setter
		public void setO_Number(String O_Number) {
			this.O_Number=O_Number;
		}
		public void setP_Number(String P_Number) {
			this.P_Number=P_Number;
		}
		public void setPO_Count(int PO_Count) {
			this.PO_Count=PO_Count;
		}
			
	}


