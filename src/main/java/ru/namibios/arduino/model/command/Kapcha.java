package ru.namibios.arduino.model.command;

import java.awt.AWTException;
import java.io.IOException;

import javax.swing.ImageIcon;

import org.apache.log4j.Logger;

import ru.namibios.arduino.Gui;
import ru.namibios.arduino.config.Application;
import ru.namibios.arduino.model.ImageParser;
import ru.namibios.arduino.model.Screen;
import ru.namibios.arduino.utils.Http;
import ru.namibios.arduino.utils.JSON;

public class Kapcha implements Command, Reloader{

	final static Logger logger = Logger.getLogger(Kapcha.class);

	private Screen screen;
	
	private int iteration;
	
	private String key;
	
	public Kapcha(int iteration) throws AWTException  {
		this.screen = new Screen(Screen.KAPCHA);
		this.iteration = iteration;
		this.key = "";
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
		
		try {
			
			screen.clearNoise(iteration);
			
			ImageParser imageParser = new ImageParser(screen);
			imageParser.parse(Screen.GRAY);
			
			Http http = new Http();
			key = http.parseKapcha(Application.getInstance().HASH(), JSON.getInstance().writeValueAsString(imageParser.getImageMatrix()));
			
		} catch (IOException | AWTException e){
			logger.error("Exception: " + e); 
		} 
		return key.replaceAll("\"",  "");
	}

	@Override
	public void reloadGui() {
		Gui.lKapchaImg.setIcon(new ImageIcon(screen.getScreenShot()));
		Gui.lKapchaText.setText(key);
		
	}
	
}