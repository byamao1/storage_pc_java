/**

 * Title: ProductService.java

 * Description: 

 * Copyright: ByTom's Studio 2016

 *            All right reserved.

 * 2016年12月28日
 */
package service;

import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

import com.tomcat.common.Hint;

import dao.ProductDao;
import model.TbProduct;

/**
 * 
 * @职责 
 * @属层 
 * @author ByTom
 */
public class ProductService {
	
	public static boolean add(Vector v){
		if(v==null){
			Hint.test("ProductService.add", "入口参数Vector为null！");
			return false;
		}
		TbProduct tbProduct = new TbProduct(v,TbProduct.Type.TABLE);
		return ProductDao.add(tbProduct);
	}
	
	public static boolean delete(TbProduct tbProduct){
		return ProductDao.delete(tbProduct);
	}
	
	public static List<TbProduct> query(List<String> whereFieldNames,Object[] params){
		return ProductDao.query(whereFieldNames, params);
	}
	
	/**
	 * 修改1个值
	 * @param tbProduct
	 * @param setFieldNames
	 * @param setValue
	 * @return
	 */
	public static boolean set(TbProduct tbProduct,String setFieldName,Object setValue){
		if(tbProduct==null||setFieldName==null
				||setFieldName.equals("")||setValue==null){
			Hint.test("ProductService.set", "入口参数为null！");
			return false;
		}
		List<String> list = new LinkedList<>();
		list.add(setFieldName);
		Object[] params = new Object[]{setValue};
		return ProductDao.set(tbProduct, list, params);
	}
}
