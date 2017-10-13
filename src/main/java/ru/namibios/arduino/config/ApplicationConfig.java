package ru.namibios.arduino.config;

import org.aeonbits.owner.Accessible;
import org.aeonbits.owner.Config.Sources;

import com.fazecast.jSerialComm.SerialPort;

import ru.namibios.arduino.utils.SerialPortConverter;

import org.aeonbits.owner.Mutable;

@Sources("file:application.properties")
public interface ApplicationConfig extends Accessible, Mutable{
	
	String HASH();
	
	String PORT();
	
	@DefaultValue("${PORT}")
	@ConverterClass(SerialPortConverter.class)
	SerialPort physicalPort();
	
	@DefaultValue("true")
	boolean FISH();
	
	@DefaultValue("true")
	boolean KEY();
	
	@DefaultValue("true")
	boolean ROCK();
	
	@DefaultValue("true")
	boolean EVENT();
	
	@DefaultValue("false")
	boolean TRASH();
	
	@DefaultValue("true")
	boolean BEER();
	
	@DefaultValue("false")
	boolean MINIGAME();
	
	@DefaultValue("3000")
	int DELAY_BEFORE_START();
	
	@DefaultValue("5000")
	int DELAY_AFTER_START();
	
	@DefaultValue("3000")
	int DELAY_BEFORE_WAIT_FISH();
	
	@DefaultValue("0")
	int DELAY_AFTER_WAIT_FISH();

	@DefaultValue("1800")
	int DELAY_BEFORE_CUT_FISH();
	
	@DefaultValue("3920")
	int DELAY_AFTER_CUT_FISH();

	@DefaultValue("0")
	int DELAY_BEFORE_KAPCHA();
	
	@DefaultValue("10000")
	int DELAY_AFTER_KAPCHA();

	@DefaultValue("5000")
	int DELAY_BEFORE_FILTER_LOOT();
	
	@DefaultValue("0")
	int DELAY_AFTER_FILTER_LOOT();
	
}