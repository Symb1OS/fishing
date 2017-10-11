package ru.namibios.arduino.config;

import com.fazecast.jSerialComm.SerialPort;

public class Property {
	
	public static final int DELAY_BEFORE_START = 3000;
	public static final int DELAY_AFTER_START = 5000;

	public static final int DELAY_BEFORE_WAIT_FISH = 3000;
	public static final int DELAY_AFTER_WAIT_FISH = 0;

	public static final int DELAY_BEFORE_CUT_FISH = 1800;
	public static final int DELAY_AFTER_CUT_FISH = 3920;

	public static final int DELAY_BEFORE_KAPCHA = 0;
	public static final int DELAY_AFTER_KAPCHA= 10000;

	public static final int DELAY_BEFORE_FILTER_LOOOT = 5000;
	public static final int DELAY_AFTER_FILTER_LOOOT= 0;

	private Property() { }
	
	private static SerialPort port;

	private static String hash;

	private static boolean bear;
	private static boolean minigame;

	private static boolean rock;
	private static boolean fish;
	private static boolean keys;
	private static boolean event;
	private static boolean trash;

	public static String hashInstance() {
		return hash;
	}

	public static void setHash(String hash) {
		Property.hash = hash;
	}

	public static boolean isBear() {
		return bear;
	}

	public static void setBear(boolean bear) {
		Property.bear = bear;
	}

	public static boolean isMinigame() {
		return minigame;
	}

	public static void setMinigame(boolean minigame) {
		Property.minigame = minigame;
	}

	public static boolean isRock() {
		return rock;
	}

	public static void setRock(boolean rock) {
		Property.rock = rock;
	}

	public static boolean isFish() {
		return fish;
	}

	public static void setFish(boolean fish) {
		Property.fish = fish;
	}

	public static boolean isKeys() {
		return keys;
	}

	public static void setKeys(boolean keys) {
		Property.keys = keys;
	}

	public static boolean isEvent() {
		return event;
	}

	public static void setEvent(boolean event) {
		Property.event = event;
	}

	public static SerialPort portInstance() {
		return port;
	}

	public static void setPort(SerialPort port) {
		Property.port = port;
	}

	public static boolean isTrash() {
		return trash;
	}

	public static void setTrash(boolean trash) {
		Property.trash = trash;
	}
}