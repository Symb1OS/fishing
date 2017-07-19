package ru.namibios.arduino.model;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import com.fazecast.jSerialComm.SerialPort;

import ru.namibios.arduino.ImageParser;
import ru.namibios.arduino.ImageType;
import ru.namibios.arduino.Screen;

public class FishLoot {

	private static final String TAKE = "9";
	
	private List<Screen> scrins;
	private Screen one;
	private Screen two;
	
	private ImageParser imageParser;
	
	public FishLoot() throws Exception {
		
		scrins = new ArrayList<>();
		
		one = new Screen(ImageType.FISH_LOOT_ONE);
		one.saveImage("loot");
		
		two = new Screen(ImageType.FISH_LOOT_TWO);
		two.saveImage("loot");
		
		scrins.add(one);
		scrins.add(two);
	}
	
	private String getKey(){
		
		boolean isOk= false;
		String loots= new String();
		for (Screen screen : scrins) {
			imageParser = new ImageParser(ImageType.FISH_LOOT, screen.getImage());
			imageParser.getCodes();
			loots+= imageParser.getkeyFromTemlate();
		}
		
		char[] array = loots.toCharArray();
		int length = array.length;
		boolean unknown = length == 0; 
		if(unknown){
			System.out.println("Loot is not recognized... Take.");
			return TAKE;
		} 
		
		for (int i = 0; i < length; i++) {
			isOk = isOk || array[i] == '0' || array[i] == '1' || array[i] == '2' || array[i] == '4';
		}
		
		if(isOk) System.out.println("Loot ok."); else System.out.println("Trash. Throw out.");
		
		return isOk ? TAKE : "";
	}
	
	public void send(SerialPort port){
		String key = getKey();
		
		if(!key.isEmpty()){
			PrintWriter output = new PrintWriter(port.getOutputStream());
			output.println(key);
			output.flush();
			
			System.out.println("Sended message: " + key);
		}
	}
	
}