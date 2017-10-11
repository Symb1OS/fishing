package ru.namibios.arduino.model.command;

import java.awt.AWTException;
import java.io.IOException;

import org.apache.log4j.Logger;

import ru.namibios.arduino.config.Property;
import ru.namibios.arduino.model.ImageParser;
import ru.namibios.arduino.model.Screen;
import ru.namibios.arduino.utils.Http;
import ru.namibios.arduino.utils.JSON;

public class Kapcha implements Command{

	final static Logger logger = Logger.getLogger(Kapcha.class);

	private Screen screen;
	
	public Kapcha(int iteration) throws AWTException  {
		this.screen = new Screen(Screen.KAPCHA);
		screen.clearNoise(iteration);
		screen.saveDebugImage();
	}
	
	public Kapcha(String file) throws IOException{
		this.screen = new Screen(file);
	}
	
	public Screen getScreen() {
		return screen;
	}

	@Override
	public String getKey(){
		String key = "";
		try {
			ImageParser imageParser = new ImageParser(screen);
			imageParser.parse(Screen.GRAY);
			
			Http http = new Http();
			key = http.parseKapcha(Property.hashInstance(), JSON.getInstance().writeValueAsString(imageParser.getImageMatrix()));
			
		} catch (IOException e){
			logger.error("Exception: " + e); 
		} 

		return key.replace("\n", "");
	}
	
}