package com.cfsp.utils;

import com.cfsp.entity.Product;

public class GlobalVariable {

	public static String USER_ID=null;//初始化为空，登录时将会被记录下来
	public static String IP="192.168.155.1";
	//public static String IP="192.168.191.1";
	public static Product p=null;
	public static String imageBasePath = "http://"+GlobalVariable.IP+":8080/CommunityFinancialServicesPlatformServer/images/";
}
