package ru.namibios.arduino.model;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import ru.namibios.arduino.config.Property;
import ru.namibios.arduino.model.command.FishLoot;
import ru.namibios.arduino.utils.Keyboard;

public class FishLootTest {
	
	@Before
	public void init() {
		Property.setKeys(true);
		Property.setEvent(true);
		Property.setFish(true);
		Property.setRock(true);
	}
	
	@Test
	public void testOk() throws IOException {

		String slotOne = "resources/test/loot/ersh.jpg";
		String slotTwo = "resources/test/loot/losos.jpg";
	
		FishLoot fishLoot = new FishLoot(slotOne, slotTwo);
		String key = fishLoot.getKey();
		
		assertEquals(key, Keyboard.Keys.TAKE);
	}
	
	@Test
	public void testTrash() throws IOException {
		
		String slotOne = "resources/test/loot/gorchak.jpg";
		String slotTwo = "resources/test/loot/boot.jpg";
	
		FishLoot fishLoot = new FishLoot(slotOne, slotTwo);
		String key = fishLoot.getKey();
		
		assertEquals(key, Keyboard.Keys.IGNORE);
	}
	
}
