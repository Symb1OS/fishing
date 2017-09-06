package ru.namibios.arduino;

import java.awt.image.BufferedImage;
import java.io.PrintWriter;

import org.apache.log4j.Logger;

import ru.namibios.arduino.model.ImageType;
import ru.namibios.arduino.model.Property;

public class Region {

	final static Logger logger = Logger.getLogger(Region.class);

	private Screen screen;
	
	private ImageParser imageParser;
	
	public Region() {}
	
	public Region(ImageType type) throws Exception {
		this.screen = new Screen(type);
	
		BufferedImage image = screen.getImage();
		this.imageParser = new ImageParser(type, image);
		imageParser.getCodes();
	}
	
	public String getKey(){
		return imageParser.getNumberkeyFromTemlate();
	}
	
	public boolean send(){
		String message = getKey().trim();
		boolean status = false;
		if(!message.isEmpty()){
			PrintWriter output = new PrintWriter(Property.portInstance().getOutputStream());
			output.println(message);
			output.flush();
			
			logger.info("Sended message: " + message);
			status = true;
		}
		return status;
	}
	
}