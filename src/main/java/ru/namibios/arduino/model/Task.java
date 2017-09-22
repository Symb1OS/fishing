package ru.namibios.arduino.model;

import org.apache.log4j.Logger;

import ru.namibios.arduino.config.Process;
import ru.namibios.arduino.utils.DelayUtils;
import ru.namibios.arduino.utils.Keyboard;

public class Task {
	
	final static Logger logger = Logger.getLogger(Task.class);
	
	private Command command;
	
	private Long delayBefore;
	private Long delayAfter;

	public Task(Command region) {
		this.command = region;
	}
	
	public Task(Command command, long delayBefore, long delayAfter) {
		this.command = command;
	}
	
	public void run(){
	
		DelayUtils.delay(delayBefore);
		boolean isOk = Keyboard.send(command);
		DelayUtils.delay(delayAfter);
		
		if(isOk) Process.next();
	}
	
	public boolean runTest(){
		
		DelayUtils.delay(delayBefore);
		boolean isOk = Keyboard.send(command);
		DelayUtils.delay(delayAfter);
		return isOk;
	}

}