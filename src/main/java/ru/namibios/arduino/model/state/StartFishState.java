package ru.namibios.arduino.model.state;

import org.apache.log4j.Logger;

import ru.namibios.arduino.config.Property;
import ru.namibios.arduino.model.Command;
import ru.namibios.arduino.model.Task;
import ru.namibios.arduino.utils.Keyboard;

public class StartFishState extends State{

	private static final Logger logger= Logger.getLogger(StartFishState.class);
	
	public StartFishState(FishBot proccess) {
		super(proccess);
	}

	@Override
	public void onNext() {
		logger.info("Start Fish...");
		
		Command command = () -> Keyboard.Keys.SPACE;
		
		Task task = new Task(command, Property.DELAY_BEFORE_START, Property.DELAY_AFTER_START);
		boolean isSend = task.runTest();
		
		if(isSend) fishBot.setState(new WaitFishState(fishBot));
	}

}
