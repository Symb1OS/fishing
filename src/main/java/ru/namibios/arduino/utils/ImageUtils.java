package ru.namibios.arduino.utils;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageUtils {
	
	private ImageUtils(){}
	
	public static BufferedImage read(File file) {
		BufferedImage image = null;
		try {
			image = ImageIO.read(file);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return image;
	}
}
