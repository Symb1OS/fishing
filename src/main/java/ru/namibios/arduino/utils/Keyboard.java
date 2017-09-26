package ru.namibios.arduino.utils;

import java.io.PrintWriter;

import org.apache.log4j.Logger;

import ru.namibios.arduino.config.Property;
import ru.namibios.arduino.model.command.Command;

public final class Keyboard {
	
	final static Logger logger = Logger.getLogger(Keyboard.class);
	
	public final class Keys {
		
		public static final String TAKE = "r";
		public static final String IGNORE = "";
		
		public static final String SPACE = "space";
		public static final String BEER = "beer";
		public static final String MINIGAME = "minigame";
		public static final String FIRST_DINNER = "dinner1";
		public static final String SECOND_DINNER = "dinner2";
		
	}
	
	public static boolean send(Command command) {
		String message = command.getKey().trim();
		boolean status = false;
		if(!message.isEmpty()){
			PrintWriter output = new PrintWriter(Property.portInstance().getOutputStream());
			output.println(message);
			output.flush();
			
			status = true;
		}
		logger.info("Sended message: " + message);
		return status;
	}
	
}