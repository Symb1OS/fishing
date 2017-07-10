package ru.namibios.arduino.model;

import java.awt.image.BufferedImage;

import ru.namibios.arduino.ImageParser;
import ru.namibios.arduino.ImageType;
import ru.namibios.arduino.Screen;

public class Shape {

	private Screen screen;
	
	private ImageParser imageParser;
	
	public Shape(ImageType type) throws Exception {
		this.screen = new Screen(type);
	
		BufferedImage image = screen.getImage();
		this.imageParser = new ImageParser(type, image);
		imageParser.getMatrix();
	}
	
	public String getKey(){
		return imageParser.getkeyFromTemlate();
		
	}
}
