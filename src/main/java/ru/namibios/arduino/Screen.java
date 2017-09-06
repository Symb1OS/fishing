package ru.namibios.arduino;

import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.log4j.Logger;

import ru.namibios.arduino.model.ImageType;
import ru.namibios.arduino.utils.DateUtils;

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
	
	public Screen(String filename, ImageType type) throws Exception{
		
		File fimport = new File(PATH_IMPORT + filename);
		File fexport = new File(PATH_EXPORT + filename);
		BufferedImage img = ImageIO.read(fimport);
		switch (type) {
			case SPACE:	    	screenShot = img.getSubimage(SPACE_X, SPACE_Y, SPASE_W, SPACE_H); 			   break;
			case LINE:	    	screenShot = img.getSubimage(LINE_X, LINE_Y, LINE_W, LINE_H); 			       break;
			case KAPCHA:    	screenShot = img.getSubimage(KAPCHA_X, KAPCHA_Y, KAPCHA_W, KAPCHA_H);          break;
			case FISH_LOOT_ONE: screenShot = img.getSubimage(LOOT_X, LOOT_Y, LOOT_W, LOOT_H);                  break;
			case FISH_LOOT_TWO: screenShot = img.getSubimage(LOOT_TWO_X, LOOT_TWO_Y, LOOT_TWO_W, LOOT_TWO_H);  break;
			default:	    throw new Exception("Unknow ImageType");
		}
		
		makeGray();
		noise = new Noise(screenShot);
		ImageIO.write(screenShot, "JPG", fexport );
	}
	
	public Screen(String filename) throws IOException{
		this.screenShot = ImageIO.read(new File(filename));
	}
	
	public Screen(ImageType type) throws Exception{
		Robot robot = new Robot();
		screenShot = robot.createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
		
	//	File fexport = new File(PATH_EXPORT + "test.jpg");
		switch (type) {
			case SPACE:	    	screenShot = screenShot.getSubimage(SPACE_X, SPACE_Y, SPASE_W, SPACE_H); 			         break; 
			case LINE:	    	screenShot = screenShot.getSubimage(LINE_X, LINE_Y, LINE_W, LINE_H); 			       	     break;
			case SUBLINE:	    screenShot = screenShot.getSubimage(SUB_LINE_X, SUB_LINE_Y, SUB_LINE_W, SUB_LINE_H); 	     break;
			case KAPCHA:        screenShot = screenShot.getSubimage(KAPCHA_X, KAPCHA_Y, KAPCHA_W, KAPCHA_H);                 break;
			case FISH_LOOT_ONE: screenShot = screenShot.getSubimage(LOOT_X, LOOT_Y, LOOT_W, LOOT_H);                		 break;
			case FISH_LOOT_TWO: screenShot = screenShot.getSubimage(LOOT_TWO_X, LOOT_TWO_Y, LOOT_TWO_W, LOOT_TWO_H);         break;
			default:	    throw new Exception("Unknow ImageType");
		}
		makeGray();
		noise = new Noise(screenShot);
		//ImageIO.write(screenShot, "JPG", fexport );
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
			ImageIO.write(screenShot, "jpg", new File("resources/debug/" + DateUtils.getYYYY_MM_DD_HH_MM_SS_S() + ".jpg"));
		} catch (IOException e) {
			logger.error("Exception " + e);
		}
	}
	
	public void saveImage(String folder) {
		
		try {
			ImageIO.write(screenShot, "jpg", new File("resources/" + folder + "/" + DateUtils.getYYYY_MM_DD_HH_MM_SS_S() + ".jpg"));
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
	
	public static void main(String[] args) throws Exception {
		
		/*Screen screen = new Screen(ImageType.FISH_LOOT_ONE);
		screen.saveImage("loot");*/
		
		Screen screen = new Screen();
		//screen.saveDebugImage();
		screen.saveImage("weapon");
	}
}

