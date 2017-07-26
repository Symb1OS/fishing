package ru.namibios.arduino;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import ru.namibios.arduino.model.ImageType;

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
	
	public static void main(String[] args) throws Exception {
		
		Screen screen = new Screen("20170705_220527.jpg", ImageType.KAPCHA);
		BufferedImage img = screen.getImage();
		Noise noise = new Noise(img);
		
		int i = 28;
		while(true){
			Screen newscreen = new Screen("20170705_2205" + i +".jpg", ImageType.KAPCHA);
			BufferedImage newimg = newscreen.getImage();
			noise.addNois(newimg);
			i++;
			
			if(i==32){
				noise.clear();
				noise.save();
				break;
			} 
		}
	}

}
