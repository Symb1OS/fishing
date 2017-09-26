package ru.namibios.arduino.model.command;

import java.awt.AWTException;
import java.awt.image.BufferedImage;

import ru.namibios.arduino.model.ImageParser;
import ru.namibios.arduino.model.ImageParser.ImageType;

public class WaitFish implements Command{

	private Screen screen;
	
	private ImageParser imageParser;
	
	public WaitFish() throws AWTException{
		this.screen = new Screen(ImageType.SPACE);
	 
		BufferedImage image = screen.getImage();
		this.imageParser = new ImageParser(ImageType.SPACE, image);
		imageParser.getCodes();
	}

	@Override
	public String getKey() {
		return imageParser.getNumberkeyFromTemlate();
	}
}
