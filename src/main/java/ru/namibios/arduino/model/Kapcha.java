package ru.namibios.arduino.model;

import java.awt.image.BufferedImage;
import java.io.PrintWriter;

import org.apache.log4j.Logger;

import com.fazecast.jSerialComm.SerialPort;

import ru.namibios.arduino.ImageParser;
import ru.namibios.arduino.ImageType;
import ru.namibios.arduino.Screen;

public class Kapcha {

	final static Logger logger = Logger.getLogger(Kapcha.class);
	
	private Screen screen;
	
	private ImageParser imageParser;
	
	public Kapcha() throws Exception {
		this.screen = new Screen(ImageType.KAPCHA);
	}
	
	private String getKey(){
		
		BufferedImage image = screen.getImage();
		this.imageParser = new ImageParser(ImageType.KAPCHA, image);
		imageParser.getCodes();
		
		return imageParser.getkeyFromTemlate();
	}
	
	public boolean send(SerialPort port){
		String message = getKey();
		boolean status = false;
		String key = imageParser.getkeyFromTemlate();
		if(!key.isEmpty()){
			PrintWriter output = new PrintWriter(port.getOutputStream());
			output.println(message);
			output.flush();
			
			logger.info("Sended message: " + message);
			status = true;
			
		}
		return status;
	}
	
	public void clearNoises(int iteration) throws Exception{
		logger.info("Clean the noise...");
		int cnt = 0;
		while(cnt < iteration){
			BufferedImage noiseImage = new Screen(ImageType.KAPCHA).getImage();
			screen.addNoise(noiseImage);
			//screen.saveDebugImage();
			cnt++;
		}
		screen.clear();
		screen.saveDebugImage();
		logger.info("Clean ended...");
	}
	
}