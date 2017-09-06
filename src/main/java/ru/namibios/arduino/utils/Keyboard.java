package ru.namibios.arduino.utils;

import java.io.PrintWriter;

import org.apache.log4j.Logger;

import ru.namibios.arduino.model.Property;

public class Keyboard {

	final static Logger logger = Logger.getLogger(Keyboard.class);
	
	public static final String TAKE = "r";
	public static final String IGNORE = "";
	
	public static void send(String message) {
		PrintWriter output = new PrintWriter(Property.portInstance().getOutputStream());
		output.println(message);
		output.flush();
		logger.info("Sended message: " + message);
		
	}
}
