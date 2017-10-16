package ru.namibios.arduino.model.command;

import java.awt.AWTException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

import org.apache.log4j.Logger;

import ru.namibios.arduino.Gui;
import ru.namibios.arduino.config.Application;
import ru.namibios.arduino.model.ImageParser;
import ru.namibios.arduino.model.Screen;
import ru.namibios.arduino.model.template.Loot;
import ru.namibios.arduino.utils.Keyboard;

public class FishLoot implements Command, Reloader{
	
	final static Logger logger = Logger.getLogger(FishLoot.class);

	private List<Screen> scrins;
	private Screen one;
	private Screen two;
	
	private ImageParser imageParser;
	
	private List<Integer> userLootOk;
	
	public FishLoot(String fileLootOne, String fileLootTwo) throws IOException {
		this.scrins = new ArrayList<>();
		this.scrins.add(new Screen(fileLootOne));
		this.scrins.add(new Screen(fileLootTwo));
		
		this.userLootOk = new ArrayList<Integer>();
		addLootOk();
	}
	
	public FishLoot() throws AWTException {
		this.scrins = new ArrayList<>();
		
		this.one = new Screen(Screen.LOOT_SLOT_ONE);
		this.one.saveImage("loot");
		
		this.two = new Screen(Screen.LOOT_SLOT_TWO);
		
		this.scrins.add(one);
		this.scrins.add(two);
		
		this.userLootOk = new ArrayList<Integer>();
		addLootOk();
	}
	
	private void addLootOk() {
		if(Application.getInstance().ROCK())  userLootOk.add(Loot.SCALA.ordinal());
		if(Application.getInstance().KEY())   userLootOk.add(Loot.KEY.ordinal());
		if(Application.getInstance().FISH())  userLootOk.add(Loot.FISH.ordinal());
		if(Application.getInstance().EVENT()) userLootOk.add(Loot.EVENT.ordinal());
	}
	
	@Override
	public String getKey(){
		
		char[] arrayLoots = getLootIndices();
		
		if(lootUnknow(arrayLoots)) return Keyboard.Keys.TAKE;
		
		boolean isApproved = checkLoot(arrayLoots);
		
		if(isApproved) { logger.info("Loot ok."); 		   return Keyboard.Keys.TAKE;} 
			  else     { logger.info("Trash. Throw out."); return Keyboard.Keys.IGNORE; } 
	}

	private boolean lootUnknow(char[] arrayLoots) {
		boolean unknown = (arrayLoots.length == 0); 
		if(unknown){
			logger.info("Loot is not recognized... Take.");
			one.saveImage("loot/unknow");
		} 
		return unknown;
	}

	private char[] getLootIndices() {
		String loots = "";
		for (Screen screen : scrins) {
			imageParser = new ImageParser(screen, Loot.values());
			imageParser.parse(Screen.GRAY);
			loots+= imageParser.getkey();
		}
		return loots.toCharArray();
	}

	private boolean checkLoot(char[] array) {
		boolean isOk = false;
		for (int okIndex : userLootOk) {
			
			for (int i = 0; i < array.length; i++) {
				int lootIndex = Character.getNumericValue(array[i]);
				isOk = isOk || okIndex == lootIndex;
			}
		}
		return isOk;
	}

	@Override
	public void reloadGui() {
		Gui.lLootImgOne.setIcon(new ImageIcon(one.getScreenShot()));
		Gui.lLootImgTwo.setIcon(new ImageIcon(two.getScreenShot()));
	}
	
}