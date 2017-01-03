/**

 * Title: InPortService.java

 * Description: 

 * Copyright: ByTom's Studio 2016

 *            All right reserved.

 * 2016年12月28日
 */
package service;

import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

import model.TbInPort;
import model.TbModel;
import model.TbProduct;
import model.TbStock;

import com.tomcat.common.Hint;

import dao.InPortDao;
import dao.ProductDao;
import dao.StockDao;

/**
 * 
 * @职责 
 * @属层 
 * @author ByTom
 */
public class InPortService {
	/**
	 * 增加入库记录时，要改变库存信息
	 * @param v
	 * @return
	 */
	public static boolean add(Vector v){
		//check
		if(v==null){
			Hint.err("InPortService.add", "入口参数Vector为null！");
			return false;
		}
		TbInPort inport = new TbInPort(v,TbInPort.Type.TABLE);
		if(InPortDao.add(inport)){
			//在库存表中加入
			TbStock stock = new TbStock();
			stock.setProduct_code(inport.getCode());
			stock.setName(inport.getName());
			stock.setNum(inport.getNum());
			if(StockDao.add(stock))
				return true;
			else{
				Hint.note("库存信息添加失败！");
				Hint.err("InPortService.add", "库存信息添加失败！");
				return false;
			}
		}
		Hint.err("InPortService.add", "入库信息添加失败！");
		return false;
	}
	
	public static boolean delete(TbInPort tbInPort){
		//check
		if(tbInPort==null){
			Hint.err("InPortService.delete", "入口参数TbInPort为null！");
			return false;
		}
		if(InPortDao.delete(tbInPort)){
			//获得该库存旧数量
			List<String> whereFieldNames = new LinkedList<>();
			whereFieldNames.add("product_code");
			Object[] code = new Object[]{tbInPort.getCode()};
			List<TbStock> tbStockList = StockDao.query(whereFieldNames, code);
			//计算库存新值
			int stockNewNum = tbStockList.get(0).getNum() - tbInPort.getNum();
			//设置
			List<String> setFieldNames = new LinkedList<>();
			setFieldNames.add("num");
			Object[] objects = new Object[]{stockNewNum, tbInPort.getCode()};
			if(StockDao.set(setFieldNames, whereFieldNames, objects)==false){
				Hint.err("InPortService.delete", "删除入库数量成功，但是修改对应库存数量失败！");
			}
			return true;
		}else
			return false;
	}
	
	public static List<TbInPort> query(List<String> whereFieldNames,Object[] params){
		return InPortDao.query(whereFieldNames, params);
	}
	
	/**
	 * 修改1个值
	 * @param tbInPort
	 * @param setFieldNames
	 * @param setValue
	 * @return
	 */
	public static boolean set(TbInPort tbInPort,String setFieldName,Object setValue){
		if(tbInPort==null||setFieldName==null
				||setValue==null){
			Hint.err("InPortService.set", "入口参数为null！");
			return false;
		}
		
		List<String> list = new LinkedList<>();
		list.add(setFieldName);
		Object[] params = new Object[]{setValue};
		if(InPortDao.set(tbInPort, list, params)){
			if(setFieldName.equals("num")){	//如果改的字段是数量，就要改库存
				//该入库记录变化量
				int delta = (int)setValue - tbInPort.getNum();
				//获得该库存旧数量
				List<String> whereFieldNames = new LinkedList<>();
				whereFieldNames.add("product_code");
				Object[] code = new Object[]{tbInPort.getCode()};
				List<TbStock> tbStockList = StockDao.query(whereFieldNames, code);
				//计算库存新值
				int stockNewNum = tbStockList.get(0).getNum() + delta;
				//设置
				List<String> setFieldNames = new LinkedList<>();
				setFieldNames.add("num");
				Object[] objects = new Object[]{stockNewNum, tbInPort.getCode()};
				if(StockDao.set(setFieldNames, whereFieldNames, objects)==false){
					Hint.err("InPortService.set", "修改入库数量成功，但是修改对应库存数量失败！");
				}
				return true;
			}
		}
		
		return false;
	}
}
