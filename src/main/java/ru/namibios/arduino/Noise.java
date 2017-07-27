package ru.namibios.arduino;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

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
	
	public void save() throws IOException{
		ImageIO.write(image, "jpg", new File("resources/changes/test.jpg"));
	}
	
}