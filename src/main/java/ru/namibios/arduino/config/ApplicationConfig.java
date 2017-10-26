package ru.namibios.arduino.config;

import org.aeonbits.owner.Accessible;
import org.aeonbits.owner.Config.Sources;
import org.aeonbits.owner.Mutable;

import com.fazecast.jSerialComm.SerialPort;

import ru.namibios.arduino.utils.SerialPortConverter;

@Sources("file:resources/application.properties")
public interface ApplicationConfig extends Accessible, Mutable{
	
	@Key("bot.key")
	String HASH();
	
	@Key("bot.port")
	String PORT();
	
	@Key("bot.rod.count")
	@DefaultValue("0")
	int COUNT_ROD();
	
	@Key("bot.rod.changetime")
	@DefaultValue("180000")
	int TIME_CHANGE_ROD();
	
	@Key("bot.delay.rod.before")
	@DefaultValue("0")
	int DELAY_BEFORE_CHANGE_ROD();
	
	@Key("bot.delay.rod.after")
	@DefaultValue("15000")
	int DELAY_AFTER_CHANGE_ROD();
	
	@DefaultValue("50")
	@Key("bot.kapcha.noise.iteration")
	int CNT_KAPCHA();
	
	@Key("bot.port.serial")
	@DefaultValue("${bot.key}")
	@ConverterClass(SerialPortConverter.class)
	SerialPort physicalPort();
	
	@Key("bot.loot.fish")
	@DefaultValue("true")
	boolean FISH();
	
	@Key("bot.loot.key")
	@DefaultValue("true")
	boolean KEY();
	
	@Key("bot.loot.rock")
	@DefaultValue("true")
	boolean ROCK();
	
	@Key("bot.loot.event")
	@DefaultValue("true")
	boolean EVENT();
	
	@Key("bot.loot.trash")
	@DefaultValue("false")
	boolean TRASH();
	
	@Key("bot.loot.unknown")
	boolean TAKE_UNKNOWN();
	
	@Key("bot.autouse.beer")
	@DefaultValue("true")
	boolean BEER();
	
	@Key("bot.autouse.minigame")
	@DefaultValue("false")
	boolean MINIGAME();
	
	@Key("bot.delay.start.before")
	@DefaultValue("3000")
	int DELAY_BEFORE_START();
	
	@Key("bot.delay.start.after")
	@DefaultValue("5000")
	int DELAY_AFTER_START();
	
	@Key("bot.delay.waitfish.before")
	@DefaultValue("3000")
	int DELAY_BEFORE_WAIT_FISH();
	
	@Key("bot.delay.waitfish.after")
	@DefaultValue("0")
	int DELAY_AFTER_WAIT_FISH();

	@Key("bot.delay.cutfish.before")
	@DefaultValue("0")
	int DELAY_BEFORE_CUT_FISH();
	
	@Key("bot.delay.cutfish.after")
	@DefaultValue("0")
	int DELAY_AFTER_CUT_FISH();
	
	@Key("bot.delay.statuscut.before")
	@DefaultValue("0")
	int DELAY_BEFORE_STATUS_CUT();
	
	@Key("bot.delay.statuscut.after")
	@DefaultValue("0")
	int DELAY_AFTER_STATUS_CUT();

	@Key("bot.delay.kapcha.before")
	@DefaultValue("0")
	int DELAY_BEFORE_KAPCHA();
	
	@Key("bot.delay.kapcha.after")
	@DefaultValue("10000")
	int DELAY_AFTER_KAPCHA();
	
	@Key("bot.delay.statuskapcha.before")
	@DefaultValue("0")
	int DELAY_BEFORE_STATUS_KAPCHA();
	
	@Key("bot.delay.statuskapcha.after")
	@DefaultValue("0")
	int DELAY_AFTER_STATUS_KAPCHA();

	@Key("bot.delay.filterloot.before")
	@DefaultValue("5000")
	int DELAY_BEFORE_FILTER_LOOT();
	
	@Key("bot.delay.filterloot.after")
	@DefaultValue("0")
	int DELAY_AFTER_FILTER_LOOT();
	
	@Key("bot.notification.telegram")
	@DefaultValue("false")
	boolean TELEGRAM();
	
	@Key("bot.notification.telegram.key")
	String TELEGRAM_KEY();
	
}