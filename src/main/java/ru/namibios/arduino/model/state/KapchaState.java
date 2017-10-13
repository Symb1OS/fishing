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
			boolean isOk = task.run();
			if(isOk) fishBot.setState(new FilterLootState(fishBot));
			
		}catch (Exception e) {
			logger.error("Exception " + e);
			DelayUtils.delay(7000);
			fishBot.setState(new StartFishState(fishBot));
		}
		
	}
}