package ru.namibios.arduino.model.command;

import java.awt.AWTException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import ru.namibios.arduino.config.Application;
import ru.namibios.arduino.model.ImageParser;
import ru.namibios.arduino.model.Screen;
import ru.namibios.arduino.model.Touch;
import ru.namibios.arduino.model.template.Loot;
import ru.namibios.arduino.utils.Keyboard;

public class FishLoot implements Command{
	
	private final static Touch[] TOUCHS = {
							new Touch(1561, 616),
							new Touch(1608, 616)
	};
	
	final static Logger logger = Logger.getLogger(FishLoot.class);

	private List<Screen> scrins;
	private Screen one;
	private Screen two;
	
	private ImageParser imageParser;
	
	private List<Integer> userLootOk;
	private List<Integer> userLootTrash;
	
	public FishLoot(String fileLootOne, String fileLootTwo) throws IOException {
		this.scrins = new ArrayList<>();
		this.scrins.add(new Screen(fileLootOne));
		this.scrins.add(new Screen(fileLootTwo));
		
		this.userLootOk = new ArrayList<Integer>();
		this.userLootTrash = new ArrayList<Integer>();
		addLootList();
	}
	
	public FishLoot() throws AWTException {
		this.scrins = new ArrayList<>();
		
		this.one = new Screen(Screen.LOOT_SLOT_ONE);
		this.one.saveImage("loot/unsort");
		
		this.two = new Screen(Screen.LOOT_SLOT_TWO);
		this.two.saveImage("loot/unsort");
		
		this.scrins.add(one);
		this.scrins.add(two);
		
		this.userLootOk = new ArrayList<Integer>();
		this.userLootTrash = new ArrayList<Integer>();
		addLootList();
	}
	
	private void addLootList() {
		if(Application.getInstance().ROCK())  userLootOk.add(Loot.SCALA.ordinal());
		if(Application.getInstance().KEY())   userLootOk.add(Loot.KEY.ordinal());
		if(Application.getInstance().FISH())  userLootOk.add(Loot.FISH.ordinal());
		if(Application.getInstance().EVENT()) userLootOk.add(Loot.EVENT.ordinal());
		
		if(Application.getInstance().TRASH()) userLootTrash.add(Loot.TRASH.ordinal());
	}
	
	@Override
	public String getKey(){
		
		String[] arrayLoots = getLootIndices();
		
		return getComamnd(arrayLoots);
	
	}
	
	private String getComamnd(String[] arrayLoots) {

		int firstSlot = Integer.parseInt(arrayLoots[0]);
		int secondSlot =Integer.parseInt(arrayLoots[1]);
		
		LootType firstIndex = initLootIndex(0, firstSlot);
		LootType secondIndex = initLootIndex(1, secondSlot);
		
		boolean isTakeAll =  ( (firstIndex.isOk() && secondIndex.isOk()) || 
						   	   (firstIndex.isOk() && secondIndex.isEmpty())
		   	    );			
		
		boolean isIgnoreAll = ( (firstIndex.isTrash() && secondIndex.isEmpty()) ||
								(firstIndex.isTrash() && secondIndex.isTrash())
				);
		
		boolean isTakeByIndex = ( (firstIndex.isOk() && secondIndex.isTrash() ) ||
								  (firstIndex.isTrash() && secondIndex.isOk() )
				);
		
		if(isTakeAll) {
			logger.info("Loot ok. Take all");
			return Keyboard.Keys.TAKE;
		}
		
		if (isIgnoreAll){
			logger.info("Trash. Throw out all");
			return Keyboard.Keys.IGNORE;
		}
		
		if(isTakeByIndex){
			logger.info("Give loot by index");
			Touch loot = getTouchIndex(firstIndex, secondIndex);
			return "Loot" + loot;
		}
		
		boolean isTakeUnknow = Application.getInstance().TAKE_UNKNOWN();
		if(isTakeUnknow) {
			logger.info("Loot unknow. Take..");
			return Keyboard.Keys.TAKE;
		}else {
			logger.info("Loot unknow. Not take..");
			return Keyboard.Keys.IGNORE;
		}
		
	}

	private Touch getTouchIndex(LootType firstIndex, LootType secondIndex) {
		if(firstIndex.isOk()) return TOUCHS[firstIndex.getIndex()];
		if(secondIndex.isOk()) return TOUCHS[secondIndex.getIndex()];
		
		return null;
	}

	private LootType initLootIndex(int index, int slot) {
		LootType lootIndex = new LootType(index);
		
		for (Integer okIndex : userLootOk) {
			if(slot == okIndex) lootIndex.setOk(true);
		}
		
		for (Integer trashIndex : userLootTrash) {
			if(slot == trashIndex) lootIndex.setTrash(true);
		}
		
		if (slot  == -1) lootIndex.setUnknow(true);
		
		if(slot == Loot.EMPTY.ordinal()) lootIndex.setEmpty(true);
		
		return lootIndex;
	}

	private String[] getLootIndices() {
		String loots = "";
		for (Screen screen : scrins) {
			imageParser = new ImageParser(screen, Loot.values());
			imageParser.parse(Screen.GRAY);
			loots+= imageParser.getKey();
		}
		return loots.split(",");
	}
	
	public Screen getOne() {
		return one;
	}

	public Screen getTwo() {
		return two;
	}
	
	public static void main(String[] args) throws IOException {
		
		FishLoot okEmpty = new FishLoot("resources/loot/ok/scala/scala.jpg", "resources/loot/ok/empty/empty.jpg");
		okEmpty.getKey();
		
		System.out.println();
		
		FishLoot okOk = new FishLoot("resources/loot/ok/scala/scala.jpg", "resources/loot/ok/scala/scala.jpg");
		okOk.getKey();
		
		System.out.println();
		
		FishLoot trashOk = new FishLoot("resources/loot/ok/scala/scala.jpg", "resources/loot/trash/fishnet.jpg");
		trashOk.getKey();
		
		System.out.println();
		
		FishLoot trashTrash = new FishLoot("resources/loot/trash/rope.jpg", "resources/loot/trash/rope.jpg");
		trashTrash.getKey();
		
	}
	
}