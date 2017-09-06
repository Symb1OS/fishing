package ru.namibios.arduino;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import ru.namibios.arduino.model.ImageType;
import ru.namibios.arduino.model.Loot;
import ru.namibios.arduino.model.Property;
import ru.namibios.arduino.utils.Keyboard;

public class FishLoot extends Region{
	
	final static Logger logger = Logger.getLogger(FishLoot.class);

	private List<Screen> scrins;
	private Screen one;
	private Screen two;
	
	private ImageParser imageParser;
	
	private List<Integer> userLootOk;
	
	public FishLoot() throws Exception {
		this.scrins = new ArrayList<>();
		
		this.one = new Screen(ImageType.FISH_LOOT_ONE);
		this.one.saveImage("loot");
		
		this.two = new Screen(ImageType.FISH_LOOT_TWO);
		
		this.scrins.add(one);
		this.scrins.add(two);
		
		this.userLootOk = new ArrayList<Integer>();
		if(Property.isRock())  userLootOk.add(Loot.SCALA.ordinal());
		if(Property.isKeys())  userLootOk.add(Loot.KEY.ordinal());
		if(Property.isFish())  userLootOk.add(Loot.FISH.ordinal());
		if(Property.isEvent()) userLootOk.add(Loot.EVENT.ordinal());
	}
	
	@Override
	public String getKey(){
		
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
			one.saveImage("loot/unknow");
			return Keyboard.TAKE;
		} 
		
		boolean isOk= false;
		for (int okIndex : userLootOk) {
			
			for (int i = 0; i < array.length; i++) {
				int lootIndex = Character.getNumericValue(array[i]);
				isOk = isOk || okIndex == lootIndex;
			}
		}
		
		if(isOk) {
			logger.info("Loot ok."); return Keyboard.TAKE;
		} else {
			logger.info("Trash. Throw out."); return Keyboard.IGNORE;
		} 
		
	}
	
}