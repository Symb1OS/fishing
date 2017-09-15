package ru.namibios.arduino.model;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import org.apache.log4j.Logger;

import ru.namibios.arduino.model.ImageParser.ImageType;
import ru.namibios.arduino.utils.DateUtils;
import ru.namibios.arduino.utils.Path;

public class Screen { 
	
	final static Logger logger = Logger.getLogger(Screen.class);
	
	private static final String PATH_IMPORT = "resources/kapcha/";
	private static final String PATH_EXPORT = "resources/changes/";
	
	private static final int SPACE_X = 928;
	private static final int SPACE_Y = 194;
	private static final int SPASE_W = 63;
	private static final int SPACE_H = 25;
	
	private static final int LINE_X = 820;
	private static final int LINE_Y = 402;
	private static final int LINE_W = 278;
	private static final int LINE_H = 25;

	private static final int SUB_LINE_X = 997;
	private static final int SUB_LINE_Y = 402;
	private static final int SUB_LINE_W = 10;
	private static final int SUB_LINE_H = 25;

	/*		
	private static final int SUB_KAPCHA_X = 780;
	private static final int SUB_KAPCHA_Y = 360;
	private static final int SUB_KAPCHA_W = 372;
	private static final int SUB_KAPCHA_H = 40;*/
	
	private static final int LOOT_X = 1537;
	private static final int LOOT_Y = 592;
	private static final int LOOT_W = 47;
	private static final int LOOT_H = 48;
	
	private static final int LOOT_TWO_X = 1584;
	private static final int LOOT_TWO_Y = 592;
	private static final int LOOT_TWO_W = 47;
	private static final int LOOT_TWO_H = 48;
	
	private static final int KAPCHA_X = 780;
	private static final int KAPCHA_Y = 350;
	private static final int KAPCHA_W = 372;
	private static final int KAPCHA_H = 58;
	
	private BufferedImage screenShot;
	
	private Noise noise;
	
	public Screen(String filename, ImageType type) throws IOException {
		
		File fimport = new File(PATH_IMPORT + filename);
		File fexport = new File(PATH_EXPORT + filename);
		BufferedImage img = ImageIO.read(fimport);
		switch (type) {
			case SPACE:	    	screenShot = img.getSubimage(SPACE_X, SPACE_Y, SPASE_W, SPACE_H); 			   break;
			case LINE:	    	screenShot = img.getSubimage(LINE_X, LINE_Y, LINE_W, LINE_H); 			       break;
			case KAPCHA:    	screenShot = img.getSubimage(KAPCHA_X, KAPCHA_Y, KAPCHA_W, KAPCHA_H);          break;
			case FISH_LOOT_ONE: screenShot = img.getSubimage(LOOT_X, LOOT_Y, LOOT_W, LOOT_H);                  break;
			case FISH_LOOT_TWO: screenShot = img.getSubimage(LOOT_TWO_X, LOOT_TWO_Y, LOOT_TWO_W, LOOT_TWO_H);  break;
			default:	   		logger.error("Unkow type. Exit"); System.exit(1); 
		}
		
		makeGray();
		noise = new Noise(screenShot);
		ImageIO.write(screenShot, "JPG", fexport );
	}
	
	public Screen(String filename) throws IOException{
		this.screenShot = ImageIO.read(new File(filename));
	}
	
	private Rectangle getRectangle(ImageType type){
		switch (type) {
			case LINE: 	 		return new Rectangle(LINE_X, LINE_Y, LINE_W, LINE_H);
			case SUBLINE:       return new Rectangle(SUB_LINE_X, SUB_LINE_Y, SUB_LINE_W, SUB_LINE_H);
			case KAPCHA: 		return new Rectangle(LINE_X, LINE_Y, LINE_W, LINE_H);
			case SPACE:  		return new Rectangle(SPACE_X, SPACE_Y, SPASE_W, SPACE_H);
			case FISH_LOOT_ONE: return new Rectangle(LOOT_X, LOOT_Y, LOOT_W, LOOT_H);
			case FISH_LOOT_TWO: return new Rectangle(LOOT_TWO_X, LOOT_TWO_Y, LOOT_TWO_W, LOOT_TWO_H);
			default: 			logger.error("Unknow region... Exit"); System.exit(1); 		
		}
		return null;
	}
	
	public Screen(ImageType type) throws AWTException{
		Robot robot = new Robot();
		Rectangle rectangle = getRectangle(type);
		screenShot = robot.createScreenCapture(rectangle); 
		
		makeGray();
		noise = new Noise(screenShot);
	}
	
	public Screen() throws AWTException{
		Robot robot = new Robot();
		screenShot = robot.createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
	}
	
	public void addNoise(BufferedImage image){
		noise.addNois(image);
	}
	
	public void clear(){
		screenShot = noise.clear();
	}
	
	public void saveDebugImage(){
		try {
			ImageIO.write(screenShot, "jpg", new File(Path.RESOURCES_DEBUG + DateUtils.getYYYY_MM_DD_HH_MM_SS_S() + ".jpg"));
		} catch (IOException e) {
			logger.error("Exception " + e);
		}
	}
	
	public void saveImage(String folder) {
		
		try {
			ImageIO.write(screenShot, "jpg", new File(Path.RESOURCES + folder + "/" + DateUtils.getYYYY_MM_DD_HH_MM_SS_S() + ".jpg"));
		} catch (IOException e) {
			logger.error("Exception " + e);
		}
	}
	
	public BufferedImage getImage(){
		return this.screenShot;
	}
	
	public void makeGray(){
	    for (int x = 0; x < screenShot.getWidth(); ++x)
	    for (int y = 0; y < screenShot.getHeight(); ++y)
	    {
	        int rgb = screenShot.getRGB(x, y);
	        int r = (rgb >> 16) & 0xFF;
	        int g = (rgb >> 8) & 0xFF;
	        int b = (rgb & 0xFF);

	        int grayLevel = (r + g + b) / 3;
	        int gray = (grayLevel << 16) + (grayLevel << 8) + grayLevel; 
	        screenShot.setRGB(x, y, gray);
	    }
	}
	
	public class Noise {
		
		private BufferedImage image;
		
		private int width;
		
		private int height;
		
		private List<BufferedImage> noises;
		
		public Noise(BufferedImage image) {
			this.image = image;
			this.height = this.image.getHeight();
			this.width = this.image.getWidth();
			this.noises = new ArrayList<>();
		}
		
		public void addNois(BufferedImage image){
			this.noises.add(image);
		}
		
		public BufferedImage clear(){
			
			for (BufferedImage bufferedImage : noises) {
				for (int i = 0; i < height; i++) {
					for (int j = 0; j < width; j++) {
					Color color = new Color(image.getRGB(j, i));
					Color noiseColor = new Color(bufferedImage.getRGB(j, i));
					boolean isOldColor = color.getRGB() < noiseColor.getRGB();
					if(isOldColor){
						image.setRGB(j, i, color.getRGB());
					}else{
						image.setRGB(j, i, noiseColor.getRGB());
					} 
						
					}
				}
			}
			return image;
		}
		
	}
}