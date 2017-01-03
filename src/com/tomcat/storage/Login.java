/**

 * Title: Login.java

 * Description: 

 * Copyright: ByTom's Studio 2016

 *            All right reserved.

 * 2016年12月19日
 */
package com.tomcat.storage;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.WindowConstants;

import com.tomcat.common.Hint;

import dao.UserDao;
import service.FrameService;
import service.UserService;
import model.TbUser;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * 
 * @职责 
 * @属层 
 * @author ByTom
 */
public class Login extends JFrame{

	private JFrame frame=this;
	private JTextField userTextField;
	private JPasswordField pwdTextField;
	private JLabel userLabel;
	private JLabel pwdLabel;
	private JButton exitBtn;
	private JButton loginBtn;
//	private TbUser tbUser;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Login() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		//set title
		setTitle("出入库管理系统");
		//set panel
		final JPanel panel = new LoginPanel();
		panel.setLayout(null);
		getContentPane().add(panel);
		setBounds(300, 200, panel.getWidth(), panel.getHeight());
		//set userLabel
		userLabel = new JLabel();
		userLabel.setText("操作员：");
		userLabel.setBounds(100, 135, 200, 18);
		panel.add(userLabel);
		//set pwdLabel
		pwdLabel = new JLabel();
		pwdLabel.setText("密  码：");
		pwdLabel.setBounds(100, 165, 200, 18);
		panel.add(pwdLabel);
		//set tbUser input
		userTextField = new JTextField();
		userTextField.setBounds(150, 135, 200, 18);
		panel.add(userTextField);
		//set pwd input
		pwdTextField = new JPasswordField();
		pwdTextField.addKeyListener(new KeyAdapter() {
			public void keyPressed(final KeyEvent e) {
				if (e.getKeyCode() == 10)
					loginBtn.doClick();
			}
		});
		pwdTextField.setBounds(150, 165, 200, 18);
		panel.add(pwdTextField);
		//set login btn
		loginBtn = new JButton();
		loginBtn.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				String name=userTextField.getText().trim(),pwd=pwdTextField.getText().trim();
				//check
				if(name.equals("")){
					Hint.note("必须输入用户名！");
					userTextField.requestFocus();
					return;
				}
				if(pwd.equals("")){
					Hint.note("必须输入密码！");
					pwdTextField.requestFocus();
					return;
				}
				//login
				if(UserService.login(name,pwd)){
					setVisible(false);
					FrameService.createMainFrame();
				}else
					return;
			}
		});
		loginBtn.setText("登录");
		loginBtn.setBounds(180, 195, 60, 18);
		panel.add(loginBtn);
		//set exit btn
		exitBtn = new JButton();
		exitBtn.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				System.exit(0);
			}
		});
		exitBtn.setText("退出");
		exitBtn.setBounds(260, 195, 60, 18);
		panel.add(exitBtn);
		setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//test
		test();
	}
//	public static TbUser getUser() {
//		return tbUser;
//	}
//	public static void setUser(TbUser tbUser) {
//		Login.tbUser = tbUser;
//	}
	public void test(){
		userTextField.setText("admin");
		pwdTextField.setText("123");
		
	}
		
}
