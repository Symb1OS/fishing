package ru.namibios.arduino;

import org.apache.log4j.Logger;

import ru.namibios.arduino.utils.DelayUtils;

public class Task {
	
	final static Logger logger = Logger.getLogger(Task.class);
	
	private Region region;
	private String key;
	
	private Long delayBefore;
	private Long delayAfter;

	public Task(Region region) {
		this.region = region;
	}
	
	public Task(Region region, long delayBefore, long delayAfter) {
		this.region = region;
	}
	
	public Task(String key, long delayBefore, long delayAfter) {
		this.key = key;
		this.delayBefore = delayBefore;
		this.delayAfter = delayAfter;
	}
	
	public boolean run(){
		DelayUtils.delay(delayBefore);
		
		boolean isOk = region.send();
		
		DelayUtils.delay(delayAfter);
		return isOk;
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