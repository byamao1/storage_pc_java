/**

 * Title: TbProduct.java

 * Description: 

 * Copyright: ByTom's Studio 2016

 *            All right reserved.

 * 2016年12月22日
 */
package model;

import java.util.Arrays;
import java.util.List;
import java.util.Vector;

import model.TbModel.Type;


/**
 * 
 * @职责 
 * @属层 
 * @author ByTom
 */
public class TbProduct extends TbModel{
	private String code;
	private String name;
	private String comment;
	private static List<String> fieldNames = Arrays.asList(new String[] {"id","code","fname","comment"});
	
	public TbProduct(){
		super();
	}
	public TbProduct(Vector v,Type type){
		super();
		setAll(v,type);
	}
	
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
			comment = (String)v.get(2);
		}else if(type==Type.DB){
			super.id = (int)v.get(0);
			code = (String)v.get(1);
			name = (String)v.get(2);
			comment = (String)v.get(3);
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
		v.add(comment);
		return v;
	}
	
}
