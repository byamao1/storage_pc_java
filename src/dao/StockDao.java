/**

 * Title: StockDao.java

 * Description: 

 * Copyright: ByTom's Studio 2016

 *            All right reserved.

 * 2016年12月26日
 */
package dao;

import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

import com.tomcat.common.Hint;
import com.tomcat.common.db.DbEntity;
import com.tomcat.common.db.DbUtil;

import model.TbInPort;
import model.TbProduct;
import model.TbStock;

/**
 * 
 * @职责 
 * @属层 
 * @author ByTom
 */
public class StockDao extends DaoUtil{
	private static String table="tb_stock";
	
	/**
	 * 先查询有没有，有就将字段“num”增加；
	 * 没有就建立row
	 * @param tbStock
	 * @return
	 */
	public static boolean add(TbStock tbStock){
		//check
		if(tbStock==null){
			Hint.test("StockDao.add", "入口参数TbStock为null");
			return false;
		}
		List<String> fields = new LinkedList<>();
		fields.add("product_code");
		List<TbStock> list = query(fields,new Object[]{tbStock.getProduct_code()});
		if(list.size()<1){
			//如果没有就创建
			String sql="insert into "+table+"(product_code,fname,num) values(?,?,?)";
			Object[] params=new Object[]{tbStock.getProduct_code(),tbStock.getName(),tbStock.getNum()};
			return DaoUtil.add(sql, params);
		}else{
			//有则在原基础上增加
			List<String> setFields = new LinkedList<>(),whereFields = new LinkedList<>();
			setFields.add("num");whereFields.add("product_code");
			Object[] params = new Object[]{tbStock.getNum()+list.get(0).getNum(),list.get(0).getProduct_code()};
			return DaoUtil.set(table, setFields, whereFields, params);
		}
	}
	
	public static boolean delete(TbStock tbStock){
		List<String> whereFields = new LinkedList<>();
		whereFields.add("id");
		Object[] params = new Object[]{tbStock.getId()};
		return DaoUtil.delete(table, whereFields, params);
	}
	
	public static List<TbStock> query(List<String> whereFieldNames,Object[] params){
		return DaoUtil.query(TbStock.class,table, whereFieldNames, params);
	}
	
	/**
	 * 根据对象修改其值
	 * @param tbStock
	 * @param setFieldNames
	 * @param setValues
	 * @return
	 */
	public static boolean set(TbStock tbStock,List<String> setFieldNames,Object[] setValues){
		List<String> whereFieldNames = new LinkedList<>();
		whereFieldNames.add("id");
		Object[] params = new Object[setValues.length+1];
		for(int i=0;i<setValues.length;i++)
			params[i] = setValues[i];
		params[setValues.length] = tbStock.getId();
		return DaoUtil.set(table, setFieldNames, whereFieldNames, params);
	}
	/**
	 * 根据whereFields修改其找到的值
	 * @param setFieldNames
	 * @param whereFieldNames
	 * @param params
	 * @return
	 */
	public static boolean set(List<String> setFieldNames,List<String> whereFieldNames,Object[] params){
		return DaoUtil.set(table, setFieldNames, whereFieldNames, params);
	}
}
