package ru.namibios.arduino.model.status;

import java.awt.AWTException;

import ru.namibios.arduino.model.ImageParser;
import ru.namibios.arduino.model.Screen;
import ru.namibios.arduino.model.template.StatusKapchaTemplate;

public class StatusKapcha implements Status<StatusKapchaTemplate>{

	private Screen screen;

	public StatusKapcha() throws AWTException {
		this.screen = new Screen(Screen.STATUS_KAPCHA);
		this.screen.saveImage("debug/statuskapcha");
		
	}
	
	public StatusKapchaTemplate getNameTemplate() {
		ImageParser parser = new ImageParser(screen, StatusKapchaTemplate.values());
		parser.parse(Screen.GRAY);
		
		return (StatusKapchaTemplate) parser.getNameTemplate();
	}
}
