package ru.namibios.arduino;

import java.io.PrintWriter;

import com.fazecast.jSerialComm.SerialPort;

import ru.namibios.arduino.model.FishLoot;
import ru.namibios.arduino.model.Kapcha;
import ru.namibios.arduino.model.Region;

public class Transfer implements Runnable{ 

	private static final int EVERY_HOUR = 1000 * 60 * 60;

	private static final String SPACE = "4";

	private SerialPort port;
	
	private boolean isRun;
	
	private boolean isStart;
	private boolean isBegin;
	private boolean isSubLine;
	private boolean isKapcha;
	private boolean isLootFilter;

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
	
	public void send(String message){
		PrintWriter output = new PrintWriter(port.getOutputStream());
		output.println(message);
		output.flush();
	}
	
	private void useBear(){
		long time = System.currentTimeMillis();
		boolean needFeed = time - startTime > EVERY_HOUR;
		if(needFeed){
			send("bear");
			startTime = System.currentTimeMillis();
		}
	}
	
	public void run() {
		System.out.println("Thread start...");
		isRun = true;
		
		try{
			Thread.sleep(3000);
			if(port.openPort()) {
				
				while(isRun){
					
					if(isStart){
						Thread.sleep(3000);
						System.out.println("Starting fish... ");
						send(SPACE);
						isStart=false; isBegin=true;
						Thread.sleep(5000);
						useBear();
						
					}else if(isBegin){
						System.out.println("Wait fish...");
						Thread.sleep(3000);
						boolean isSend =  new Region(ImageType.SPACE).send(port);
						if(isSend){ isBegin= false; isSubLine= true; }
						
					}else if(isSubLine){
						System.out.println("Cut the fish...");
						Thread.sleep(1200);
						send(ImageType.SPACE.toString());
						isSubLine=false;
						isKapcha=true;
						
					}else if(isKapcha){
						Thread.sleep(3920);
						try{
							System.out.println("Start parsing kapcha...");
							Kapcha kapcha = new Kapcha();
							kapcha.clearNoises(20);
							kapcha.send(port);
						}catch (Exception e){ 
							isStart=true; isKapcha=false;
						}
						
						isKapcha=false; isLootFilter=true;
					}else if(isLootFilter){
						Thread.sleep(5000);
						System.out.println("Check loot...");
						new FishLoot().send(port);
						isLootFilter=false; isStart= true;
					}
				}
			}
			
			} catch (Exception e) {
				e.printStackTrace();
				port.closePort();
			}
			
			port.closePort();
			System.out.println("Thread stopped...");
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