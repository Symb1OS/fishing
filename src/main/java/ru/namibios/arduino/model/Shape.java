package ru.namibios.arduino.model;

import java.awt.image.BufferedImage;
import java.io.PrintWriter;

import com.fazecast.jSerialComm.SerialPort;

import ru.namibios.arduino.ImageParser;
import ru.namibios.arduino.ImageType;
import ru.namibios.arduino.Screen;

public class Shape {

	private Screen screen;
	
	private ImageParser imageParser;
	
	public Shape(ImageType type) throws Exception {
		this.screen = new Screen(type);
	
		BufferedImage image = screen.getImage();
		this.imageParser = new ImageParser(type, image);
		imageParser.getMatrix();
	}
	
	private String getKey(){
		return imageParser.getkeyFromTemlate();
	}
	
	public boolean send(SerialPort port){
		String message = getKey();
		boolean status = false;
		String key = imageParser.getkeyFromTemlate();
		if(!key.equals("-1")){
			PrintWriter output = new PrintWriter(port.getOutputStream());
			output.println(message);
			output.flush();
			
			System.out.println("Sended message: " + message);
			status = true;
		}
		return status;
	}
}
