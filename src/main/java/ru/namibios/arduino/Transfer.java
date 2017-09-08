package ru.namibios.arduino;

import org.apache.log4j.Logger;

import ru.namibios.arduino.model.FishLoot;
import ru.namibios.arduino.model.FixedKey;
import ru.namibios.arduino.model.ImageParser.ImageType;
import ru.namibios.arduino.model.Kapcha;
import ru.namibios.arduino.model.Property;
import ru.namibios.arduino.model.Region;
import ru.namibios.arduino.model.SubTasker;
import ru.namibios.arduino.model.SubTasker.SubTask;
import ru.namibios.arduino.model.Task;
import ru.namibios.arduino.utils.DelayUtils;
import ru.namibios.arduino.utils.Keyboard.ArduinoSubTask;
import ru.namibios.arduino.utils.Process;

public class Transfer implements Runnable{ 
	
	final static Logger logger = Logger.getLogger(Transfer.class);

	private static final int EVERY_HOUR = 1000 * 60 * 60;

	private SubTasker subTasker;
	
	public Transfer() {
		
		subTasker = new SubTasker();
		if(Property.isBear()) subTasker.add(new SubTask("bear", EVERY_HOUR));

		Process.initStart();
		
	}
	
	public void run() {
		
		logger.info("Start...");
		DelayUtils.delay(3000);
		
		Task task = null;
		
		if(!Property.portInstance().isOpen()) {
			logger.info("Port is closed. Exit");
			System.exit(1);
		} 
		
		logger.info("Port is open...");
			
			switch (Process.getInstance()) {
				case START:{
					logger.info("Starting fish... ");
					
					Region fixedRegion = new FixedKey(ArduinoSubTask.space);
					
					task = new Task(fixedRegion, 3000, 5000);
					task.run();
					
					logger.info("Check sub-task...");
					subTasker.check();
					
					break;
				}
				case WAIT_FISH: {
					logger.info("Wait fish...");
					try {
						Region waitFish = new Region(ImageType.SPACE);
						
						task = new Task(waitFish, 3000, 0);
						task.run();
					}catch (Exception e) {
						 logger.error("Exception " + e);
					}
					break;
				}
				case PARSE_LINE:{
					logger.info("Cut the fish...");
					try {
						Region parseLine = new FixedKey(ArduinoSubTask.space);
						
						task = new Task(parseLine, 1200, 0);
						task.run();
						
					}catch (Exception e) {
						logger.error("Exception " + e);
					}
					break;
				}
				case PARSE_KAPCHA:{
					logger.info("Parsing kapcha...");
					
					try{
						Region kapcha = new Kapcha();
						
						task = new Task(kapcha, 3920, 10000);
						task.run();
						
					}catch (Exception e) {
						logger.error("Exception " + e);
						DelayUtils.delay(7000);
						Process.initStart();
					}
					break;
				}
				case FILTER_LOOT:{
					logger.info("Check loot...");
					
					try {
						Region filter = new FishLoot();
						
						task = new Task(filter, 5000, 0);
						task.run();
						
					}catch (Exception e) {
						logger.error("Exception " + e);
					}
					
					break;
				} 	
			}
			
		Property.portInstance().closePort();
		logger.info("Port closed...");
		logger.info("Thread stop.");
	}
	
}