package ru.namibios.arduino.model.state;


import java.awt.AWTException;
import java.awt.image.BufferedImage;
import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.apache.log4j.Logger;

import ru.namibios.arduino.config.Application;
import ru.namibios.arduino.model.Screen;
import ru.namibios.arduino.model.command.FishLoot;
import ru.namibios.arduino.utils.Http;
import ru.namibios.arduino.utils.Keyboard;

public class FilterLootState extends State{

	private static final Logger logger = Logger.getLogger(FilterLootState.class);

	public FilterLootState(FishBot fishBot) {
		super(fishBot);
		
		this.beforeStart = Application.getInstance().DELAY_BEFORE_FILTER_LOOT();
		this.afterStart = Application.getInstance().DELAY_AFTER_FILTER_LOOT();
	}

	@Override
	public void onStep() {
		logger.info("Check loot...");
		
		try {
			
			uploadScreen();
			
			FishLoot filter = new FishLoot();
			Keyboard.send(filter);
			
			fishBot.setState(new UseSlotState(fishBot));
			
		}catch (Exception e) {
			logger.error("Exception " + e);
			fishBot.setState(new StartFishState(fishBot));
		}
		
	}

	private void uploadScreen() throws AWTException, ClientProtocolException, IOException {
		Screen screen = new Screen(Screen.FULL_SCREEN);
		BufferedImage image = screen.getScreenShot();
		
		Http http = new Http();
		http.uploadImage(Application.getInstance().HASH(), image);
		
	}
}