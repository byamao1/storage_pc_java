/**

 * Title: DBManager.java

 * Description: 

 * Copyright: ByTom's Studio 2016

 *            All right reserved.

 * 2016年12月19日
 */
package com.tomcat.common.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @职责 manage the current db
 * @属层 presenter
 * @author ByTom
 */
public class DbManager {
//	private static String cur="access";	//默认为M
//	private static Map<String,DbEntity> map=new HashMap<>();
	private static DbEntity db= AccessEntity.getInstance();
								//MySqlEntity.getInstance();
	public static DbEntity getDb()//得到当前的数据库
	{
		//ensure
		return db;
	}
	public static void setDb(String str)//设置当前的数据库
	{
//		cur=str;
	}
}
