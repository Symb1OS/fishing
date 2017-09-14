package ru.namibios.arduino.model;

import org.apache.log4j.Logger;

import ru.namibios.arduino.utils.DelayUtils;
import ru.namibios.arduino.utils.Process;

public class Task {
	
	final static Logger logger = Logger.getLogger(Task.class);
	
	private Region region;
	
	private Long delayBefore;
	private Long delayAfter;

	public Task(Region region) {
		this.region = region;
	}
	
	public Task(Region region, long delayBefore, long delayAfter) {
		this.region = region;
	}
	
	public void run(){
		
		DelayUtils.delay(delayBefore);
		boolean isOk = region.send();
		DelayUtils.delay(delayAfter);
		
		if(isOk) Process.next();
	}

	public Long getDelayBefore() {
		return delayBefore;
	}

	public void setDelayBefore(Long delayBefore) {
		this.delayBefore = delayBefore;
	}

	public Long getDelayAfter() {
		return delayAfter;
	}

	public void setDelayAfter(Long delayAfter) {
		this.delayAfter = delayAfter;
	}

}