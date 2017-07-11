package ru.namibios.arduino;

import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import ru.namibios.arduino.utils.DateUtils;

public class Screen { 
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
	
	private static final int KAPCHA_X = 780;
	private static final int KAPCHA_Y = 350;
	private static final int KAPCHA_W = 372;
	private static final int KAPCHA_H = 58;
	
	private static final int SUB_KAPCHA_X = 780;
	private static final int SUB_KAPCHA_Y = 360;
	private static final int SUB_KAPCHA_W = 372;
	private static final int SUB_KAPCHA_H = 40;
	
	private BufferedImage screenShot;
	
	private Noise noise;
	
	public Screen(String filename, ImageType type) throws Exception{
		
		File fimport = new File(PATH_IMPORT + filename);
		File fexport = new File(PATH_EXPORT + filename);
		BufferedImage img = ImageIO.read(fimport);
		switch (type) {
			case SPACE:	    screenShot = img.getSubimage(SPACE_X, SPACE_Y, SPASE_W, SPACE_H); 			          break; 
			case LINE:	    screenShot = img.getSubimage(LINE_X, LINE_Y, LINE_W, LINE_H); 				          break;
			case SUBLINE:   screenShot = img.getSubimage(SUB_LINE_X, SUB_LINE_Y, SUB_LINE_W, SUB_LINE_H);         break;
			case KAPCHA:    screenShot = img.getSubimage(KAPCHA_X, KAPCHA_Y, KAPCHA_W, KAPCHA_H);                 break;
			case SUB_KAPCHA:screenShot = img.getSubimage(SUB_KAPCHA_X, SUB_KAPCHA_Y, SUB_KAPCHA_W, SUB_KAPCHA_H); break;
			default:	    throw new Exception("Unknow ImageType");
		}
		
		makeGray();
		
		noise = new Noise(screenShot);
		ImageIO.write(screenShot, "JPG", fexport );
	}
	
	public Screen(ImageType type) throws Exception{
		Robot robot = new Robot();
		screenShot = robot.createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
		
		File fexport = new File(PATH_EXPORT + "test.jpg");
		switch (type) {
			case SPACE:	    screenShot = screenShot.getSubimage(SPACE_X, SPACE_Y, SPASE_W, SPACE_H); 			         break; 
			case LINE:	    screenShot = screenShot.getSubimage(LINE_X, LINE_Y, LINE_W, LINE_H); 				         break;
			case SUBLINE:   screenShot = screenShot.getSubimage(SUB_LINE_X, SUB_LINE_Y, SUB_LINE_W, SUB_LINE_H);         break;
			case KAPCHA:    screenShot = screenShot.getSubimage(KAPCHA_X, KAPCHA_Y, KAPCHA_W, KAPCHA_H);                 break;
			case SUB_KAPCHA:screenShot = screenShot.getSubimage(SUB_KAPCHA_X, SUB_KAPCHA_Y, SUB_KAPCHA_W, SUB_KAPCHA_H); break;
			default:	    throw new Exception("Unknow ImageType");
		}
		noise = new Noise(screenShot);
		ImageIO.write(screenShot, "JPG", fexport );
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
	
	public void saveImage() throws IOException{
		ImageIO.write(screenShot, "jpg", new File("resources/debug/" + DateUtils.getYYYY_MM_DD_HH_MM_SS_S() + ".jpg"));
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
}