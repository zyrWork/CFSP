package com.cfsp.entity;

public class News {
	private String N_ID; //����ID
	private String N_Code;//���ű��
	private String N_Title; //���ű���
	private String N_Info;//��������
	private String N_Publisher; //���ŷ�����
	private String N_PubTime;//����ʱ��
	private String N_BuyLink; //��������
	private String N_OrderLink;//�µ�����
	private String N_Picture;//ͼƬ

	//getter
	public String getN_ID() {
		return N_ID;
	}
	public String getN_Code() {
		return N_Code;
	}
	public String getN_Title() {
		return N_Title;
	}
	public String getN_Info() {
		return N_Info;
	}
	public String getN_Publisher() {
		return N_Publisher;
	}
	public String getN_PubTime() {
		return N_PubTime;
	}
	public String getN_BuyLink() {
		return N_BuyLink;
	}
	public String getN_OrderLink() {
		return N_OrderLink;
	}
	public String getN_Picture(){
		return N_Picture;
	}
	
	//setter
	public void setN_ID(String N_ID) {
		this.N_ID=N_ID;
	}
	public void setN_Code(String N_Code) {
		this.N_Code=N_Code;
	}
	public void setN_Title(String N_Title) {
		this.N_Title=N_Title;
	}
	public void setN_Info(String N_Info) {
		this.N_Info=N_Info;
	}
	public void setN_Publisher(String N_Publisher) {
		this.N_Publisher=N_Publisher;
	}
	public void setN_PubTime(String N_PubTime){
		this.N_PubTime=N_PubTime;
	}
	public void setN_BuyLink(String N_BuyLink){
		this.N_BuyLink=N_BuyLink;
	}
	public void setN_OrderLink(String N_OrderLink){
		this.N_OrderLink=N_OrderLink;
	}
	public void setN_Picture(String N_Picture){
		this.N_Picture=N_Picture;
	}
}
