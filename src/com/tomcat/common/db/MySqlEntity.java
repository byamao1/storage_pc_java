/**

 * Title: AccessEntity.java

 * Description: 

 * Copyright: ByTom's Studio 2016

 *            All right reserved.

 * 2016年12月20日
 */
package com.tomcat.common.db;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.JOptionPane;

import com.tomcat.common.Hint;

/**
 * 
 * @职责 
 * @属层 
 * @author ByTom
 */
public class MySqlEntity extends DbEntity {
	private static DbEntity db=null;					//单例模式
	private static String driver="com.mysql.jdbc.Driver";//声明驱动类字符串
	//声明数据库连接字符串
	private  String url="jdbc:mysql://127.0.0.1:3306/db_storage?characterEncoding=UTF-8";//加入?后面的是为转化为utf8传给MySQL，以防止传过去的中文乱码
	private  String account="root",pwd="";
//	private  Connection conn=null;//声明数据库连接对象引用
//	private  Statement stat=null;//声明语句对象引用
//	private  PreparedStatement psInsert=null;//声明预编译语句对象引用
//	private  ResultSet rs=null;//声明结果集对象引用
	
	//单例模式
	private MySqlEntity() {
		
	}
	public static DbEntity getInstance(){
		if(db == null){
			db = new MySqlEntity();
		}
		return db;
	}
	protected Connection getConnection()//得到数据库连接的方法
	{
		try
		{
			Class.forName(driver);								//加载驱动类
			conn=DriverManager.getConnection(url,account,pwd);	//得到连接
			conn.setAutoCommit(false); 							//设置自动提交为false
		}
		catch (Exception e)
		{
			Hint.note("MySQL数据库无法连接！");
			System.err.println("connectDB Error!" + e.getMessage());
			e.printStackTrace();
		}
		Hint.test("MySqlEntity.getConnection","成功连接到MySQL数据库！");
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
			Hint.note("MySQL数据库无法关闭！");
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
		Hint.test("MySqlEntity.getConnection","成功关闭MySQL数据库！");
	}

	
	/* (non-Javadoc)
	 * @see com.tomcat.common.db.DbEntity#query(java.lang.String)
	 */
//	@Override
//	public Vector query(String sql) {
//		Vector ans=null;
//		try
//		{   
//			conn=getConnection();//得到数据库连接
//			stat=conn.createStatement();//创建语句对象
//			rs=stat.executeQuery(sql);//执行查询
//			ans=super.rs2v(rs);
//		}
//		catch(Exception e)
//		{
//			e.printStackTrace();		
//		}
//		finally{closeCon();}//关闭数据库连接
//		return ans;
//	}
	
	/* (non-Javadoc)
	 * @see com.tomcat.common.db.DbEntity#setUrl(java.lang.String)
	 */
	@Override
	public void setUrl(String url) {
		this.url = url;
		
	}
}
