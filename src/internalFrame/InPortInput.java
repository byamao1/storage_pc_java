/**

 * Title: InPortInput.java

 * Description: 

 * Copyright: ByTom's Studio 2016

 *            All right reserved.

 * 2016年12月24日
 */
package internalFrame;


import java.awt.EventQueue;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;

import service.AudioService;
import service.FrameService;
import service.InPortService;
import service.ProductService;
import service.UserService;
import model.TbInPort;
import model.TbProduct;
import model.TbStock;

import com.tomcat.common.Hint;
import com.tomcat.common.component.EditableTableModel;
import com.tomcat.common.component.TableModelUtil;
import com.tomcat.storage.Login;

import dao.InPortDao;
import dao.ProductDao;
import dao.StockDao;

/**
 * 
 * @职责 
 * @属层 
 * @author ByTom
 */
public class InPortInput extends InputInternalFrame {
	private JTextField codeTextField;
	private JTextField nameTextField;
	private JTextField numTextField;
	private JTextField priceTextField;
	private JTextField userTextField;
	private JTextField dateTextField;
	private JTextArea commentTextArea;
	private Date date = new Date();
	private DateThread dateThread;
	private Map<String,Integer> codeMap = new HashMap();//存放code和行号

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InPortInput frame = new InPortInput();
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
	public InPortInput() {
		super();
		setTitle("入库输入");
		initUI();
		initTimeField();
	}
	private void initUI(){
		clearTextBtn.setSize(93, 23);
		clearTextBtn.setLocation(337, 63);
		
		JLabel lblNewLabel = new JLabel("扫描码：");
		lblNewLabel.setBounds(10, 10, 54, 15);
		panel.add(lblNewLabel);
		
		codeTextField = new JTextField();
		codeTextField.setBounds(62, 7, 160, 21);
		panel.add(codeTextField);
		codeTextField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("名称：");
		lblNewLabel_1.setBounds(244, 10, 41, 15);
		panel.add(lblNewLabel_1);
		
		nameTextField = new JTextField();
		nameTextField.setEditable(false);
		nameTextField.setBounds(281, 7, 148, 21);
		panel.add(nameTextField);
		nameTextField.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("数量：");
		lblNewLabel_2.setBounds(10, 38, 41, 15);
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("价格：");
		lblNewLabel_3.setBounds(113, 38, 41, 15);
		panel.add(lblNewLabel_3);
		
		numTextField = new JTextField();
		numTextField.setBounds(62, 36, 41, 21);
		panel.add(numTextField);
		numTextField.setColumns(10);
		
		priceTextField = new JTextField();
		priceTextField.setBounds(156, 35, 66, 21);
		panel.add(priceTextField);
		priceTextField.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("操作员：");
		lblNewLabel_4.setBounds(232, 38, 54, 15);
		panel.add(lblNewLabel_4);
		
		userTextField = new JTextField();
		userTextField.setEditable(false);
		userTextField.setBounds(281, 35, 66, 21);
		panel.add(userTextField);
		userTextField.setColumns(10);
		userTextField.setText(UserService.getCurUser().getName());
		
		JLabel lblNewLabel_5 = new JLabel("时间：");
		lblNewLabel_5.setBounds(10, 67, 41, 15);
		panel.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("备注：");
		lblNewLabel_6.setBounds(439, 10, 41, 15);
		panel.add(lblNewLabel_6);
		
		dateTextField = new JTextField();
		dateTextField.setHorizontalAlignment(SwingConstants.CENTER);
		dateTextField.setBounds(62, 64, 128, 21);
		panel.add(dateTextField);
		dateTextField.setColumns(10);
		
		commentTextArea = new JTextArea();
		commentTextArea.setLineWrap(true);//自动换行
		commentTextArea.setWrapStyleWord(true);//断行不断字
		JScrollPane scrollPane = new JScrollPane(commentTextArea);//为备注添加滚动条
		scrollPane.setBounds(474, 7, 199, 79);
		panel.add(scrollPane);
		//upTable
		final EditableTableModel upModel = (EditableTableModel) upTable.getModel();
		String[] upTableHeads = new String[]{"扫描码", "名称", "数量", "价格","操作员","时间", "备注"};
		upModel.setColumnIdentifiers(upTableHeads);
		upTable.setColUneditable(0);upTable.setColUneditable(1);//设置前2列不可编辑
		
		//downTable
		final EditableTableModel downModel = (EditableTableModel) downTable.getModel();
		String[] downTableHeads = new String[]{"扫描码", "名称", "数量", "价格","操作员","时间", "备注"};
		downModel.setColumnIdentifiers(downTableHeads);
		
		//Action
		codeTextField.addActionListener(new CodeTextAction(codeTextField,nameTextField));
		codeTextField.getDocument().addDocumentListener(new CodeTextDocumentListener(nameTextField));
//		codeTextField.addFocusListener(new CodeTextFocus(nameTextField));
		addBtn.addActionListener(new AddBtnAction(upModel));
		saveBtn.addActionListener(new SaveBtnAction(upModel,downModel));
		clearTextBtn.addActionListener(new ClearTextBtnAction());
	}
	//清空输入区
	private void clearText(){
		codeTextField.setText("");
		nameTextField.setText("");
//		numTextField.setText("");
//		priceTextField.setText("");
//		userTextField.setText("");
		commentTextArea.setText("");
		codeTextField.requestFocus();//扫描码获得焦点
	}
	// 启动入库时间线程
	private void initTimeField() {
		dateThread = new DateThread();
		dateThread.start();
	}
	
