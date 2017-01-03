/**

 * Title: EditableTableModel.java

 * Description: 

 * Copyright: ByTom's Studio 2017

 *            All right reserved.

 * 2017年1月3日
 */
package com.tomcat.common.component;

import java.util.List;

import javax.swing.table.DefaultTableModel;

import com.tomcat.common.component.EditSortTable.Point;

/**
 * 
 * @职责 
 * @属层 
 * @author ByTom
 */
public class EditableTableModel extends DefaultTableModel{
	private List<Point> editedPoints;
	
	public EditableTableModel(List<Point> editedPoints){
		super();
		this.editedPoints = editedPoints;
	}
	
	/**
	 * 清空
	 * @param dftm
	 */
	public void clear(){
		int num = getRowCount();
		for (int i = 0; i < num; i++)
			removeRow(0);
		editedPoints.clear();
	}
	/**
	 * 删除指定行
	 * @param list 指定行列表
	 */
	public void delete(List<Integer> rows){
		for (int i = rows.size()-1; i >= 0; i--){
			int row = rows.get(i);
			removeRow(row);
			for(int j=editedPoints.size()-1;j>=0;j--){
				Point p = editedPoints.get(j);
				if(p.getRow()==row)
					editedPoints.remove(j);
			}
		}
	}
	public void delete(int[] rows){
		for (int i = rows.length-1; i >= 0; i--){
			int row = rows[i];
			removeRow(row);
			for(int j=editedPoints.size()-1;j>=0;j--){
				Point p = editedPoints.get(j);
				if(p.getRow()==row)
					editedPoints.remove(j);
			}
		}
	}
	public void delete(int row){
		removeRow(row);
		for(int j=editedPoints.size()-1;j>=0;j--){
			Point p = editedPoints.get(j);
			if(p.getRow()==row)
				editedPoints.remove(j);
		}
	}

}
