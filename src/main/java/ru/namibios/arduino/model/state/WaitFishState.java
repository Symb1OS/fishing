package ru.namibios.arduino.model.state;

import org.apache.log4j.Logger;

import ru.namibios.arduino.config.Application;
import ru.namibios.arduino.model.Task;
import ru.namibios.arduino.model.command.Command;
import ru.namibios.arduino.model.command.WaitFish;

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
			
			Task task = new Task(waitFish, Application.getInstance().DELAY_BEFORE_WAIT_FISH(), Application.getInstance().DELAY_AFTER_WAIT_FISH());
			boolean isOk = task.run();
			if(isOk) fishBot.setState(new CutFishState(fishBot));
			
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception " + e);
		}
		
	}
}