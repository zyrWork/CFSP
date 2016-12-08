package com.cfsp.daoInterface;

import java.util.ArrayList;

import com.cfsp.entity.*;


public interface NewsInterface {
	public void addNews(News news) throws Exception;
	public void deleteNewsID(String ID)throws Exception;
	public void updateNews(News news)throws Exception;
	public News searchNewsByID(String ID)throws Exception;
	public ArrayList<News> searchAllNews()throws Exception;
}