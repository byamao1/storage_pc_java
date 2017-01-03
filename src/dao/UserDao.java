/**

 * Title: UserDao.java

 * Description: 

 * Copyright: ByTom's Studio 2016

 *            All right reserved.

 * 2016年12月22日
 */
package dao;

import java.util.LinkedList;
import java.util.List;

import com.tomcat.common.Hint;

import model.TbInPort;
import model.TbProduct;
import model.TbUser;


/**
 * 用户
 * @职责 
 * @属层 
 * @author ByTom
 */
public class UserDao {
	private static String table="tb_user";
	
	public static boolean add(TbUser tbUser){
		//check
		if(tbUser==null){
			Hint.err("UserDao.add", "入口参数TbUser为null");
			return false;
		}
		String sql="insert into "+table+"(fname,pwd,comment) values(?,?,?)";
		Object[] params=new Object[]{tbUser.getName(),tbUser.getPwd(),tbUser.getComment()};
		return DaoUtil.add(sql, params);
	}
	
	public static boolean delete(TbUser tbUser){
		List<String> whereFields = new LinkedList<>();
		whereFields.add("id");
		Object[] params = new Object[]{tbUser.getId()};
		return DaoUtil.delete(table, whereFields, params);
	}
	
	public static List<TbUser> query(String name){
		List<String> whereFieldNames = new LinkedList<>();
		whereFieldNames.add("fname");
		return  DaoUtil.query(TbUser.class, table, whereFieldNames, new Object[]{name});
	}
	
	public static boolean set(TbUser tbUser,List<String> setFieldNames,Object[] setValues){
		List<String> whereFields = new LinkedList<>();
		whereFields.add("id");
		Object[] params = new Object[setValues.length+1];
		for(int i=0;i<setValues.length;i++)
			params[i] = setValues[i];
		params[setValues.length] = tbUser.getId();
		return DaoUtil.set(table, setFieldNames, whereFields, params);
	}
}
