package com.cfsp.entity;

public class News {
	private String N_ID; //新闻ID
	private String N_Code;//新闻编号
	private String N_Title; //新闻标题
	private String N_Info;//新闻内容
	private String N_Publisher; //新闻发布人
	private String N_PubTime;//发布时间
	private String N_BuyLink; //购买链接
	private String N_OrderLink;//下单链接
	private String N_Picture;//图片

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
