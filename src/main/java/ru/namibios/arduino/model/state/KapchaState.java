package ru.namibios.arduino.model.state;

import org.apache.log4j.Logger;

import ru.namibios.arduino.config.Property;
import ru.namibios.arduino.model.Command;
import ru.namibios.arduino.model.Kapcha;
import ru.namibios.arduino.model.Task;
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
			Command kapcha = new Kapcha();
			
			Task task = new Task(kapcha, Property.DELAY_BEFORE_KAPCHA, Property.DELAY_AFTER_KAPCHA);
			boolean isOk = task.run();
			if(isOk) fishBot.setState(new FilterLootState(fishBot));
			
		}catch (Exception e) {
			logger.error("Exception " + e);
			DelayUtils.delay(7000);
			fishBot.setState(new StartFishState(fishBot));
		}
		
	}

}
