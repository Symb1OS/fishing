package ru.namibios.arduino.model.state;

import java.awt.AWTException;

import org.apache.log4j.Logger;

import ru.namibios.arduino.config.Property;
import ru.namibios.arduino.model.Command;
import ru.namibios.arduino.model.Task;
import ru.namibios.arduino.model.WaitFish;

public class WaitFishState extends State {
	
	private static final Logger logger = Logger.getLogger(WaitFishState.class);

	public WaitFishState(FishBot fishBot) {
		super(fishBot);
	}

	@Override
	public void onNext() {
		logger.info("Wait fish..");
		
		try {
			
			Command waitFish = new WaitFish();
			Task task = new Task(waitFish, Property.DELAY_BEFORE_WAIT_FISH, Property.DELAY_AFTER_WAIT_FISH);
			boolean isOk = task.run();
			if(isOk) fishBot.setState(new CutFishState(fishBot));
			
		}catch (AWTException e) {
			logger.error("Exception " + e);
		}
		
	}
}