	/* (non-Javadoc)
	 * @see javax.swing.JInternalFrame#dispose()
	 */
	@Override
	public void dispose() {
		//关闭时间线程
		dateThread.interrupt();
		super.dispose();
	}
	private class DateThread extends Thread {
		public void run() {
			Hint.test("InPortInput.DateThread.run"," dateThread starts.");
			while (true) {
				if(this.isInterrupted()){
					Hint.test("InPortInput.DateThread.run"," dateThread stops.");
					System.out.println("TomCat:InPortInput dateThread stops.");
					return;
				}
				try {
					date = new Date();
					dateTextField.setText(date.toLocaleString());
					sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
		}
	}
	
	//当在“扫描码输入区”按enter，将会查询其对应名称
	private final class CodeTextAction implements ActionListener {
		private final JTextField codeText,nameText;
		
		public CodeTextAction(JTextField codeTextField,JTextField nameTextField) {
			this.codeText = codeTextField;
			this.nameText = nameTextField;
		}
		
		/* (non-Javadoc)
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		@Override
		public void actionPerformed(ActionEvent arg0) {
			List<String> fields = new LinkedList<>();
			Object[] params = null;
			if(!codeText.getText().trim().equals("")){
				fields.add("code");
				params = new Object[]{codeText.getText().trim()};
			}else{
				Hint.note("请输入扫描码！");
				return;
			}
			List<TbProduct> list = ProductService.query(fields, params);
			if(list.size()<1){
				if(Hint.confirm("没有相应商品信息，是否创建？")==0){
					//创建新的商品信息
					ProductInput pInput = (ProductInput) FrameService.openInternalFrame("ProductInput");
					pInput.getCodeTextField().setText(codeText.getText().trim());;
				}else{
					//不建立
					return;
				}
			}else
				nameText.setText(list.get(0).getName());
		}
		
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
//			nameText.setText("");
//			Hint.test("changedUpdate");
		}

		/* (non-Javadoc)
		 * @see javax.swing.event.DocumentListener#insertUpdate(javax.swing.event.DocumentEvent)
		 */
		@Override
		public void insertUpdate(DocumentEvent arg0) {
			nameText.setText("");
//			Hint.test("insertUpdate");
			
		}

		/* (non-Javadoc)
		 * @see javax.swing.event.DocumentListener#removeUpdate(javax.swing.event.DocumentEvent)
		 */
		@Override
		public void removeUpdate(DocumentEvent arg0) {
			nameText.setText("");
//			Hint.test("removeUpdate");			
		}
		
	}
	//“扫描码输入区”一旦获得焦点，就清空名称输入框
//	private final class CodeTextFocus implements FocusListener{
//		private final JTextField nameText;
//		public CodeTextFocus(JTextField nameText){
//			this.nameText = nameText;
//		}
//		
//		@Override
//		public void focusGained(FocusEvent arg0) {
//			nameText.setText("");
//		}
//
//		@Override
//		public void focusLost(FocusEvent arg0) {
//			// TODO Auto-generated method stub
//			
//		}
//	}
	private final class ClearTextBtnAction implements ActionListener {

		/* (non-Javadoc)
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		@Override
		public void actionPerformed(ActionEvent arg0) {
			InPortInput.this.clearText();
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
			String numStr = numTextField.getText().trim();
			String priceStr = priceTextField.getText().trim();
			String user = userTextField.getText().trim();
			String date = dateTextField.getText().trim();
			String comment = commentTextArea.getText().trim();
			if(code.equals("")||name.equals("")
					||numStr.equals("")||priceStr.equals("")
					||user.equals("")||date.equals("")){
				Hint.note("除“备注”外都不能空！");
				return;
			}
			//如果验证通过，则加入table并清空文本框
//			if(codeMap.containsKey(code)){
//				int row = codeMap.get(code);
//				int oldNum = Integer.valueOf((String)dftm.getValueAt(row, 2));
//				int newNum = oldNum + Integer.valueOf(numStr);
//			}else{
				Vector rowData = new Vector();
				rowData.add(code);
				rowData.add(name);
				rowData.add(numStr);
				rowData.add(priceStr);
				rowData.add(user);
				rowData.add(date);
				rowData.add(comment);
				dftm.addRow(rowData);
			
				InPortInput.this.clearText();
				codeMap.put(code, dftm.getRowCount()-1);
//			}
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
			//从upTable中获取数据并写入数据库
			if(Hint.confirm("确定要保存到数据库吗？")==0){
				Vector<Vector> vectors = dftm1.getDataVector();
				List<Integer> indxs = new LinkedList<>();//记录所处理成功的upTable中的行序号
				int indx = 0;
				for(Vector v:vectors){
					if(InPortService.add(v)){
						dftm2.addRow(new Vector(v));
						indxs.add(indx++);
					}else{
						indx++;
					}
				}
				//删除upTable中成功的行
				TableModelUtil.delete(indxs, dftm1);
				//声音提示完成
				AudioService.note();
				//提示
				Hint.note("保存至数据库"+indxs.size()+"条记录。");
			}
		}
		
	}
}
