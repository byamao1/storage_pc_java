/**

 * Title: TableModelUtil.java

 * Description: 

 * Copyright: ByTom's Studio 2016

 *            All right reserved.

 * 2016年12月26日
 */
package com.tomcat.common.component;

import java.util.List;

import javax.swing.table.DefaultTableModel;

/**
 * 对EditableTableModel进行操作
 * @职责 
 * @属层 
 * @author ByTom
 */
public class TableModelUtil {
	/**
	 * 清空
	 * @param dftm
	 */
	public static void clear(EditableTableModel dftm){
		dftm.clear();
	}
	/**
	 * 删除指定行
	 * @param list 指定行列表
	 * @param dftm
	 */
	public static void delete(List<Integer> rows,EditableTableModel dftm){
		dftm.delete(rows);
	}
	public static void delete(int[] rows,EditableTableModel dftm){
		dftm.delete(rows);
	}
	public static void delete(int row,EditableTableModel dftm){
		dftm.delete(row);
	}
}
