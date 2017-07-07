package ru.namibios.arduino;

import java.awt.image.BufferedImage;
import java.io.PrintWriter;

import com.fazecast.jSerialComm.SerialPort;

public class Transfer implements Runnable{ 

	private static SerialPort choosenPort;
	
	private boolean isRun;
	
	private boolean isBegin;
	private boolean isLine;
	private boolean isKapcha;
	
	public Transfer(String port) {
		
		isBegin = true;
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
	
	private String parse(ImageType imageType) throws Exception{
		System.out.println("Parsing proccess..");
		Screen screen = new Screen("20170705_214629.jpg");
		screen.getSubImage(imageType);
		BufferedImage image = screen.getImage();
		
		ImageParser imageParser = new ImageParser(imageType, image);
		imageParser.getMatrix();
		return imageParser.getkeyFromTemlate();
	}
	
	public void run() {
		System.out.println("Thread start...");
		isRun = true;
		
		try{
			if(choosenPort.openPort()) {
			
				String message = null;
				if(isBegin){
					Thread.sleep(5000);
					message = parse(ImageType.SPACE);					
					isBegin = false;
					isLine = true;
				}
				if(isLine){
					message = parse(ImageType.SUBLINE);
					isLine = false;
					isKapcha = true;
				} 
				if(isKapcha){
					message  = parse(ImageType.KAPCHA);
					isKapcha = false;
					isBegin = true;
				}
				
				send(message);
				
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