package com.cfsp.entity;

public class Product {

		private String S_Number; //加盟商编号，外键
		private String P_Number;//产品编号，主键
		private String P_Name;//产品名称
		private double P_Price;//默认为0
		private String P_Explain;//产品说明
		private String P_BelongTo;//所属机构
		private String P_Category;//产品类别
		private String P_InvestKind;//投资类型
		private String P_InvestStyle;//投资风格
		private String P_InvestEvaluate;//风险评估
		private String P_Code;//产品代码
		private String P_Deadline;//产品期限
		private double P_AnnualizedReturn;//年化收益率
		private String P_SubscriptionPoint;//认购起点
		private double P_ExpectedRate;//预期收益值
		private String P_DateBegin;//发行开始日期
		private String P_DateEnd;//发行结束日期
		private int P_BrowseCount;//浏览次数
		private int P_BuyCount;//被购买次数
		
		//getter
		public String getS_Number() {
			return S_Number;
		}
		public String getP_Number() {
			return P_Number;
		}
		public String getP_Name() {
			return P_Name;
		}
		public double getP_Price() {
			return P_Price;
		}
		public String getP_Explain() {
			return P_Explain;
		}
		public String getP_BelongTo() {
			return P_BelongTo;
		}
		public String getP_Category() {
			return P_Category;
		}
		public String getP_InvestKind() {
			return P_InvestKind;
		}
		public String getP_InvestStyle() {
			return P_InvestStyle;
		}
		public String getP_InvestEvaluate() {
			return P_InvestEvaluate;
		}
		public String getP_Code() {
			return P_Code;
		}
		public String getP_Deadline() {
			return P_Deadline;
		}
		public double getP_AnnualizedReturn( ) {
			return P_AnnualizedReturn;
		}
		public String getP_SubscriptionPoint() {
			return P_SubscriptionPoint;
		}
		public double getP_ExpectedRate() {
			return P_ExpectedRate;
		}
		public String getP_DateBegin() {
			return P_DateBegin;
			}
		public int getP_BuyCount() {
			return P_BuyCount;	
		}
		public int getP_BrowseCount() {
			return P_BrowseCount;
			}
		public String getP_DateEnd() {
			return P_DateEnd;	
		}
		//setter
		public void setS_Number(String S_Number) {
			 this.S_Number=S_Number;
		}
		public void setP_Number(String P_Number) {
			 this.P_Number=P_Number;
		}
		public void setP_Name(String P_Name) {
			 this.P_Name=P_Name;
		}
		public void setP_Price(double P_Price) {
			 this.P_Price=P_Price;
		}
		public void setP_Explain(String P_Explain) {
			 this.P_Explain=P_Explain;
		}
		public void setP_BelongTo(String P_BelongTo) {
			 this.P_BelongTo=P_BelongTo;
		}
		public void setP_Category(String P_Category) {
			 this.P_Category=P_Category;
		}
		public void setP_InvestKind(String P_InvestKind) {
			 this.P_InvestKind=P_InvestKind;
		}
		public void setP_InvestStyle(String P_InvestStyle) {
			 this.P_InvestStyle=P_InvestStyle;
		}
		public void setP_InvestEvaluate(String P_InvestEvaluate) {
			 this.P_InvestEvaluate=P_InvestEvaluate;
		}
		public void setP_Code(String P_Code) {
			 this.P_Code=P_Code;
		}
		public void setP_Deadline(String P_Deadline) {
			 this.P_Deadline=P_Deadline;
		}	
		public void setP_AnnualizedReturn(double P_AnnualizedReturn) {
			 this.P_AnnualizedReturn=P_AnnualizedReturn;
		}
		public void setP_SubscriptionPoint(String P_SubscriptionPoint) {
			 this.P_SubscriptionPoint=P_SubscriptionPoint;
		}	
		public void setP_ExpectedRate(double P_ExpectedRate) {
			 this.P_ExpectedRate=P_ExpectedRate;
		}
		public void setP_DateBegin(String P_DateBegin) {
			 this.P_DateBegin=P_DateBegin;
		}	
		public void setP_DateEnd(String P_DateEnd) {
			 this.P_DateEnd=P_DateEnd;
		}	
		public void setP_BrowseCount(int P_BrowseCount) {
			 this.P_BrowseCount=P_BrowseCount;
		}
		public void setP_BuyCount(int P_BuyCount) {
			 this.P_BuyCount=P_BuyCount;
		}	
}
