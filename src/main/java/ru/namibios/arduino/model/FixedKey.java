package ru.namibios.arduino.model;

import ru.namibios.arduino.utils.Keyboard.ArduinoSubTask;

public class FixedKey extends Region {
	
	private ArduinoSubTask key;
	
	public FixedKey(ArduinoSubTask key) {
		this.key = key;
	}
	
	@Override
	public String getKey() {
		return key.name();
	}

}