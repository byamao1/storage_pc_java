/**

 * Title: AudioService.java

 * Description: 

 * Copyright: ByTom's Studio 2016

 *            All right reserved.

 * 2016年12月30日
 */
package service;

import com.tomcat.common.AudioUtil;

/**
 * 播放声音服务
 * @职责 
 * @属层 
 * @author ByTom
 */
public class AudioService {
	public static void note(){
		new Thread(){
			public void run() {
				AudioUtil.play("./res/audio/complete.wav");
			}
		}.start();
	}
}
