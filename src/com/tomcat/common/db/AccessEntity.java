/**

 * Title: AccessEntity.java

 * Description: 

 * Copyright: ByTom's Studio 2016

 *            All right reserved.

 * 2016年12月20日
 */
package com.tomcat.common.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.Vector;

import javax.swing.JOptionPane;

import com.tomcat.common.Hint;

/**
 * 
 * @职责 
 * @属层 
 * @author ByTom
 */
public class AccessEntity extends DbEntity {
	private static DbEntity db=null;					//单例模式
	private String driver="sun.jdbc.odbc.JdbcOdbcDriver";//声明驱动类字符串
	//声明数据库连接字符串
	private String url="jdbc:odbc:driver={Microsoft Access Driver (*.mdb)};DBQ=./res/db/storageDb.mdb";
//	private Connection con=null;//声明数据库连接对象引用
//	private Statement stat=null;//声明语句对象引用
//	private PreparedStatement psInsert=null;//声明预编译语句对象引用
//	private ResultSet rs=null;//声明结果集对象引用
	
	//单例模式
	private AccessEntity() {
		
	}
	public static DbEntity getInstance(){
		if(db == null){
			db = new AccessEntity();
		}
		return db;
	}
	
	
	protected Connection getConnection()//得到数据库连接的方法
	{
		try
		{
			Class.forName(driver);				//加载驱动类
			Properties pro = new Properties(); //解决中文乱码
	        pro.setProperty("charSet","GB2312");
			conn=DriverManager.getConnection(url, pro);	//得到连接
//			conn.setAutoCommit(false); 					//Access不能设置，会导致连接关闭失败
		}
		catch (Exception e)
		{
			Hint.note("Access数据库无法连接！");
			System.err.println("connectDB Error!" + e.getMessage());
			e.printStackTrace();
		}
		Hint.test("AccessEntity.getConnection","成功连接到Access数据库！");
		return conn;
	}
	protected void closeCon()//关闭数据库连接的方法
	{
		try
		{
			if (rs != null){
				rs.close();rs=null;
			}
			if (pstm != null)
			{
				pstm.close();pstm=null;
			}
			if (cstm != null)
			{
				cstm.close();cstm=null;
			}
			if (conn != null)
			{
				conn.close();conn=null;
			}
		}
		catch (Exception e)
		{
			Hint.note("Access数据库无法关闭！");
			System.err.println("closeDB Error!" + e.getMessage());
			e.printStackTrace();
			return;
		}
		finally
		{
			pstm = null;
			cstm = null;
			conn = null;
		}
		Hint.test("AccessEntity.getConnection","成功关闭Access数据库！");
	}
	
//	/* (non-Javadoc)
//	 * @see com.tomcat.common.database.DbEntity#getConnection()
//	 */
//	@Override
//	public Connection getConnection() {
//		if(url==null){
//			//alert dialog
//			JOptionPane.showMessageDialog(null,"请选择Access文件！","提示",JOptionPane.ERROR_MESSAGE);
//			return null;
//		}
//		try
//		{
//			Class.forName(driver);//加载驱动类
//			con=DriverManager.getConnection(url);//得到连接
//		}
//		catch(Exception e){e.printStackTrace();}
//		return con;//返回连接
//	}
//
//	/* (non-Javadoc)
//	 * @see com.tomcat.common.database.DbEntity#closeCon()
//	 */
//	@Override
//	public void closeCon() {
//		try
//		{
//			if(rs!=null){rs.close(); rs=null;}//如果结果集不为空关闭结果集并赋值null
//			if(stat!=null){stat.close(); stat=null;}//如果语句对象不为空关闭语句对象并赋值null
//			if(con!=null){con.close(); con=null;}//如果连接不为空关闭连接并赋值null				
//		}catch(Exception e){e.printStackTrace();}
//
//	}

	


	/* (non-Javadoc)
	 * @see com.tomcat.common.database.DbEntity#setUrl(java.lang.String)
	 */
	@Override
	public void setUrl(String url) {
//		this.url="jdbc:odbc:driver={Microsoft Access Driver (*.mdb)};DBQ="+url;//   db/mydb.mdb";
		this.url = url;
	}

}
