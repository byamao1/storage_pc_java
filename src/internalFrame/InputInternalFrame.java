/**

 * Title: De_InputInternalFrame.java

 * Description: 

 * Copyright: ByTom's Studio 2016

 *            All right reserved.

 * 2016年12月24日
 */
package internalFrame;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import javax.swing.JButton;

import com.tomcat.common.component.EditSortTable;
import com.tomcat.common.component.EditableTableModel;
import com.tomcat.common.component.TableModelUtil;

import javax.swing.border.LineBorder;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 输入窗口的父类
 * @职责 
 * @属层 
 * @author ByTom
 */
public class InputInternalFrame extends JInternalFrame {
	protected JButton addBtn,delBtn,saveBtn,clearTextBtn,clearReadyBtn,clearResultBtn;
	protected EditSortTable upTable,downTable;
	protected JPanel panel;
	protected JScrollPane scrollPane,scrollPane_1;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InputInternalFrame frame = new InputInternalFrame();
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
	public InputInternalFrame() {
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
		
		JLabel lblNewLabel_1 = new JLabel("待处理区：");
		lblNewLabel_1.setBounds(11, 102, 81, 15);
		getContentPane().add(lblNewLabel_1);
		
		addBtn = new JButton("加入待处理区");
		addBtn.setBounds(569, 99, 105, 54);
		getContentPane().add(addBtn);
		
		//table
		upTable = new EditSortTable(true,true);
		final EditableTableModel upModel = (EditableTableModel) upTable.getModel();
		String[] upTableHeads = new String[]{"Object", "1", "2", "3","4", "comment"};
		upModel.setColumnIdentifiers(upTableHeads);
		scrollPane = new JScrollPane(upTable);
		scrollPane.setBounds(1, 120, 547, 156);
		getContentPane().add(scrollPane);
		
		delBtn = new JButton("待处理区删除");
		delBtn.setBounds(569, 209, 105, 36);
		getContentPane().add(delBtn);
		
		saveBtn = new JButton("保存至数据库");
		saveBtn.setBounds(569, 305, 105, 43);
		getContentPane().add(saveBtn);
		
		JLabel lblNewLabel_3 = new JLabel("操作成功区：");
		lblNewLabel_3.setBounds(12, 285, 81, 15);
		getContentPane().add(lblNewLabel_3);
		
		//downTable
		downTable = new EditSortTable(false,true);
		final EditableTableModel downModel = (EditableTableModel) downTable.getModel();
		String[] downTableHeads = new String[]{"Object", "1", "2", "3","4", "comment"};
		downModel.setColumnIdentifiers(downTableHeads);
		//
		scrollPane_1 = new JScrollPane(downTable);
		scrollPane_1.setBounds(1, 305, 547, 155);
		getContentPane().add(scrollPane_1);
		
		clearReadyBtn = new JButton("清空待处理区");
		clearReadyBtn.setBounds(439, 97, 105, 23);
		getContentPane().add(clearReadyBtn);
		
		clearResultBtn = new JButton("清空成功区");
		clearResultBtn.setBounds(439, 281, 105, 23);
		getContentPane().add(clearResultBtn);
		
		//Action
		delBtn.addActionListener(new DelBtnAction(upTable,upModel));
		clearReadyBtn.addActionListener(new ClearReadyBtnAction(upModel));
		clearResultBtn.addActionListener(new ClearResultBtnAction(downModel));
	}
	
	private final class DelBtnAction implements ActionListener {
		private final JTable table;
		private final EditableTableModel dftm;
		public DelBtnAction(JTable table,EditableTableModel dftm) {
			this.table = table;
			this.dftm = dftm;
		}
		/* (non-Javadoc)
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		@Override
		public void actionPerformed(ActionEvent arg0) {
			int[] rows = table.getSelectedRows();
			TableModelUtil.delete(rows, dftm);
		}
		
	}
	private final class ClearReadyBtnAction implements ActionListener {
		private final EditableTableModel dftm;
		public ClearReadyBtnAction(EditableTableModel dftm) {
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
