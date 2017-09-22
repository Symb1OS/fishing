package ru.namibios.arduino.utils;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

public class SubTasker {
	
	final static Logger logger = Logger.getLogger(SubTasker.class);

	private List<SubTask> tasks;
	
	public SubTasker() {
		this.tasks = new ArrayList<>();
	}
	
	public void add(SubTask subTask) {
		tasks.add(subTask);
	}
	
	public void check() {
		for (SubTask subTask : tasks) {
			if(subTask.run()) {
				logger.info("SubTask is runned. Other wait next launch");
				break;
			} 
		}
	}
	
	public static class SubTask {
		
		private static final String USING = "Using %s";

		private Long startTime;
		
		private String name;
		
		private Long period;

		public SubTask(String name, long period) {
			this.name = name;
			this.period = period;
			this.startTime = System.currentTimeMillis();
			
		}
		
		private boolean run() {
			
			long time  = System.currentTimeMillis();
			
			long periodLastStart = (time - startTime);
			boolean isTimeRun = periodLastStart >  period; 
			if( isTimeRun ) {
				logger.info(String.format(USING, name));
				Keyboard.send(() -> name);
				startTime = System.currentTimeMillis();
				
			}
			
			return isTimeRun;
			
		}
	}
}
