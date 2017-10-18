package ru.namibios.arduino.model.command;

import java.awt.AWTException;
import java.io.IOException;

import ru.namibios.arduino.model.ImageParser;
import ru.namibios.arduino.model.Screen;
import ru.namibios.arduino.model.template.Chars;
import ru.namibios.arduino.utils.MatrixUtils;

public class CheckCut implements Command{

	private Screen screen;
	
	public CheckCut() throws AWTException {
		screen = new Screen(Screen.PERFECT);
		screen.saveImage("debug");
	}
	
	public CheckCut(String filename) throws IOException{
		screen = new Screen(filename);
	}
	
	@Override
	public String getKey() {
		ImageParser parser = new ImageParser(screen, Chars.values());
		parser.parse(Screen.WHITE);
		
		return parser.getNumber();
	}
	
	public static void main(String[] args) throws IOException, AWTException {
		Screen screen = new Screen("resources/debug/20171019_002319_664.jpg");
		
		ImageParser imageParser = new ImageParser(screen);
		imageParser.parse(Screen.WHITE);
		
		int[][] matrix = imageParser.getImageMatrix();
		
		MatrixUtils.printTemplate(matrix);
	}

}