package ru.namibios.arduino.model;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import ru.namibios.arduino.ImageParser;

public enum Loot {
	
	SCALA("resources/loot/ok/scala"),
	
	KEY("resources/loot/ok/key"),
	
	FISH("resources/loot/ok/fish"),
	
	TRASH("resources/loot/trash"),
	
	EVENT("resources/loot/ok/event");
	
	private final List<int[][]> templates;
	
	public List<int[][]> getTemplates() {
		return templates;
	}
	
	private Loot(String fileFolderName){
		this.templates = new ArrayList<int[][]>();
		
		File dir = new File(fileFolderName);
		for (File file : dir.listFiles()) {
			
			BufferedImage image = null;
			try{
				image = ImageIO.read(file);
			}catch(IOException e){
				e.printStackTrace();
			}
			
			ImageParser parser = new ImageParser(ImageType.FISH_LOOT, image);
			parser.getCodes();
			
			templates.add(parser.getImageMatrix());
			
		}
		
	}

}