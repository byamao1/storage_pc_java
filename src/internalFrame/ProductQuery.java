/**

 * Title: ProductQuery.java

 * Description: 

 * Copyright: ByTom's Studio 2016

 *            All right reserved.

 * 2016年12月28日
 */
package internalFrame;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.table.DefaultTableModel;

import model.TbProduct;

import org.eclipse.core.internal.runtime.Product;

import service.AudioService;
import service.ProductService;

import com.tomcat.common.Hint;
import com.tomcat.common.component.EditSortTable;
import com.tomcat.common.component.EditSortTable.Point;
import com.tomcat.common.component.EditableTableModel;
import com.tomcat.common.component.TableModelUtil;

import dao.ProductDao;

/**
 * 
 * @职责 
 * @属层 
 * @author ByTom
 */
public class ProductQuery extends QueryInternalFrame {
	private JComboBox nameCondition;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ProductQuery frame = new ProductQuery();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ProductQuery() {
		super();
		setTitle("商品信息查询修改");
		initUI();
		
	}
	
	private void initUI(){
		JLabel label_1 = new JLabel("名称：");
		label_1.setBounds(10, 29, 54, 15);
		panel.add(label_1);
		
		//JComboBox
		nameCondition = new JComboBox();
		DefaultComboBoxModel cbModel = new DefaultComboBoxModel();
		cbModel.addElement("");
		List<TbProduct> list = searchInfo("");//找出所有商品
		for(TbProduct p:list)
			cbModel.addElement(p.getName());
		nameCondition.setModel(cbModel);
		nameCondition.setBounds(74, 26, 190, 21);
		panel.add(nameCondition);
		
		//table
		final EditableTableModel tableModel = (EditableTableModel) table.getModel();
		String[] upTableHeads = new String[]{"id","扫描码", "名称", "备注"};
		tableModel.setColumnIdentifiers(upTableHeads);
//		table.setColUneditable(0);
		//Action
		clearTextBtn.addActionListener(new ClearTextBtnAction());
		queryBtn.addActionListener(new QueryBtnAction(table));
		delBtn.addActionListener(new DelBtnAction());
		updateBtn.addActionListener(new UpdateBtnAction());
	}
	//清空输入区
	private void clearText(){
		nameCondition.setSelectedIndex(0);
	}
	//search info
	private List<TbProduct> searchInfo(String name) {
		List<String> fields = new LinkedList<>();
		Object[] params = null;
		if(!name.equals("")){
			fields.add("fname");
			params = new Object[]{name};
		}else{
			params = new Object[]{};
		}
		return ProductService.query(fields, params);
	}
	
	//刷新table
	private void flushTable(List list, EditSortTable table ) {
		EditableTableModel dftm = (EditableTableModel)table.getModel();
		//clear the old data
		dftm.clear();
		TbProduct p=null;
		Vector rowData=null;
		for(int i=0;i<list.size();i++){
			p=(TbProduct)list.get(i);
			dftm.addRow(p.getTableVector());
		}
	}
	
	private final class ClearTextBtnAction implements ActionListener {

		/* (non-Javadoc)
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		@Override
		public void actionPerformed(ActionEvent arg0) {
			clearText();
		}
	}
	private final class QueryBtnAction implements ActionListener {
		private EditSortTable table;
		private QueryBtnAction(EditSortTable table) {
			this.table = table;
		}
		/* (non-Javadoc)
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		@Override
		public void actionPerformed(ActionEvent arg0) {
			List<TbProduct> list = searchInfo((String)nameCondition.getSelectedItem());
			flushTable(list,table);
			AudioService.note();
			Hint.note("查询到"+list.size()+"条记录。");
		}
	}
	
	private final class DelBtnAction implements ActionListener {

		/* (non-Javadoc)
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		@Override
		public void actionPerformed(ActionEvent arg0) {
			if(Hint.confirm("确定要删除吗？")==0){
				int[] rows = table.getSelectedRows();
				int num=0;
//				EditableTableModel dftm = (EditableTableModel) table.getModel();
				EditableTableModel dftm = (EditableTableModel)table.getModel();
				for(int i=rows.length-1;i>=0;i--){
					TbProduct tbProduct = (TbProduct)table.getValueAt(rows[i], 0);
					if(ProductService.delete(tbProduct)){
						//从table中删除该条
						dftm.delete(rows[i]);
						num++;
					}
				}
				AudioService.note();
				Hint.note("成功删除"+num+"条记录。");
			}
		}
	}
	
	private final class UpdateBtnAction implements ActionListener {

		/* (non-Javadoc)
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		@Override
		public void actionPerformed(ActionEvent arg0) {
			if(Hint.confirm("确定要修改吗？")==0){
				List<EditSortTable.Point> points = table.getEditedPoints();
				int successNum=0,failNum = points.size();
				for(int i = points.size()-1;i>=0;i--){	//从后往前，便于points中的删除
					EditSortTable.Point point = points.get(i);
					TbProduct tbProduct = (TbProduct) table.getValueAt(point.getRow(), 0);
					Object setValue = table.getValueAt(point.getRow(), point.getCol());
					if(ProductService.set(tbProduct, tbProduct.getFieldName(point.getCol()), setValue)){
						//更新该条记录的对象，保持一致性
						List<String> whereFieldNames = new LinkedList<>();
						whereFieldNames.add("id");
						Object[] params = new Object[]{tbProduct.getId()};
						TbProduct newTbProduct = ProductService.query(whereFieldNames, params).get(0);
						table.setValueAt(newTbProduct, point.getRow(), 0);
						//从table的列表中删除该点
						points.remove(i);
						successNum++;
						failNum--;
					}
				}
				AudioService.note();
				if(failNum == 0)
					Hint.note("全部修改成功。共修改"+successNum+"处。");
				else
					Hint.note("成功修改"+successNum+"处。失败"+failNum+"处。");
			}
				
		}
		
		
	}
}
