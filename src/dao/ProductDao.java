/**

 * Title: ProductDao.java

 * Description: 

 * Copyright: ByTom's Studio 2016

 *            All right reserved.

 * 2016年12月22日
 */
package dao;

import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

import model.TbInPort;
import model.TbStock;
import model.TbUser;
import model.TbProduct;

import com.tomcat.common.Hint;
import com.tomcat.common.db.DbEntity;
import com.tomcat.common.db.DbUtil;

/**
 * 
 * @职责 
 * @属层 
 * @author ByTom
 */
public class ProductDao {
	private static String table="tb_product";
	
	public static boolean add(TbProduct tbProduct){
		//check
		if(tbProduct==null){
			Hint.test("ProductDao.add", "入口参数TbProduct为null");
			return false;
		}
		String sql="insert into "+table+"(code,fname,comment) values(?,?,?)";
		Object[] params=new Object[]{tbProduct.getCode(),tbProduct.getName(),tbProduct.getComment()};
		return DaoUtil.add(sql, params);
	}
	
	public static boolean delete(TbProduct tbProduct){
		List<String> whereFields = new LinkedList<>();
		whereFields.add("id");
		Object[] params = new Object[]{tbProduct.getId()};
		return DaoUtil.delete(table, whereFields, params);
	}
	
	public static List<TbProduct> query(List<String> whereFieldNames,Object[] params){
		return DaoUtil.query(TbProduct.class,table, whereFieldNames, params);
	}
	
	public static boolean set(TbProduct tbProduct,List<String> setFieldNames,Object[] setValues){
		List<String> whereFields = new LinkedList<>();
		whereFields.add("id");
		Object[] params = new Object[setValues.length+1];
		for(int i=0;i<setValues.length;i++)
			params[i] = setValues[i];
		params[setValues.length] = tbProduct.getId();
		return DaoUtil.set(table, setFieldNames, whereFields, params);
	}
	
}
