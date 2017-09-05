package ru.namibios.arduino.model;

import com.fazecast.jSerialComm.SerialPort;

public class Property {

	private Property() { }
	
	private static SerialPort port;

	private static String hash;

	private static boolean bear;
	private static boolean minigame;

	private static boolean rock;
	private static boolean fish;
	private static boolean keys;
	private static boolean event;

	public static String getHash() {
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

}