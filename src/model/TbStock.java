/**

 * Title: T.java

 * Description: 

 * Copyright: ByTom's Studio 2016

 *            All right reserved.

 * 2016年12月26日
 */
package model;

import java.util.Arrays;
import java.util.List;
import java.util.Vector;

import model.TbModel.Type;

import com.tomcat.common.Hint;


/**
 * 库存
 * @职责 
 * @属层 
 * @author ByTom
 */
public class TbStock extends TbModel{
	private String product_code;//扫描码
	private String name;//商品名称
	private int num;//数量
	private static List<String> fieldNames = Arrays.asList(new String[] {"id","product_code","fname","num"});
	
	public TbStock(){
		super();
	}
	public TbStock(Vector v,Type type){
		super();
		setAll(v,type);
	}
	public String getProduct_code() {
		return product_code;
	}
	public void setProduct_code(String product_code) {
		this.product_code = product_code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	/* (non-Javadoc)
	 * @see model.TbModel#setAllByStr(java.util.Vector)
	 */
	@Override
	public void setAll(Vector v,Type type) {
		if(type==Type.TABLE){
			product_code = (String)v.get(0);
			name = (String)v.get(1);
			num = (int)v.get(2);
		}else if(type==Type.DB){
			super.id = (int)v.get(0);
			product_code = (String)v.get(1);
			name = (String)v.get(2);
			num = (int)v.get(3);
		}
		
	}
	/* (non-Javadoc)
	 * @see model.TbModel#toString()
	 */
	@Override
	public String toString() {
		return String.valueOf(id);
	}
	/* (non-Javadoc)
	 * @see com.tomcat.common.model.TbModel#getFieldName(int)
	 */
	@Override
	public String getFieldName(int index) {
		return fieldNames.get(index);
	}
	/* (non-Javadoc)
	 * @see com.tomcat.common.model.TbModel#getTableVector()
	 */
	@Override
	public Vector getTableVector() {
		Vector v = new Vector();
		v.add(this);
		v.add(product_code);
		v.add(name);
		v.add(num);
		return v;
	}

}
