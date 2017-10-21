package ru.namibios.arduino.model.state;

import java.awt.AWTException;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.log4j.Logger;

import ru.namibios.arduino.config.Application;
import ru.namibios.arduino.model.Screen;
import ru.namibios.arduino.model.command.Command;
import ru.namibios.arduino.model.command.WaitFish;
import ru.namibios.arduino.utils.Keyboard;

public class WaitFishState extends State {
	
	private static final Logger logger = Logger.getLogger(WaitFishState.class);

	public WaitFishState(FishBot fishBot) {
		super(fishBot);
		
		this.beforeStart = Application.getInstance().DELAY_BEFORE_WAIT_FISH();
		this.afterStart = Application.getInstance().DELAY_AFTER_WAIT_FISH();
		
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			
			@Override
			public void run() {
				try { 
					Screen screen = new Screen(Screen.FULL_SCREEN); 
					screen.saveDebugImage();
				} catch (AWTException e) { 
					e.printStackTrace(); 
				}
			}
		}, 1000 * 60 * 4);
	}

	@Override
	public void onStep() {
		logger.info("Wait fish..");
		
		try {
			
			Command waitFish = new WaitFish();
			boolean isFishBite = Keyboard.send(waitFish);
			
			if(isFishBite) fishBot.setState(new CutFishState(fishBot));
			
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception " + e);
		}
		
	}
	
}