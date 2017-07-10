package ru.namibios.arduino;

import java.io.PrintWriter;

import com.fazecast.jSerialComm.SerialPort;

import ru.namibios.arduino.model.Shape;
import ru.namibios.arduino.model.Space;
import ru.namibios.arduino.model.SubLine;

public class Transfer implements Runnable{ 

	private static SerialPort choosenPort;
	
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
		
		choosenPort = SerialPort.getCommPort(port);
		choosenPort.setComPortTimeouts(SerialPort.TIMEOUT_SCANNER, 0, 0);
	}
	
	private void send(String message){
		System.out.println("Sended message: " + message);
		PrintWriter output = new PrintWriter(choosenPort.getOutputStream());
		output.println(message);
		output.flush();
	}
	
	public void run() {
		System.out.println("Thread start...");
		isRun = true;
		
		try{
			
			if(choosenPort.openPort()) {
			
				while(true){
					
					if(isBegin){
						Thread.sleep(5000);
						String key = new Shape(ImageType.SPACE).getKey();
						if(!key.equals("-1")){
							send(key);
							isBegin= false; 
							isSubLine= true;
						} 
					}else if(isSubLine){
						String key = new Shape(ImageType.SUBLINE).getKey();
						if(!key.equals("-1")){
							send(key);
							isSubLine= false;
							isKapcha= true;
						}
					}else if(isKapcha) break;
					
				}
				
			}
			
			} catch (Exception e) {
				e.printStackTrace();
				choosenPort.closePort();
			}
			
			choosenPort.closePort();
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