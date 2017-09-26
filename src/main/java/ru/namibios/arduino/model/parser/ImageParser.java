package ru.namibios.arduino.model.parser;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import ru.namibios.arduino.model.template.MatrixTemplate;

public class ImageParser {
	
	private static final double CHARS_MIN_KOEF = 0.88;
	
	private int[][] imageMatrix;
	private ArrayList<int[][]> keyWordList;
	
	private BufferedImage screenShot;
	
	private MatrixTemplate[] collection;
	
	private int row;
	private int column;
	
	public ImageParser(Screen screen, MatrixTemplate[] collection) {
		this.screenShot = screen.getScreenShot();
		this.row = screenShot.getHeight(); 
		this.column = screenShot.getWidth();
		this.imageMatrix = new int[row][column];
		this.collection = collection;
	}
	
	public ImageParser(Screen screen, Color colorIdentifield, MatrixTemplate[] collection) {
		this.screenShot = screen.getScreenShot();
		this.row = screenShot.getHeight(); 
		this.column = screenShot.getWidth();
		this.imageMatrix = new int[row][column];
		this.collection = collection;
	}
	
	public ImageParser(BufferedImage screenShot, MatrixTemplate[] collection){
		this.screenShot = screenShot;
		this.row = screenShot.getHeight();
		this.column = screenShot.getWidth();
		this.imageMatrix = new int[row][column];
		this.keyWordList = new ArrayList<int[][]>();
		this.collection = collection;
	}
	
	public void parse(Color identificationColor){

		for (int i = 0; i < row; i++) {
			for (int j = 0; j < column; j++) {
				Color color = new Color(screenShot.getRGB(j, i));
				
				boolean isIdentified = color.getRed()   > identificationColor.getRed()
									&& color.getGreen() > identificationColor.getGreen()
									&& color.getRed()   > identificationColor.getRed();
				
				imageMatrix[i][j] = isIdentified ? 1 : 0;
			
			}
		}
		keyWordList.add(imageMatrix);
	}
		
	private int equalsMatrix(int[][] numberMatrix) {
		int rezultIndex = -1;
		
		double koef = 0;
		double templateKoef = 0;
		double calcKoef = 0;
		double maxCalcKoef = 0;

		int index = 0;
		int size = collection.length;
		while(index < size){
			
			List<int[][]> templateNumber = collection[index].getTemplates(); 
			for (int[][] template : templateNumber) {
				
				if(calcKoef > CHARS_MIN_KOEF) break;
				if(template.length != numberMatrix.length ) continue;
					
				templateKoef=0; koef = 0;
				for (int i = 0; i < row; i++) {
					for (int j = 0; j < column; j++) {
						boolean isValue;
						try{ isValue = (template[i][j] == 1); }catch(ArrayIndexOutOfBoundsException e){break;}
						if(isValue) templateKoef++;
						
						boolean valuesEqual = numberMatrix[i][j] == template[i][j] && template[i][j] != 0; 
						if(valuesEqual) koef +=1;
					}
				}
				
				calcKoef = koef / templateKoef;
				boolean isNewKoef = calcKoef > maxCalcKoef; 
				if( isNewKoef ){
					rezultIndex = index;
					maxCalcKoef = calcKoef;
				}
				
				boolean isUndefined = maxCalcKoef < CHARS_MIN_KOEF;
				if( isUndefined ) rezultIndex = -1;
				
			}
			index++;
		}
		
		return rezultIndex == 10 ? -1 : rezultIndex;
	}
	
	public String getkey() {
		StringBuilder rezult = new StringBuilder();
		
		for (int[][] numberMatrix : keyWordList) {
			rezult.append(equalsMatrix(numberMatrix));
		}	
		return rezult.toString().replace("-1", "");
	}
	 
	public String getNumber() {
		StringBuilder rezult = new StringBuilder();
		
		for (int[][] numberMatrix : keyWordList) {
			int rezultIndex = equalsMatrix(numberMatrix);
			if(rezultIndex != -1){
				rezult.append(collection[equalsMatrix(numberMatrix)]);
			}
		}	
		return rezult.toString();
	}

	public int[][] getImageMatrix() {
		return imageMatrix;
	}
	
}