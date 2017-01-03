/**

 * Title: De_InputInternalFrame.java

 * Description: 

 * Copyright: ByTom's Studio 2016

 *            All right reserved.

 * 2016年12月24日
 */
package internalFrame.input;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;

import java.awt.GridBagLayout;

import javax.swing.JLabel;

import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.ScrollPaneConstants;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import java.awt.GridLayout;
import java.awt.CardLayout;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.tomcat.common.component.EditSortTable;

import net.miginfocom.swing.MigLayout;

/**
 * 输入窗口的父类
 * @职责 
 * @属层 
 * @author ByTom
 */
public class De_InputInternalFrame extends JInternalFrame {
	protected JTable upTable,downTable;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					De_InputInternalFrame frame = new De_InputInternalFrame();
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
	public De_InputInternalFrame() {
		//set frame
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		setBounds(100, 100, 659, 475);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{12, 0, 0, -31, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 14, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.gridheight = 3;
		gbc_panel.gridwidth = 17;
		gbc_panel.insets = new Insets(0, 0, 5, 0);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 0;
		getContentPane().add(panel, gbc_panel);
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 0;
		gbc_lblNewLabel_1.gridy = 3;
		getContentPane().add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		JButton btnNewButton_1 = new JButton("New button");
		GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
		gbc_btnNewButton_1.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewButton_1.gridx = 16;
		gbc_btnNewButton_1.gridy = 3;
		getContentPane().add(btnNewButton_1, gbc_btnNewButton_1);
		
		//table
		upTable = new EditSortTable(true,true);
		final DefaultTableModel upModel = (DefaultTableModel) upTable.getModel();
		String[] upTableHeads = new String[]{"扫描码1", "名称", "数量", "价格","操作员", "备注",};
		upModel.setColumnIdentifiers(upTableHeads);
		JScrollPane scrollPane = new JScrollPane(upTable);
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridheight = 6;
		gbc_scrollPane.gridwidth = 16;
		gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 4;
		getContentPane().add(scrollPane, gbc_scrollPane);
		
		JButton btnNewButton_2 = new JButton("New button");
		GridBagConstraints gbc_btnNewButton_2 = new GridBagConstraints();
		gbc_btnNewButton_2.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewButton_2.gridx = 16;
		gbc_btnNewButton_2.gridy = 5;
		getContentPane().add(btnNewButton_2, gbc_btnNewButton_2);
		
		JButton btnNewButton = new JButton("New button");
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewButton.gridx = 16;
		gbc_btnNewButton.gridy = 9;
		getContentPane().add(btnNewButton, gbc_btnNewButton);
		
		JLabel lblNewLabel_3 = new JLabel("New label");
		GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
		gbc_lblNewLabel_3.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_3.gridx = 0;
		gbc_lblNewLabel_3.gridy = 10;
		getContentPane().add(lblNewLabel_3, gbc_lblNewLabel_3);
		
		//downTable
		downTable = new EditSortTable(false,true);
		final DefaultTableModel downModel = (DefaultTableModel) downTable.getModel();
		String[] downTableHeads = new String[]{"扫描码2", "名称", "数量", "价格","操作员", "备注"};
		downModel.setColumnIdentifiers(downTableHeads);
		//
		JScrollPane scrollPane_1 = new JScrollPane(downTable);
		GridBagConstraints gbc_scrollPane_1 = new GridBagConstraints();
		gbc_scrollPane_1.gridheight = 4;
		gbc_scrollPane_1.gridwidth = 16;
		gbc_scrollPane_1.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane_1.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_1.gridx = 0;
		gbc_scrollPane_1.gridy = 11;
		getContentPane().add(scrollPane_1, gbc_scrollPane_1);
	}
}
