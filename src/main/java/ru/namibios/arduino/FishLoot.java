package ru.namibios.arduino;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.fazecast.jSerialComm.SerialPort;

import ru.namibios.arduino.model.ImageType;
import ru.namibios.arduino.model.Loot;
import ru.namibios.arduino.model.Property;

public class FishLoot {
	
	final static Logger logger = Logger.getLogger(FishLoot.class);

	private static final String TAKE = "r";
	
	private List<Screen> scrins;
	private Screen one;
	private Screen two;
	
	private ImageParser imageParser;
	
	private List<Integer> userLootOk;
	
	public FishLoot(Property property) throws Exception {
		
		this.scrins = new ArrayList<>();
		
		this.one = new Screen(ImageType.FISH_LOOT_ONE);
		this.one.saveImage("loot");
		
		this.two = new Screen(ImageType.FISH_LOOT_TWO);
		
		this.scrins.add(one);
		this.scrins.add(two);
		
		this.userLootOk = new ArrayList<Integer>();
		if(property.isRock())  userLootOk.add(Loot.SCALA.ordinal());
		if(property.isKeys())  userLootOk.add(Loot.KEY.ordinal());
		if(property.isFish())  userLootOk.add(Loot.FISH.ordinal());
		if(property.isEvent()) userLootOk.add(Loot.EVENT.ordinal());
	}
	
	private String getKey(){
		
		String loots= new String();
		for (Screen screen : scrins) {
			imageParser = new ImageParser(ImageType.FISH_LOOT, screen.getImage());
			imageParser.getCodes();
			loots+= imageParser.getkeyFromTemlate();
		}
		
		char[] array = loots.toCharArray();
		int length = array.length;
		boolean unknown = (length == 0); 
		if(unknown){
			logger.info("Loot is not recognized... Take.");
			try{one.saveImage("loot/unknow");} catch(IOException i){logger.error("Exception: " + i);}
			return TAKE;
		} 
		
		boolean isOk= false;
		for (int okIndex : userLootOk) {
			
			for (int i = 0; i < array.length; i++) {
				int lootIndex = Character.getNumericValue(array[i]);
				isOk = isOk || okIndex == lootIndex;
			}
		}
		
		if(isOk) logger.info("Loot ok."); else logger.info("Trash. Throw out.");
		
		return isOk ? TAKE : "";
	}
	
	public void send(SerialPort port){
		String key = getKey();
		
		if(!key.isEmpty()){
			PrintWriter output = new PrintWriter(port.getOutputStream());
			output.println(key);
			output.flush();
			
			logger.info("Sended message: " + key);
		}
	}
	
}