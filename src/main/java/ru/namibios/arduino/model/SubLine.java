package ru.namibios.arduino.model;

import java.awt.image.BufferedImage;

import ru.namibios.arduino.ImageParser;
import ru.namibios.arduino.ImageType;
import ru.namibios.arduino.Screen;

public class SubLine {

	private static final ImageType TYPE = ImageType.SUBLINE;
	
	private Screen screen;
	
	private ImageParser imageParser;
	
	public SubLine() throws Exception {
		this.screen = new Screen(TYPE);
		BufferedImage image = screen.getImage();
		
		this.imageParser = new ImageParser(TYPE, image);
		imageParser.getMatrix();
	}
	
	public String getKey(){
		return imageParser.getkeyFromTemlate();
		
	}
}