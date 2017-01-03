/**

 * Title: DBUtil.java

 * Description: 

 * Copyright: ByTom's Studio 2016

 *            All right reserved.

 * 2016年12月19日
 */
package com.tomcat.common.db;

import java.sql.ResultSet;
import java.util.Vector;


/**
 * 
 * @职责 
 * @属层 
 * @author ByTom
 */
public class DbUtil {
	synchronized
	public static Vector executeQuery(String sql, Object[] params, int type){
		DbEntity db=DbManager.getDb();
		return db.executeQuery(sql, params, type);
	}
	synchronized
	public static boolean executeUpdate(String sql, Object[] params, int type){
		DbEntity db=DbManager.getDb();
		return db.executeUpdate(sql, params, type);
	}
}
