package ru.namibios.arduino;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.PrintWriter;

import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;

import com.fazecast.jSerialComm.SerialPort;

import ru.namibios.arduino.model.ImageType;
import ru.namibios.arduino.utils.Http;

public class Kapcha {

	final static Logger logger = Logger.getLogger(Kapcha.class);

	private Screen screen;

	public Kapcha() throws Exception {
		this.screen = new Screen(ImageType.KAPCHA);
		
	}
	
	public Kapcha(String file) throws Exception{
		this.screen = new Screen(file);
				
	}
	
	private String getKey(String hash){
		
		ImageParser imageParser = new ImageParser(ImageType.KAPCHA, screen.getImage());
		imageParser.getCodes();
		
		int[][] matrix = imageParser.getImageMatrix();
		
		ObjectMapper mapper= new ObjectMapper();

		Http http = new Http();
		
		String keys = "";
		try {
			keys = http.parseKapcha(hash, mapper.writeValueAsString(matrix));
		} catch (IOException e) {logger.error("Exception: " + e); } 

		return keys;
	}
	
	public boolean send(String hash, SerialPort port){
		String message = getKey(hash);
		boolean status = false;
		if(!message.isEmpty()){
			PrintWriter output = new PrintWriter(port.getOutputStream());
			output.println(message);
			output.flush();
			
			logger.info("Sended message: " + message);
			status = true;
			
		}
		return status;
	}
	
	public void clearNoises(int iteration) throws Exception{
		logger.info("Clean the noise...");
		int cnt = 0;
		while(cnt < iteration){
			BufferedImage noiseImage = new Screen(ImageType.KAPCHA).getImage();
			screen.addNoise(noiseImage);
			cnt++;
		}
		screen.clear();
	 	//screen.saveDebugImage();
		logger.info("Clean ended...");
	}
	
	public static void main(String[] args) throws Exception {
		Kapcha kapcha = new Kapcha("resources/debug/6/20170711_235951_232.jpg");
		System.out.println("Key + " + kapcha.getKey("bef1c08eedddbe9f9d83a0f07d0d26ce9b360a55"));
	}
	
}
