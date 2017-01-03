/**

 * Title: AudioUtil.java

 * Description: 

 * Copyright: ByTom's Studio 2016

 *            All right reserved.

 * 2016年12月30日
 */
package com.tomcat.common;

import java.io.File; 
import java.io.IOException; 
 
import javax.sound.sampled.AudioFormat; 
import javax.sound.sampled.AudioInputStream; 
import javax.sound.sampled.AudioSystem; 
import javax.sound.sampled.DataLine; 
import javax.sound.sampled.LineUnavailableException; 
import javax.sound.sampled.SourceDataLine; 
import javax.sound.sampled.UnsupportedAudioFileException; 

/**
 * 播放声音
 * @职责 
 * @属层 
 * @author ByTom
 */
public class AudioUtil {
	
	public static void play(String filePath){
		//check
		if(filePath==null||filePath.equals("")){
			Hint.test("AudioUtil.play", "入库参数filePath为null或空");
			return;
		}
		 
		try { 
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(filePath)); 
			AudioFormat audioFormat = audioInputStream.getFormat(); 
			DataLine.Info dataLine_info = new DataLine.Info(SourceDataLine.class,audioFormat); 
			SourceDataLine sourceDataLine = (SourceDataLine)AudioSystem.getLine(dataLine_info); 
			byte[] b = new byte[1024]; 
			int len = 0; 
			sourceDataLine.open(audioFormat, 1024); 
			sourceDataLine.start(); 
			while ((len = audioInputStream.read(b)) > 0){ 
				sourceDataLine.write(b, 0, len); 
			} 
			audioInputStream.close(); 
			sourceDataLine.drain(); 
			sourceDataLine.close();
		} catch (IOException|LineUnavailableException|UnsupportedAudioFileException e) { 
			Hint.printError("AudioUtil.play", e);
		}
	}
}
