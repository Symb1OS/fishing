package ru.namibios.arduino.model;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import ru.namibios.arduino.model.template.MatrixTemplate;

public class ImageParser {
	
	private static final double CHARS_MIN_KOEF = 0.88;
	
	private int[][] screenMatrix;
	private ArrayList<int[][]> keyList;
	
	private BufferedImage screenShot;
	
	private MatrixTemplate[] collectionTemplate;
	
	private int row;
	private int column;
	
	public ImageParser(Screen screen, MatrixTemplate[] matrixTemplate) {
		this.screenShot = screen.getScreenShot();
		this.row = screenShot.getHeight(); 
		this.column = screenShot.getWidth();
		this.screenMatrix = new int[row][column];
		this.collectionTemplate = matrixTemplate;
		this.keyList = new ArrayList<int[][]>();
	}
	
	public ImageParser(Screen screen){
		this.screenShot = screen.getScreenShot();
		this.row = screenShot.getHeight();
		this.column = screenShot.getWidth();
		this.screenMatrix = new int[row][column];
		this.keyList = new ArrayList<int[][]>();
	}
	
	public ImageParser(BufferedImage screenShot){
		this.screenShot = screenShot;
		this.row = screenShot.getHeight();
		this.column = screenShot.getWidth();
		this.screenMatrix = new int[row][column];
		this.keyList = new ArrayList<int[][]>();
	}
	
	public int[][] getImageMatrix() {
		return screenMatrix;
	}
	
	public void parse(Color identificationColor){
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < column; j++) {
				Color color = new Color(screenShot.getRGB(j, i));
				
				boolean isIdentified = color.getRed()   > identificationColor.getRed()
									&& color.getGreen() > identificationColor.getGreen()
									&& color.getRed()   > identificationColor.getRed();
				
				screenMatrix[i][j] = isIdentified ? 1 : 0;
			}
		}
		keyList.add(screenMatrix);
	}
		
	private int compare(int[][] numberMatrix) {
	
		Coefficient coef = new Coefficient(CHARS_MIN_KOEF);
		
		int index = 0;
		while(index < collectionTemplate.length){
			List<int[][]> templateNumber = collectionTemplate[index].getTemplates(); 
			for (int[][] template : templateNumber) {
				coef.init(numberMatrix, template);
				coef.calculate(index);
			}
			
			if(coef.isFound()) break; else coef.resetRezultIndex();
			index++;
		}
		
		return coef.getRezultIndex();
	}

	public String getkey() {
		StringBuilder rezult = new StringBuilder();
		
		for (int[][] numberMatrix : keyList) {
			rezult.append(compare(numberMatrix));
		}	
		return rezult.toString().replace("-1", "");
	}
	 
	public String getNumber() {
		StringBuilder rezult = new StringBuilder();
		
		for (int[][] numberMatrix : keyList) {
			int rezultIndex = compare(numberMatrix);
			if(rezultIndex != -1){
				rezult.append(collectionTemplate[rezultIndex]);
			}
		}	
		return rezult.toString();
	}

	class Coefficient{

		private final double minKoef;
		
		private double maxCalcKoef;
		private double calcKoef;
		
		private double valueKoef;
		private double templateKoef;
		
		private int rezultIndex;
		
		public Coefficient(double minKoef) {
			this.minKoef = minKoef; 
			this.calcKoef = 0;
			this.maxCalcKoef = 0;
		}
		
		public void init(int[][] value, int[][] template) {
			
			valueKoef = templateKoef = 0;
			for (int i = 0; i < template.length; i++) {
				for (int j = 0; j < template[0].length; j++) {
					if(template[i][j] == 1) templateKoef++;
					if(value[i][j] == template[i][j] && template[i][j] != 0) valueKoef++;
				}
			}
		}
		
		public void calculate(int index) {
			calcKoef = valueKoef / templateKoef;
			boolean isNewKoef = calcKoef > maxCalcKoef; 
			if( isNewKoef ){
				rezultIndex = index;
				maxCalcKoef = calcKoef;
			}
		}
		
		public boolean isFound() {
			return maxCalcKoef > minKoef;
		}
		
		public void resetRezultIndex() {
			rezultIndex = -1;
		}
		
		public int getRezultIndex() {
			return rezultIndex;
		}

		@Override
		public String toString() {
			return "Coefficient [minKoef=" + minKoef + ", maxCalcKoef=" + maxCalcKoef + ", calcKoef=" + calcKoef
					+ ", valueKoef=" + valueKoef + ", templateKoef=" + templateKoef + ", rezultIndex=" + rezultIndex
					+ "]";
		}
	}
}