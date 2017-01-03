/**

 * Title: Port.java

 * Description: 

 * Copyright: ByTom's Studio 2016

 *            All right reserved.

 * 2016年12月25日
 */
package model;

import java.util.Arrays;
import java.util.List;
import java.util.Vector;

import model.TbModel.Type;


/**
 * 出入库记录的父类
 * @职责 
 * @属层 
 * @author ByTom
 */
public abstract class Port extends TbModel{
	private String code;  //扫描码
	private String name;  //商品编号
	private int num;  //数量
	private float price; //价格
	private String user;//操作员名
	private String date;  //支付时间
	private String comment;  //注释
	private static List<String> fieldNames = Arrays.asList(new String[] {"id","code","fname","num","price","user","fdate","comment"});
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
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
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	/* (non-Javadoc)
	 * @see model.TbModel#setAll(java.util.Vector)
	 */
	@Override
	public void setAll(Vector v,Type type) {
		if(type==Type.TABLE){
			code = (String)v.get(0);
			name = (String)v.get(1);
			num = Integer.valueOf((String) v.get(2));
			price = Float.valueOf((String) v.get(3));
			user = (String)v.get(4);
			date = (String)v.get(5);
			comment = (String)v.get(6);
		}else if(type==Type.DB){
			super.id = (int)v.get(0);
			code = (String)v.get(1);
			name = (String)v.get(2);
			num = (int) v.get(3);
			price = (float) v.get(4);
			user = (String)v.get(5);
			date = (String)v.get(6);
			comment = (String)v.get(7);
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
		v.add(code);
		v.add(name);
		v.add(num);
		v.add(price);
		v.add(user);
		v.add(date);
		v.add(comment);
		return v;
	}
	
}