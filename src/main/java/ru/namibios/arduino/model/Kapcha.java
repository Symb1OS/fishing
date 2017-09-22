package ru.namibios.arduino.model;

import java.awt.AWTException;
import java.awt.image.BufferedImage;
import java.io.IOException;

import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;

import ru.namibios.arduino.config.Property;
import ru.namibios.arduino.model.ImageParser.ImageType;
import ru.namibios.arduino.utils.Http;

public class Kapcha implements Command{

	final static Logger logger = Logger.getLogger(Kapcha.class);

	private Screen screen;
	
	private ObjectMapper mapper= new ObjectMapper();

	public Kapcha() throws AWTException  {
		this.screen = new Screen(ImageType.KAPCHA);
	}
	
	public Kapcha(String file) throws IOException{
		this.screen = new Screen(file);
	}
	
	@Override
	public String getKey(){
		
		try { clearNoises(30); } catch (AWTException e) { logger.error("Exception " + e); }
		
		ImageParser imageParser = new ImageParser(ImageType.KAPCHA, screen.getImage());
		imageParser.getCodes();
		
		Http http = new Http();
		
		String keys = "";
		try {
			keys = http.parseKapcha(Property.hashInstance(), mapper.writeValueAsString(imageParser.getImageMatrix()));
		} catch (IOException e) {logger.error("Exception: " + e); } 

		return keys.replace("\n", "");
	}
	
	public void clearNoises(int iteration) throws AWTException {
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
	
	public static void main(String[] args) throws Exception {
		Screen kapcha = new Screen("20170705_214741.jpg", ImageType.KAPCHA);
		kapcha.saveDebugImage();
	}
}