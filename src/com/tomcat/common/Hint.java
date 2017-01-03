/**

 * Title: Hint.java

 * Description: 

 * Copyright: ByTom's Studio 2016

 *            All right reserved.

 * 2016年12月24日
 */
package com.tomcat.common;

import javax.swing.JOptionPane;

/**
 * 
 * @职责 
 * @属层 
 * @author ByTom
 */
public class Hint {
	
	/**
	 * 给用户警告
	 */
	public static void warn(String content){
		javax.swing.JOptionPane.showMessageDialog(null, content,"警告",JOptionPane.ERROR_MESSAGE);
	}
	/**
	 * 给用户提示
	 */
	public static void note(String content){
		javax.swing.JOptionPane.showMessageDialog(null, content,"提示",JOptionPane.INFORMATION_MESSAGE);
	}
	/**
	 * 确定是否
	 * @param content
	 */
	public static int confirm(String content){
		return javax.swing.JOptionPane.showConfirmDialog(null, content, "提示" ,JOptionPane.YES_NO_OPTION);
	}
	
	/**
	 * 测试打印用的
	 * @param name	使用该方法的  "类名.方法名"
	 * @param content
	 */
	public static void test(String name,String content){
		System.out.println("TomCat: ["+name+"] <test>"+content);
	}
	public static void test(String content){
		System.out.println("TomCat:  <test>"+content);
	}
	/**
	 * 打印错误
	 * @param name
	 * @param content
	 */
	public static void err(String name,String content){
		System.err.println("TomCat: ["+name+"] <err>"+content);
	}
	public static void err(String content){
		System.err.println("TomCat:  <err>"+content);
	}
	/**
	 * 输出错误信息
	 * @param name 使用该方法的  "类名.方法名"
	 * @param e 错误对象
	 */
	public static void err(String name,Exception e)
	{
		System.out.println("TomCat: ["+name+"] <Error>"+e.getMessage());
		e.printStackTrace();
	}
}
