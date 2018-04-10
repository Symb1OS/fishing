package ru.namibios.arduino.model.state;

import org.apache.log4j.Logger;

import ru.namibios.arduino.config.Application;
import ru.namibios.arduino.model.command.Command;
import ru.namibios.arduino.model.command.WaitFish;
import ru.namibios.arduino.utils.Keyboard;

public class WaitFishState extends State {
	
	private static final Logger logger = Logger.getLogger(WaitFishState.class);

	public WaitFishState(FishBot fishBot) {
		super(fishBot);
		
		this.beforeStart = Application.getInstance().DELAY_BEFORE_WAIT_FISH();
		this.afterStart = Application.getInstance().DELAY_AFTER_WAIT_FISH();
		
	}

	@Override
	public void onStep() {
		logger.info("Wait fish..");
		
		try {
			
			Command waitFish = new WaitFish();
			boolean isFishBite = Keyboard.send(waitFish);
			
			if(isFishBite) fishBot.setState(new CutFishState(fishBot));
			
			if(checkTime(Application.getInstance().TIME_CHANGE_ROD())){
				fishBot.setState(new ChangeRodState(fishBot));
			} 
			
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception " + e);
		}
	}
	
}