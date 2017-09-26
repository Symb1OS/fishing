package ru.namibios.arduino.model.command;

import java.awt.AWTException;

import ru.namibios.arduino.model.ImageParser;
import ru.namibios.arduino.model.Screen;

public class WaitFish implements Command{

	private Screen screen;
	
	private ImageParser imageParser;
	
	public WaitFish() throws AWTException{
		this.screen = new Screen(Screen.SPACE);
	 
		this.imageParser = new ImageParser(screen);
		imageParser.parse(Screen.WHITE);
	}

	@Override
	public String getKey() {
		return imageParser.getNumber();
	}
}