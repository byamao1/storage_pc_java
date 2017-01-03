/**

 * Title: TbUser.java

 * Description: 

 * Copyright: ByTom's Studio 2016

 *            All right reserved.

 * 2016年12月19日
 */
package service;


import java.util.List;

import com.tomcat.common.Hint;

import model.TbUser;


import dao.UserDao;


/**
 * 
 * @职责 管理用户，管理登录状态
 * @属层 
 * @author ByTom
 */
public class UserService {
	private static TbUser curUser;
	
	/**
	 * 获取当前登录的操作员
	 * @return
	 */
	public static TbUser getCurUser(){
		if(curUser==null){
			Hint.note("请登录！");
			return null;
		}else
			return curUser;
	}
	/**
	 *  登录
	 * @param name
	 * @return
	 */
	public static boolean login(String name,String pwd){
		//query
		List<TbUser> list = UserDao.query(name);
		if(list.size()<1){
			Hint.warn("该用户不存在！");
			return false;
		}else if(!list.get(0).getPwd().equals(pwd)){
			Hint.warn("密码错误！");
			return false;
		}else{
			curUser = list.get(0);
			return true;
		}
	}
}
