package ru.namibios.arduino.model;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.PrintWriter;

import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;

import com.fazecast.jSerialComm.SerialPort;

import ru.namibios.arduino.ImageType;
import ru.namibios.arduino.Screen;
import ru.namibios.arduino.utils.Http;

public class Kapcha {

	final static Logger logger = Logger.getLogger(Kapcha.class);

	private static final int GRAY = 40;
	
	private Screen screen;
	
	public Kapcha() throws Exception {
		this.screen = new Screen(ImageType.KAPCHA);
	}
	
	private int[][] getMatrix(){
		
		BufferedImage image = screen.getImage();
		int row = image.getHeight();
		int column = image.getWidth();
		int[][]imageMatrix = new int[row][column];
		
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < column; j++) {
				Color color = new Color(image.getRGB(j, i));
				boolean isGray = (color.getRed() > GRAY && color.getGreen() > GRAY && color.getBlue() > GRAY);
				
				imageMatrix[i][j] = isGray ? 1 : 0;
			}
		}
		return imageMatrix;
	}
	
	private String getKey(String hash){
		
		ObjectMapper mapper= new ObjectMapper();
		int[][] matrix = getMatrix();

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
		screen.saveDebugImage();
		logger.info("Clean ended...");
	}
	
}
