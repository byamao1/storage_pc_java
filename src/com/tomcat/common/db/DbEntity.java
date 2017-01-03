/**

 * Title: DBEntity.java

 * Description: 

 * Copyright: ByTom's Studio 2016

 *            All right reserved.

 * 2016年12月20日
 */
package com.tomcat.common.db;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import com.tomcat.common.Hint;

/**
 * 
 * @职责 
 * @属层 
 * @author ByTom
 */
public abstract class DbEntity {
	
	protected Connection conn=null;		//声明数据库连接对象引用
	protected PreparedStatement pstm = null;
	protected CallableStatement cstm = null;
	protected ResultSet rs=null;//声明结果集对象引用
	//定义常量
	public static final int PSTM_TYPE=0;
	public static final int CALL_TYPE=1;
	
	public abstract void setUrl(String url);	//设置数据库位置
	protected abstract Connection getConnection(); //得到数据库连接的方法
	protected abstract void closeCon();			//关闭数据库连接的方法
//	public abstract Vector query(String sql);
	/**
	 * 设置PrepareStatement对象中Sql语句中的参数
	 * @param sql sql语句
	 * @param params 参数列表
	 */
	protected void setPrepareStatementParams(String sql, Object[] params)
	{
		try
		{
			pstm = conn.prepareStatement(sql); //获取对象
			if (params != null)
			{
				for (int i = 0; i < params.length; i++) // 遍历参数列表填充参数
				{
					pstm.setObject(i + 1, params[i]);
				}
			}
		}
		catch (SQLException e)
		{
			System.err.println("setPrepareStatementParams Error!"+ e.getMessage());
			e.printStackTrace();
		}
	}

	/**
	 * 设置CallableStatementParams对象中sql语句中的参数
	 * @param sql sql语句
	 * @param params 参数列表
	 */
	protected void setCallableStatementParams(String sql, Object[] params)
	{
		try
		{
			cstm = conn.prepareCall(sql); //获取CallableStatement对象
			if (params != null)
			{
				for (int i = 0; i < params.length; i++) // 遍历数组填充参数
				{
					cstm.setObject(i + 1, params[i]);
				}
			}
		}
		catch (SQLException e)
		{
			System.err.println("setCallableStatementParams Error!"+ e.getMessage());
			e.printStackTrace();
		}
	}
	/**
	 * 执行查询
	 * @param sql sql语句
	 * @param params 参数列表
	 * @param type sql语句的类型
	 * @return 返回Vector类型的查询结果
	 */
	public Vector executeQuery(String sql, Object[] params, int type)
	{ 
		Vector ans=null;
//		ResultSet rs = null;
		try
		{   
			getConnection();//得到数据库连接
			//判断是PrepareStatement还是CallableStatement
			switch (type){
			case PSTM_TYPE:
				setPrepareStatementParams(sql, params); //填充参数
				rs = pstm.executeQuery(); // 执行查询操作
				break;
			case CALL_TYPE:
				setCallableStatementParams(sql, params); //填充参数
				rs = cstm.executeQuery(); //执行查询
				break;
			default:
				throw new Exception("不存在该选项");
			}
			ans=rs2v(rs);
		}
		catch(Exception e)
		{
			Hint.printError("DbEntity.executeQuery", e);
		}
		finally{closeCon();}//关闭数据库连接
		return ans;
	}
	/**
	 * 更新数据库操作
	 * @param sql sql语句
	 * @param params 参数列表
	 * @param type sql语句的类型
	 * @return 执行操作的结果
	 */
	public boolean executeUpdate(String sql, Object[] params, int type) // 执行无返回数据的数据查询，返回值是被改变的书库的数据库项数
	{
		boolean result=false;
//		params = objs2Strs(params);
		try
		{
			getConnection();//得到数据库连接
			//判断是PrepareStatement还是CallableStatement
			switch (type){
			case PSTM_TYPE:  
				setPrepareStatementParams(sql, params); //填充参数
			    pstm.executeUpdate(); // 执行更新
			    commitChange();
			    result=true;
				break;
			case CALL_TYPE:
				setCallableStatementParams(sql, params); //填充参数
				cstm.executeUpdate(); // 执行更新
				commitChange();
				result=true;
				break;
			default:
				throw new Exception("不存在该选项");
			}
		}
		catch (Exception e)
		{
			Hint.printError("DbEntity.executeUpdate", e);
		}
		finally{closeCon();}//关闭数据库连接
		return result;
	}

	/**
	 * 提交信息到数据库
	 * @throws SQLException
	 */
	protected void commitChange() throws SQLException
	{
		try
		{
			conn.commit();
			Hint.test("DbEntity.executeUpdate", "数据提交成功！");
		}
		catch (Exception e)
		{
			System.out.println("CommitChange Error!" + e.getMessage());
			e.printStackTrace();
		}
	}
	/**
	 * 将ResultSet转换为二维向量。
	 * （DbVector是我自定义的Vector）
	 * @param rs
	 * @return 
	 */
	protected Vector rs2v(ResultSet rs){
		Vector ans = new Vector();
		Vector v = null;
		try{
			java.sql.ResultSetMetaData rsm = rs.getMetaData();
			int col = rsm.getColumnCount();
			while(rs.next()){
				v = new Vector();
				for(int j=1;j<=col;j++){
					v.add(rs.getObject(j));//将rs每个值存入向量
				}
				ans.add(v);//存入一行
			}
		}catch(SQLException e){e.printStackTrace();}
		return ans;
	}
	
	/**
	 * 将object[]内的元素类型转换为String
	 * (Access对于参数都要求为String，无论数据库类型)
	 * @param objs
	 * @return
	 */
	protected Object[] objs2Strs(Object[] objs){
		if(objs==null)return objs;
		int length = objs.length;
		for(int i=0;i<length;i++){
			Object o = objs[i];
			objs[i] = String.valueOf(o);
		}
		return objs;
	}
}
