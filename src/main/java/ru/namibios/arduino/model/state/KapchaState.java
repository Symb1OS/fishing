package ru.namibios.arduino.model.state;

import org.apache.log4j.Logger;

import ru.namibios.arduino.config.Application;
import ru.namibios.arduino.model.Task;
import ru.namibios.arduino.model.command.Kapcha;
import ru.namibios.arduino.utils.DelayUtils;

public class KapchaState extends State {

	private static final Logger logger = Logger.getLogger(KapchaState.class);

	public KapchaState(FishBot fishBot) {
		super(fishBot);
	}
	
	@Override
	public void onNext() {
	
		logger.info("Parsing kapcha...");
		
		try{
			Kapcha kapcha = new Kapcha(30);
			
			Task task = new Task(kapcha, Application.getInstance().DELAY_BEFORE_KAPCHA(), Application.getInstance().DELAY_AFTER_KAPCHA());
			boolean isIdentified = task.run();
			
			kapcha.reloadGui();
			
			if(isIdentified){
				logger.info("Ok. Go to the next state...");
				fishBot.setState(new FilterLootState(fishBot));
			}
			else {
				logger.info("Captcha is not recognized. Return to start...");
				fishBot.setState(new StartFishState(fishBot));
			}
			
		}catch (Exception e) {
			logger.error("Exception " + e);
			DelayUtils.delay(7000);
			fishBot.setState(new StartFishState(fishBot));
		}
		
	}
}