/**

 * Title: InPortDao.java

 * Description: 

 * Copyright: ByTom's Studio 2016

 *            All right reserved.

 * 2016年12月25日
 */
package dao;


import java.util.LinkedList;
import java.util.List;

import com.tomcat.common.Hint;

import model.TbInPort;
import model.TbProduct;

/**
 * 
 * @职责 
 * @属层 
 * @author ByTom
 */
public class InPortDao {
	private static String table="tb_inport";
	
	public static boolean add(TbInPort tbInport){
		//check
		if(tbInport==null){
			Hint.err("InPortDao.add", "入口参数TbInPort为null");
			return false;
		}
		String sql="insert into "+table+"(code,fname,num,price,user,fdate,comment) values(?,?,?,?,?,?,?)";
		Object[] params=new Object[]{tbInport.getCode(),tbInport.getName(),tbInport.getNum(),tbInport.getPrice(),
				                     tbInport.getUser(),tbInport.getDate(),tbInport.getComment()};
		return DaoUtil.add(sql, params);
	}
	
	public static boolean delete(TbInPort tbInport){
		//check
		if(tbInport==null){
			Hint.err("InPortDao.delete", "入口参数TbInPort为null");
			return false;
		}
		List<String> whereFields = new LinkedList<>();
		whereFields.add("id");
		Object[] params = new Object[]{tbInport.getId()};
		return DaoUtil.delete(table, whereFields, params);
	}
	
	
	public static List<TbInPort> query(List<String> whereFieldNames,Object[] params){
		return DaoUtil.query(TbInPort.class,table, whereFieldNames, params);
	}
	
	public static boolean set(TbInPort tbInPort,List<String> setFieldNames,Object[] setValues){
		List<String> whereFields = new LinkedList<>();
		whereFields.add("id");
		Object[] params = new Object[setValues.length+1];
		for(int i=0;i<setValues.length;i++)
			params[i] = setValues[i];
		params[setValues.length] = tbInPort.getId();
		return DaoUtil.set(table, setFieldNames, whereFields, params);
	}
}
