/**

 * Title: SQLUtil.java

 * Description: 

 * Copyright: ByTom's Studio 2016

 *            All right reserved.

 * 2016年12月19日
 */
package com.tomcat.common.db;

import java.util.List;

/**
 * 
 * @职责 create the sql string
 * @属层 
 * @author ByTom
 */
public class SqlUtil {
	public static String getLogin(String name,String pwd){
		return "select * from user where user_name='"+name+"' and password='"+pwd+"'";
	}
	public static String select(String table,List<String> equalField,List<String> equalValue){
		//require
		if(table==null||equalField==null||equalValue==null)return null;
		if(table.equals("")||equalField.size()<1||equalValue.size()<1||equalField.size()!=equalValue.size())return null;
		StringBuilder sb=new StringBuilder("select * from "+table+" where ");
		//invariant
		for(int i=0;i<equalField.size();i++){
			if(i!=0)sb.append("and ");
			sb.append(equalField.get(i)+"='");
			sb.append(equalValue.get(i)+"' ");
		}
		//ensure
		return sb.toString();
	}
	/**
	 * 
	 * @param table	表名
	 * @param equalField	等于
	 * @param equalValue
	 * @param bigField		大于
	 * @param bigValue
	 * @param lessField		小于
	 * @param lessValue
	 * @return
	 */
	public static String select(String table,List<String> equalField,List<String> equalValue,
											List<String> bigField,List<String> bigValue,
											List<String> lessField,List<String> lessValue){
		//require
//		if(table==null||field==null||value==null)return null;
//		if(table.equals("")||field.size()<1||value.size()<1||field.size()!=value.size())return null;
		String sql= "select * from "+table+" where ";
		for(int i=0;i<equalField.size();i++){
			
		}
//		user_name='"+name+"' and password='"+pwd+"'";
		return null;
	}
}
