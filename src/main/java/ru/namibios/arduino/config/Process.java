package ru.namibios.arduino.config;

public enum Process {

	START, WAIT_FISH, PARSE_LINE, PARSE_KAPCHA, FILTER_LOOT;
	
	private static Process process;
	
	public static Process getInstance() {
		return process;
	}
	
	public static void initStart() {
		process = START;
	}
	
	public static void next() {
		int index = process.ordinal();
		process = index < maxLength() ? Process.values()[index + 1] : Process.START;
	}
	
	private static int maxLength(){
		return Process.values().length - 1;
	}
	
}
