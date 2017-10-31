package ru.namibios.arduino.model.command;

import java.awt.AWTException;
import java.io.IOException;

import ru.namibios.arduino.model.ImageParser;
import ru.namibios.arduino.model.Screen;

public class PersonalMessage {
	
	private Screen screen;
	
	private double allowableCoef;
	
	public PersonalMessage(double coef) throws AWTException, IOException {
		//this.screen = new Screen(Screen.CHAT);
		this.screen = new Screen("debug/pm2.png");
		this.allowableCoef = coef;
	}
	
	public boolean isDetected() {
		ImageParser imageParser = new ImageParser(screen);
		imageParser.parse(Screen.GRAY);
		return imageParser.getCoefWhite() > allowableCoef;
	}
	
	public static void main(String[] args) throws AWTException, IOException {
		PersonalMessage message = new PersonalMessage(0.05);
		System.out.println("Key= " + message.isDetected());
	}
	
}