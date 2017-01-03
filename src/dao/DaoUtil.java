/**

 * Title: abstractDao.java

 * Description: 

 * Copyright: ByTom's Studio 2016

 *            All right reserved.

 * 2016年12月22日
 */
package dao;

import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

import model.TbModel;

import com.tomcat.common.Hint;
import com.tomcat.common.db.DbEntity;
import com.tomcat.common.db.DbUtil;

/**
 * Dao类的方法类
 * （不作为父类的原因是静态方法无法继承）
 * @职责 
 * @属层 
 * @author ByTom
 */
public abstract class DaoUtil {
	/**
	 *  向数据库中添加记录
	 * @param sql sql语句
	 * @param params 参数列表
	 * @return 执行结果
	 */
	public static boolean add(String sql,Object[] params)
	{
		//check
		if(sql==null||params==null){
			Hint.test("DaoUtil.add", "入口参数为null");
			return false;
		}
		if(sql.equals("")||params.length<1){
			Hint.test("DaoUtil.add", "入口参数为空");
			return false;
		}
		return DbUtil.executeUpdate(sql, params, DbEntity.PSTM_TYPE);
	}
	
	/**
	 * 
	 * SQL例句:update tb_inport set num=num+1 , price=5 where code='14'
	 * @param table
	 * @param setFieldNames 要设置的字段
	 * @param whereFieldNames 查找字段
	 * @param params 参数。包含设置值和查找值
	 * @return
	 */
	public static boolean set(String table,List<String> setFieldNames,List<String> whereFieldNames,Object[] params)
	{
		//check
		if(table==null||setFieldNames==null
				||whereFieldNames==null||params==null){
			Hint.test("DaoUtil.set", "入口参数为null");
			return false;
		}
		if(table.equals("")||setFieldNames.size()<1||params.length<1){//whereFieldNames为空时就是全部设置
			Hint.test("DaoUtil.set", "入口参数为空");
			return false;
		}
		if(setFieldNames.size()+whereFieldNames.size()!=params.length){
			Hint.test("DaoUtil.set", "sql语句参数个数不匹配");
			return false;
		}
		//construct sql
		StringBuilder sb=new StringBuilder("update "+table+" set ");
		for(int i=0;i<setFieldNames.size();i++){
			sb.append(setFieldNames.get(i)+"=? ");
			if(i!=setFieldNames.size()-1)
				sb.append(",");
		}
		if(whereFieldNames.size()>0)
			sb.append("where ");
		for(int i=0;i<whereFieldNames.size();i++){
			if(i!=0)sb.append("and ");
			sb.append(whereFieldNames.get(i)+"=? ");
		}
		return DbUtil.executeUpdate(sb.toString(), params, DbEntity.PSTM_TYPE);
	}
	
	/**
	 * 查询
	 * 使用泛型
	 * @param table
	 * @param whereFieldNames
	 * @param params
	 * @return
	 */
	public static <T extends TbModel> List<T> query(Class<T> c,String table,List<String> whereFieldNames,Object[] params)
	{
		//check
		if(c==null||table==null||whereFieldNames==null||params==null){
			Hint.test("DaoUtil.query", "入口参数为null");
			return new LinkedList<>();
		}
		if(table.equals("")){	//whereFieldNames和params为空时就是查询该表所有项
			Hint.test("DaoUtil.query", "入口参数table为空");
			return new LinkedList<>();
		}
		if(whereFieldNames.size()!=params.length){
			Hint.test("DaoUtil.query", "sql语句参数个数不匹配");
			return new LinkedList<>();
		}
		//construct sql
		StringBuilder sb=new StringBuilder("select * from "+table+" ");
		if(whereFieldNames.size()>0)
			sb.append("where ");
		for(int i=0;i<whereFieldNames.size();i++){
			if(i!=0)sb.append("and ");
			sb.append(whereFieldNames.get(i)+"=? ");
		}
		//query
		Vector vectors=DbUtil.executeQuery(sb.toString(), params, DbEntity.PSTM_TYPE);
		//get list of product
		List<T> list=new LinkedList<>();
		T t=null;
		for(int i=0;i<vectors.size();i++)
		{
			try {
				t = (T) c.newInstance();		//不能用 t= new T()
			} catch (InstantiationException | IllegalAccessException e) {
				Hint.printError("DaoUtil.query", e);
			}
			t.setAll((Vector)vectors.get(i),TbModel.Type.DB);
			list.add(t);
		}
		return list;
	}
	
	/**
	 * 删除
	 * @param table
	 * @param whereFieldNames
	 * @param params
	 * @return
	 */
	public static boolean delete(String table,List<String> whereFieldNames,Object[] params)
	{
		//check
		if(table==null||whereFieldNames==null||params==null){
			Hint.test("DaoUtil.delete", "入口参数为null");
			return false;
		}
		if(table.equals("")){		//whereFieldNames和params为空时，就是删除该表
			Hint.test("DaoUtil.delete", "入口参数为空");
			return false;
		}
		if(whereFieldNames.size()!=params.length){
			Hint.test("DaoUtil.delete", "sql语句参数个数不匹配");
			return false;
		}
		//construct sql
		StringBuilder sb=new StringBuilder("delete from "+table+" ");
		if(whereFieldNames.size()>0)
			sb.append("where ");
		for(int i=0;i<whereFieldNames.size();i++){
			if(i!=0)sb.append("and ");
			sb.append(whereFieldNames.get(i)+"=? ");
		}
		//delete
		return DbUtil.executeUpdate(sb.toString(), params, DbEntity.PSTM_TYPE);
	}
}
