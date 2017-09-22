package ru.namibios.arduino.model.state;

import org.apache.log4j.Logger;

import ru.namibios.arduino.config.Property;
import ru.namibios.arduino.model.Command;
import ru.namibios.arduino.model.FishLoot;
import ru.namibios.arduino.model.Task;

public class FilterLootState extends State{

	private static final Logger logger = Logger.getLogger(FilterLootState.class);

	public FilterLootState(FishBot fishBot) {
		super(fishBot);
	}

	@Override
	public void onNext() {
		logger.info("Check loot...");
		
		try {
			Command filter = new FishLoot();
			
			Task task = new Task(filter, Property.DELAY_BEFORE_FILTER_LOOOT, Property.DELAY_AFTER_FILTER_LOOOT);
			boolean isOk = task.runTest();
			if(isOk) fishBot.setState(new StartFishState(fishBot));
			
		}catch (Exception e) {
			logger.error("Exception " + e);
			fishBot.setState(new StartFishState(fishBot));
		}
		
	}

}
