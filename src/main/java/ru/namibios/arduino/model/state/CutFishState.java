package ru.namibios.arduino.model.state;

import org.apache.log4j.Logger;

import ru.namibios.arduino.config.Application;
import ru.namibios.arduino.model.Task;
import ru.namibios.arduino.model.command.Command;
import ru.namibios.arduino.utils.Keyboard;

public class CutFishState extends State {

	private static final Logger logger = Logger.getLogger(CutFishState.class);

	public CutFishState(FishBot fishBot) {
		super(fishBot);
	}

	@Override
	public void onNext() {
		
		Command line = () -> Keyboard.Keys.SPACE;
	
		Task task = new Task(line, Application.getInstance().DELAY_BEFORE_CUT_FISH(), Application.getInstance().DELAY_AFTER_CUT_FISH());
		
		boolean isOk = task.run();
		if(isOk) {
			logger.info("Cut the fish...");
			fishBot.setState(new KapchaState(fishBot));
		}
		
	}

}