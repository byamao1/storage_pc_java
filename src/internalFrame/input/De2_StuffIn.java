/**

 * Title: De2_StuffIn.java

 * Description: 

 * Copyright: ByTom's Studio 2016

 *            All right reserved.

 * 2016年12月23日
 */
package internalFrame.input;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.tomcat.common.component.EditSortTable;

/**
 * 
 * @职责 
 * @属层 
 * @author ByTom
 */
public class De2_StuffIn extends De_InputInternalFrame{
	private JTextField codeTextField;
	private JTextField nameTextField;
	private JTextField numTextField;
	private JTextField priceTextField;
	private JTextField userTextField;
	private JTextField dateTextField;
	private JTextField commentTextField;
	private JTable table;
	private JTable table_1;
	private Date date;
	
	
	public De2_StuffIn() {
		super();		//千万别忘记写了
		initUI();
//		initTimeField();
	}
	
	private void initUI(){
		setTitle("入库输入");
		
		JLabel lblNewLabel = new JLabel(" 扫描码：");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 0;
		getContentPane().add(lblNewLabel, gbc_lblNewLabel);
		
		codeTextField = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.gridwidth = 3;
		gbc_textField.insets = new Insets(0, 0, 5, 5);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 1;
		gbc_textField.gridy = 0;
		getContentPane().add(codeTextField, gbc_textField);
		codeTextField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("名称：");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 4;
		gbc_lblNewLabel_1.gridy = 0;
		getContentPane().add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		nameTextField = new JTextField();
		GridBagConstraints gbc_textField_1 = new GridBagConstraints();
		gbc_textField_1.gridwidth = 3;
		gbc_textField_1.insets = new Insets(0, 0, 5, 5);
		gbc_textField_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_1.gridx = 5;
		gbc_textField_1.gridy = 0;
		getContentPane().add(nameTextField, gbc_textField_1);
		nameTextField.setColumns(10);
		
		JLabel label = new JLabel(" 数量：");
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.anchor = GridBagConstraints.EAST;
		gbc_label.insets = new Insets(0, 0, 5, 5);
		gbc_label.gridx = 0;
		gbc_label.gridy = 1;
		getContentPane().add(label, gbc_label);
		
		numTextField = new JTextField();
		GridBagConstraints gbc_textField_2 = new GridBagConstraints();
		gbc_textField_2.insets = new Insets(0, 0, 5, 5);
		gbc_textField_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_2.gridx = 1;
		gbc_textField_2.gridy = 1;
		getContentPane().add(numTextField, gbc_textField_2);
		numTextField.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("价格：");
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_2.gridx = 2;
		gbc_lblNewLabel_2.gridy = 1;
		getContentPane().add(lblNewLabel_2, gbc_lblNewLabel_2);
		
		priceTextField = new JTextField();
		GridBagConstraints gbc_textField_3 = new GridBagConstraints();
		gbc_textField_3.insets = new Insets(0, 0, 5, 5);
		gbc_textField_3.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_3.gridx = 3;
		gbc_textField_3.gridy = 1;
		getContentPane().add(priceTextField, gbc_textField_3);
		priceTextField.setColumns(10);
		
		JLabel label_1 = new JLabel("操作员：");
		GridBagConstraints gbc_label_1 = new GridBagConstraints();
		gbc_label_1.anchor = GridBagConstraints.EAST;
		gbc_label_1.insets = new Insets(0, 0, 5, 5);
		gbc_label_1.gridx = 4;
		gbc_label_1.gridy = 1;
		getContentPane().add(label_1, gbc_label_1);
		
		userTextField = new JTextField();
		GridBagConstraints gbc_textField_4 = new GridBagConstraints();
		gbc_textField_4.insets = new Insets(0, 0, 5, 5);
		gbc_textField_4.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_4.gridx = 5;
		gbc_textField_4.gridy = 1;
		getContentPane().add(userTextField, gbc_textField_4);
		userTextField.setColumns(10);
		
		JLabel label_2 = new JLabel("时间：");
		GridBagConstraints gbc_label_2 = new GridBagConstraints();
		gbc_label_2.anchor = GridBagConstraints.EAST;
		gbc_label_2.insets = new Insets(0, 0, 5, 5);
		gbc_label_2.gridx = 0;
		gbc_label_2.gridy = 2;
		getContentPane().add(label_2, gbc_label_2);
		
		dateTextField = new JTextField();
		GridBagConstraints gbc_textField_5 = new GridBagConstraints();
		gbc_textField_5.gridwidth = 2;
		gbc_textField_5.insets = new Insets(0, 0, 5, 5);
		gbc_textField_5.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_5.gridx = 1;
		gbc_textField_5.gridy = 2;
		getContentPane().add(dateTextField, gbc_textField_5);
		dateTextField.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("备注：");
		GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
		gbc_lblNewLabel_3.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_3.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_3.gridx = 3;
		gbc_lblNewLabel_3.gridy = 2;
		getContentPane().add(lblNewLabel_3, gbc_lblNewLabel_3);
		
		commentTextField = new JTextField();
		GridBagConstraints gbc_textField_6 = new GridBagConstraints();
		gbc_textField_6.gridwidth = 2;
		gbc_textField_6.insets = new Insets(0, 0, 5, 5);
		gbc_textField_6.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_6.gridx = 4;
		gbc_textField_6.gridy = 2;
		getContentPane().add(commentTextField, gbc_textField_6);
		commentTextField.setColumns(10);
		
		JButton btnNewButton = new JButton("加入待处理");
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton.gridx = 6;
		gbc_btnNewButton.gridy = 2;
		getContentPane().add(btnNewButton, gbc_btnNewButton);
		
		JLabel lblNewLabel_4 = new JLabel(" 待处理区：");
		GridBagConstraints gbc_lblNewLabel_4 = new GridBagConstraints();
		gbc_lblNewLabel_4.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_4.gridx = 0;
		gbc_lblNewLabel_4.gridy = 3;
		getContentPane().add(lblNewLabel_4, gbc_lblNewLabel_4);
		
		JButton btnNewButton_1 = new JButton("从待处理删除");
		GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
		gbc_btnNewButton_1.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_1.gridx = 5;
		gbc_btnNewButton_1.gridy = 3;
		getContentPane().add(btnNewButton_1, gbc_btnNewButton_1);
		
		table = new EditSortTable(true,true);
		GridBagConstraints gbc_table = new GridBagConstraints();
		gbc_table.gridwidth = 7;
		gbc_table.insets = new Insets(0, 0, 5, 5);
		gbc_table.fill = GridBagConstraints.BOTH;
		gbc_table.gridx = 0;
		gbc_table.gridy = 4;
		getContentPane().add(table, gbc_table);
		
		JLabel lblNewLabel_5 = new JLabel("完成区：");
		GridBagConstraints gbc_lblNewLabel_5 = new GridBagConstraints();
		gbc_lblNewLabel_5.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_5.gridx = 0;
		gbc_lblNewLabel_5.gridy = 5;
		getContentPane().add(lblNewLabel_5, gbc_lblNewLabel_5);
		
		JButton button = new JButton("入数据库");
		GridBagConstraints gbc_button = new GridBagConstraints();
		gbc_button.insets = new Insets(0, 0, 5, 5);
		gbc_button.gridx = 6;
		gbc_button.gridy = 5;
		getContentPane().add(button, gbc_button);
		
		table_1 = new JTable();
		final DefaultTableModel upModel = (DefaultTableModel) table_1.getModel();
		String[] upTableHeads = new String[]{"扫描码1", "名称", "数量", "价格","操作员", "备注",};
		upModel.setColumnIdentifiers(upTableHeads);
		//put table into JScrollPane
		final JScrollPane scrollPane = new JScrollPane(table_1);
		GridBagConstraints gbc_table_1 = new GridBagConstraints();
		gbc_table_1.gridheight = 2;
		gbc_table_1.gridwidth = 7;
		gbc_table_1.insets = new Insets(0, 0, 0, 5);
		gbc_table_1.fill = GridBagConstraints.BOTH;
		gbc_table_1.gridx = 0;
		gbc_table_1.gridy = 6;
		getContentPane().add(scrollPane, gbc_table_1);
//		//第一行
//		setupComponet(new JLabel(" 扫描码："), 0, 0, 1, 0);
//		codeTextField = new JTextField();
//		setupComponet(codeTextField, 1, 0, 3, 100);
//		setupComponet(new JLabel(" 货名："), 20, 0, 1, 0);//设为20是为第二行的“价格”标签和文本框放入
//		nameTextField = new JTextField();
//		setupComponet(nameTextField, 21, 0, 3, 120);
//		//第二行
//		setupComponet(new JLabel(" 数量："), 0, 2, 1, 0);
//		numTextField = new JTextField();
//		setupComponet(numTextField, 1, 2, 1, 30);
//		setupComponet(new JLabel(" 价格："), 3, 2, 1, 0);
//		priceTextField = new JTextField();
//		setupComponet(priceTextField, 4, 2, 3, 3);
		
//		final JButton addButton = new JButton();
//		setupComponet(addButton, 4, 0, 1, 1, false);
//		addButton.setText("加入");
//		
//		final JButton showAllButton = new JButton();
//		setupComponet(showAllButton, 5, 0, 1, 1, false);
//		showAllButton.setText("从待加入区删除");
//		setupComponet(new JLabel(" 待加入区："), 0, 2, 1, 1, false);
	}
	// 启动入库时间线程
	private void initTimeField() {
		new Thread(new Runnable() {
			public void run() {
				try {
					while (true) {
						date = new Date();
						dateTextField.setText(date.toLocaleString());
						Thread.sleep(100);
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}).start();
	}
}
