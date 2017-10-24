package ru.namibios.arduino.model;

import java.util.ArrayList;
import java.util.List;

import ru.namibios.arduino.config.Application;
import ru.namibios.arduino.model.template.Loot;

public class Looter {

	private List<LootType> lootTypeList;
	
	private List<Integer> lootOk;
	private List<Integer> lootTrash;
	
	private LootCount count;
	
	public Looter(String[] slots) {
		
		this.lootOk = new ArrayList<>();
		this.lootTrash = new ArrayList<>();
		this.lootTypeList = new ArrayList<>();
		
		if(Application.getInstance().ROCK())  lootOk.add(Loot.SCALA.ordinal());
		if(Application.getInstance().KEY())   lootOk.add(Loot.KEY.ordinal());
		if(Application.getInstance().FISH())  lootOk.add(Loot.FISH.ordinal());
		if(Application.getInstance().EVENT()) lootOk.add(Loot.EVENT.ordinal());
		
		if(Application.getInstance().TRASH()) lootTrash.add(Loot.TRASH.ordinal());
		

		for (int index = 0; index < slots.length; index++) {
			
			int slot = Integer.parseInt(slots[index]);
		
			LootType lootType = new LootType(index);
			for (Integer okIndex : lootOk) {
				if(slot == okIndex) lootType.setOk(true);
			}
			
			for (Integer trashIndex : lootTrash) {
				if(slot == trashIndex) lootType.setTrash(true);
			}
			
			if (slot  == -1) lootType.setUnknow(true);
			
			if(slot == Loot.EMPTY.ordinal()) lootType.setEmpty(true);
			lootTypeList.add(lootType);
		}
		
		int length = lootTypeList.size();
		count = new LootCount(length);
		for(int index = 0; index < length; index++) {
			LootType lootType = lootTypeList.get(index);
			if(lootType.isOk()) count.incOk();;
			if(lootType.isEmpty()) count.incEmpty();
			if(lootType.isTrash()) count.incTrash();
			if(lootType.isUnknow()) count.incUnknow();
		}
	}
	
	public List<LootType> getLootTypeList() {
		return lootTypeList;
	}
	
	public boolean isTakeAll() {
		return ((count.getOk() > 0 && count.getEmpty() > 0) || (count.getOk() == count.getLength()));
	}
	
	public boolean isIgnoreAll(){
		return ((count.getTrash() > 0 && count.getEmpty() > 0) || (count.getTrash() == count.getLength()));
	}

	public boolean isTakeByIndex() {
		return (count.getOk() > 0 && count.getTrash() > 0);
	}
}