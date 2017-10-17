package ru.namibios.arduino;

import org.apache.log4j.Logger;

import ru.namibios.arduino.config.Application;
import ru.namibios.arduino.model.SubTasker;
import ru.namibios.arduino.model.SubTasker.SubTask;
import ru.namibios.arduino.model.state.FishBot;
import ru.namibios.arduino.utils.DelayUtils;

public class Transfer implements Runnable{ 
	
	final static Logger logger = Logger.getLogger(Transfer.class);

	private static final int EVERY_HOUR = 1000 * 60 * 60;

	private SubTasker subTasker;
	
	public Transfer() {
		
		subTasker = new SubTasker();
		if(Application.getInstance().BEER()) subTasker.add(new SubTask("bear", EVERY_HOUR));
	}
	
	public void run() {
		
		logger.info("Start...");

		Application.getPhysicalPort().openPort();
		DelayUtils.delay(3000);
		
		if(!Application.getPhysicalPort().isOpen()) {
			logger.info("Port is closed. Check you port in settings");
			return;
		} 
		
		logger.info("Port is open...");
		
		FishBot fishBot = new FishBot();
		while(fishBot.isRunned()) fishBot.getState().start();
		
		Application.getPhysicalPort().closePort();
		logger.info("Port closed...");
		logger.info("Thread stop.");
	}
}


