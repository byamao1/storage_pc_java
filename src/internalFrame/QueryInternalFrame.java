/**

 * Title: QueryInternalFrame.java

 * Description: 

 * Copyright: ByTom's Studio 2016

 *            All right reserved.

 * 2016年12月28日
 */
package internalFrame;


import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;

import com.tomcat.common.component.EditSortTable;
import com.tomcat.common.component.EditableTableModel;
import com.tomcat.common.component.TableModelUtil;

import javax.swing.border.LineBorder;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 
 * @职责 
 * @属层 
 * @author ByTom
 */
public class QueryInternalFrame extends JInternalFrame {
	protected JButton clearTextBtn,queryBtn,delBtn,updateBtn,clearReadyBtn;
	protected EditSortTable table;
	protected JPanel panel;
	protected JScrollPane scrollPane;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					QueryInternalFrame frame = new QueryInternalFrame();
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
	public QueryInternalFrame() {
		//set frame
//		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		setBounds(100, 20, 700, 500);
		getContentPane().setLayout(null);
		
		panel = new JPanel();
		panel.setBorder(new LineBorder(Color.LIGHT_GRAY));
		panel.setBounds(1, 1, 683, 91);
		panel.setLayout(null);
		getContentPane().add(panel);
		
		clearTextBtn = new JButton("清空输入区");
		clearTextBtn.setBounds(580, 63, 93, 23);
		panel.add(clearTextBtn);
		
		JLabel lblNewLabel_1 = new JLabel("结果区：");
		lblNewLabel_1.setBounds(11, 102, 81, 15);
		getContentPane().add(lblNewLabel_1);
		
		//table
		table = new EditSortTable(true,true);
		final EditableTableModel upModel = (EditableTableModel) table.getModel();
		String[] upTableHeads = new String[]{"Object", "1", "2", "3","4", "comment"};
		upModel.setColumnIdentifiers(upTableHeads);
		table.setColUneditable(0);		//第一列id，不可编辑
		scrollPane = new JScrollPane(table);
		scrollPane.setBounds(12, 127, 547, 333);
		getContentPane().add(scrollPane);
		
		delBtn = new JButton("从数据库删除");
		delBtn.setBounds(569, 209, 105, 36);
		getContentPane().add(delBtn);
		
		updateBtn = new JButton("更新至数据库");
		updateBtn.setBounds(569, 305, 105, 43);
		getContentPane().add(updateBtn);
		
		clearReadyBtn = new JButton("清空结果区");
		clearReadyBtn.setBounds(439, 97, 105, 23);
		getContentPane().add(clearReadyBtn);
		
		queryBtn = new JButton("查   询");
		queryBtn.setBounds(569, 124, 105, 36);
		getContentPane().add(queryBtn);
		
		//Action
		clearReadyBtn.addActionListener(new ClearResultBtnAction(upModel));

	}
	
	
	private final class ClearResultBtnAction implements ActionListener {
		private final EditableTableModel dftm;
		public ClearResultBtnAction(EditableTableModel dftm) {
			this.dftm = dftm;
		}
		/* (non-Javadoc)
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		@Override
		public void actionPerformed(ActionEvent arg0) {
			TableModelUtil.clear(dftm);
		}
		
	}
}
