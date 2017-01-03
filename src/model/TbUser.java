package model;

import java.util.Arrays;
import java.util.List;
import java.util.Vector;

import model.TbModel.Type;

import com.tomcat.common.Hint;

public class TbUser extends TbModel{
	private String name;
	private String pwd;
	private String comment;
//	private String quan;
	private static List<String> fieldNames = Arrays.asList(new String[] {"id","fname","pwd","comment"});
	
	public TbUser(){
		super();
	}
	public TbUser(Vector v,Type type){
		super();
		setAll(v,type);
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPwd() {
		return this.pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getComment() {
		return this.comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
//	public String getQuan() {
//		return this.quan;
//	}
//	public void setQuan(String quan) {
//		this.quan = quan;
//	}
	/* (non-Javadoc)
	 * @see model.TbModel#setAll(java.util.Vector)
	 */
	@Override
	public void setAll(Vector v,Type type) {
		if(type==Type.TABLE){
			name = (String)v.get(0);
			pwd = (String)v.get(1);
			comment = (String)v.get(2);
		}else if(type==Type.DB){
			super.id = (int)v.get(0);
			name = (String)v.get(1);
			pwd = (String)v.get(2);
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
//		String fieldName = "";
//		switch(index){
//		case 0:fieldName = "fname";break;
//		case 1:fieldName = "pwd";break;
//		case 2:fieldName = "comment";break;
//		}
		return fieldNames.get(index);
	}
	/* (non-Javadoc)
	 * @see com.tomcat.common.model.TbModel#getTableVector()
	 */
	@Override
	public Vector getTableVector() {
		Vector v = new Vector();
		v.add(this);
		v.add(name);
		v.add(pwd);
		v.add(comment);
		return v;
	}
	
}