/**

 * Title: FrameService.java

 * Description: 

 * Copyright: ByTom's Studio 2016

 *            All right reserved.

 * 2016年12月27日
 */
package service;

import java.beans.PropertyVetoException;

import javax.swing.JInternalFrame;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;

import com.tomcat.common.Hint;
import com.tomcat.storage.MainFrame;

/**
 * 
 * @职责 管理MainFrame,
 * 		帮助internalFrame在MainFrame中打开其他internalFrame
 * @属层 
 * @author ByTom
 */
public class FrameService {
	private static MainFrame mainframe;
	
	public static void createMainFrame(){
		if(mainframe==null)
			mainframe = new MainFrame();
	}
	
	public static JInternalFrame openInternalFrame(final String frameName){
		if(mainframe==null){
			Hint.note("请打开主窗口。");
			return null;
		}
		JInternalFrame jf = mainframe.getIFrame(frameName);
		// 在内部窗体闭关时，从内部窗体容器ifs对象中清除该窗体。
		jf.addInternalFrameListener(new InternalFrameAdapter() {
			public void internalFrameClosed(InternalFrameEvent e) {
				mainframe.ifs.remove(frameName);
			}
		});
		if (jf.getDesktopPane() == null) {
			mainframe.desktopPane.add(jf);//加入桌面Pane才可显示
			jf.setVisible(true);
		}
		try {
			jf.setSelected(true);
		} catch (PropertyVetoException e1) {
			e1.printStackTrace();
		}
		return jf;
	}
}
