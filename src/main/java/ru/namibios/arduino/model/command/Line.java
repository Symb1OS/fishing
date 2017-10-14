package ru.namibios.arduino.model.command;

import java.awt.AWTException;
import java.io.IOException;

import ru.namibios.arduino.model.ImageParser;
import ru.namibios.arduino.model.Screen;
import ru.namibios.arduino.model.template.Chars;

public class Line implements Command {

	private Screen screen;
	
	private ImageParser imageParser;
	
	public Line() throws AWTException {
		screen = new Screen(Screen.SUB_LINE);
		
		imageParser = new ImageParser(screen, Chars.values());
		imageParser.parse(Screen.GRAY);
		
		screen.saveImage("debug/line");
	}
	
	@Override
	public String getKey() {
		return imageParser.getNumber();
	}
	
	public static void main(String[] args) throws IOException {

		Screen screen =new Screen("resources/debug/line/20171014_231306_848.jpg");
		
		ImageParser parser = new ImageParser(screen, Chars.values());
		parser.parse(Screen.WHITE);
		
		String number = parser.getNumber();
		System.out.println("Number: " + number);
		
	}

}