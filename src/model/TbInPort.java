/**

 * Title: TbInPort.java

 * Description: 

 * Copyright: ByTom's Studio 2016

 *            All right reserved.

 * 2016年12月25日
 */
package model;

import java.util.Vector;

import model.TbModel.Type;


/**
 * 
 * @职责 
 * @属层 
 * @author ByTom
 */
public class TbInPort extends Port{
	
	public TbInPort(){
		super();
	}
	public TbInPort(Vector v,Type type){
		super();
		super.setAll(v,type);
	}


}
