package ru.namibios.arduino.model;

import java.awt.image.BufferedImage;
import java.io.IOException;

import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;

import ru.namibios.arduino.model.ImageParser.ImageType;
import ru.namibios.arduino.utils.Http;

public class Kapcha extends Region{

	final static Logger logger = Logger.getLogger(Kapcha.class);

	private Screen screen;

	public Kapcha() throws Exception {
		this.screen = new Screen(ImageType.KAPCHA);
	}
	
	public Kapcha(String file) throws Exception{
		this.screen = new Screen(file);
	}
	
	@Override
	public String getKey(){
		
		try { clearNoises(30); } catch (Exception e) { logger.error("Exception " + e); }
		
		ImageParser imageParser = new ImageParser(ImageType.KAPCHA, screen.getImage());
		imageParser.getCodes();
		
		int[][] matrix = imageParser.getImageMatrix();
		
		ObjectMapper mapper= new ObjectMapper();

		Http http = new Http();
		
		String keys = "";
		try {
			keys = http.parseKapcha(Property.hashInstance(), mapper.writeValueAsString(matrix));
		} catch (IOException e) {logger.error("Exception: " + e); } 

		return keys.replace("\n", "");
	}
	
	public void clearNoises(int iteration) throws Exception{
		logger.info("Clean the noise...");
		int cnt = 0;
		while(cnt < iteration){
			BufferedImage noiseImage = new Screen(ImageType.KAPCHA).getImage();
			screen.addNoise(noiseImage);
			cnt++;
		}
		screen.clear();
	 	//screen.saveDebugImage();
		logger.info("Clean ended...");
	}
	
}