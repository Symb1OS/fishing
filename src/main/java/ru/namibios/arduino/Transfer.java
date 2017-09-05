package ru.namibios.arduino;

import java.io.PrintWriter;

import org.apache.log4j.Logger;

import com.fazecast.jSerialComm.SerialPort;

import ru.namibios.arduino.model.Chars;
import ru.namibios.arduino.model.ImageType;
import ru.namibios.arduino.model.Property;
import ru.namibios.arduino.utils.DelayUtils;
import ru.namibios.arduino.utils.Process;

public class Transfer implements Runnable{ 
	
	final static Logger logger = Logger.getLogger(Transfer.class);

	private static final int EVERY_HOUR = 1000 * 60 * 60;

	private SerialPort port;
	
	private boolean isRun;
	
	private boolean isStart;
	private boolean isBegin;
	private boolean isSubLine;
	private boolean isKapcha;
	private boolean isLootFilter;

	private Property property;
	
	private long startTime;

	public Transfer(String port) {

		startTime = System.currentTimeMillis();
		isStart = false;
		isBegin = true;
		isSubLine = false;
		isKapcha = false;
		
		Process.initStart();
		this.port = SerialPort.getCommPort(port);
		this.port.setComPortTimeouts(SerialPort.TIMEOUT_SCANNER, 0, 0);
	}
	
	public void setProperty(Property property) {
		this.property = property;
		
	}
	
	public void send(String message){
		PrintWriter output = new PrintWriter(port.getOutputStream());
		output.println(message);
		output.flush();
		logger.info("Sended message: " + message);
	}
	
	private void useBear(){
		long time = System.currentTimeMillis();
		boolean needFeed = (property.isBear() && (time - startTime > EVERY_HOUR));
		if(needFeed){
			logger.info("Using bear...");
			send("bear");
			startTime = System.currentTimeMillis();
		}
	}
	
	public void run() {
		
		isRun = true;
		logger.info("Start...");
		DelayUtils.delay(3000);
		
		Task task = null;
		
		if(!port.isOpen()) {
			logger.info("Port is closed. Exit");
			System.exit(1);
		} 
		
		logger.info("Port is open...");
		while(isRun){
			
			switch (Process.getInstance()) {
				case START:{
					logger.info("Starting fish... ");
					
					Region start = new Region();
					
					task = new Task(start, 3000, 5000);
					task.run();
					
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
					
					try {
						Region parseLine = new Region();
						
						task = new Task(parseLine);
						task.run();
					}catch (Exception e) {
						logger.error("Exception " + e);
					}
					
					break;
				}
				
				case PARSE_KAPCHA:{
					
					logger.info("Parsing kapcha...");
					
					try{
						Region kapcha = new Region();
						
						task = new Task(kapcha, 3920, 10000);
						task.run();
					
					}catch (Exception e) {
						logger.error("Exception " + e);
					}
					
					break;
				}
				case FILTER_LOOT:{
					logger.info("Check loot...");
					
					Region filter = new Region();
					
					task = new Task(filter, 5000, 0);
					
					break;
				} 	
			}
			
			
			/*if(isStart){
				Thread.sleep(3000);
				logger.info("Starting fish... ");
				send(Chars.space.name());
				isStart=false; isBegin=true;
				Thread.sleep(5000);
				useBear();
				
			}else if(isBegin){
				logger.info("Wait fish...");
				Thread.sleep(3000);
				boolean isSend =  new Region(ImageType.SPACE).send(port);
				if(isSend){ isBegin= false; isSubLine= true; }
				
			}else if(isSubLine){
				String key= new Region(ImageType.SUBLINE).getKey();
				if(key.equals(String.valueOf(Chars.space.name()))) {
					logger.info("Cut the fish...");
					Thread.sleep(680);
					send(Chars.space.name());
					isSubLine=false;
					isKapcha=true;
				}
			}else if(isKapcha){
				Thread.sleep(3920);
				try{
					logger.info("Parsing kapcha...");
					Kapcha kapcha = new Kapcha();
					kapcha.clearNoises(30);
					boolean isDefined = kapcha.send(property.getHash(), port);
					if(!isDefined){
						logger.info("Kaptcha undefuned");
						Thread.sleep(10000);
						isStart=true; isKapcha=false;
					}
				}catch (Exception e){
					logger.error("Exception " + e);
					isStart=true; isKapcha=false;
					Thread.sleep(7000);
				}
				isKapcha=false; isLootFilter=true;
			}else if(isLootFilter){
				Thread.sleep(5000);
				logger.info("Check loot...");
				new FishLoot(property).send(port);
				isLootFilter=false; isStart= true;
			}
		}*/
		}
			
		port.closePort();
		logger.info("Port closed...");
		logger.info("Thread stop.");
	}
	
	void restart(){
		isRun = true;
	}
	
	void pause(){
		isRun = false;
	}
	
	public static void main(String[] args) {
	
		System.out.println(Chars.space.ordinal());
		
	}

}