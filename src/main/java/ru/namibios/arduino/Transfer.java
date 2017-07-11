package ru.namibios.arduino;

import com.fazecast.jSerialComm.SerialPort;

import ru.namibios.arduino.model.Kapcha;
import ru.namibios.arduino.model.Shape;

public class Transfer implements Runnable{ 

	private static SerialPort port;
	
	private boolean isRun;
	
	private boolean isBegin;
	private boolean isLine;
	private boolean isSubLine;
	private boolean isKapcha;

	public Transfer(String port) {
		
		isBegin = true;
		isSubLine = false;
		isLine = false;
		isKapcha = false;
		
		this.port = SerialPort.getCommPort(port);
		this.port.setComPortTimeouts(SerialPort.TIMEOUT_SCANNER, 0, 0);
	}
	
	public void run() {
		System.out.println("Thread start...");
		isRun = true;
	
		try{
			
			if(port.openPort()) {
			
				while(isRun){
					
					if(isBegin){
						Thread.sleep(5000);
						boolean isSend =  new Shape(ImageType.SPACE).send(port);
						if(isSend){ isBegin= false; isSubLine= true; }
						
					}else if(isSubLine){
						boolean isSend = new Shape(ImageType.SUBLINE).send(port);
						if(isSend){	isSubLine = false; isKapcha=true;}
						
					}else if(isKapcha){
						Kapcha kapcha = new Kapcha();
						kapcha.clearNoises(15);
						kapcha.send(port);
						break;
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