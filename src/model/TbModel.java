/**

 * Title: model.java

 * Description: 

 * Copyright: ByTom's Studio 2016

 *            All right reserved.

 * 2016年12月22日
 */
package model;

import java.util.List;
import java.util.Vector;


/**
 * 模型的公用类
 * @职责 
 * @属层 
 * @author ByTom
 */
public abstract class TbModel implements java.io.Serializable{
	protected int id;
	//枚举类型
	public enum Type {  
	    DB, TABLE;  
	}  
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	/**
	 * 通过向量对该模型所有的字段进行赋值
	 * @param v
	 * @param type 根据dbType还是tableType来构造
	 */
	public abstract void setAll(Vector v,Type type);
	
	/**
	 * 通过向量对该模型所有的字段进行赋值
	 * （方便从数据库获取向量直接转换为对象）
	 * @param v
	 */
//	public abstract void setAll(DbVector v);
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public abstract String toString();
	
	/**
	 * 根据在Table的序号得到对应数据库中的字段名
	 * @param index Table的序号（从0起）
	 * @return
	 */
	public abstract String getFieldName(int index);
	
	/**
	 * 得到Vector，方便在Table显示
	 * @return
	 */
	public abstract Vector getTableVector();

	/**
	 * 根据字段名设置
	 * @param fieldName
	 */
//	public abstract void setByFieldName(String fieldName);
}
