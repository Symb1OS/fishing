package ru.namibios.arduino;

import java.io.PrintWriter;

import org.apache.log4j.Logger;

import com.fazecast.jSerialComm.SerialPort;

import ru.namibios.arduino.model.FishLoot;
import ru.namibios.arduino.model.Kapcha;
import ru.namibios.arduino.model.Property;
import ru.namibios.arduino.model.Region;

public class Transfer implements Runnable{ 
	
	final static Logger logger = Logger.getLogger(Transfer.class);

	private static final int EVERY_HOUR = 1000 * 60 * 60;

	private static final String SPACE = "4";

	private SerialPort port;
	
	private boolean isRun;
	
	private boolean isStart;
	private boolean isBegin;
	private boolean isSubLine;
	private boolean isKapcha;
	private boolean isLootFilter;

	private boolean bear;
	private boolean minigame;
	private boolean dinner1;
	private boolean dinner2;
	private boolean dinner3;
	
	private long startTime;

	public Transfer(String port) {

		startTime = System.currentTimeMillis();
		isStart = false;
		isBegin = true;
		isSubLine = false;
		isKapcha = false;
		
		this.port = SerialPort.getCommPort(port);
		this.port.setComPortTimeouts(SerialPort.TIMEOUT_SCANNER, 0, 0);
	}
	
	public void setProperty(Property property) {
		this.bear = property.isBear();
		this.minigame = property.isMinigame();
		this.dinner1 = property.isDinner1();
		this.dinner2 = property.isDinner2();
		this.dinner3 = property.isDinner3();
		
	}
	
	public void send(String message){
		PrintWriter output = new PrintWriter(port.getOutputStream());
		output.println(message);
		output.flush();
	}
	
	@SuppressWarnings("unused")
	private void use(String method, long sleep){
		logger.info("Using " + method);
		send(method);
		try{Thread.sleep(sleep);}catch(InterruptedException e){}
	}
	
	private void useBear(){
		long time = System.currentTimeMillis();
		boolean needFeed = time - startTime > EVERY_HOUR;
		if(needFeed){
			logger.info("Using bear...");
			send("bear");
			startTime = System.currentTimeMillis();
		}
	}
	
	public void run() {
		logger.info("Start...");
		isRun = true;
		
		try{
			Thread.sleep(3000);
			if(port.openPort()) {
				logger.info("Port is open...");
				while(isRun){
					
					if(isStart){
						Thread.sleep(3000);
						logger.info("Starting fish... ");
						send(SPACE);
						isStart=false; isBegin=true;
						Thread.sleep(5000);
						useBear();
						
					}else if(isBegin){
						logger.info("Wait fish...");
						Thread.sleep(3000);
						boolean isSend =  new Region(ImageType.SPACE).send(port);
						if(isSend){ isBegin= false; isSubLine= true; }
						
					}else if(isSubLine){
						logger.info("Cut the fish...");
						Thread.sleep(1200);
						send(ImageType.SPACE.toString());
						isSubLine=false;
						isKapcha=true;
						
					}else if(isKapcha){
						Thread.sleep(3920);
						try{
							logger.info("Parsing kapcha...");
							Kapcha kapcha = new Kapcha();
							kapcha.clearNoises(30);
							kapcha.send(port);
						}catch (Exception e){
							logger.error("Exception " + e);
							isStart=true; isKapcha=false;
						}
						isKapcha=false; isLootFilter=true;
					}else if(isLootFilter){
						Thread.sleep(5000);
						logger.info("Check loot...");
						new FishLoot().send(port);
						isLootFilter=false; isStart= true;
					}
				}
			}
			
			} catch (Exception e) {
				logger.error("Exception " + e);
				e.printStackTrace();
				port.closePort();
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
		Transfer transfer = new Transfer("ttyACM0");
		transfer.run();
		
	}

}