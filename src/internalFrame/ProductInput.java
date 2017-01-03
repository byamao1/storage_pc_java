/**

 * Title: ProductInput.java

 * Description: 

 * Copyright: ByTom's Studio 2016

 *            All right reserved.

 * 2016年12月27日
 */
package internalFrame;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.event.DocumentEvent;

import service.AudioService;
import service.ProductService;

import com.tomcat.common.Hint;
import com.tomcat.common.component.EditableTableModel;
import com.tomcat.common.component.TableModelUtil;

/**
 * 
 * @职责 
 * @属层 
 * @author ByTom
 */
public class ProductInput extends InputInternalFrame {
	private JTextField codeTextField;
	private JTextField nameTextField;
	private JTextField imgPathTextField;
	private JTextArea commentTextArea;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ProductInput frame = new ProductInput();
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
	public ProductInput() {
		super();
		setTitle("商品信息输入");
		initUI();
	}
	private void initUI(){
		clearTextBtn.setLocation(341, 67);
		JLabel label = new JLabel("扫描码：");
		label.setBounds(10, 10, 54, 15);
		panel.add(label);
		
		codeTextField = new JTextField();
		codeTextField.setBounds(65, 7, 138, 21);
		panel.add(codeTextField);
		codeTextField.setColumns(10);
		
		JLabel label_1 = new JLabel("名称：");
		label_1.setBounds(10, 42, 54, 15);
		panel.add(label_1);
		
		nameTextField = new JTextField();
		nameTextField.setBounds(65, 39, 138, 21);
		panel.add(nameTextField);
		nameTextField.setColumns(10);
		
		JLabel label_2 = new JLabel("备注：");
		label_2.setBounds(213, 10, 36, 15);
		panel.add(label_2);
		
		commentTextArea = new JTextArea();
		commentTextArea.setLineWrap(true);//自动换行
		commentTextArea.setWrapStyleWord(true);//断行不断字
		JScrollPane scrollPane = new JScrollPane(commentTextArea);//为备注添加滚动条
		scrollPane.setBounds(248, 10, 269, 47);
		panel.add(scrollPane);
		
		JLabel label_3 = new JLabel("图片：");
		label_3.setBounds(10, 71, 36, 15);
		panel.add(label_3);
		
		imgPathTextField = new JTextField();
		imgPathTextField.setBounds(65, 68, 138, 21);
		panel.add(imgPathTextField);
		imgPathTextField.setColumns(10);
		
		JButton button = new JButton("浏览");
		button.setBounds(213, 67, 57, 23);
		panel.add(button);
		
		JLabel lblNewLabel = new JLabel("图  片  区");
		lblNewLabel.setBounds(527, 10, 146, 71);
		panel.add(lblNewLabel);
		
		//upTable
		final EditableTableModel upModel = (EditableTableModel) upTable.getModel();
		String[] upTableHeads = new String[]{"扫描码", "名称",  "备注"};
		upModel.setColumnIdentifiers(upTableHeads);
		
		//downTable
		final EditableTableModel downModel = (EditableTableModel) downTable.getModel();
		String[] downTableHeads = new String[]{"扫描码", "名称",  "备注"};
		downModel.setColumnIdentifiers(downTableHeads);
				
		//Action
		codeTextField.getDocument().addDocumentListener(new CodeTextDocumentListener(nameTextField));
		addBtn.addActionListener(new AddBtnAction(upModel));
		saveBtn.addActionListener(new SaveBtnAction(upModel,downModel));
		clearTextBtn.addActionListener(new ClearTextBtnAction());
	}
	//清空输入区
	private void clearText(){
		codeTextField.setText("");
		nameTextField.setText("");
		commentTextArea.setText("");
		codeTextField.requestFocus();//扫描码获得焦点
	}
	
	//当在“扫描码输入区”改变内容，就清空“名称”
	private final class CodeTextDocumentListener implements javax.swing.event.DocumentListener{
		private final JTextField nameText;
		public CodeTextDocumentListener(JTextField nameText){
			this.nameText = nameText;
		}
		/* (non-Javadoc)
		 * @see javax.swing.event.DocumentListener#changedUpdate(javax.swing.event.DocumentEvent)
		 */
		@Override
		public void changedUpdate(DocumentEvent arg0) {
		}

		/* (non-Javadoc)
		 * @see javax.swing.event.DocumentListener#insertUpdate(javax.swing.event.DocumentEvent)
		 */
		@Override
		public void insertUpdate(DocumentEvent arg0) {
			nameText.setText("");
			
		}

		/* (non-Javadoc)
		 * @see javax.swing.event.DocumentListener#removeUpdate(javax.swing.event.DocumentEvent)
		 */
		@Override
		public void removeUpdate(DocumentEvent arg0) {
			nameText.setText("");
		}
		
	}
	private final class ClearTextBtnAction implements ActionListener {

		/* (non-Javadoc)
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		@Override
		public void actionPerformed(ActionEvent arg0) {
			ProductInput.this.clearText();
		}
		
	}
	
	private final class AddBtnAction implements ActionListener {
		private final EditableTableModel dftm;
		public AddBtnAction(EditableTableModel dftm) {
			this.dftm = dftm;
		}

		/* (non-Javadoc)
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		@Override
		public void actionPerformed(ActionEvent arg0) {
			//check
			String code = codeTextField.getText().trim();
			String name = nameTextField.getText().trim();
			String comment = commentTextArea.getText().trim();
			if(code.equals("")||name.equals("")){
				Hint.note("“扫描码”和“名称”不能空！");
				return;
			}
			//如果验证通过，则加入table并清空文本框
			Vector rowData = new Vector();
			rowData.add(code);
			rowData.add(name);
			rowData.add(comment);
			dftm.addRow(rowData);
			ProductInput.this.clearText();
		}
		
	}
	
	private final class SaveBtnAction implements ActionListener {
		private final EditableTableModel dftm1,dftm2;
		public SaveBtnAction(EditableTableModel dftm1,EditableTableModel dftm2) {
			this.dftm1 = dftm1;
			this.dftm2 = dftm2;
		}
		
		/* (non-Javadoc)
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		@Override
		public void actionPerformed(ActionEvent arg0) {
			if(Hint.confirm("确定要保存到数据库吗？")==0){
				//从upTable中获取数据并写入数据库
				Vector<Vector> vectors = dftm1.getDataVector();
				List<Integer> indxs = new LinkedList<>();//记录所处理成功的upTable中的行序号
				int indx = 0;
				for(Vector v:vectors){
					if(ProductService.add(v)){
						dftm2.addRow(new Vector(v));
						indxs.add(indx++);
					}else{
						indx++;
					}
				}
				//删除upTable中成功的行
				TableModelUtil.delete(indxs, dftm1);
				AudioService.note();
				//提示
				Hint.note("保存至数据库"+indxs.size()+"条记录。");
			}
		}
		
	}

	public JTextField getCodeTextField() {
		return codeTextField;
	}

	public void setCodeTextField(JTextField codeTextField) {
		this.codeTextField = codeTextField;
	}
}
