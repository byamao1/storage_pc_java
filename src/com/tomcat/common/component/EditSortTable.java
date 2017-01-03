/**

 * Title: EditableTable.java

 * Description: 

 * Copyright: ByTom's Studio 2016

 *            All right reserved.

 * 2016年12月23日
 */
package com.tomcat.common.component;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.swing.JComponent;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import com.tomcat.common.Hint;

import javax.swing.AbstractAction;
import javax.swing.Action;
/**
 * Table控件
 * 可排序
 * 可编辑：可让所有的列可编辑，也可以指定列；被编辑后的单元格字体为红色
 * 表头和内容居中
 * @职责 
 * @属层 
 * @author ByTom
 */
public class EditSortTable extends JTable {
	private EditSortTable table;
	private Set<Integer> colUneditableSet;//保存可编辑列
	private List<Point> editedPoints;//保存已被修改的单元格
	/**
	 * @param editable 是否可编辑
	 * @param sortable 是否可排序
	 */
	public EditSortTable(boolean editable,boolean sortable) {
		super();
		table = this;
		colUneditableSet = new HashSet<>();
		editedPoints = new LinkedList<>();
		//设置自定义模型
		DefaultTableModel model = new EditableTableModel(editedPoints);
		this.setModel(model);
		//是否可编辑
		setEnabled(editable);
		//可排序
		if(sortable)
			setRowSorter(new TableRowSorter(model));
//			setRowSorter(new TableRowSorter((DefaultTableModel)getModel()));
		//设置为多选模式
		setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		//单元格被修改后加入修改列表
		 Action action = new AbstractAction(){
	            public void actionPerformed(ActionEvent e)
	            {
	                TableCellListener tcl = (TableCellListener)e.getSource();
	                editedPoints.add(new Point(tcl.getRow(), tcl.getColumn()));
	            }
        };
        TableCellListener tcl = new TableCellListener(this, action);
        //
		
	}
	
	//内容居中
	@Override
	public TableCellRenderer getDefaultRenderer(Class<?> columnClass) {
		DefaultTableCellRenderer cr = (DefaultTableCellRenderer)super.getDefaultRenderer(columnClass);
		cr.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
		return cr;
	}
	//表格头居中
	@Override
	public JTableHeader getTableHeader() {
		JTableHeader tableHeader = super.getTableHeader();//获得表格头对象
		tableHeader.setReorderingAllowed(false);//设置表格列不可重排序
		DefaultTableCellRenderer hr = (DefaultTableCellRenderer)tableHeader
										.getDefaultRenderer();//获得表格头的单元格对象
		hr.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);//设置列名居中显示
		return tableHeader;
	}

	//设置指定列能否编辑
	public void setColUneditable(int col) {
		colUneditableSet.add(col);
	}
	@Override
	public boolean isCellEditable(int row, int column) {
		if(colUneditableSet.contains(column))
			return false;
		else
			return true;
//		return super.isCellEditable(row, column);
	}
	
	//如果被修改过，单元格字颜色为红色
	@Override
	public TableCellRenderer getCellRenderer(int row, int column) {
		TableCellRenderer renderer = super.getCellRenderer(row, column);
		if (renderer instanceof JComponent) {
			for(Point p:editedPoints){
				if(p.getRow()==row&&p.getCol()==column){
					((JComponent) renderer).setForeground(Color.RED);
					return renderer;
				}
			}
			((JComponent) renderer).setForeground(Color.BLACK);//不要忘记其他的单元格正常显示
        }
        return renderer;
	}
	//坐标
	public class Point{
		private int row,col;
		public Point(int row,int col){
			this.row = row;
			this.col = col;
		}
		public int getRow() {
			return row;
		}
		public void setRow(int row) {
			this.row = row;
		}
		public int getCol() {
			return col;
		}
		public void setCol(int col) {
			this.col = col;
		}
		
	}
	
	public List<Point> getEditedPoints() {
		return editedPoints;
	}

	public void setEditedPoints(List<Point> editedPoints) {
		this.editedPoints = editedPoints;
	}
	
	public void clearEditedPoints() {
		editedPoints.clear();
	}


}

	
