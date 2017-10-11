package ru.namibios.arduino.config;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.aeonbits.owner.ConfigFactory;

public class Application {

	private static final String PROPERTY_FILE_NAME = "application.properties";
	
	private static ApplicationConfig config;
	
	private Application() {}
	
	public static ApplicationConfig getInstance() {
		if(config == null) {
			config = ConfigFactory.create(ApplicationConfig.class);
		}
		return config;
	}
	
	public static void record() throws FileNotFoundException, IOException {
		Application.config.store(new FileOutputStream(new File(PROPERTY_FILE_NAME)), "");
	}
}